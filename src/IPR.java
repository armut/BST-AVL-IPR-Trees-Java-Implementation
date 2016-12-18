
public class IPR<T extends Comparable<T>> extends BST<T> {

    void SetWeight(Node<T> n) {
        // If n only has a left child,
        if( n.getLeft() != null && n.getRight() == null )
            n.setWeight(1 + n.getLeft().getWeight());
        // Else, if n only has a right child,
        else if( n.getLeft() == null && n.getRight() != null )
            n.setWeight(1 + n.getRight().getWeight());
        // Else, if n has two children,
        else if( n.getLeft() != null && n.getRight() != null )
            n.setWeight(1 + n.getLeft().getWeight() + n.getRight().getWeight());

        // Else, n is a leaf.
        // It already has its own weight 1.
        // Leave it alone...
    }

    void VerifyIPR(Node<T> n) {
        //TODO:IPR özelliğinin kontrol edilmesi.
    }

    public void Insert(T key) {
        super.Insert(key);
        Node<T> p = Search(key);
        while( p != root ) {
            SetWeight(p);
            p = Search(p.getKey());
        }
        SetWeight(root);
    }

    protected void RightRotate(Node<T> x, Node<T> y) {
        super.RightRotate(x, y);
        SetWeight(x);
        SetWeight(y);
    }

    protected void LeftRotate(Node<T> x, Node<T> y) {
        super.LeftRotate(x, y);
        SetWeight(x);
        SetWeight(y);
    }

}
