
public class AVL<T extends Comparable<T>> extends BST<T> {

    private void SetHeight(Node<T> n) {
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
            RightRotate(p, left);
        }
        // If left's right child is heavier,
        else if( leftHeight < rightHeight ) {
            // then, we need two single rotations (a.k.a double rotation)
            LeftRotate(left, left.getRight());
            RightRotate(p, p.getLeft());
        }
    }

    private void AVLRightHeavy(Node<T> p, Node<T> right) {
        int leftHeight = right.getLeft() != null ? right.getLeft().getHeight() : -1;
        int rightHeight = right.getRight() != null ? right.getRight().getHeight() : -1;

        // If right's left child is heavier,
        if( leftHeight > rightHeight ) {
            // then, we need a double rotation.
            RightRotate(right, right.getLeft());
            LeftRotate(p, p.getRight());
        }
        // If right's right child is heavier,
        else if( leftHeight < rightHeight ) {
            // then, we need a single rotation.
            LeftRotate(p, right);
        }
    }

    private void VerifyAVL(Node<T> p) {
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

    public void Insert(T key) {
        super.Insert(key);
        Node<T> p = Search(key);
        while ( p != root ) {
            SetHeight(p);
            VerifyAVL(p);
            p = Search(p.getKey());
        }
        SetHeight(root);
        VerifyAVL(root);
        SetHeight(root);
    }

    //TODO:Bu ide'nin derdi ne ya hu.
    private void RightRotate(Node<T> x, Node<T> y) {
        // Right rotate x.
        Node<T> p = Search(x.getKey());
        boolean isLeft = this.isLeft;
        x.setLeft(y.getRight());
        y.setRight(x);
        if( x == root )
            root = y;
        else if( isLeft )
            p.setLeft(y);
        else
            p.setRight(y);

        SetHeight(x);
        SetHeight(y);
    }

    private void LeftRotate(Node<T> x, Node<T> y) {
        // Left rotate x.
        Node<T> p = Search(x.getKey());
        boolean isLeft = this.isLeft;
        x.setRight(y.getLeft());
        y.setLeft(x);
        if( x == root )
            root = y;
        else if( isLeft )
            p.setLeft(y);
        else
            p.setRight(y);

        SetHeight(x);
        SetHeight(y);
    }
}
