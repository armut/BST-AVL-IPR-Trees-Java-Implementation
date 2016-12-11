/**
 * armut on 11.12.2016.
 */
public class Main {
    public static void main(String args[]) {
        BST<Integer> tree = new BST<>();
        Integer[] nums = {15, 30, 7, 7, 9, 31, 8};

        for( Integer i :
                nums ) {
            tree.Insert(i);
        }

    }
}
