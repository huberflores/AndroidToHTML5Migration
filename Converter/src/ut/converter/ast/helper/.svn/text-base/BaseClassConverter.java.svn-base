package ut.converter.ast.helper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import ut.converter.ast.ASTUtil;
import ut.converter.ast.ClassName;
import ut.converter.ast.JavaLexer;

public abstract class BaseClassConverter {
	
	private final ClassName m_originalClassName;
	private final boolean m_direct;
	
	public ClassName originalClassName() { return m_originalClassName; }
	public boolean direct() { return m_direct; } // only direct subclass need to be processed
	
	public BaseClassConverter(String className, boolean direct) {
		this(new ClassName(className), direct);
	}
	
	public BaseClassConverter(ClassName oldClassName, boolean direct) {
		m_originalClassName = oldClassName;
		m_direct = direct;
	}
		
	protected abstract void processSubClass(CommonTree classTree, List<ClassName> imports);
	
	public abstract Set<ClassName> getImports();
	
	public void doPrococessClass(CommonTree classTree, List<ClassName> imports) {
		removeSyncBlocks(classTree);
		removeAnnotations(classTree);
		processSubClass(classTree, imports);
	}

	protected static void removeSyncBlocks(CommonTree classTree) {
		List<CommonTree> syncTrees = ASTUtil.findNode(classTree, 115, true, false); // synchronized
		for (CommonTree syncTree : syncTrees ) {
			CommonTree parent = (CommonTree) syncTree.getParent();
			int startIndex = syncTree.getChildIndex();
			int endIndex = parent.getChildCount();
			List<CommonTree> nodesToDelete = new ArrayList<CommonTree>();
			for (int i = startIndex; i < endIndex; i++) {
				CommonTree child = (CommonTree) parent.getChild(i);
				if (child.getType() == JavaLexer.BLOCK) {
					break;
				}
				nodesToDelete.add(child);
			}
			for (CommonTree toDelete: nodesToDelete) {
				ASTUtil.removeNode(toDelete);
			}
			
		}
	}
	
	private static void removeAnnotations(CommonTree tree) {
		List<CommonTree> annotations = ASTUtil.findNode(tree, JavaLexer.ANNOTATION);
		for (CommonTree annotation : annotations) {
			Tree parent = annotation.getParent();
			int index = annotation.getChildIndex();
			parent.deleteChild(index);
		}
	}
	
	/*protected static void replaceTypes(CommonTree classTree, List<ClassName> imports) {
		List<CommonTree> typeTrees = ASTUtil.findNode(classTree, JavaLexer.TYPE, false, false);
		for (CommonTree typeTree : typeTrees) {
			CommonTree typeNode = (CommonTree)typeTree.getChild(0);
			ClassName className= new ClassName(typeNode);
			ClassName typeFullName = className.getFirstBackwardEquals(imports);
			if (typeFullName == null) {
				typeFullName = className;
			} 
			BaseClassConverterHelper helper = classMap.get(typeFullName);
			if (helper != null) {
				ClassName newClass = helper.className();
				if (newClass != null) {
					ASTUtil.replaceNode(typeNode, newClass.createLastNodeTree());
				} else if (helper.deleteIfNull()){
					ASTUtil.removeNode(typeNode);
				}
			}
		}
	}*/
	
	
	
	/* ************
	 *    DefaultClassConverterHelper
	 * *************/
	public static class DefaultClassConverter extends BaseClassConverter {
		
		public DefaultClassConverter() {
			super("", true);
		}

		@Override
		protected void processSubClass(CommonTree classTree, List<ClassName> imports) {
			/*noop*/
		}

		@Override public Set<ClassName> getImports() {
			return null;
		}
	}
	
}
