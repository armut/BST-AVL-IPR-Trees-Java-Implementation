
class Node<T extends Comparable<T>> {
    private int height = 0, weight = 1;
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
