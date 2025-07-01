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
        

    }
}
