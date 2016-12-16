import java.util.Scanner;

/**
 * armut on 11.12.2016.
 */
public class Main {
    public static void main(String args[]) {
        AVL<Integer> tree = new AVL<>();
        Integer[] nums = {60, 30, 90, 15, 35, 70, 95};

        for( Integer i :
                nums ) {
            tree.Insert(i);
        }


        System.out.println("AVL Tree Java Implementation");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("======Insert Operation======\n");
        System.out.println("Insert a key to tree: ");
        Scanner reader = new Scanner(System.in);
        int key = reader.nextInt();
        while(key != -1) {
            tree.Insert(key);
            tree.PrintInOrder();
            System.out.println("=======================");
            System.out.println("Insert a key to tree: ");
            key = reader.nextInt();
        }
        System.out.println("Sayounara.");
    }
}
