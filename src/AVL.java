
public class AVL<T extends Comparable<T>> extends BST<T> {

    private void setHeight(Node<T> n) {
        if( n.getLeft() == null && n.getRight() == null )
            n.setHeight(0);
        else if( n.getLeft() != null && n.getRight() == null )
            n.setHeight(n.getLeft().getHeight() + 1);
        else if( n.getLeft() == null && n.getRight() != null )
            n.setHeight(n.getRight().getHeight() + 1);
        else if( n.getLeft() != null && n.getRight() != null ) {
            int max = (n.getLeft().getHeight() > n.getRight().getHeight()) ? n.getLeft().getHeight() :
                    n.getRight().getHeight();
            n.setHeight(max + 1);
        }
    }

    private void AVLLeftHeavy(Node<T> p, Node<T> left) {
        int leftHeight = left.getLeft() != null ? left.getLeft().getHeight() : -1;
        int rightHeight = left.getRight() != null ? left.getRight().getHeight() : -1;

        // If left's left child is heavier,
        if( leftHeight > rightHeight ) {
            // then, we need a single rotation.
            rightRotate(p, left);
        }
        // If left's right child is heavier,
        else if( leftHeight < rightHeight ) {
            // then, we need two single rotations (a.k.a double rotation)
            leftRotate(left, left.getRight());
            rightRotate(p, p.getLeft());
        }
    }

    private void AVLRightHeavy(Node<T> p, Node<T> right) {
        int leftHeight = right.getLeft() != null ? right.getLeft().getHeight() : -1;
        int rightHeight = right.getRight() != null ? right.getRight().getHeight() : -1;

        // If right's left child is heavier,
        if( leftHeight > rightHeight ) {
            // then, we need a double rotation.
            rightRotate(right, right.getLeft());
            leftRotate(p, p.getRight());
        }
        // If right's right child is heavier,
        else if( leftHeight < rightHeight ) {
            // then, we need a single rotation.
            leftRotate(p, right);
        }
    }

    private void verifyAVL(Node<T> p) {
        Node<T> left = p.getLeft(), right = p.getRight();
        int balanceFactor;

        // If p has 2 children,
        if( left != null && right != null ) {
            // Calculate balance factor by subtracting left from right.
            balanceFactor = right.getHeight() - left.getHeight();

            // If the left sub-tree is heavier.
            if( balanceFactor < -1 )
                AVLLeftHeavy(p, left);
                // Else, if the right sub-tree is heavier.
            else if( balanceFactor > 1 )
                AVLRightHeavy(p, right);

        }
        // If the left child is null and the right is not,
        else if( left == null && right != null ) {
            // -1 for null node height.
            balanceFactor = right.getHeight() - (-1);
            if( balanceFactor > 1 )
                AVLRightHeavy(p, right);
        }
        // and vice versa...
        else if( left != null && right == null ) {
            // -1 for null node height.
            balanceFactor = (-1) - left.getHeight();
            if( balanceFactor < -1 )
                AVLLeftHeavy(p, left);
        }
        // Else, p is a leaf. Nothing to do.
        // ...
    }

    public void insert(T key) {
        super.insert(key);
        Node<T> p = search(key);
        while ( p != root ) {
            setHeight(p);
            verifyAVL(p);
            p = search(p.getKey());
        }
        setHeight(root);
        verifyAVL(root);
    }

    protected void deletePrivate(Node<T> parent, boolean isLeft) {
        super.deletePrivate(parent, isLeft);
        Node<T> p = parent;
        while( p != root ) {
            setHeight(p);
            verifyAVL(p);
            p = search(p.getKey());
        }
        setHeight(root);
        verifyAVL(root);
    }

    protected void rightRotate(Node<T> x, Node<T> y) {
        super.rightRotate(x, y);
        setHeight(x);
        setHeight(y);
    }

    protected void leftRotate(Node<T> x, Node<T> y) {
        super.leftRotate(x, y);
        setHeight(x);
        setHeight(y);
    }
}
