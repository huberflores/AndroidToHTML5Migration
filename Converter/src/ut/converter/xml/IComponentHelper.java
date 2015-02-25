package ut.converter.xml;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import ut.converter.ast.ClassName;

import ut.Util;

public interface IComponentHelper { // TODO: attributes processing
	boolean allowChildProcessing();
	String id();
	String wrapperName();
	String componentName();
	String type();
	Attributes attributes();
	void process();
	void addChild(IComponentHelper child);
	void finishProcessing();
	
	
	/* *
	 *  BaseComponentHelper
	 * */
	public static abstract class BaseComponentHelper implements IComponentHelper { 
		private final StringBuilder m_sb;
		private final String m_name;
		private final String m_type;
		private final Attributes m_attributes;
		private final String m_id;
		
		public String wrapperName() { return m_name; }
		public String componentName() { return "c_"+m_name; }
		public String type() { return m_type; }
		public Attributes attributes() { return m_attributes; }
		public String id() { return m_id; }
		
		protected StringBuilder sb() { return m_sb; }
		
		public BaseComponentHelper(StringBuilder sb, String name, String id, String type, Attributes attributes) {
			m_sb = sb;
			m_name = name;
			m_type = type;
			m_attributes = attributes;
			m_id = id;
		}
		
	}
	
	
	/* *
	 *  TextBoxHelper
	 * */
	public static class TextBoxHelper extends BaseComponentHelper {
		
		public TextBoxHelper(StringBuilder sb, String name, String id, String type, Attributes attributes) {
			super(sb, name, id, type, attributes);
		}
		
		@Override public boolean allowChildProcessing() {
			sb().append(Util.COMMENT_PREFIX).append(Util.TODO).append("Check layout file");
			return false;
		}
		
		@Override public void process() {
			String editable = attributes().getValue(Util.ATTR_EDITABLE);
			boolean isLabel = Util.EL_TEXT_VEIW.equals(type()) && (editable == null || Util.KEYWORD_FALSE.equals(editable) ) ||
					(Util.EL_AUTO_COMPLETE_TEXT_VIEW.equals(type())|| Util.EL_AUTO_COMPLETE_TEXT_VIEW.equals(type())) && Util.KEYWORD_FALSE.equals(editable);
			Util.createNewObject(sb(), isLabel ? Util.GWT_LABEL : Util.GWT_TEXTBOX , componentName());
			sb().append(Util.TAB).append(Util.TAB);
			Util.createNewObject(sb(), Util.COMPONENT_WRAPPER  , wrapperName(), id(),componentName());
		}

		@Override public void addChild(IComponentHelper child) {
			System.err.println("TextBoxHelper: cant add child ");
		}

		@Override public void finishProcessing() { /* noop*/ }
		
	}
	
	/* *
	 *  ButtonHelper
	 * */
	public static class ButtonHelper extends BaseComponentHelper {
		
		public ButtonHelper(StringBuilder sb, String name, String id, String type, Attributes attributes) {
			super(sb, name, id, type, attributes);
		}
		
		@Override public boolean allowChildProcessing() {
			sb().append(Util.COMMENT_PREFIX).append(Util.TODO).append("Check layout file");
			return false;
		}
		
		@Override public void process() {
			Util.createNewObject(sb(), Util.GWT_BUTTON , componentName());
			sb().append(Util.TAB).append(Util.TAB);
			Util.createNewObject(sb(), Util.COMPONENT_WRAPPER  , wrapperName(), id(), componentName());
		}

		@Override public void addChild(IComponentHelper child) {
			System.err.println("ButtonHelper: cant add child ");
		}

		@Override public void finishProcessing() { /* noop*/ }
		
	}
	
	/* *
	 *  ImageHelper
	 * */
	public static class ImageHelper extends BaseComponentHelper {
		
		public ImageHelper(StringBuilder sb, String name, String id, String type, Attributes attributes) {
			super(sb, name, id, type, attributes);
		}
		
		@Override public boolean allowChildProcessing() {
			sb().append(Util.COMMENT_PREFIX).append(Util.TODO).append("Check layout file");
			return false;
		}
		
		@Override public void process() {
			Util.createNewObject(sb(), Util.GWT_IMAGE , componentName());
			sb().append(Util.TAB).append(Util.TAB);
			Util.createNewObject(sb(), Util.COMPONENT_WRAPPER  , wrapperName(), id(),componentName());
		}

		@Override public void addChild(IComponentHelper child) {
			System.err.println("ImageHelper: cant add child ");
		}

		@Override public void finishProcessing() { /* noop*/ }
		
	}
	
	
	/* *
	 *  LinearLayoutHelper
	 * */
	public static class LinearLayoutHelper extends BaseComponentHelper {
		private ArrayList<IComponentHelper> m_children = new ArrayList<IComponentHelper>();
		
		public LinearLayoutHelper(StringBuilder sb, String name, String id, String type, Attributes attributes) {
			super(sb, name, id, type, attributes);
		}
			
		@Override public boolean allowChildProcessing() {
			return true;
		}
		
		@Override public void process() {
			Util.createNewObject(sb(), Util.VAL_VERTICAL.equals(attributes().getValue(Util.ATTR_ORIENTATION)) ? Util.GWT_VERTICAL_PANEL : Util.GWT_HORIZAONTAL_PANEL , componentName());
			sb().append(Util.TAB).append(Util.TAB);
			Util.createNewObject(sb(), Util.COMPONENT_WRAPPER  , wrapperName(), id(),componentName());
		}

		@Override public void addChild(IComponentHelper child) {
			m_children.add(child);
		}

		@Override public void finishProcessing() { 
			for (IComponentHelper child : m_children) {
				sb().append(Util.TAB).append(Util.TAB);
				Util.createMethodCall(sb(), wrapperName(), Util.METHOD_ADD, child.wrapperName());
				sb().append(Util.TAB).append(Util.TAB);
				Util.createMethodCall(sb(), componentName(), Util.METHOD_ADD, child.componentName());
			}
		}
	}
	
	/* *
	 *  FrameLayoutHelper
	 * */
	public static class FrameLayoutHelper extends BaseComponentHelper {
		private ArrayList<IComponentHelper> m_children = new ArrayList<IComponentHelper>();
		
		public FrameLayoutHelper(StringBuilder sb, String name, String id, String type, Attributes attributes) {
			super(sb, name, id, type, attributes);
		}
			
		@Override public boolean allowChildProcessing() {
			return true;
		}
		
		@Override public void process() {
			Util.createNewObject(sb(), Util.GWT_ABSOLUTE_PANEL , componentName());
			sb().append(Util.TAB).append(Util.TAB);
			Util.createMethodCall(sb(), componentName(), Util.METHOD_SET_SIZE, "\"100%\"", "\"100%\"");
			sb().append(Util.TAB).append(Util.TAB);
			Util.createNewObject(sb(), Util.COMPONENT_WRAPPER  , wrapperName(), id(), componentName());
		}

		@Override public void addChild(IComponentHelper child) {
			m_children.add(child);
		}

		@Override public void finishProcessing() { 
			for (IComponentHelper child : m_children) {
				sb().append(Util.TAB).append(Util.TAB);
				Util.createMethodCall(sb(), wrapperName(), Util.METHOD_ADD, child.wrapperName());
				sb().append(Util.TAB).append(Util.TAB);
				Util.createMethodCall(sb(), componentName(), Util.METHOD_ADD, child.componentName());
			}
		}
	}
	
	
	/* *
	 *  RelativeLayoutHelper
	 * */
	public static class RelativeLayoutHelper extends BaseComponentHelper {
		private ArrayList<IComponentHelper> m_children = new ArrayList<IComponentHelper>();
		
		public RelativeLayoutHelper(StringBuilder sb, String name, String id, String type, Attributes attributes) {
			super(sb, name, id, type, attributes);
		}
			
		@Override public boolean allowChildProcessing() {
			return true;
		}
		
		@Override public void process() {
			Util.createNewObject(sb(), Util.GWT_ABSOLUTE_PANEL , componentName());
			sb().append(Util.TAB).append(Util.TAB);
			Util.createMethodCall(sb(), componentName(), Util.METHOD_SET_SIZE, "\"100%\"", "\"100%\"");
			sb().append(Util.TAB).append(Util.TAB);
			Util.createNewObject(sb(), Util.COMPONENT_WRAPPER  , wrapperName(), id(),componentName());
		}

		@Override public void addChild(IComponentHelper child) {
			m_children.add(child);
		}

		@Override public void finishProcessing() { 
			for (IComponentHelper child : m_children) {
				sb().append(Util.TAB).append(Util.TAB);
				Util.createMethodCall(sb(), wrapperName(), Util.METHOD_ADD, child.wrapperName());
				sb().append(Util.TAB).append(Util.TAB);
				Util.createMethodCall(sb(), componentName(), Util.METHOD_ADD, child.componentName());
			}
		}
	}
	
	/* *
	 *  CustomComponentHelper
	 * */
	public static class CustomComponentHelper extends BaseComponentHelper {
		
		public CustomComponentHelper(StringBuilder sb, String name, String id, String type, Attributes attributes) {
			super(sb, name, id, type, attributes);
		}
			
		@Override public boolean allowChildProcessing() {
			return false;
		}
		
		@Override public void process() {
			ClassName cn = new ClassName(type());
			String objName = cn.splitName().get(cn.splitName().size()-1);
			Util.createNewObject(sb(), objName , componentName(), Util.CONTEXT_INSTANCE);
			sb().append(Util.TAB).append(Util.TAB);
			Util.createNewObject(sb(), Util.COMPONENT_WRAPPER  , wrapperName(), id(), componentName());
		}

		@Override public void addChild(IComponentHelper child) {
			/* noop */
		}

		@Override public void finishProcessing() { 
			/* noop */
		}
	}
	
}
