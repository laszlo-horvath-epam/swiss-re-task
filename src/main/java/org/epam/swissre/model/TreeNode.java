package org.epam.swissre.model;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T> {

    private T data;
    private TreeNode<T> parent;
    private List<TreeNode<T>> children;

    public TreeNode(T data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    public TreeNode(T data, TreeNode<T> parent) {
        this.data = data;
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    public T getData() {
        return data;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
        if (parent != null) {
            parent.addChild(this);
        }
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public void addChild(TreeNode<T> child) {
        children.add(child);
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }

    public boolean isRoot() {
        return parent == null;
    }

}
