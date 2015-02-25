package ut;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TemplateMgr {
	private static TemplateMgr s_instance = new TemplateMgr();
	
	public static String TEMPLATES_ROOT = "templates";
	public static String GWT_MODULE_CONFIG_TEMPLATE = "appModule.gwt.xml";
	public static String CORE_INITILIZER_TEMPLATE = "CoreInitilizer.java";
	
	private static String PACKAGE_PLACEHOLDER = "//PACKAGE";
	private static String IMPORTS_PLACEHOLDER = "//IMPORTS";
	private static String LAYOUT_PLACEHOLDER = "//LAYOUT"; 
	private static String DRAWABLES_PLACEHOLDER = "//DRAWABLE";
	private static String STRINGS_PLACEHOLDER = "//STRINGS";
	private static String AST_PLACEHOLDER = "//AST";
	private static String SRC_PLACEHOLDER = "//SRC";
	
	private static String ENTRYPOINT_PLACEHOLDER = "<!--ENTRY_POINT-->";
	private static String SOURCES_PLACEHOLDER = "<!--SOURCES-->";
	
	public static TemplateMgr instance() { return s_instance; }
	
	private final Map<String, String> m_templates = new HashMap<String, String>();
	
	private TemplateMgr() {}
	
	public void load(String templateRoot) {
		m_templates.clear();
		if (templateRoot == null) {
			return;
		}
		File root = new File(templateRoot);
		if (root.exists() && root.isDirectory()) {
			File[] files = root.listFiles();
			for (File file : files) {
				if (file.isFile()) {
					String content = loadFile(file);
					if (!Util.isEmpty(content)) {
						m_templates.put(file.getName(), content);
					}
				}
			}
		}
	}
	
	public Set<String> getTemplateNames() {
		return m_templates.keySet();
	}

	public String getTemplateForModuleConfig(String entryPoint, List<String> src) {
		String content = m_templates.get(GWT_MODULE_CONFIG_TEMPLATE);
		String ret = null;
		if (content != null) {
			ret = content.replaceFirst(ENTRYPOINT_PLACEHOLDER, entryPoint);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < src.size(); i++) {
				sb.append("<source path='").append(src.get(i)).append("'/>").append(Util.NEWLINE);
			}
			ret = ret.replaceFirst(SOURCES_PLACEHOLDER, sb.toString());
		}
		return ret;
	}
	
	public String getTemplateForCoreInitilizer(String packageName, StringBuilder imports, StringBuilder layouts, StringBuilder drawables, StringBuilder strings) {
		String content = m_templates.get(CORE_INITILIZER_TEMPLATE);
		String ret = null;
		if (content != null) {
			ret = content.replaceFirst(PACKAGE_PLACEHOLDER, packageName);
			ret = ret.replaceFirst(IMPORTS_PLACEHOLDER, imports.toString());
			ret = ret.replaceFirst(LAYOUT_PLACEHOLDER, layouts.toString());
			ret = ret.replaceFirst(DRAWABLES_PLACEHOLDER, drawables.toString());
			ret = ret.replaceFirst(STRINGS_PLACEHOLDER, strings.toString());
		}
		return ret;
	}
	
	public String getPreTemplateForPackage(String templateName) {
		String content = m_templates.get(templateName);
		if (content != null) {
			int index = content.indexOf(AST_PLACEHOLDER);
			if (index >=0) {
				content = content.substring(0, index);
			}
		}
		return content;
	}
	
	public String getPostTemplateForPackage(String templateName) {
		String content = m_templates.get(templateName);
		if (content != null) {
			int index = content.indexOf(AST_PLACEHOLDER);
			if (index >=0) {
				content = content.substring(index+AST_PLACEHOLDER.length());
			} else {
				content  = "";
			}
		}
		return content;
	}
	
	private static String loadFile(File file) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ( (line = br.readLine()) != null) {
				sb.append(line).append(Util.NEWLINE);
			}
			br.close();
		} catch (IOException iex) { /*noop*/}
		return sb.toString();
	}
}
