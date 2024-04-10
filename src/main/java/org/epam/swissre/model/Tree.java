package org.epam.swissre.model;

public class Tree<T> {

    private final TreeNode<T> root;

    public Tree(TreeNode<T> root) {
        this.root = root;
    }

    public TreeNode<T> getRoot() {
        return root;
    }
}
