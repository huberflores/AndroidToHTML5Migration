package ut;

public class Util {
	private static final String ID_DELIMETER ="/";
	private static final String ID_PREFIX = "R.id.";
	private static final String LAYOUT_PREFIX = "R.layout.";
	private static final String STRINGS_PREFIX = "R.string.";
	private static final String DRAWABLES_PREFIX = "R.drawable.";
	public static final String NAME_PREFIX = "m_id_";
	
	
	public static final String EL_STRING = "string";
	
	public static final String EL_LINEAR_LAYOUT = "LinearLayout";
	public static final String EL_FRAME_LAYOUT = "FrameLayout";
	public static final String EL_RELATIVE_LAYOUT = "RelativeLayout";
	public static final String EL_TEXT_VEIW = "TextView";
	public static final String EL_EDIT_TEXT = "EditText";
	public static final String EL_BUTTON = "Button";
	public static final String EL_IMAGE_VEIW = "ImageView";
	public static final String EL_AUTO_COMPLETE_TEXT_VIEW = "AutoCompleteTextView";
	public static final String EL_PROGRESS_BAR =  "ProgressBar";
	
	public static final String EL_TAB_HOST = "TabHost";
	
	public static final String ATTR_ID = "android:id";
	public static final String ATTR_ORIENTATION = "android:orientation";
	public static final String ATTR_EDITABLE = "android:editable";
	public static final String ATTR_TEXT = "android:text";
	
	public static final String ATTR_NAME = "name";
	
	public static final String ATTR_LAYOUT_BELOW = "android:layout_below";
	public static final String ATTR_LAYOUT_ABOVE = "android:layout_above";
	public static final String ATTR_LAYOUT_RIGHT_OF = "android:layout_below";
	public static final String ATTR_LAYOUT_LEFT_OF = "android:layout_below";
	
	
	public static final String VAL_VERTICAL = "vertical";
	public static final String VAL_HORIZAONTAL = "horizontal";
	
	public static final String COMPONENT_WRAPPER = "WidgetWrapper";
	
	public static final String GWT_VERTICAL_PANEL = "VerticalPanel";
	public static final String GWT_HORIZAONTAL_PANEL = "HorizontalPanel";
	public static final String GWT_ABSOLUTE_PANEL = "AbsolutePanel";
	public static final String GWT_LABEL = "TextView";
	public static final String GWT_TEXTBOX = "TextBox";
	public static final String GWT_BUTTON = "Button";
	public static final String GWT_IMAGE = "Image";
	public static final String GWT_PROGRESS_BAR =  "ProgressBar";
	public static final String GWT_ABSOLUTE_LAYOUT = "AbsolutePanel";
	
	public static final String GWT_ENTRY_POINT = "Activity";
	
	public static final String GWT_UNKNOWN = "UNKNOWN";
	
	public static final String KEYWORD_NEW = "new";
	public static final String KEYWORD_TRUE = "true";
	public static final String KEYWORD_FALSE = "false";
	
	public static final String METHOD_PUT = "put"; 
	public static final String METHOD_ADD = "add";  
	public static final String METHOD_SET_SIZE = "setSize";  
	public static final String METHOD_ADD_DRAWABLE = "addDrawable";
	public static final String METHOD_ADD_STRING = "addString";
	public static final String METHOD_ADD_LAYOUT = "addLayout";
	
	private static final String EQUALS = "=";
	private static final String OPEN_BRACKET = "(";
	private static final String CLOSE_BRACKET = ")";
	public static final String SEMICOL = ";";
	private static final String DOT = ".";
	private static final String COMA = ",";
	public static final String NEWLINE =  System.getProperty("line.separator");
	
	public static final String COMMENT_PREFIX = "\t//\t";	
	public static final String TODO = "TODO:";
	public static final String TAB = "\t";
	public static final String PACKAGE = "package";
	public static final String CONTEXT_INSTANCE = "Context.instance()";

	
	private static int m_counter;
	
	public static void appendWords (StringBuilder sb, String ... words) {
		int index = 0;
		for (String word : words) {
			if (index > 0) {
				sb.append(" ");
			}
			sb.append(word);
			index++;
		}
	}
	
	public static void createMethodCall(StringBuilder sb,String object,String methodName, String ... methodArgs) {
		sb.append(object).append(DOT).append(methodName).append(OPEN_BRACKET);
		int index = 0;
		for (String arg : methodArgs) {
			if (index > 0) {
				sb.append(COMA);
			}
			sb.append(arg);
			index++;
		}
		sb.append(CLOSE_BRACKET).append(SEMICOL).append(NEWLINE);
	}
	
	public static void createNewObject(StringBuilder sb, String objClass, String varName, String ... params) {
		appendWords(sb, objClass, varName, EQUALS, KEYWORD_NEW, objClass);
		sb.append(OPEN_BRACKET);
		int index = 0;
		for (String arg : params) {
			if (index > 0) {
				sb.append(COMA);
			}
			sb.append(arg);
			index++;
		}
		sb.append(CLOSE_BRACKET).append(SEMICOL).append(NEWLINE);
	}
	
	public static void createNewGeneralObject(StringBuilder sb, String objClass, String constructorClass, String varName, String ... params) {
		appendWords(sb, objClass, varName, EQUALS, KEYWORD_NEW, constructorClass);
		sb.append(OPEN_BRACKET);
		int index = 0;
		for (String arg : params) {
			if (index > 0) {
				sb.append(COMA);
			}
			sb.append(arg);
			index++;
		}
		sb.append(CLOSE_BRACKET).append(SEMICOL).append(NEWLINE);
	}
	
	public static String extractId (String id) {
		String ret = id;
		int index = id == null? -1 : id.lastIndexOf(ID_DELIMETER);
		if (index >=0) {
			ret = id.substring(index+1);
		} 
		return ret == null ? null : ID_PREFIX + ret;
	} 
	
	public static String getStringId (String name) {
		return name == null ? null : STRINGS_PREFIX + name;
	}
	
	public static String getLayoutId (String name) {
		return LAYOUT_PREFIX+name;
	}
	
	public static String getDrawableId (String fileName) {
		String name = fileName;
		int index = fileName != null ? fileName.indexOf(".") : -1;
		if (index > 0) {
			name = fileName.substring(0, index);
		}
		
		return name == null ? null : DRAWABLES_PREFIX + name;
	}
	
	public static String getNewId() {
		m_counter ++;
		return "id_"+ m_counter;
	}
	
	public static boolean isEmpty(String s) {
		return s == null || s.length() == 0;
	}
}
