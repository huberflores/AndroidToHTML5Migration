package ut.converter.ast.helper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.tree.CommonTree;

import ut.converter.ast.ASTUtil;
import ut.converter.ast.ClassName;
import ut.converter.ast.JavaLexer;

public class ThreadClassConverter extends BaseClassConverter {
	private static String RUN_MENTHOD = "run";
	private static ClassName TIMER_CLASS  = new ClassName("com.google.gwt.user.client.Timer");
	
	public ThreadClassConverter() {
		super("Thread", false);
	}

	@Override
	protected void processSubClass(CommonTree classTree, List<ClassName> imports) {
		CommonTree body = ASTUtil.findChild(classTree, JavaLexer.CLASS_BODY);
		List<CommonTree> methods = ASTUtil.findChildren(body, JavaLexer.METHOD);
		for (CommonTree method : methods) {
			CommonTree voidRetType = ASTUtil.findChild(method, JavaLexer.VOID);
			if (voidRetType == null) {
				continue;
			}
			CommonTree name = ASTUtil.findChild(method, JavaLexer.Identifier);
			if (!RUN_MENTHOD.equals(name.getText())) {
				continue;
			}
			CommonTree parameters = ASTUtil.findChild(method, JavaLexer.PARAMETERS);
			if (parameters == null || parameters.getChildCount() > 0) {
				continue;
			}
			// run found
			CommonTree blockNode = ASTUtil.findChild(method, JavaLexer.BLOCK);
			processRunMethod(blockNode);
			
		}

	}

	@SuppressWarnings("unchecked")
	private static void processRunMethod(CommonTree block) {
		List<CommonTree> blockChildren = new ArrayList<CommonTree>();
		blockChildren.addAll(block.getChildren());
		ASTUtil.removeChildren(block);
		block.addChild(constructTimer(blockChildren));
	}
	
	private static CommonTree constructTimer(List<CommonTree> blockChildren) {
		CommonTree timerTemplate = new CommonTree(new CommonToken(JavaLexer.TEMPLATE, "Timer.java"));
		CommonTree currentRoot = timerTemplate;
		boolean whileProcessing = false;
		boolean generateWarning = false;;
		for (CommonTree tree : blockChildren) {
			if (tree.getType() == JavaLexer.MEMBER) {
				if (whileProcessing) {
					currentRoot = new CommonTree(new CommonToken(JavaLexer.COMMENT));
					generateWarning = true;
				} else {
					currentRoot = timerTemplate;
				}
			} else if (tree.getType() == JavaLexer.PRIMARY && !whileProcessing) {
				currentRoot = new CommonTree(new CommonToken(JavaLexer.COMMENT));
				timerTemplate.addChild(currentRoot);
				generateWarning = true;
			} else if (tree.getType() == 123) { // while 
				currentRoot = new CommonTree(new CommonToken(JavaLexer.TEMPLATE, "TimerRun.java"));
				timerTemplate.addChild(currentRoot);
				tree= new CommonTree(new CommonToken(100, "if"));
				whileProcessing = true;
			} else if (tree.getType() == JavaLexer.BLOCK && whileProcessing) {
				whileProcessing = false; 
			}
			currentRoot.addChild(tree);
		}
		if (generateWarning) {
			System.err.println("WARNING: Thread conversion containing errors");
		}
		
		return timerTemplate;
	}
	
	@Override
	public Set<ClassName> getImports() {
		Set<ClassName> ret = new HashSet<ClassName>();
		ret.add(new ClassName("android.support.InterruptedException"));
		ret.add(new ClassName("android.support.Thread"));
		ret.add(TIMER_CLASS);
		return ret;
	}

}
