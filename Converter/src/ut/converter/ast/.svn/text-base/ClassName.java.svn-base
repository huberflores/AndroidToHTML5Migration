package ut.converter.ast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;


import ut.converter.ast.ASTUtil.IBinaryTreeProcessor;


public class ClassName {
	private static final String ANY_NAME = "*";
	private static final String DELIMETER_REGEXP = "\\.";
	
	private final String m_name;
	private final List<String> m_splitName;
	
	public String name() { return m_name; }
	public List<String> splitName() { return m_splitName; }
	
	public ClassName (String name) {
		m_name = name;
		String[] parts = name.split(DELIMETER_REGEXP);
		m_splitName = new ArrayList<String>();
		for (int i =0; i < parts.length; i++) {
			m_splitName.add(parts[i]);
		}
	}
	
	public ClassName (String... parts) {
		m_splitName = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		for (int i =0; i < parts.length; i++) {
			m_splitName.add(parts[i]);
			if (i != 0) {
				sb.append(".");
			}
			sb.append(parts[i]);
		}
		m_name = sb.toString();
	}
	
	public ClassName (ClassName parent, String name) {
		m_name = parent.name()+"."+name;
		m_splitName = new ArrayList<String>(parent.splitName());
		m_splitName.add(name);
	}
	
	public ClassName (CommonTree packageTree, String name) {
		final StringBuilder sb = new StringBuilder();
		final List<String> packages = new ArrayList<String>();
		ASTUtil.processBinary(packageTree, new IBinaryTreeProcessor() {
			@Override public void process(Tree node) {
				sb.append(node.getText());
				if (node.getType() != JavaLexer.DOT) {
					packages.add(node.getText());
				}
			}
		}, 0);
		sb.append(".").append(name);
		m_name=sb.toString();
		m_splitName = new ArrayList<String>(packages);
		m_splitName.add(name);
	}
	
	public ClassName (CommonTree typeTree) {
		final StringBuilder sb = new StringBuilder();
		final List<String> packages = new ArrayList<String>();
		ASTUtil.processBinary(typeTree, new IBinaryTreeProcessor() {
			@Override public void process(Tree node) {
				sb.append(node.getText());
				if (node.getType() != JavaLexer.DOT) {
					packages.add(node.getText());
				}
			}
		}, 0);
		m_name=sb.toString();
		m_splitName = new ArrayList<String>(packages);
	} 
	
	
	private ClassName (ClassName other, int levels, boolean removeLast) {
		m_splitName = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		List<String> otherSplitName = other.m_splitName;
		int limit =  Math.min(otherSplitName.size() - (removeLast ? 1 : 0) , levels);
		for (int i =0; i < limit; i++) {
			m_splitName.add(otherSplitName.get(i));
			if (i != 0) {
				sb.append(".");
			}
			sb.append(otherSplitName.get(i));
		}
		m_name = sb.toString();
	}
		
	public CommonTree createCommonTree() {
		return constructBinaryCommonTree(m_splitName);
	}
	
	public String getLastIdentifier() {
		return m_splitName.get(m_splitName.size()-1);
	}
	
	public CommonTree createLastNodeTree() {
		CommonToken identToken = new CommonToken(JavaLexer.Identifier, getLastIdentifier());
		CommonTree ret = new CommonTree(identToken);
		return ret;
	}
	
	public CommonTree createTypeTree() {
		CommonToken type = new CommonToken(JavaLexer.TYPE);
		CommonTree typeTree = new CommonTree(type);
		typeTree.addChild( createLastNodeTree());
		return typeTree;
	}
	
	public CommonTree createImportTree() {
		CommonToken importToken = new CommonToken(JavaLexer.IMPORT, "import");
		CommonTree ret = new CommonTree(importToken);
		ret.addChild(createCommonTree());
		return ret;
	}
	
	public ClassName getFirstBackwardEquals(List<ClassName> imports) {
		for (ClassName imp : imports) {
			if (compareBackward(this, imp)) {
				return imp;
			}
		}
		return null;
	}
	
	public boolean isInDirectory(ClassName packageName) {
		int packageSize = packageName.m_splitName.size();
		if (packageSize > m_splitName.size()) {
			return false;
		} else {
			String subName1;
			String subName2;
			boolean same = true;
			for (int i = 0 ; i < packageSize; i ++) {
				subName1 = m_splitName.get(i);
				subName2 = packageName.m_splitName.get(i);
				if (!ANY_NAME.equals(subName2) && !subName1.equals(subName2)) {
					same = false;
					break;
				}
			}
			return same;
		}
	}
	
	@Override public boolean equals(Object obj) {
		if (obj instanceof ClassName) {
			return m_name.equals(((ClassName) obj).name());
		}
		return super.equals(obj);
	}
	
	@Override public int hashCode() {
		return m_name.hashCode();
	}
	
	@Override public String toString() {
		return name();
	}
	
	public static boolean sameClassName(ClassName name, ClassName fullName, List<ClassName> imports) {
		boolean same = compareForward(name, fullName);
		if (!same) {
			boolean sameName = compareBackward(name, fullName);
			if (sameName) { // try to find corresponding import
				for (ClassName imp : imports) {
					if (compareForward(fullName, imp)) {
						same = true; // corresponding import found, same class
						break;
					}
				}
			}
		}
		return same;
	}
	
	
	public static boolean compareBackward(ClassName name1, ClassName name2) {
		List<String> package1 = name1.splitName();
		List<String> package2 = name2.splitName();
		int index1 = package1.size() -1;
		int index2 = package2.size() - 1;
		boolean same = true;
		while ( index1 >= 0 && index2 >=0) {
			if (!package1.get(index1).equals(package2.get(index2))) {
				same = false;
				break;
			}
			index1--;
			index2--;
		}
		return same;
	}
	
	
	public static List<ClassName> extractCommonPackageLevels(Collection<ClassName> classNames , int levels, boolean removeLast) {
		List<ClassName> ret = new ArrayList<ClassName>();
		for (ClassName cn : classNames) {
			ClassName module = new ClassName(cn, levels, removeLast);
			if (!ret.contains(module)) {
				ret.add(module);
			}
		}
		return ret;
	}
	
	private static boolean compareForward(ClassName name1, ClassName name2) {
		List<String> package1 = name1.splitName();
		List<String> package2 = name2.splitName();
		int size1 = package1.size();
		int size2 = package2.size();
		String subName1;
		String subName2;
		boolean same = true;
		if (size1 != size2) {
			same = false;
		} else {
			for (int i =0; i < size1; i++) {
				subName1 = package1.get(i);
				subName2 = package2.get(i);
				if (!ANY_NAME.equals(subName1) && !ANY_NAME.equals(subName2) && !subName1.equals(subName2)) {
					same = false;
					break;
				}
			}
		}
		return same;
	}
	
	private static CommonTree constructBinaryCommonTree(List<String> nodes) {
		int nodesSize = nodes.size();
		if (nodesSize == 0) {
			return null;
		} else if (nodesSize == 1) {
			CommonToken identToken = new CommonToken(JavaLexer.Identifier, nodes.get(0));
			CommonTree ret = new CommonTree(identToken);
			return ret;
		} else {
			int middleIndex = nodesSize / 2 ;
			List<String> leftNodes = new ArrayList<String>();
			List<String> rightNodes = new ArrayList<String>();
			for (int i=0; i<nodesSize; i++ ) {
				if (i < middleIndex) {
					leftNodes.add(nodes.get(i));
				} else {
					rightNodes.add(nodes.get(i));
				}
			}
			
			CommonToken dotToken = new CommonToken(JavaLexer.DOT, ".");
			CommonTree ret = new CommonTree(dotToken);
			CommonTree leftChild = constructBinaryCommonTree(leftNodes);
			CommonTree rightChild = constructBinaryCommonTree(rightNodes);
			if (leftChild != null) {
				ret.addChild(leftChild);
			}
			if (rightChild != null) {
				ret.addChild(rightChild);
			}
			return ret;
		}
	}
	
}
