
class Node<T extends Comparable<T>> {
    private int height = 0;

    // 0 -> balanced node, 1 -> right heavy node, -1 -> left heavy node.
    private byte orientation = 0;
    private Node<T> left, right;
    private T key;

    Node(T key, Node<T> left, Node<T> right) {
        this.left = left;
        this.right = right;
        this.key = key;
    }

    int getHeight() {
        return height;
    }

    void setHeight(int height) {
        this.height = height;
    }

    Node<T> getLeft() {
        return this.left;
    }

    void setLeft(Node<T> left) {
        this.left = left;
    }

    Node<T> getRight() {
        return this.right;
    }

    void setRight(Node<T> right) {
        this.right = right;
    }

    T getKey() {
        return this.key;
    }

    void setKey(T key) {
        this.key = key;
    }

    public byte getOrientation() {
        return orientation;
    }

    public void incrementOrientation() {
        this.orientation += 1;
    }

    public void decrementOrientation() {
        this.orientation -= 1;
    }
}
