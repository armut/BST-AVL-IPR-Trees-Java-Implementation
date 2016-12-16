import com.sun.istack.internal.Nullable;

public class BST<T extends Comparable<T>> {
    protected Node<T> root;
    protected boolean isLeft, isRoot;

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
                    // Recursively call Insert on the right sub-tree.
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
                    // Recursively call Insert on the left sub-tree.
                    InsertPrivate(key, n.getLeft());
            }
        }
    }

    public void Delete(T key) {
        if( root == null )
            System.out.println("The tree is empty.");
        else {
            Node<T> node = SearchPrivate(key, root);
            boolean isLeft = this.isLeft;
            if( node == root && isRoot )
                DeleteRoot();
            else if( node != null )
                DeletePrivate(node, isLeft);
            else
                System.out.println("No such key in the tree.\n");
        }
    }

    private void DeleteRoot() {
        if( root.getLeft() != null && root.getRight() != null )
            DeleteWith2Children(root);
        else if( root.getLeft() != null && root.getRight() == null )
            root = root.getLeft();
        else if( root.getLeft() == null && root.getRight() != null )
            root = root.getRight();
        else
            root = null;
    }

    private void DeleteWith2Children(Node<T> n) {
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
                DeleteWith2Children(n);
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
                DeleteWith2Children(n);
            }

        }
    }

    public Node<T> Search(T key) {
        if( root == null )
            return null;

        return SearchPrivate(key, root);
    }

    private Node<T> SearchPrivate(T key, Node<T> n) {
        // If the key we are looking up is the root's itself.
        if( root.getKey() == key ) {
            isRoot = true;
            return root;
        }

        // If the key is the left child's key,
        if( n.getLeft() != null && key == n.getLeft().getKey() ) {
            isLeft = true;
            isRoot = false;
            return n;
        }
        // Else if the key is the right child's key,
        else if( n.getRight() != null && key == n.getRight().getKey() ) {
            isLeft = false;
            isRoot = false;
            return n;
        }
        // Else if the key is greater than n,
        else if( n.getRight() != null && key.compareTo(n.getKey()) > 0 ) {
            // then look up the right sub-tree.
            return SearchPrivate(key, n.getRight());
        }
        // Else if the key is less than n,
        else if( n.getLeft() != null && key.compareTo(n.getKey()) < 0 ) {
            // then look up the left sub-tree.
            return SearchPrivate(key, n.getLeft());
        }

        // If none of the above, then the key does not exist.
        return null;
    }

    @Nullable
    public Node<T> getSuccessor(T key) {
        // Return null if the tree is empty.
        if( root == null )
            return null;
        else {
            // Get parent of the node with key 'key'
            Node<T> parent = SearchPrivate(key, root);

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
                            tmp = Search(tmp.getKey());
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

    public void PrintInOrder() {
        PrintInOrderPrivate(root);
    }

    private void PrintInOrderPrivate(Node<T> n) {
        if( root == null )
            System.out.println("The tree is empty.");
        else {
            if( n.getLeft() != null )
                PrintInOrderPrivate(n.getLeft());

            if( n.getLeft() == null && n.getRight() == null )
                System.out.println(n.getKey() + " l:null" + " r:null");
            else if( n.getLeft() != null && n.getRight() == null )
                System.out.println(n.getKey() + " l:" + n.getLeft().getKey() + " r:null");
            else if( n.getLeft() == null && n.getRight() != null )
                System.out.println(n.getKey() + " l:null" + " r:" + n.getRight().getKey());
            else
                System.out.println(n.getKey() + " l:" + n.getLeft().getKey() + " r:" + n.getRight().getKey());

            if( n.getRight() != null )
                PrintInOrderPrivate(n.getRight());
        }
    }
    //TODO:PrintTree.
    boolean flag = true;
    public void PrintTree(Node<T> n) {
        int max = (int)Math.pow(2, root.getHeight() + 1) - 1;
        int floor = root.getHeight();
        if( n.getLeft() != null && n.getRight() != null ) {
            //System.out.print(n.getLeft().getKey() + "  "  + n.getRight().getKey());
            for(int i = 0; i <= floor; i++) {
                System.out.println(i);
            }
        }
    }
    //TODO: getPredecessor
}
