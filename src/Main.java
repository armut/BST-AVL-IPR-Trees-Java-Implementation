import java.io.IOException;
import java.util.Scanner;

/**
 * armut on 11.12.2016.
 */
public class Main {

    private static void menu() {
        System.out.println("\nAVL Tree Java Implementation");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("| [1] Insert key           |");
        System.out.println("| [2] Delete key           |");
        System.out.println("| [3] Search key           |");
        System.out.println("| [0] Exit                 |");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.print(">>> ");
    }

    public static void main(String args[]) {
        AVL<Integer> tree = new AVL<>();
        Integer[] nums = {60, 30, 90, 15, 35, 70, 95};

        for( Integer i :
                nums ) {
            tree.Insert(i);
        }

        menu();
        Scanner reader = new Scanner(System.in);
        int select = reader.nextInt();
        while(true) {
            if( select == 1 ) {
                System.out.println("\n======Insert Operation======");
                System.out.println("| [-1] Exit                |\n");
                System.out.print("Insert a key to tree: ");
                int key = reader.nextInt();
                while(key != -1) {
                    tree.Insert(key);
                    tree.PrintInOrder();
                    System.out.println("=======================");
                    System.out.print("Insert a key to tree: ");
                    key = reader.nextInt();
                }
                menu();
                select = reader.nextInt();
            } else if( select == 2 ) {
                System.out.println("\n======Delete Operation======");
                System.out.println("| [-1] Exit                |\n");
                System.out.print("Delete a key from tree: ");
                int key = reader.nextInt();
                while(key != -1) {
                    tree.Delete(key);
                    tree.PrintInOrder();
                    System.out.println("=======================");
                    System.out.print("Delete a key from tree: ");
                    key = reader.nextInt();
                }
                menu();
                select = reader.nextInt();
            } else if( select == 3 ) {
                System.out.println("\n======Search Operation======");
                System.out.println("| [-1] Exit                |\n");
                System.out.print("Search a key in tree: ");
                int key = reader.nextInt();
                while ( key != -1 ) {
                    tree.SearchTree(key);
                    System.out.print("Search a key in tree: ");
                    key = reader.nextInt();
                }
                menu();
                select = reader.nextInt();
            } else if( select == 0 )
                System.exit(0);
        }
    }
}
