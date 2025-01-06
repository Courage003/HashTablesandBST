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

    //add a node to BST
    public void add(K key){
        this.root=this.addRecursively(root,key);
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
}



public class BinarySearchTreeDemo {
    public static void main(String[] args){
        MyBinarySearchTree<Integer> bst = new MyBinarySearchTree<>();

        // Add nodes to the BST
        bst.add(56); // Root node
        bst.add(30); // Left of root
        bst.add(70); // Right of root

        // Print the BST in in-order traversal
        System.out.println("In-order traversal of BST:");
        bst.printInOrder();
    }
}
