package ut.converter.ast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.tree.CommonTree;

import ut.converter.ast.helper.ActivityClassConverter;
import ut.converter.ast.helper.BaseClassConverter;
import ut.converter.ast.helper.BaseClassConverter.DefaultClassConverter;
import ut.converter.ast.helper.ClassRemover;
import ut.converter.ast.helper.ViewClassConverter;
import ut.converter.ast.helper.ThreadClassConverter;


public class SrcProcessor {
	
	private final BaseClassConverter DEFAULT_CONVERTER = new DefaultClassConverter();
	
	private final Map <ClassName, BaseClassConverter> m_classToClassMapping = new HashMap<ClassName, BaseClassConverter >();
	
	private final Map<ClassName, List<ClassName>> m_dependenceisMap;
	private final Map<CommonTree, ClassName> m_preProcessedClasses;
	private final List<ClassName> m_classesToMakePublic;
	
	private final List<ClassName> m_imports = new ArrayList<ClassName>();
	
	public SrcProcessor(Map<ClassName, List<ClassName>> dependenceisMap, Map<CommonTree, ClassName> preProcessedClasses, List<ClassName> classesToOpen, ClassName coreInitilizerDir) {
		m_dependenceisMap = dependenceisMap;
		m_preProcessedClasses = preProcessedClasses;
		m_classesToMakePublic = classesToOpen;
		adjustDependencies();
		initMapping(coreInitilizerDir);
	}
	
	private void adjustDependencies() {
		List<ClassName> moreDependencies = new ArrayList<ClassName>();
		moreDependencies.add(new ClassName("android.view.View"));
		m_dependenceisMap.put(new ClassName("android.view.SurfaceView"), moreDependencies);
	}
	
	private void initMapping(ClassName coreInitilizerDir) {
		BaseClassConverter helper = new ViewClassConverter();
		m_classToClassMapping.put(helper.originalClassName(), helper);
		
		helper = new ThreadClassConverter();
		m_classToClassMapping.put(helper.originalClassName(), helper);
		
		helper = new ActivityClassConverter(coreInitilizerDir);
		m_classToClassMapping.put(helper.originalClassName(), helper);
		
		helper = new ClassRemover("android.test.ActivityInstrumentationTestCase");
		m_classToClassMapping.put(helper.originalClassName(), helper);
	}
	
	
	
	public void process(CommonTree tree) {
		m_imports.clear();
		prcessImports(tree);
		convertClasses(tree);
		
	}
	
	private void prcessImports(CommonTree tree) {
		List<CommonTree> importTrees = ASTUtil.findChildren(tree, JavaLexer.IMPORT);
		for (CommonTree importTree : importTrees ) {
			ClassName importClass = new ClassName((CommonTree)importTree.getChild(0));
			m_imports.add(importClass);
		}
	}
	
	private void convertClasses(CommonTree tree) {
		List<CommonTree> classes = ASTUtil.findNode(tree, JavaLexer.CLASS_DECLARATION);
		Set<ClassName> newImports = new HashSet<ClassName>();
		for (CommonTree classTree : classes) {
			ClassName fullClassName = m_preProcessedClasses.get(classTree);
			if (fullClassName != null) {
				List<BaseClassConverter> helpers = getSubClassHeplers(fullClassName);
				for (BaseClassConverter helper : helpers) {
					helper.doPrococessClass(classTree, m_imports);
					Set<ClassName> imports = helper.getImports();
					if (imports != null && !imports.isEmpty())  {
						newImports.addAll(imports);
					}
				}
			}
			
			if (m_classesToMakePublic.contains(fullClassName)) {
				makeClassPublic(classTree);
			}
		}
		CommonTree packageNode = ASTUtil.findChild(tree, JavaLexer.PACKAGE);	
		for (ClassName newImport : newImports) {
			ASTUtil.insertNode(tree, newImport.createImportTree(), packageNode.getChildIndex()+1);
		}
	}
	
	private List<BaseClassConverter> getSubClassHeplers(ClassName className) {
		List<BaseClassConverter> helpers = new ArrayList<BaseClassConverter>();
		findHelpersRecursevly(className, m_dependenceisMap, helpers, 0, m_classToClassMapping);
		if (helpers.isEmpty()) {
			helpers.add(DEFAULT_CONVERTER);
		}
		return helpers;
	}
	
	private static void makeClassPublic(CommonTree classTree) {
		CommonTree modifiers = ASTUtil.findChild(classTree, JavaLexer.MODIFIER_LIST);
		if (modifiers == null) {
			CommonToken modifiersTok = new CommonToken(JavaLexer.MODIFIER_LIST);
			modifiers = new CommonTree(modifiersTok);
			classTree.addChild(modifiers);
		} 
		CommonTree publicMod = ASTUtil.findChild(modifiers, 110);  // 110 - public modifier
		if (publicMod == null) {
			CommonToken modToken = new CommonToken(110, "public");
			CommonTree modTree = new CommonTree(modToken);
			modifiers.addChild(modTree);
		}
	}
	
	private static void findHelpersRecursevly(ClassName rootClass, Map<ClassName, List<ClassName>> dependences, List<BaseClassConverter> helpers, int recLevel, Map <ClassName, BaseClassConverter > mapping) {
		BaseClassConverter helper = mapping.get(rootClass);
		if (helper != null && (!helper.direct() || recLevel < 2)) { //indirect helper will be added always; direct - only if direct subclass 
			helpers.add(helper);
		}
		List<ClassName> children = dependences.get(rootClass);
		if (children != null) {
			for (ClassName child : children) {
				findHelpersRecursevly(child, dependences, helpers, recLevel+1, mapping);
			}
		}
	}
	
}
