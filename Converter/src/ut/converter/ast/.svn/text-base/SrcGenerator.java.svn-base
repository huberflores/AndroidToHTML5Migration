package ut.converter.ast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.tree.BaseTree;

import ut.TemplateMgr;
import ut.Util;


public class SrcGenerator {
	
	private static final int[] BINARY_ROOT_TYPES = new int [] { JavaLexer.PACKAGE, JavaLexer.IMPORT, JavaLexer.TYPE }; 
	private static final int[] DESCRIPTIVE_TYPES = new int [] {0, JavaLexer.MODIFIER_LIST, JavaLexer.TYPE, JavaLexer.MEMBER, JavaLexer.METHOD, JavaLexer.CONSTRUCTOR,
																JavaLexer.TYPE_LIST, JavaLexer.COMMENT, JavaLexer.PARAMETERS, JavaLexer.PARAMETER, JavaLexer.CLASS_DECLARATION,
																JavaLexer.BLOCK, JavaLexer.PRIMARY, JavaLexer.ARGS, JavaLexer.TEMPLATE, JavaLexer.CLASS_BODY};
	private static final int[] PROCESS_BEFORE_ROOT = new int[] {JavaLexer.MODIFIER_LIST };
	
	private static final Map<Integer, String> TYPES_TO_TEXT_MAPPING_PRE = new HashMap<Integer, String>();
	private static final Map<Integer, String> TYPES_TO_TEXT_MAPPING_POST = new HashMap<Integer, String>();
	
	static {
		TYPES_TO_TEXT_MAPPING_PRE.put(JavaLexer.CLASS_DECLARATION, "class");
		
		TYPES_TO_TEXT_MAPPING_PRE.put(JavaLexer.COMMENT, "/*");
		TYPES_TO_TEXT_MAPPING_POST.put(JavaLexer.COMMENT, "*/");
		
		TYPES_TO_TEXT_MAPPING_PRE.put(JavaLexer.PARAMETERS, "(");
		TYPES_TO_TEXT_MAPPING_POST.put(JavaLexer.PARAMETERS, ")");
		
		TYPES_TO_TEXT_MAPPING_PRE.put(JavaLexer.BLOCK, "{");
		TYPES_TO_TEXT_MAPPING_POST.put(JavaLexer.BLOCK, "}");
		
		TYPES_TO_TEXT_MAPPING_PRE.put(JavaLexer.CLASS_BODY, "{");
		TYPES_TO_TEXT_MAPPING_POST.put(JavaLexer.CLASS_BODY, "}");
		
		TYPES_TO_TEXT_MAPPING_PRE.put(JavaLexer.ARGS, "(");
		TYPES_TO_TEXT_MAPPING_POST.put(JavaLexer.ARGS, ")");
		
		TYPES_TO_TEXT_MAPPING_POST.put(JavaLexer.IMPORT, ";");
		
		TYPES_TO_TEXT_MAPPING_POST.put(JavaLexer.PACKAGE, ";");
		
	}
	
	
	private final StringBuilder m_data; 
	private final BaseTree m_ast;
	private int m_currentLine;
	private BaseTree m_lastAppendedNode;
	
	public SrcGenerator(BaseTree tree) {
		m_data = new StringBuilder();
		m_ast = tree;
	}
	
	public StringBuilder process() {
		process(m_ast, 0);
		return m_data;
	}
	
	private void process(BaseTree tree, int level) {
		if (tree == null) {
			return;
		}
		
		if (tree.getType() == JavaLexer.TEMPLATE) {
			String content = TemplateMgr.instance().getPreTemplateForPackage(tree.getText());
			if (!Util.isEmpty(content)) {
				m_data.append(content);
			}
		}
		
		if (!processBinary(tree, level)) {
			@SuppressWarnings("unchecked")
			List<BaseTree>  children = tree.getChildren();
			if (children != null) {
				for (BaseTree child : children) {
					if (processBeforeRoot(child.getType())) {
						process(child, level+1);
					}
				}
			}
					
			appendTreeNode(tree, level);
			
			if (children != null) {
				for (BaseTree child : children) {
					if (!processBeforeRoot(child.getType())) {
						process(child, level+1);
					}
				}
			}
		}
		String postText = TYPES_TO_TEXT_MAPPING_POST.get(tree.getType());
		if (postText != null) {
			m_data.append(postText);
		}
		if (tree.getType() == JavaLexer.TEMPLATE) {
			String content = TemplateMgr.instance().getPostTemplateForPackage(tree.getText());
			if (!Util.isEmpty(content)) {
				m_data.append(content);
			}
		}

	}
	
	private void appendTreeNode(BaseTree tree, int level) {
		if (ASTUtil.DEBUG) {
			for (int i = 0 ; i < level ; i++) {
				System.out.print("\t");
			}
			System.out.println(tree.getType()+":"+tree.getText() + ":["+tree.getLine()+"]");
		}

		if (m_currentLine < tree.getLine()) {
			m_data.append(Util.NEWLINE);
		} else if (appendSpace(m_lastAppendedNode, tree)){
			m_data.append(" ");
		}
		
		String preText = TYPES_TO_TEXT_MAPPING_PRE.get(tree.getType());
		if (preText != null) {
			m_data.append(preText);
		}
		
		if (!isDescriptive(tree.getType())) {
			m_data.append(tree.getText());
		}
		m_currentLine = tree.getLine();
		m_lastAppendedNode = tree;
	}
	
	private boolean processBinary(BaseTree tree, int level) {
		if (readyForBinaryProcessing(tree.getType())) {
			boolean appendSpace = appendSpace(m_lastAppendedNode, tree);
			appendTreeNode(tree, level);
			if (!isDescriptive(tree.getType())) {
				m_data.append(" ");
			}
			ASTUtil.processBinary(tree.getChild(0), m_data, level+1);
			if (appendSpace){
				m_data.append(" ");
			}
			return true;
		}
		return false;
	}
	
	private boolean appendSpace(BaseTree prevNode, BaseTree curNode) {
		boolean ret = true;
		if (curNode.getType() == JavaLexer.EQ && (prevNode.getType() == JavaLexer.GT || prevNode.getType() == JavaLexer.LS)) {
			ret = false;
		}
		return ret;
	}
	
	private boolean readyForBinaryProcessing(int type) {
		return isTypeIn(type, BINARY_ROOT_TYPES);
	}
	
	private boolean isDescriptive(int type) {
		return isTypeIn(type, DESCRIPTIVE_TYPES);
	}
	
	private boolean processBeforeRoot(int type) {
		return isTypeIn(type, PROCESS_BEFORE_ROOT);
	}
	
	private boolean isTypeIn(int type, int[] arr) {
		for (int i=0; i < arr.length; i++) {
			if (type == arr[i]) {
				return true;
			}
		}
		return false;
	}
}
