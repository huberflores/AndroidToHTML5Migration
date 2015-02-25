package ut.converter.ast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import ut.Util;

public class SrcPreprocessor {
	private final Map<ClassName, List<ClassName>> m_dependenceisMap = new HashMap<ClassName, List<ClassName>>();
	private final Map<CommonTree, ClassName> m_processedClasses = new HashMap<CommonTree, ClassName>();
	
	/** map contains ClassName - list of extend classes and implement interfaces */
	public Map<ClassName, List<ClassName>> dependenciesMap() { return m_dependenceisMap; } 
	
	
	public Map<CommonTree, ClassName> processedClasses() { return m_processedClasses; }
	
	public void process(CommonTree tree) {
		List<CommonTree> classes = ASTUtil.findNode(tree, JavaLexer.CLASS_DECLARATION);
		CommonTree packageTree = ASTUtil.findChild(tree, JavaLexer.PACKAGE); 
		List<CommonTree> importTrees = ASTUtil.findChildren(tree, JavaLexer.IMPORT);
		List<ClassName> imports = new ArrayList<ClassName>();
		for (CommonTree importTree : importTrees ) {
			ClassName importClass = new ClassName((CommonTree)importTree.getChild(0));
			imports.add(importClass);
		}

		for (CommonTree classTree: classes) {
			Tree parent = classTree.getParent();
			if (parent == tree || (parent.getType() == JavaLexer.CLASS_BODY && m_processedClasses.containsKey(parent.getParent()))) {
				ClassName parentName = m_processedClasses.get(parent);
				CommonTree nameTree = (CommonTree) classTree.getChild(0);
				
				ClassName nameObj = parentName != null 
						                  ? new ClassName(parentName,  nameTree.getText())
						                  : new ClassName((CommonTree) packageTree.getChild(0), nameTree.getText()); 
				m_processedClasses.put(classTree, nameObj);
				List<ClassName> dependencies = new ArrayList<ClassName>();
				m_dependenceisMap.put(nameObj, dependencies);
				
				CommonTree extendsNode = ASTUtil.findChild(classTree, JavaLexer.EXTENDS);
				CommonTree implementsNode = ASTUtil.findChild(classTree, JavaLexer.IMPLEMENTS);
				
				if (extendsNode != null) {
					dependencies.addAll(ASTUtil.extractTypes(extendsNode, imports));
				}
				
				if (implementsNode != null) {
					dependencies.addAll(ASTUtil.extractTypes(implementsNode, imports));
				}
			}
		}
	}
	
	public ClassName getEntryPointInDirectory(ClassName dir) {
		for (ClassName cn : m_processedClasses.values()) {
			if (cn.isInDirectory(dir) && isEntryPoint(cn, m_dependenceisMap)) {
				return cn;
			}
		}
		return null;
	}
	
	public ClassName getRFile() {
		for (ClassName cn : m_processedClasses.values()) {
			if (cn.getLastIdentifier().equals("R")) {
				return cn;
			}
		}
		return null;
	}
	
	private static boolean isEntryPoint(ClassName cn, Map<ClassName, List<ClassName>> dependenceisMap) { 
		if (cn.getLastIdentifier().equals(Util.GWT_ENTRY_POINT)) {
			return true;
		} else {
			List<ClassName> dependencies = dependenceisMap.get(cn);
			if (dependencies != null && !dependencies.isEmpty()) {
				for (ClassName subCn : dependencies) {
					boolean found = isEntryPoint(subCn, dependenceisMap);
					if (found) {
						return true;
					}
				}
			}
		}
		return false;
	}
		
}
