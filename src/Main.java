/**
 * armut on 11.12.2016.
 */
public class Main {
    public static void main(String args[]) {
        AVL<Integer> tree = new AVL<>();
        Integer[] nums = {30, 15, 60, 7, 20, 16, 65, 70, 6, 5};

        for( Integer i :
                nums ) {
            tree.Insert(i);
        }

    }
}
