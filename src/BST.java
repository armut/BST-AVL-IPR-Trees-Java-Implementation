
public class BST<T extends Comparable<T>> {
    private Node<T> root;

    public void Insert(T key) {
        Insertp(key, root);
    }

    private void Insertp(T key, Node<T> n) {
        // Place the key in root if the root is empty.
        if(root == null) {
            root = new Node<T>(key, null, null);
            System.out.println("root = " + key);
        } else {
            // If the key is greater than the n's key.
            if(key.compareTo(n.getKey()) > 0) {
                // Place the new key the first place suitable you have found.
                if(n.getRight() == null) {
                    n.setRight(new Node<>(key, null, null));
                    System.out.println(n.getKey() + "'s right -> " + key);
                } else
                    // Recursively call Insert on the right subtree.
                    Insertp(key, n.getRight());
            }
            // If the key is equal to the root's key.
            else if(key.compareTo(n.getKey()) == 0) {
                // Do not insert the same keys.
                System.out.println("The key " + key + " already exists in the tree.");
            }
            // If the key is less than the n's key.
            else {
                // Place the new key the first place suitable you have found again.
                if(n.getLeft() == null) {
                    n.setLeft(new Node<>(key, null, null));
                    System.out.println(n.getKey() + "'s left -> " + key);
                } else
                    // Recursively call Insert on the left subtree.
                    Insertp(key, n.getLeft());
            }
        }
    }

    public void Delete(T key) {
        // Case 1: Deleting a leaf.
        // Case 2: Deleting a node with one child.
        // Case 3: Deleting a node with two children.
    }

    public void Search(T key) {

    }

    public void InOrderTraversal() {

    }

    public T getSuccessor(T key) {
        return null;
    }

    public T getPredecessor(T key) {
        return null;
    }
}
