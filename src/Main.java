/**
 * armut on 11.12.2016.
 */
public class Main {
    public static void main(String args[]) {
        BST<Integer> tree = new BST<>();
        //Integer[] nums = {15, 30, 5, 9, 31, 8, 14, 12, 6, 13};
        //Integer[] nums = {12, 9, 8, 10};
        //Integer[] nums = {7, 9, 8, 12, 10, 11};
        Integer[] nums = {5, 2, -4, 3, 12, 9, 21, 19, 25};


        for( Integer i :
                nums ) {
            tree.Insert(i);
        }

        tree.Delete(12);
        tree.PrintTree();
    }
}
