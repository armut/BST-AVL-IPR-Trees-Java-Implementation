import com.sun.istack.internal.Nullable;

public class BST<T extends Comparable<T>> {
    private Node<T> root;
    private boolean isLeft;

    public void Insert(T key) {
        InsertPrivate(key, root);
    }

    private void InsertPrivate(T key, Node<T> n) {
        // Place the key in root if the root is empty.
        if( root == null ) {
            root = new Node<T>(key, null, null);
            //System.out.println("root = " + key);
        } else {
            // If the key is greater than the n's key.
            if( key.compareTo(n.getKey()) > 0 ) {
                // Place the new key the first place suitable you have found.
                if( n.getRight() == null ) {
                    n.setRight(new Node<>(key, null, null));
                    //System.out.println(n.getKey() + "'s right -> " + key);
                } else
                    // Recursively call Insert on the right subtree.
                    InsertPrivate(key, n.getRight());
            }
            // If the key is equal to the root's key.
            else if( key.compareTo(n.getKey()) == 0 ) {
                // Do not insert the same keys.
                System.out.println("The key " + key + " already exists in the tree.");
            }
            // If the key is less than the n's key.
            else {
                // Place the new key the first place suitable you have found again.
                if( n.getLeft() == null ) {
                    n.setLeft(new Node<>(key, null, null));
                    //System.out.println(n.getKey() + "'s left -> " + key);
                } else
                    // Recursively call Insert on the left subtree.
                    InsertPrivate(key, n.getLeft());
            }
        }
    }

    public void Delete(T key) {
        Node<T> node = SearchPrivate(key, root);
        if( node != null )
            DeletePrivate(node, isLeft);
        else
            System.out.println("No such key in the tree.\n");
    }

    //TODO:Root'un silinmesi.
    //TODO: Tüm işlemlerde: Eğer ağaç boşsa.
    private void DeleteRoot() {
        if( root.getLeft() != null && root.getRight() != null ) {
            DeleteWith2Children(root);
        } else if( root.getLeft() != null && root.getRight() == null ) {
            Node<T> tmp = root;
            root = tmp.getLeft();
            tmp = null;
        } else if( root.getLeft() == null && root.getRight() != null ) {
            Node<T> tmp = root;
            root = getSuccessor(root.getKey());
            getSuccessor(tmp.getKey()).setKey(tmp.getKey());
            Delete(tmp.getKey());
        }
    }

    private void DeleteWith2Children(Node<T> n) {
        // Get the successor of n.
        Node<T> successor = getSuccessor(n.getKey());

        // Replace n's key with its successor.
        n.setKey(successor.getKey());

        // Now, we have two nodes with the same key.
        // Since we replaced successor with n, we must delete the duplicate of the successor.
        // tmp is the root of n's right subtree, where the successor which we want to delete should be located.
        Node<T> tmp = n.getRight();

        // If tmp has a left subtree,
        if( tmp.getLeft() != null ) {
            // then we want the left-most node of it.
            while ( tmp.getLeft().getLeft() != null )
                tmp = tmp.getLeft();
            // Doing so, we are located to the duplicate node we want to delete and it is a left child.
            DeletePrivate(tmp, true);
        } else
            // otherwise right child of n is the duplicate node and it is a right child.
            DeletePrivate(n, false);
    }

    private void DeletePrivate(Node<T> parent, boolean isLeft) {
        if( isLeft ) {
            Node<T> n = parent.getLeft();
            // Case 1: Deleting a leaf.
            if( n.getLeft() == null && n.getRight() == null ) {
                System.out.println("The key " + n.getKey() + " (which was a leaf) has been deleted.\n");
                parent.setLeft(null);
            }
            // Case 2: Deleting a node with one child.
            // Case 2.1: If the node is the left node.
            else if( n.getLeft() != null && n.getRight() == null ) {
                parent.setLeft(n.getLeft());
                System.out.println(n.getKey() + " is deleted.");
                n = null;
            }
            //Case 2.2: If the node is the right node.
            else if( n.getLeft() == null && n.getRight() != null ) {
                parent.setLeft(n.getRight());
                System.out.println(n.getKey() + " is deleted.");
                n = null;
            }
            // Case 3: Deleting a node with two children.
            else if( n.getLeft() != null && n.getRight() != null ) {
                DeleteWith2Children(n);
            }
        } else {
            Node<T> n = parent.getRight();
            // Case 1: Deleting a leaf.
            if( n.getLeft() == null && n.getRight() == null ) {
                System.out.println("The key " + n.getKey() + " (which was a leaf) has been deleted.\n");
                parent.setRight(null);
            }
            // Case 2: Deleting a node with one child.
            // Case 2.1: If the node is the left node.
            else if( n.getLeft() != null && n.getRight() == null ) {
                parent.setRight(n.getLeft());
                System.out.println(n.getKey() + " is deleted.");
                n = null;
            }
            //Case 2.2: If the node is the right node.
            else if( n.getLeft() == null && n.getRight() != null ) {
                parent.setRight(n.getRight());
                System.out.println(n.getKey() + " is deleted.");
                n = null;
            }
            // Case 3: Deleting a node with two children.
            else if( n.getLeft() != null && n.getRight() != null ) {
                DeleteWith2Children(n);
            }

        }
    }

    public Node<T> Search(T key) {
        return SearchPrivate(key, root);
    }

    private Node<T> SearchPrivate(T key, Node<T> n) {
        // If the key we are looking up is the root's itself.
        if( root.getKey() == key )
            return root;

        // If the key is the left child's key,
        if( n.getLeft() != null && key == n.getLeft().getKey() ) {
            isLeft = true;
            return n;
        }
        // Else if the key is the right child's key,
        else if( n.getRight() != null && key == n.getRight().getKey() ) {
            isLeft = false;
            return n;
        }
        // Else if the key is greater than n,
        else if( n.getRight() != null && key.compareTo(n.getKey()) > 0 ) {
            // then look up the right subtree.
            return SearchPrivate(key, n.getRight());
        }
        // Else if the key is less than n,
        else if( n.getLeft() != null && key.compareTo(n.getKey()) < 0 ) {
            // then look up the left subtree.
            return SearchPrivate(key, n.getLeft());
        }

        // If none of the above, then the key does not exist.
        System.out.println("Couldn't find the key. Sorry. " + n.getKey());
        return null;
    }

    public void InOrderTraversal() {

    }

    @Nullable
    //TODO: Root'un successor'unu bulmak.
    private Node<T> getSuccessor(T key) {
        Node<T> parent = SearchPrivate(key, root);

        if( isLeft ) {
            Node<T> n = parent.getLeft();
            if( n.getRight() != null )
                return getMinimum(n.getRight());
            else
                return parent;
        } else {
            Node<T> n = parent.getRight();
            if( n.getRight() != null )
                return getMinimum(n.getRight());
            else {
                Node<T> tmp = new Node<>(parent.getKey(), parent.getLeft(), parent.getRight());
                while ( !isLeft ) {
                    tmp = SearchPrivate(tmp.getKey(), root);
                    if( tmp == root && tmp.getKey().compareTo(n.getKey()) < 0 )
                        // Which means n is the greatest.
                        return null;
                }
                return tmp;
            }
        }
    }

    public T getPredecessor(T key) {
        return null;
    }

    private Node<T> getMinimum(Node<T> n) {
        while ( n.getLeft() != null )
            n = n.getLeft();

        return n;
    }

    public void PrintTree() {
        PrintTreePrivate(root);
    }

    private void PrintTreePrivate(Node<T> n) {
        if( n.getLeft() != null )
            PrintTreePrivate(n.getLeft());

        if( n.getLeft() == null && n.getRight() == null )
            System.out.println(n.getKey() + " l:null" + " r:null");
        else if( n.getLeft() != null && n.getRight() == null )
            System.out.println(n.getKey() + " l:" + n.getLeft().getKey() + " r:null");
        else if( n.getLeft() == null && n.getRight() != null )
            System.out.println(n.getKey() + " l:null" + " r:" + n.getRight().getKey());
        else
            System.out.println(n.getKey() + " l:" + n.getLeft().getKey() + " r:" + n.getRight().getKey());

        if( n.getRight() != null )
            PrintTreePrivate(n.getRight());
    }
}
