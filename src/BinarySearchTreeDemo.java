class MyBinaryNode<K extends Comparable<K>>{
    K key;
    MyBinaryNode<K> left;
    MyBinaryNode<K> right;

    public MyBinaryNode(K key) {
        this.key = key;
        this.left= null;
        this.right= null;
    }
}


//Binary Search Tree class
class MyBinarySearchTree<K extends Comparable<K>>{
    private MyBinaryNode<K> root;
    private int size;

    // Constructor
    public MyBinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    // Add a node to the BST
    public void add(K key) {
        this.root = this.addRecursively(root, key);
        this.size++;
    }
    // Recursive helper method to add nodes
    private MyBinaryNode<K> addRecursively(MyBinaryNode<K> current, K key) {
        if (current == null) {
            return new MyBinaryNode<>(key);
        }

        if (key.compareTo(current.key) < 0) {
            current.left = addRecursively(current.left, key);
        } else if (key.compareTo(current.key) > 0) {
            current.right = addRecursively(current.right, key);
        }

        return current;
    }

    // Get the size of the tree
    public int size() {
        return this.size;
    }

    // In-order traversal to print the tree
    public void printInOrder() {
        printInOrderRecursively(this.root);
        System.out.println();
    }

    private void printInOrderRecursively(MyBinaryNode<K> node) {
        if (node != null) {
            printInOrderRecursively(node.left);
            System.out.print(node.key + " ");
            printInOrderRecursively(node.right);
        }
    }

    // Search for a key in the BST
    public boolean search(K key) {
        return searchRecursively(this.root, key);
    }

    private boolean searchRecursively(MyBinaryNode<K> current, K key) {
        if (current == null) {
            return false;
        }

        if (key.compareTo(current.key) == 0) {
            return true;
        } else if (key.compareTo(current.key) < 0) {
            return searchRecursively(current.left, key);
        } else {
            return searchRecursively(current.right, key);
        }
    }
}



public class BinarySearchTreeDemo {
    public static void main(String[] args){
        MyBinarySearchTree<Integer> bst = new MyBinarySearchTree<>();

        // Add nodes to the BST
        bst.add(56); // Root node
        bst.add(30); // Left of root
        bst.add(70); // Right of root
        bst.add(63); // Node to search later

        // Print the BST in in-order traversal
        System.out.println("In-order traversal of BST:");
        bst.printInOrder();

        // Check the size of the tree
        System.out.println("Size of the BST: " + bst.size());

        // Search for the key 63
        int searchKey = 63;
        boolean isFound = bst.search(searchKey);
        System.out.println("Is key " + searchKey + " present in the BST? " + isFound);
    }
}
