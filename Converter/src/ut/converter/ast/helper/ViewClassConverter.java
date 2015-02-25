package ut.converter.ast.helper;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.antlr.runtime.tree.CommonTree;

import ut.converter.ast.ASTUtil;
import ut.converter.ast.ClassName;
import ut.converter.ast.JavaLexer;

public class ViewClassConverter extends BaseClassConverter {
	
	private static final List<ClassName> TYPES_TO_DELETE = Arrays.asList(new ClassName[] {
			new ClassName("android.util.AttributeSet")
		});
	
	public ViewClassConverter() {
		super("android.view.View", false);
	}


	@Override protected void processSubClass(CommonTree classTree, List<ClassName> imports) {
		processImportst(classTree);
		processConstructors(classTree, imports);
	}

	private void processConstructors(CommonTree classTree, List<ClassName> imports) {
		List<CommonTree> constructors = ASTUtil.findNode(classTree, JavaLexer.CONSTRUCTOR, false, false);
		for (CommonTree constructTree : constructors ) {
			CommonTree parametersTree = ASTUtil.findChild(constructTree, JavaLexer.PARAMETERS);
			if (parametersTree != null) {
				List<CommonTree> parameterTrees = ASTUtil.findChildren(parametersTree, JavaLexer.PARAMETER);
				for (CommonTree parameterTree : parameterTrees) {
					for (ClassName typeToDelete: TYPES_TO_DELETE) {
						if (ASTUtil.checkParameter(imports, parameterTree, typeToDelete)) {
							CommonTree comma = ASTUtil.findPrevousSibling(parameterTree, 68); // "," 
							if (comma != null) {
								ASTUtil.removeNode(comma);
							}
							ASTUtil.removeNode(parameterTree);
						}
					}
				}
			}
			CommonTree superCall = ASTUtil.findChild(constructTree, JavaLexer.SUPER);
			if (superCall != null) {
				CommonTree args = ASTUtil.findNextSibling(superCall, -1);
				if (args != null && args.getType() == JavaLexer.ARGS) {
					ASTUtil.removeChildren(args);
				}
			}
		}
	}
	
	private void processImportst(CommonTree classTree) {
		CommonTree parent = classTree;
		while (parent != null && !parent.isNil()) {
			parent = (CommonTree) parent.getParent();
		}
		
		if (parent != null) {
			List<CommonTree> imports = ASTUtil.findChildren(parent, JavaLexer.IMPORT);
			for (CommonTree importTree : imports ) {
				ClassName importClass = new ClassName((CommonTree)importTree.getChild(0));
				if (TYPES_TO_DELETE.contains(importClass)) {
					ASTUtil.removeNode(importTree);
				}
			}
		}
	}
	
	
	@Override public Set<ClassName> getImports() {
		// TODO Auto-generated method stub
		return null;
	}



}
