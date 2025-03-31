import java.util.Scanner;

// I want to make changes
public class TaskSix {
    static Scanner input = new Scanner(System.in);
    static String[][] toDoList = new String[100][];
    static String[][] completedList = new String[100][];
    static int toDoCount = 0, completedCount = 0;


    public static void Menu () {
        System.out.print("""
                Menu
                ==================================
                1. View the list
                2. Update the list
                3. Exit
                
                Enter your choice:\s""");
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                viewList();
                break;
            case 2:
                updateList();
                break;
            case 3:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid input");
        }
    }

    public static void viewList () {
        System.out.print("""
                Menu
                ==================================
                1. To-do List
                2. Completed list
                3. Search by subject
                3. Return

                Enter your choice:\s""");
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                displayToDoList();
                break;
            case 2:
                displayCompletedList();
                break;
            case 3:
                search("key");
                break;
            case 4:
                Menu();
                break;
            default:
                System.out.println("Invalid input");
        }
    }

    public static void updateList() {
        System.out.print("""
            Menu
            ==================================
            1. Add item to list
            2. Delete item from list
            3. Sort List
            4. Return

            Enter your choice:/s
            """);

        int choice = input.nextInt();

        switch(choice) {
            case 1:

                break;
            case 2:
                break;
            case 3:
                sortList();
            case 4:
                Menu();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    public static void displayToDoList() {

    }

    public static void displayCompletedList() {

    }

    public static void sortList() {
        System.out.print("""
            Menu
            ==================================
            1. Sort by duration
            2. Sort by subject (A-Z)
            3. Return

            Enter your choice:/s
            """);

        int choice = input.nextInt();

        switch(choice) {
            case 1:
                sortByDuration();
                break;
            case 2:
                sortBySubject();
                break;
            case 3:
                updateList();
                break;
        }
    }

    public static void sortByDuration() {

    }

    public static void sortBySubject() {

    }

    public static void search(String key) {

    }

    public static void main (String[] args) {
        Menu();
    }
}
