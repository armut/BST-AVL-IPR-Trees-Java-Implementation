
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

    public void Insert(T key) {
        super.Insert(key);
        //System.out.println(key + " added.");
        Node<T> p = Search(key);
        while( p != root ) {
            SetHeight(p);
            //System.out.println(p.getKey() + "'s height: " + p.getHeight());
            p = Search(p.getKey());
        }
        SetHeight(root);
        //System.out.println("root's height: " + p.getHeight());
        //System.out.println("==========================");
    }
}
