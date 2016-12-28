import java.util.Scanner;

/**
 * armut on 11.12.2016.
 */
public class Main {
    private static void menu() {
        System.out.println("\nAVL/IPR Tree Java Implementation");
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
        BST<String> tree = new AVL<>();
        //BST<String> tree = new IPR<>();
        //Integer[] nums = {60, 30, 90, 15, 35, 70, 95};
        String[] cities = { "Jefferson", "Hampton", "Greenville", "Ahoskie", "Gurnsey", "Boone", "Gusey", "Hamilton" };

        for( String i :
                cities ) {
            tree.insert(i);
        }

        menu();
        Scanner reader = new Scanner(System.in);
        int select = reader.nextInt();
        while(true) {
            if( select == 1 ) {
                System.out.println("\n======Print Tree======");
                tree.printInOrder();
                tree.drawTree("tree.dot");
                System.out.println("=======================");
                menu();
                select = reader.nextInt();
            } else if( select == 2 ) {
                System.out.println("\n======insert Operation======");
                System.out.println("| [-1] exit                |\n");
                System.out.print("insert a key to tree: ");
                String key = reader.next();
                while(!key.equals("exit")) {
                    tree.insert(key);
                    System.out.println("=======================");
                    System.out.print("insert a key to tree: ");
                    key = reader.next();
                }
                menu();
                select = reader.nextInt();
            } else if( select == 3 ) {
                System.out.println("\n======delete Operation======");
                System.out.println("| [-1] exit                |\n");
                System.out.print("delete a key from tree: ");
                String key = reader.next();
                while(!key.equals("exit")) {
                    tree.delete(key);
                    System.out.println("=======================");
                    System.out.print("delete a key from tree: ");
                    key = reader.next();
                }
                menu();
                select = reader.nextInt();
            } else if( select == 4 ) {
                System.out.println("\n======search Operation======");
                System.out.println("| [-1] exit                |\n");
                System.out.print("search a key in tree: ");
                String key = reader.next();
                while(!key.equals("exit")) {
                    tree.searchTree(key);
                    System.out.print("search a key in tree: ");
                    key = reader.next();
                }
                menu();
                select = reader.nextInt();
            } else if( select == 0 )
                System.exit(0);
        }
    }
}
