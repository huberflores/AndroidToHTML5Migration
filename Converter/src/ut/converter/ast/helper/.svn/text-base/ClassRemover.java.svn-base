package ut.converter.ast.helper;

import java.util.List;
import java.util.Set;

import org.antlr.runtime.tree.CommonTree;

import ut.converter.ast.ASTUtil;
import ut.converter.ast.ClassName;
import ut.converter.ast.JavaLexer;

public class ClassRemover extends BaseClassConverter {

	public ClassRemover(String className) {
		super(className, false);
	}

	@Override protected void processSubClass(CommonTree classTree, List<ClassName> imports) {
		CommonTree parent = (CommonTree) classTree.getParent();
		if (parent.isNil()) {
			List<CommonTree> treeList = ASTUtil.findNode(parent, JavaLexer.PACKAGE);
			for (CommonTree tree: treeList) {
				ASTUtil.removeNode(tree);
			}
		} else {
			ASTUtil.removeNode(classTree);
		}
	}

	@Override
	public Set<ClassName> getImports() {
		return null;
	}

}
