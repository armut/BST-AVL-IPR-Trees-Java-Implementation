
public class IPR<T extends Comparable<T>> extends BST<T> {

    private void SetWeight(Node<T> n) {
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
    private void VerifyIPR(Node<T> p) {
        int wl = p.getLeft() == null ? 0 : p.getLeft().getWeight();
        int wr = p.getRight() == null ? 0 : p.getRight().getWeight();

        // If p's left sub-tree is heavier,
        if( wl > wr ) {
            int wll = p.getLeft().getLeft() == null ? 0 : p.getLeft().getLeft().getWeight();
            int wlr = p.getLeft().getRight() == null ? 0 : p.getLeft().getRight().getWeight();

            // then; if p's left's left is heavier than p's right sub-tree,
            if( wll > wr ) {
                // then we need a single right rotation.
                RightRotate(p, p.getLeft());
            }
            // then; if p's left's right is heavier than p's right sub-tree,
            else if( wlr > wr ) {
                // then we need double rotation.
                LeftRotate(p.getLeft(), p.getLeft().getRight());
                RightRotate(p, p.getLeft());
            }
        }
        // Else, if p's right sub-tree is heavier,
        else if( wr > wl ) {
            int wrr = p.getRight().getRight() == null ? 0 : p.getRight().getRight().getWeight();
            int wrl = p.getRight().getLeft() == null ? 0 : p.getRight().getLeft().getWeight();

            // then; if p's right's right is heavier than p's left sub-tree,
            if( wrr > wl ) {
                // then we need a single left rotation.
                LeftRotate(p, p.getRight());
            }
            // then; if p's right's left is heavier than p's left sub-tree,
            else if( wrl > wl ) {
                // then we need double rotation.
                RightRotate(p.getRight(), p.getRight().getLeft());
                LeftRotate(p, p.getRight());
            }
        }
    }

    @Override
    public void Insert(T key) {
        super.Insert(key);
        Node<T> p = Search(key);
        while( p != root ) {
            SetWeight(p);
            VerifyIPR(p);
            p = Search(p.getKey());
        }
        SetWeight(root);
        VerifyIPR(root);
    }

    @Override
    protected void DeletePrivate(Node<T> parent, boolean isLeft) {
        super.DeletePrivate(parent, isLeft);
        Node<T> p = parent;
        while( p != root ) {
            SetWeight(p);
            VerifyIPR(p);
            p = Search(p.getKey());
        }
        SetWeight(root);
        VerifyIPR(root);
    }

    @Override
    protected void RightRotate(Node<T> x, Node<T> y) {
        super.RightRotate(x, y);
        SetWeight(x);
        SetWeight(y);
    }

    @Override
    protected void LeftRotate(Node<T> x, Node<T> y) {
        super.LeftRotate(x, y);
        SetWeight(x);
        SetWeight(y);
    }

}
