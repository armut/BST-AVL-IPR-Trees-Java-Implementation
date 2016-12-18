import java.io.IOException;
import java.util.Scanner;

/**
 * armut on 11.12.2016.
 */
public class Main {

    private static void menu() {
        System.out.println("\nAVL Tree Java Implementation");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("| [1] Print Tree           |");
        System.out.println("| [2] Insert key           |");
        System.out.println("| [3] Delete key           |");
        System.out.println("| [4] Search key           |");
        System.out.println("| [0] Exit                 |");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.print(">>> ");
    }

    public static void main(String args[]) {
        IPR<String> tree = new IPR<>();
        Integer[] nums = {60, 30, 90, 15, 35, 70, 95};
        String[] letters = {"m", "b", "a", "q", "z"};

        for( String i :
                letters ) {
            tree.Insert(i);
        }

        menu();
        Scanner reader = new Scanner(System.in);
        int select = reader.nextInt();
        while(true) {
            if( select == 1 ) {
                System.out.println("\n======Print Tree======");
                tree.PrintInOrder();
                System.out.println("=======================");
                menu();
                select = reader.nextInt();
            } else if( select == 2 ) {
                System.out.println("\n======Insert Operation======");
                System.out.println("| [-1] Exit                |\n");
                System.out.print("Insert a key to tree: ");
                String key = reader.next();
                while(!key.equals("exit")) {
                    tree.Insert(key);
                    tree.PrintInOrder();
                    System.out.println("=======================");
                    System.out.print("Insert a key to tree: ");
                    key = reader.next();
                }
                menu();
                select = reader.nextInt();
            } else if( select == 3 ) {
                System.out.println("\n======Delete Operation======");
                System.out.println("| [-1] Exit                |\n");
                System.out.print("Delete a key from tree: ");
                String key = reader.next();
                while(!key.equals("exit")) {
                    tree.Delete(key);
                    tree.PrintInOrder();
                    System.out.println("=======================");
                    System.out.print("Delete a key from tree: ");
                    key = reader.next();
                }
                menu();
                select = reader.nextInt();
            } else if( select == 4 ) {
                System.out.println("\n======Search Operation======");
                System.out.println("| [-1] Exit                |\n");
                System.out.print("Search a key in tree: ");
                String key = reader.next();
                while(!key.equals("exit")) {
                    tree.SearchTree(key);
                    System.out.print("Search a key in tree: ");
                    key = reader.next();
                }
                menu();
                select = reader.nextInt();
            } else if( select == 0 )
                System.exit(0);
        }
    }
}
