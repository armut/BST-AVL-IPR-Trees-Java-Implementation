
public class IPR<T extends Comparable<T>> extends BST<T> {

    private void setWeight(Node<T> n) {
        // If n is a mere leaf,
        if( n.getLeft() == null && n.getRight() == null )
            n.setWeight(1);
        // If n only has a left child,
        else if( n.getLeft() != null && n.getRight() == null )
            n.setWeight(1 + n.getLeft().getWeight());
        // Else, if n only has a right child,
        else if( n.getLeft() == null && n.getRight() != null )
            n.setWeight(1 + n.getRight().getWeight());
        // Else, if n has two children,
        else if( n.getLeft() != null && n.getRight() != null )
            n.setWeight(1 + n.getLeft().getWeight() + n.getRight().getWeight());
    }

    /* Paper can be found at https://www.researchgate.net/profile/Gaston_Gonnet/publication/220420554_Balancing_Binary_Trees_by_Internal_Path_Reduction/ */
    private void verifyIPR(Node<T> p) {
        int wl = p.getLeft() == null ? 0 : p.getLeft().getWeight();
        int wr = p.getRight() == null ? 0 : p.getRight().getWeight();

        // If p's left sub-tree is heavier,
        if( wl > wr ) {
            int wll = p.getLeft().getLeft() == null ? 0 : p.getLeft().getLeft().getWeight();
            int wlr = p.getLeft().getRight() == null ? 0 : p.getLeft().getRight().getWeight();

            // then; if p's left's left is heavier than p's right sub-tree,
            if( wll > wr ) {
                // then we need a single right rotation.
                rightRotate(p, p.getLeft());
            }
            // then; if p's left's right is heavier than p's right sub-tree,
            else if( wlr > wr ) {
                // then we need double rotation.
                leftRotate(p.getLeft(), p.getLeft().getRight());
                rightRotate(p, p.getLeft());
            }
        }
        // Else, if p's right sub-tree is heavier,
        else if( wr > wl ) {
            int wrr = p.getRight().getRight() == null ? 0 : p.getRight().getRight().getWeight();
            int wrl = p.getRight().getLeft() == null ? 0 : p.getRight().getLeft().getWeight();

            // then; if p's right's right is heavier than p's left sub-tree,
            if( wrr > wl ) {
                // then we need a single left rotation.
                leftRotate(p, p.getRight());
            }
            // then; if p's right's left is heavier than p's left sub-tree,
            else if( wrl > wl ) {
                // then we need double rotation.
                rightRotate(p.getRight(), p.getRight().getLeft());
                leftRotate(p, p.getRight());
            }
        }
    }

    @Override
    public void insert(T key) {
        super.insert(key);
        Node<T> p = search(key);
        while( p != root ) {
            setWeight(p);
            verifyIPR(p);
            p = search(p.getKey());
        }
        setWeight(root);
        verifyIPR(root);
    }

    @Override
    protected void deletePrivate(Node<T> parent, boolean isLeft) {
        super.deletePrivate(parent, isLeft);
        Node<T> p = parent;
        while( p != root ) {
            setWeight(p);
            verifyIPR(p);
            p = search(p.getKey());
        }
        setWeight(root);
        verifyIPR(root);
    }

    @Override
    protected void rightRotate(Node<T> x, Node<T> y) {
        super.rightRotate(x, y);
        setWeight(x);
        setWeight(y);
    }

    @Override
    protected void leftRotate(Node<T> x, Node<T> y) {
        super.leftRotate(x, y);
        setWeight(x);
        setWeight(y);
    }

}
