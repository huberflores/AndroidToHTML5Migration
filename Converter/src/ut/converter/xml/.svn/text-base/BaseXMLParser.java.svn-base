package ut.converter.xml;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ut.Util;
import ut.converter.ast.ClassName;
import ut.converter.xml.IComponentHelper.*;

public class BaseXMLParser {	
	private SAXParser m_parser;
	
	public BaseXMLParser() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			m_parser = factory.newSAXParser();
		} catch (ParserConfigurationException e) {
			m_parser = null;
			e.printStackTrace();
		} catch (SAXException e) {
			m_parser = null;
			e.printStackTrace();
		}
	}
	
	
	public void parseLayout(File xmlFile, StringBuilder sb, String layoutName, Map<ClassName, List<ClassName>> dependenceisMap, List <ClassName> customImports) {
		if (m_parser == null ) {
			return;
		}
		try {
			LayoutHandler handler = new LayoutHandler(layoutName, dependenceisMap); 
			m_parser.parse(xmlFile, handler);	
			sb.append(handler.getResult());
			customImports.addAll(handler.customImports());
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void parseStrings(File xmlFile, StringBuilder sb) {
		StringsHandler handler = new StringsHandler();
		try {
			m_parser.parse(xmlFile, handler);
			sb.append(handler.getResult());
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	
	/* **************
	 *     StringsHandler
	 * ***************/
	private static class StringsHandler extends DefaultHandler {
		private StringBuilder m_sb = new StringBuilder();
		private String m_currentStringId = null;
		
		public StringBuilder getResult() {
			return m_sb;
		}
		
		@Override public void startElement(String uri, String localName,String qName,  Attributes attributes) throws SAXException {
			if (Util.EL_STRING.equals(qName)) {
				m_currentStringId = Util.getStringId(attributes.getValue(Util.ATTR_NAME));
			}
		}
		
		@Override public void characters(char[] ch, int start, int length) throws SAXException {
			if (m_currentStringId != null) {
				m_sb.append(Util.TAB).append(Util.TAB);
				String text = new String(ch, start, length);
				Util.createMethodCall(m_sb, "Strings", Util.METHOD_ADD_STRING, m_currentStringId, "\"" +text +"\"");
			}
		}
		
		@Override public void endElement(String uri, String localName, String qName) throws SAXException {
			m_currentStringId = null;
		}	
	}
	
	/* *******************
	 *     LayoutHandler
	 * ********************/
	private static class LayoutHandler extends DefaultHandler {
		//private Set<String> m_usedGwtComponents = new HashSet<String>(); TODO: generate imports
		private StringBuilder m_sb = new StringBuilder();
		private List<ClassName> m_customImports = new ArrayList<ClassName>();
		private int m_idCounter = 0;
		private final String m_layoutMapName ;
		private final Map<ClassName, List<ClassName>> m_dependenceisMap;
		private Stack<IComponentHelper> m_layoutsStack = new Stack<IComponentHelper>();
			
		public LayoutHandler(String layoutName, Map<ClassName, List<ClassName>> dependenceisMap) {
			m_layoutMapName = layoutName;
			m_dependenceisMap = dependenceisMap;
		}
		
		public StringBuilder getResult() {
			return m_sb;
		}
		
		public List<ClassName> customImports() {
			return m_customImports;
		}
		
		private String getNewName() {
			m_idCounter ++;
			return Util.NAME_PREFIX +m_layoutMapName+"_"+m_idCounter; 
		}
		
		@Override public void startElement(String uri, String localName,String qName,  Attributes attributes) throws SAXException {	 
			String id = Util.extractId(attributes.getValue(Util.ATTR_ID));
			if (id == null) {
				id = "-1";
			}
			String name = getNewName();
			IComponentHelper layoutHelper = null;
			if (Util.EL_LINEAR_LAYOUT.equals(qName)) {
				layoutHelper = new LinearLayoutHelper(m_sb, name, id ,qName, attributes);
			} else if (Util.EL_RELATIVE_LAYOUT.equals(qName)) {
				layoutHelper = new RelativeLayoutHelper(m_sb, name, id, qName, attributes);
			} else if (Util.EL_FRAME_LAYOUT.equals(qName)) {
				layoutHelper = new FrameLayoutHelper(m_sb, name, id ,qName, attributes);
			} else if (Util.EL_EDIT_TEXT.equals(qName) || Util.EL_AUTO_COMPLETE_TEXT_VIEW.equals(qName) || Util.EL_TEXT_VEIW.equals(qName)) {
				layoutHelper = new TextBoxHelper(m_sb, name, id ,qName, attributes);
				m_customImports.add(new ClassName("android.widget.TextView"));
			} else if (Util.EL_BUTTON.equals(qName)) {
				layoutHelper = new ButtonHelper(m_sb, name, id ,qName, attributes);
			} else if (Util.EL_IMAGE_VEIW.equals(qName)) {
				layoutHelper = new ImageHelper(m_sb, name, id ,qName, attributes);
			} 
			
			if (layoutHelper == null) { // try to find it in custom components
				ClassName customClass = new ClassName(qName);
				if (m_dependenceisMap.containsKey(customClass)) {
					m_customImports.add(customClass);
					m_customImports.add(new ClassName("android.content.Context"));
					layoutHelper = new CustomComponentHelper(m_sb, name, id, qName, attributes);
				}
			}
			
			if (layoutHelper == null) {
				layoutHelper = new BaseComponentHelper(m_sb, name, id ,qName, attributes) {

					@Override public void process() {
						sb().append(Util.COMMENT_PREFIX);
						Util.createNewObject(sb(), Util.GWT_UNKNOWN+"["+type()+"]" , wrapperName());
					}

					@Override public void addChild(IComponentHelper child) { 
						sb().append(Util.COMMENT_PREFIX);
						Util.createMethodCall(sb(), wrapperName(), Util.METHOD_ADD, child.wrapperName());
					}

					@Override public void finishProcessing() { 
						
					}

					@Override public boolean allowChildProcessing() { return true; }
				};
			}
			
			IComponentHelper parentLayout = m_layoutsStack.isEmpty() ? null : m_layoutsStack.peek();
			if (parentLayout == null || parentLayout.allowChildProcessing()) {
				m_sb.append(Util.TAB).append(Util.TAB);
				layoutHelper.process();
			}
			
			if (parentLayout == null) {
				m_sb.append(Util.TAB).append(Util.TAB);
				Util.createMethodCall(m_sb, "Layout", Util.METHOD_ADD_LAYOUT, Util.getLayoutId(m_layoutMapName), name);
			} else {
				parentLayout.addChild(layoutHelper);
			}
			
			m_layoutsStack.push(layoutHelper);

		}
	 
		@Override public void endElement(String uri, String localName, String qName) throws SAXException {
			//System.out.println("End Element:" + qName);
			IComponentHelper helper = m_layoutsStack.pop();
			helper.finishProcessing();
		}
	}
}
