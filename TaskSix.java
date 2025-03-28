import java.util.Scanner;

public class TaskSix {
    static Scanner input = new Scanner(System.in);

    public static void Menu () {
        System.out.print("""
                Menu
                ==================================
                1. View the list
                2. Update the list
                3. Exit
                
                Enter your choice:\s
                """);
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                viewList();
                break;
            case 2:
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
                3. Return

                Enter your choice:\s """);
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                displayToDoList();
                break;
            case 2:
                displayCompletedList();
                break;
            case 3:
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
            3. Return

            Enter your choice:/s
            """);

        int choice = input.nextInt();

        switch(choice) {
            case 1:
                break;
            case 2:
                break;
            case 3:
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


    public static void main (String[] args) {
        Menu();
    }
}
