/**
 * armut on 11.12.2016.
 */
public class Main {
    public static void main(String args[]) {
        BST<Integer> tree = new BST<>();
        //Integer[] nums = {15, 30, 5, 9, 31, 8, 14, 12, 6, 13};
        //Integer[] nums = {12, 9, 8, 10};
        //Integer[] nums = {7, 9, 8, 12, 10, 11};
        //Integer[] nums = {5, 2, -4, 3, 12, 9, 21, 19, 25};
        Integer[] nums = {10, 5, 6, 9, 8, 12, 15};


        for( Integer i :
                nums ) {
            tree.Insert(i);
        }

        //tree.Delete(5);
        try {
            System.out.println(tree.getSuccessor(15).getKey());
        } catch (Exception NullPointerException) {
            System.out.println("No such key.");
        }
        tree.PrintTree();
    }
}
