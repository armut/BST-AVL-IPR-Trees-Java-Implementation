
public class Node<T extends Comparable<T>> {
    private int height;
    private Node<T> left, right;
    private T key;

    public Node(T key, Node<T> left, Node<T> right) {
        this.left = left;
        this.right = right;
        this.key = key;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Node<T> getLeft() {
        return this.left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return this.right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public T getKey() {
        return this.key;
    }

    public void setKey(T key) {
        this.key = key;
    }
}
