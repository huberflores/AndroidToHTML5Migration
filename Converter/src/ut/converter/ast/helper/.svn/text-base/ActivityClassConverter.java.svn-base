package ut.converter.ast.helper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.tree.CommonTree;

import ut.TemplateMgr;
import ut.converter.ast.ASTUtil;
import ut.converter.ast.ClassName;
import ut.converter.ast.JavaLexer;

public class ActivityClassConverter extends BaseClassConverter {	
	
	private final ClassName m_coreInitilizer;
	
	public ActivityClassConverter(ClassName coreInitilizerDir) {
		super("android.app.Activity", true);
		if (coreInitilizerDir!= null) {
			m_coreInitilizer = new ClassName (coreInitilizerDir, "*");
		} else {
			m_coreInitilizer = null;
		}
	}
	
	@Override protected void processSubClass(CommonTree classTree, List<ClassName> imports) {
		CommonTree classBody = ASTUtil.findChild(classTree, JavaLexer.CLASS_BODY);
		CommonToken token = new CommonToken(JavaLexer.TEMPLATE, "Activity.java");
		CommonTree tree = new CommonTree(token);
		classBody.addChild(tree);
	}


	@Override
	public Set<ClassName> getImports() {
		Set<ClassName> ret = new HashSet<ClassName>();
		if (m_coreInitilizer != null) {
			ret.add(m_coreInitilizer);
		}
		return ret;
	}
	
	
	
	/*
	 private static final List<String> TEMPLATE_METHODS = Arrays.asList(new String[]{"setContentView","findViewById", "getText"}); // see Activity.java template for details
	public void postProcess(CommonTree rootNode, CommonTree classNode) {
		// generate additional imports
		CommonTree packageNode = ASTUtil.findChild(rootNode, JavaLexer.PACKAGE);	
		ASTUtil.insertNode(rootNode, (new ClassName("com.google.gwt.user.client.ui.RootPanel")).createImportTree(), packageNode.getChildIndex()+1);
		ASTUtil.insertNode(rootNode, (new ClassName("com.google.gwt.user.client.ui.Widget")).createImportTree(), packageNode.getChildIndex()+1);
		ASTUtil.insertNode(rootNode, (new ClassName("com.google.gwt.user.client.ui.MenuBar")).createImportTree(), packageNode.getChildIndex()+1);
		ASTUtil.insertNode(rootNode, (new ClassName("com.google.gwt.user.client.Command")).createImportTree(), packageNode.getChildIndex()+1);
	}

	public void process(CommonTree classTree, List<ClassName> imports) {
		CommonTree extendsSubTree = ASTUtil.findChild(classTree, JavaLexer.EXTENDS);
		CommonTree implementsSubTree = ASTUtil.findChild(classTree, JavaLexer.IMPLEMENTS);
		CommonTree classBody = ASTUtil.findChild(classTree, JavaLexer.CLASS_BODY);
		if (extendsSubTree!= null) {
			CommonTree typeToken = (CommonTree) extendsSubTree.getChild(0); // TYPE node 
			CommonTree typeTree = (CommonTree) typeToken.getChild(0);
			
			if (implementsSubTree != null) {
				ASTUtil.removeNode(extendsSubTree);
				CommonToken type = new CommonToken(JavaLexer.TYPE);
				typeTree = new CommonTree(type);
				typeTree.addChild(className().createLastNodeTree());
				implementsSubTree.addChild(typeTree);
			} else {
				ASTUtil.replaceNode(extendsSubTree, className().createImplementsTree());
			}
		}
		
		List<CommonTree> methods = ASTUtil.findChildren(classBody, JavaLexer.METHOD);
		
		List <String > memberNames = getMemeberNames(classBody);
		List <String > methodNames = getMethodNames(classBody);
		methodNames.addAll(TEMPLATE_METHODS);
		List <String > vars = new ArrayList<String>();
		
		for (CommonTree method : methods) {
			CommonTree voidTree = ASTUtil.findChild(method, JavaLexer.VOID);
			
			CommonTree ident = ASTUtil.findChild(method, JavaLexer.Identifier);
			String methodName = ident.getText();
			
			CommonTree paremetersNode = ASTUtil.findChild(method, JavaLexer.PARAMETERS);
			List<CommonTree> paremetersNodes = ASTUtil.findChildren(paremetersNode, JavaLexer.PARAMETER);
			if (voidTree != null) {
				if (METHOD_ON_PAUSE.equals(methodName)) {
					if (paremetersNodes.size() == 0) {
						ASTUtil.removeNode(method);
						continue;
					}
				} else if (METHOD_ON_SAVE_INST.equals(methodName)) {
					if (ASTUtil.checkParameters(imports, paremetersNodes, BUNDLE_CLASS)) {
						ASTUtil.removeNode(method);
						continue;
					}
				} 
			}
			
			vars.clear();
			for (CommonTree param: paremetersNodes) {
				vars.add(ASTUtil.findChild(param, JavaLexer.Identifier).getText());
			}
			
			List<CommonTree> blocks = ASTUtil.findNode(method, JavaLexer.BLOCK, true, false);
			for (CommonTree block : blocks) {
				@SuppressWarnings("unchecked")
				List<CommonTree> children = new ArrayList(block.getChildren());
				for(CommonTree child: children) {
					if (child.getType() == JavaLexer.MEMBER) {
						vars.add(ASTUtil.findChild(child, JavaLexer.Identifier).getText());
					} else if (child.getType() == JavaLexer.PRIMARY) {
						processPrimaryNode(child, memberNames, vars, methodNames, imports);
					}
				}
			}
		}

		List<CommonTree> primaryNodes = ASTUtil.findNode(classBody, JavaLexer.PRIMARY, false, false);
		for (CommonTree primaryNode : primaryNodes) {
			if (primaryNode.getChild(0).getType() == JavaLexer.SUPER) {
				ASTUtil.removeNode(primaryNode);
			}
		}
		
		CommonToken token = new CommonToken(JavaLexer.TEMPLATE, "Activity.java");
		CommonTree tree = new CommonTree(token);
		classBody.addChild(tree);
		
		replaceTypes(classTree, imports);
	}
	
	
	private static void processPrimaryNode(CommonTree primaryNode, List<String> classMembers, List<String> methodMembers, List<String> methodNames, List<ClassName> imports) {
		CommonTree args = ASTUtil.findChild(primaryNode, JavaLexer.ARGS);
		CommonTree superNode = ASTUtil.findChild(primaryNode, JavaLexer.SUPER);
		int childCount = primaryNode.getChildCount();
		CommonTree identifier = ASTUtil.findChild(primaryNode, JavaLexer.Identifier);
		String id = identifier.getText();
		if (superNode != null) {
			ASTUtil.removeNode(primaryNode);
		} else if (args != null && childCount == 2) { // this class method call
			if (!methodNames.contains(id)) {
				ASTUtil.removeNode(primaryNode);
			}
		} else if (args != null && childCount > 2) {
			if (!classMembers.contains(id) && !methodMembers.contains(id)) {
				ClassName testClass = new ClassName(id);
				ClassName fullTestClass = testClass.getFirstBackwardEquals(imports);
				if (fullTestClass == null) {
					ASTUtil.removeNode(primaryNode);
				} else {
					BaseClassConverterHelper helper = SrcProcessor.classToClassMapping().get(fullTestClass);
					if (helper == null || helper.deleteIfNull()) {
						ASTUtil.removeNode(primaryNode);
					}
				}
			}
		}
	}
	
	private static List<String> getMemeberNames(CommonTree classBody) {
		List<String> members = new ArrayList<String>();
		List<CommonTree> memberTrees = ASTUtil.findChildren(classBody, JavaLexer.MEMBER);
		for (CommonTree memberTree : memberTrees ) {
			CommonTree id = ASTUtil.findChild(memberTree, JavaLexer.Identifier);
			members.add(id.getText());
		}
		return members;
	}
	
	private static List<String> getMethodNames(CommonTree classBody) {
		List<String> methods = new ArrayList<String>();
		List<CommonTree> methodTrees = ASTUtil.findChildren(classBody, JavaLexer.METHOD);
		for (CommonTree methodTree : methodTrees ) {
			CommonTree id = ASTUtil.findChild(methodTree, JavaLexer.Identifier);
			methods.add(id.getText());
		}
		return methods;
	}

	*/
	

}
