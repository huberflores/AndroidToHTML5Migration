package ut;

import static ut.Util.NEWLINE;
import static ut.Util.TAB;
import static ut.Util.createMethodCall;
import static ut.Util.createNewObject;
import static ut.Util.getDrawableId;
import static ut.Util.getNewId;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;

import ut.converter.ast.ASTUtil;
import ut.converter.ast.ClassName;
import ut.converter.ast.JavaLexer;
import ut.converter.ast.JavaParser;
import ut.converter.ast.SrcGenerator;
import ut.converter.ast.SrcPreprocessor;
import ut.converter.ast.SrcProcessor;
import ut.converter.xml.BaseXMLParser;

public class ProjectConverter {
	private static final String IN_LAYOUT_DIR = "layout";
	private static final String IN_DRAWABLE_DIR = "drawable";
	private static final String IN_STRINGS_FILE = "strings.xml";

	private static final String SRC_FILE_EXT = ".java";

	private static final String OUT_GEN_PACKAGE = "gen";

	
// ======= windows ======
//	private static final String INPUT_PROJECT_PATH =  "E:/workspace_16_05_10/LunarLander";
//	private static final String OUTPUT_PROJECT_ROOT = "E:/workspace_16_05_10/LunarLanderHTML2";


	
// ======== linux =======
	private static final String INPUT_PROJECT_PATH = "/home/ol/workspace/LunarLander";
	private static final String OUTPUT_PROJECT_ROOT = "/home/ol/workspace/LunarLanderHTML";
	
	private static final String OUTPUT_PROJECT_PATH = OUTPUT_PROJECT_ROOT+"/src";
	private static final String OUTPUT_PROJECT_DRAWABLES_DIR = OUTPUT_PROJECT_ROOT+"/war";
	
	public static void main (String [] args) {
		TemplateMgr.instance().load(TemplateMgr.TEMPLATES_ROOT);
		File inputDir = new File(INPUT_PROJECT_PATH);
		Map<File, CommonTree> srcMap = getSrcTrees(inputDir);
		if (srcMap.isEmpty()) {
			System.err.println("ERROR: no sources found, exit");
			return;
		}
		
		// preprocessor , to construct dependencies map
		SrcPreprocessor preProcessor = new SrcPreprocessor();
		
		for(Entry< File, CommonTree> entry: srcMap.entrySet()) {
			CommonTree tree = entry.getValue();
			preProcessor.process(tree);
		}
		
		List<ClassName> moduleRoot = ClassName.extractCommonPackageLevels(preProcessor.processedClasses().values(), 1, true);
		ClassName rootPackage; // root package for appModule.gwt.xml file
		if (moduleRoot.size() < 1) {
			System.err.println("ERROR: no modules found, exit");
			return;
		} else {
			if (moduleRoot.size() > 1) {
				System.err.println("WARNING: modules size > 1, for correct conversion one common root package require");
			}
			rootPackage = moduleRoot.get(0);
		}
		
		createGwtConfig(preProcessor, rootPackage);

		// layout parser
		BaseXMLParser xmlParser = new BaseXMLParser();
		StringBuilder layoutSb = new StringBuilder();
		List<ClassName> customImports = new ArrayList<ClassName>(); // will be filled by parser
		File layoutDir = findDir(inputDir, IN_LAYOUT_DIR);
		if (layoutDir != null) {
			File [] layouts = layoutDir.listFiles();
			for (File layout : layouts) {
				String name = layout.getName();
				int index = name.indexOf(".");
				name = name.substring(0, index);
				layoutSb.append("/*-------------------------").append(name).append("------------------------------- */").append(NEWLINE);
				xmlParser.parseLayout(layout, layoutSb, name, preProcessor.dependenciesMap(), customImports);
			}
		}
		StringBuilder customImportsSb = new StringBuilder();
		for (ClassName cn : customImports) {
			customImportsSb.append("import ").append(cn.name()).append(";").append(NEWLINE);
		}
		ClassName rFile = preProcessor.getRFile();
		if (rFile == null) {
			System.err.println("WARNING: R.java not found");
		} else {
			customImportsSb.append("import ").append(rFile.name()).append(";").append(NEWLINE);
		}
		
		// drawables processing
		List<File> drawablesDirs = findDirs(inputDir, IN_DRAWABLE_DIR);
		Set<String> names = new HashSet<String>();
		StringBuilder drawablesSb =  new StringBuilder();
		for(File dir: drawablesDirs) {
			File [] list = dir.listFiles();
			for (File file : list ) {
				processDrawable(file, names, drawablesSb);
			}
		}
		
		// strings handler
		StringBuilder stringsSb = new StringBuilder();
		File stringsFile = findFile(inputDir, IN_STRINGS_FILE);
		if (stringsFile != null) {
			xmlParser.parseStrings(stringsFile, stringsSb);
		}
		ClassName coreInitilizerDir = createCoreInitilizer(rootPackage, layoutSb, customImportsSb, drawablesSb, stringsSb);
	
		try {

			//change AST
			SrcProcessor processor = new SrcProcessor(preProcessor.dependenciesMap(), preProcessor.processedClasses(), customImports, coreInitilizerDir);
			for(Entry< File, CommonTree> entry: srcMap.entrySet()) {
				CommonTree tree = entry.getValue();
				processor.process(tree);
			}
			
			
			//generate src;
			for(Entry<File, CommonTree> entry: srcMap.entrySet()) {
				String fileName = entry.getKey().getName();
				CommonTree tree = entry.getValue();
				List<CommonTree> treeList = ASTUtil.findNode(tree, JavaLexer.PACKAGE);
				if (treeList.size() == 1) {
					SrcGenerator generator = new SrcGenerator(tree);
					String packageName = ASTUtil.getPackageName(treeList.get(0));
					File fileDir = createDirForPackage(OUTPUT_PROJECT_PATH, packageName);
					BufferedWriter sWriter = new BufferedWriter(new FileWriter(fileDir.getAbsolutePath()+"/"+fileName));
					sWriter.append(generator.process());
					sWriter.close();
				}
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static ClassName createCoreInitilizer(ClassName rootPackage,StringBuilder layoutSb, StringBuilder customImportsSb, StringBuilder drawablesSb, StringBuilder stringsSb) {
		ClassName genPackage = new ClassName(rootPackage, OUT_GEN_PACKAGE);
		File genOutDir = createDirForPackage(OUTPUT_PROJECT_PATH, (genPackage.name()));
		String coreIntiContent = TemplateMgr.instance().getTemplateForCoreInitilizer(genPackage.name(), customImportsSb, layoutSb, drawablesSb, stringsSb);
		
		if (coreIntiContent != null) {
			try {
				FileWriter coreInitWriter = new FileWriter(genOutDir.getAbsolutePath()+"/" + TemplateMgr.CORE_INITILIZER_TEMPLATE);
				coreInitWriter.write(coreIntiContent);
				coreInitWriter.close();
				return genPackage;
			} catch (IOException e) {
				System.err.println("WARNING: failed to write CoreInitilizer");
			}
		} else {
			System.err.println("WARNING: CoreInit is emtpy, nothig written");
		}
		return null;
	}
	
	private static Map<File, CommonTree> getSrcTrees(File inputDir) {
		Map<File, CommonTree> srcMap = new HashMap<File, CommonTree>();
		// fill map File - AST
		try {
			List<File> srcs = findAllSourceFiles(inputDir);
			CharStream cs;
			for (File file : srcs) {
				
				/*if (!"LunarView.java".equals(file.getName())) { // TODO: remove, testing
					continue;
				}*/
				
				cs = new ANTLRFileStream(file.getAbsolutePath());
				JavaLexer lexer = new JavaLexer(cs);

				CommonTokenStream tokens = new CommonTokenStream();
				tokens.setTokenSource(lexer);
				
				JavaParser parser = new JavaParser(tokens);
				
				JavaParser.compilationUnit_return parserResult = parser.compilationUnit();
				CommonTree tree = (CommonTree) parserResult.getTree();
				srcMap.put(file, tree);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RecognitionException e) {
			e.printStackTrace();
		}
		return srcMap;
	}
	
	private static void createGwtConfig(SrcPreprocessor preProcessor, ClassName rootPackage) {
		ClassName entryPoint = preProcessor.getEntryPointInDirectory(rootPackage);
		if (entryPoint == null) {
			System.err.println("ERROR: no entry points found for directory, exit ("+rootPackage.name()+")");
			return;
		}
		
		List<ClassName> modules = ClassName.extractCommonPackageLevels(preProcessor.processedClasses().values(), 2, true);
		List<String> srcDirs = new ArrayList<String>();
		for (ClassName module : modules) {
			if (module.isInDirectory(rootPackage)) {
				srcDirs.add(module.getLastIdentifier());
			}
		}
		if (srcDirs.isEmpty()) {
			System.err.println("ERROR: no src dirs found for directory, exit ("+rootPackage.name()+")");
			return;
		}
		
		File gwtOutDir = createDirForPackage(OUTPUT_PROJECT_PATH, rootPackage.name());
		srcDirs.add(OUT_GEN_PACKAGE);
		
		String gwtConfigContent = TemplateMgr.instance().getTemplateForModuleConfig(entryPoint.name(), srcDirs);
		
		if (gwtConfigContent != null) {
			try {
				FileWriter gwtConfigWriter = new FileWriter(gwtOutDir.getAbsolutePath()+"/" + TemplateMgr.GWT_MODULE_CONFIG_TEMPLATE);
				gwtConfigWriter.write(gwtConfigContent);
				gwtConfigWriter.close();
				
			} catch (IOException ioex) {
				System.err.println("WARNING: failed to write GWT config file");
			}
		} else {
			System.err.println("WARNING: GWT config file is emtpy, nothig written");
		}
	}
	
	private static void processDrawable(File file, Set<String> names, StringBuilder drawablesSb) {
		String name = file.getName();
		File destFile = new File(OUTPUT_PROJECT_DRAWABLES_DIR+"/"+name);
		if (!file.isDirectory() && !names.contains(name)) {
			try {
				copyFile(file, destFile);
				String id = getNewId();
				drawablesSb.append(TAB).append(TAB);
				createNewObject(drawablesSb, "Image", id, "\""+name+"\"");
				drawablesSb.append(TAB).append(TAB);
				createMethodCall(drawablesSb, "Drawables", Util.METHOD_ADD_DRAWABLE, getDrawableId(name), id);
			} catch (IOException e) {
				System.err.println("WARNING: drawable processing failed ("+file.getAbsolutePath()+")");
			}
		}
	}
	
	private static File findDir(File root, String dirName) {
		if (root.isDirectory() && dirName.equals(root.getName())) {
			return root;
		}
		if (root.isDirectory()) {
			File [] list = root.listFiles();
			for (File file : list ) {
				if (file.isDirectory()) {
					File ret = findDir(file, dirName);
					if (ret != null) {
						return ret;
					}
				}
			}
		}
		return null;
	}
	
	private static List<File> findDirs(File root, String dirNamePrefix) {
		List<File> ret = new ArrayList<File>();
		findDirs(root, dirNamePrefix, ret);
		return ret;
	}
	
	private static void findDirs(File root, String dirNamePrefix, List<File> dirs) {
		if (root.isDirectory() && root.getName().startsWith(dirNamePrefix)) {
			dirs.add(root);
		}
		if (root.isDirectory()) {
			File [] list = root.listFiles();
			for (File file : list ) {
				if (file.isDirectory()) {
					findDirs(file, dirNamePrefix, dirs);
				}
			}
		}
	}
	
	private static File findFile(File root, String fileName) {
		if (!root.isDirectory() && fileName.equals(root.getName())) {
			return root;
		}
		if (root.isDirectory()) {
			File [] list = root.listFiles();
			for (File file : list ) {
				File ret = findFile(file, fileName);
				if (ret != null) {
					return ret;
				}
			}
		}
		return null;
	}
	
	private static List<File> findAllSourceFiles(File root) {
		List<File> files = new ArrayList<File>();
		findAllSourceFiles(root, files);
		return files;
	}
	
	private static void findAllSourceFiles(File root, List<File> files) {
		String fName = root.getName();
		if (!root.isDirectory() && fName != null && fName.endsWith(SRC_FILE_EXT) ) {
			files.add(root);
		}
		if (root.isDirectory()) {
			File [] list = root.listFiles();
			for (File file : list ) {
				findAllSourceFiles(file, files);
			}
		}
	}
	
	private static File createDirForPackage(String root, String packageName) {
		String packageSuffix = packageName.replaceAll("\\.", "/");
		File ret = new File(root+"/"+packageSuffix);
		return ret.exists() || ret.mkdirs() ? ret : null;
	}

	public static void copyFile(File sourceFile, File destFile) throws IOException {
	    if(!destFile.exists()) {
	        destFile.createNewFile();
	    }

	    FileChannel source = null;
	    FileChannel destination = null;

	    try {
	        source = new FileInputStream(sourceFile).getChannel();
	        destination = new FileOutputStream(destFile).getChannel();
	        destination.transferFrom(source, 0, source.size());
	    }
	    finally {
	        if(source != null) {
	            source.close();
	        }
	        if(destination != null) {
	            destination.close();
	        }
	    }
	}

}
