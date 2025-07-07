import java.util.*;
class BST {
    Node root;

    public BST(){
        this.root = null;
    }

    public void insert(int data) {
        root = insertVal(data, root);
    }

    public Node insertVal(int data, Node root) {
        if(root == null) return new Node(data);
        if(data < root.data) root.left = insertVal(data, root.left);
        else root.right = insertVal(data, root.right);
        return root;
    }

    public void delete(int data){
        root = deleteNode(root, data);
    }

    public Node deleteNode(Node root, int data){
        if(root == null) return null;
        if(root.data < data) root.right = deleteNode(root.right, data);
        else if(root.data > data) root.left = deleteNode(root.left, data);
        else{
            if(root.left == null && root.right == null) return null;
            else if(root.left == null) return root.right;
            else if(root.right == null) return root.left;
            Node newNode = minNode(root.right);
            root.data = newNode.data;
            root.right = deleteNode(root.right, newNode.data);
        }
        return root;
    }

    public Node minNode(Node root) {
        while(root.left != null){
            root = root.left;
        }
        return root;
    }

    public void inOrder() {
        inOrder(root);
        System.out.println();
    }

    void inOrder(Node node) {
        if(node != null){
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }

    public void preOrder() {
        preOrder(root);
        System.out.println();
    }

    public void preOrder(Node node) {
        if(node != null){
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void postOrder() {
        postOrder(root);
        System.out.println();
    }

    public void postOrder(Node node) {
        if(node != null){
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data + " ");
        }
    }

    public void levelOrder() {
        if(root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.data + " ");
            if(current.left != null) queue.add(current.left);
            if(current.right != null) queue.add(current.right);
        }
        System.out.println();
    }

    public void search(int data) {
        Node temp = root;
        temp = searchElement(root, data);
        System.out.println(temp == null ? "Not Found" : "Found");
    }

    public Node searchElement(Node root, int data) {
        if(root == null) return null;
        if(root.data == data) return root;
        else if(data < root.data) return searchElement(root.left, data);
        else return searchElement(root.right, data);
    }

    public int findHeight() {
        return findHeight(root);
    }

    public int findHeight(Node node) {
        if (node == null) return -1;
        int leftHeight = findHeight(node.left);
        int rightHeight = findHeight(node.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public int depth(int target) {
        return depth(root,target,0);
    }

    public int depth(Node root, int target, int d){
        if(root == null) return -1;
        if(root.data == target) return d;
        else if(root.data < target) return depth(root.right, target, d+1);
        else return depth(root.left, target, d+1);
    }

    private int level = 0;

    public int diameter() {
        diameter(root);
        return level;
    }

    public int diameter(Node node){
        if(node == null) return 0;
        int left = diameter(node.left);
        int right = diameter(node.right);
        level = Math.max(level,(left+right));
        return Math.max(left,right)+1;
    }

    public Node findLCA(int n1, int n2) {
        return findLCA(root, n1, n2);
    }

    public Node findLCA(Node node, int n1, int n2) {
        if (node == null) return null;
        if (n1 < node.data && n2 < node.data) return findLCA(node.left, n1, n2);
        else if (n1 > node.data && n2 > node.data) return findLCA(node.right, n1, n2);
        else return node;
    }

    private int distanceFromNode(Node root, int val) {
        if (root == null) return -1;
        if (root.data == val) return 0;
        if (val < root.data) {
            int left = distanceFromNode(root.left, val);
            return (left == -1) ? -1 : 1 + left;
        } else {
            int right = distanceFromNode(root.right, val);
            return (right == -1) ? -1 : 1 + right;
        }
    }

    public int distanceBetweenNodes(int n1, int n2) {
        Node lca = findLCA(root, n1, n2);
        if (lca == null) return -1;
        int d1 = distanceFromNode(lca, n1);
        int d2 = distanceFromNode(lca, n2);
        if (d1 == -1 || d2 == -1) return -1;
        return d1 + d2;
    }

    public class Node {
        int data;
        Node left, right;

        public Node(int d) {
            this.data = d;
            this.left = right = null;
        }
    }
}

public class MainFile {
    public static void main(String[] args) {
        BST bst = new BST();

        // Insert
        bst.insert(40);
        bst.insert(30);
        bst.insert(50);
        bst.insert(10);
        bst.insert(20);
        bst.insert(45);
        bst.insert(55);

        // Traversal
        System.out.println("InOrder Traversal: ");
        bst.inOrder();
        System.out.println("PreOrder Traversal: ");
        bst.preOrder();
        System.out.println("PostOrder Traversal: ");
        bst.postOrder();
        System.out.println("LevelOrder Traversal: ");
        bst.levelOrder();

        // Deletion
        System.out.println("Deletion of a Node: ");
        bst.delete(55);
        bst.delete(30);
        bst.inOrder();

        // Search
        System.out.println("Search Node: ");
        bst.search(50);
        bst.search(46);

        // Height
        System.out.println("Height of the Tree: " + bst.findHeight());

        // Depth
        System.out.println("Depth of the Tree: " + bst.depth(50));

        // Diameter
        System.out.println("Diameter of the Tree: " + bst.diameter());

        // LCA
        BST.Node lcaNode = bst.findLCA(10, 50);
        if (lcaNode != null) System.out.println("LCA of 10 and 50 is: " + lcaNode.data);
        else System.out.println("LCA not found");

        // Distance Between 2 Nodes
        System.out.println("Distance between 20 and 50: " + bst.distanceBetweenNodes(20, 50));

    }
}