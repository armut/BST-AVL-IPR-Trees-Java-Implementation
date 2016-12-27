import java.io.IOException;
import java.io.PrintWriter;

public class BST<T extends Comparable<T>> {
    protected Node<T> root;
    protected boolean isLeft, isRoot;

    public void insert(T key) {
        insertPrivate(key, root);
    }

    private void insertPrivate(T key, Node<T> n) {
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
                } else
                    // Recursively call insert on the right sub-tree.
                    insertPrivate(key, n.getRight());
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
                } else
                    // Recursively call insert on the left sub-tree.
                    insertPrivate(key, n.getLeft());
            }
        }
    }

    public void delete(T key) {
        if( root == null )
            System.out.println("The tree is empty.");
        else {
            Node<T> node = searchPrivate(key, root);
            boolean isLeft = this.isLeft;
            if( node == root && isRoot )
                deleteRoot();
            else if( node != null )
                deletePrivate(node, isLeft);
            else
                System.out.println("No such key in the tree.\n");
        }
    }

    private void deleteRoot() {
        if( root.getLeft() != null && root.getRight() != null )
            deleteWith2Children(root);
        else if( root.getLeft() != null && root.getRight() == null )
            root = root.getLeft();
        else if( root.getLeft() == null && root.getRight() != null )
            root = root.getRight();
        else
            root = null;
    }

    private void deleteWith2Children(Node<T> n) {
        // Get the successor of n.
        Node<T> successor = getSuccessor(n.getKey());

        // Replace n's key with its successor.
        n.setKey(successor.getKey());

        // Now, we have two nodes with the same key.
        // Since we replaced successor with n, we must delete the duplicate of the successor.
        // tmp is the root of n's right sub-tree, where the successor which we want to delete should be located.
        Node<T> tmp = n.getRight();

        // If tmp has a left sub-tree,
        if( tmp.getLeft() != null ) {
            // then we want the left-most node of it.
            while ( tmp.getLeft().getLeft() != null )
                tmp = tmp.getLeft();
            // Doing so, we are located to the duplicate node we want to delete and it is a left child.
            deletePrivate(tmp, true);
        } else
            // otherwise right child of n is the duplicate node and it is a right child.
            deletePrivate(n, false);
    }

    protected void deletePrivate(Node<T> parent, boolean isLeft) {
        if( isLeft ) {
            Node<T> n = parent.getLeft();
            // Case 1: Deleting a leaf.
            if( n.getLeft() == null && n.getRight() == null ) {
                parent.setLeft(null);
            }
            // Case 2: Deleting a node with one child.
            // Case 2.1: If the node is the left node.
            else if( n.getLeft() != null && n.getRight() == null ) {
                parent.setLeft(n.getLeft());
                n = null;
            }
            //Case 2.2: If the node is the right node.
            else if( n.getLeft() == null && n.getRight() != null ) {
                parent.setLeft(n.getRight());
                n = null;
            }
            // Case 3: Deleting a node with two children.
            else if( n.getLeft() != null && n.getRight() != null ) {
                deleteWith2Children(n);
            }
        } else {
            Node<T> n = parent.getRight();
            // Case 1: Deleting a leaf.
            if( n.getLeft() == null && n.getRight() == null ) {
                parent.setRight(null);
            }
            // Case 2: Deleting a node with one child.
            // Case 2.1: If the node is the left node.
            else if( n.getLeft() != null && n.getRight() == null ) {
                parent.setRight(n.getLeft());
                n = null;
            }
            //Case 2.2: If the node is the right node.
            else if( n.getLeft() == null && n.getRight() != null ) {
                parent.setRight(n.getRight());
                n = null;
            }
            // Case 3: Deleting a node with two children.
            else if( n.getLeft() != null && n.getRight() != null ) {
                deleteWith2Children(n);
            }

        }
    }

    public boolean searchTree(T key) {
        if( root == null ) {
            System.out.println("The tree is empty.");
            return false;
        }

        Node<T> n = searchPrivate(key, root);
        boolean isLeft = this.isLeft;

        if( n == null ) {
            System.out.println("The key " + key + " does not exist in the tree.");
            return false;
        }

        if( isLeft ) {
            System.out.println("Node with key " + n.getLeft().getKey() + " is found and its parent is " + n.getKey());
            return true;
        } else {
            System.out.println("Node with key " + n.getRight().getKey() + " is found and its parent is " + n.getKey());
            return false;
        }

    }

    public Node<T> search(T key) {
        if( root == null )
            return null;

        return searchPrivate(key, root);
    }

    private Node<T> searchPrivate(T key, Node<T> n) {
        // If the key we are looking up is the root's itself.
        if( root.getKey().compareTo(key) == 0 ) {
            isRoot = true;
            return root;
        }

        // If the key is the left child's key,
        if( n.getLeft() != null && key.compareTo(n.getLeft().getKey()) == 0 ) {
            isLeft = true;
            isRoot = false;
            return n;
        }
        // Else if the key is the right child's key,
        else if( n.getRight() != null && key.compareTo(n.getRight().getKey()) == 0 ) {
            isLeft = false;
            isRoot = false;
            return n;
        }
        // Else if the key is greater than n,
        else if( n.getRight() != null && key.compareTo(n.getKey()) > 0 ) {
            // then look up the right sub-tree.
            return searchPrivate(key, n.getRight());
        }
        // Else if the key is less than n,
        else if( n.getLeft() != null && key.compareTo(n.getKey()) < 0 ) {
            // then look up the left sub-tree.
            return searchPrivate(key, n.getLeft());
        }

        // If none of the above, then the key does not exist.
        return null;
    }

    public Node<T> getSuccessor(T key) {
        // Return null if the tree is empty.
        if( root == null )
            return null;
        else {
            // Get parent of the node with key 'key'
            Node<T> parent = searchPrivate(key, root);

            // If parent is null, then no such key.
            if( parent == null )
                return null;

            // If parent's itself is root,
            if( isRoot ) {
                // then, root has no parent.
                // Set n to parent.
                Node<T> n = parent;

                // If root has a right sub-tree,
                if( n.getRight() != null )
                    // we can get the minimum of it, which is the successor.
                    return getMinimum(n.getRight());
                else
                    // Otherwise, n --which is root-- is the greatest. It has no successor.
                    return n;
            }
            // If the key we are looking its successor is a left child.
            else if( isLeft ) {
                // Same as the situation in root's. Except returning parent.
                Node<T> n = parent.getLeft();
                if( n.getRight() != null )
                    return getMinimum(n.getRight());
                else
                    return parent;
                // Else if the key we are looking its successor is a right child.
            } else if( !isLeft ) {
                Node<T> n = parent.getRight();
                if( n.getRight() != null )
                    return getMinimum(n.getRight());
                else {
                    // If n has no right sub-tree,
                    // it is located on the left side of the root,
                    if( n.getKey().compareTo(root.getKey()) < 0 ) {
                        Node<T> tmp = parent;
                        while ( !isLeft )
                            tmp = search(tmp.getKey());
                        return tmp;
                    } else
                        // or n is the greatest.
                        return n;
                }
            }

            // If none of the above,
            return null;
        }
    }


    private Node<T> getMinimum(Node<T> n) {
        while ( n.getLeft() != null )
            n = n.getLeft();
        return n;
    }

    public void printInOrder() {
        printInOrderPrivate(root);
        printDot(root);
    }

    private void printInOrderPrivate(Node<T> n) {
        if( root == null )
            System.out.println("The tree is empty.");
        else {
            if( n.getLeft() != null )
                printInOrderPrivate(n.getLeft());

            if( n.getLeft() == null && n.getRight() == null )
                System.out.println(n.getKey() + "\tl:null" + "\tr:null");
            else if( n.getLeft() != null && n.getRight() == null )
                System.out.println(n.getKey() + "\tl:" + n.getLeft().getKey() + "\tr:null");
            else if( n.getLeft() == null && n.getRight() != null )
                System.out.println(n.getKey() + "\tl:null" + "\tr:" + n.getRight().getKey());
            else
                System.out.println(n.getKey() + "\tl:" + n.getLeft().getKey() + "\tr:" + n.getRight().getKey());

            if( n.getRight() != null )
                printInOrderPrivate(n.getRight());
        }
    }

    protected void rightRotate(Node<T> x, Node<T> y) {
        // Right rotate x.
        Node<T> p = search(x.getKey());
        boolean isLeft = this.isLeft;
        x.setLeft(y.getRight());
        y.setRight(x);
        if( x == root )
            root = y;
        else if( isLeft )
            p.setLeft(y);
        else
            p.setRight(y);
    }

    protected void leftRotate(Node<T> x, Node<T> y) {
        // Left rotate x.
        Node<T> p = search(x.getKey());
        boolean isLeft = this.isLeft;
        x.setRight(y.getLeft());
        y.setLeft(x);
        if( x == root )
            root = y;
        else if( isLeft )
            p.setLeft(y);
        else
            p.setRight(y);
    }

    private void printDotNull(T key, int count) {
        output += "   null" + count + " [shape=point];\n";
        output += "   " + key + " -> null" + count +";\n";
    }

    int count = 0;
    String output = "";
    private void printDotAux(Node<T> n) {
        if( n.getLeft() != null ) {
            output += "   " + n.getKey() + " -> " + n.getLeft().getKey() + ";\n";
            printDotAux(n.getLeft());
        } else
            printDotNull(n.getKey(), count++);

        if( n.getRight() != null ) {
            output += "   " + n.getKey() + " -> " + n.getRight().getKey() + ";\n";
            printDotAux(n.getRight());
        } else
            printDotNull(n.getKey(), count++);
    }

    private void printDot(Node<T> tree) {
        output = "digraph tree {\n";
        output += "   node [fontname=\"Arial\"];\n";

        if( tree == null )
            output += "\n";
        else if( tree.getLeft() == null && tree.getRight() == null )
            output += "   " + tree.getKey() + ";\n";
        else
            printDotAux(tree);

        output += "}\n";
        try {
            PrintWriter wr = new PrintWriter("tree.dot", "UTF-8");
            wr.print(output);
            wr.close();
        } catch( IOException e) {
            System.out.println("Error.");
        }
        count = 0;
        output = "";
    }
    //TODO: getPredecessor
}
