import java.util.Scanner;

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

    public static String[][] loadArray (String filename) {
        List<String[]> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line.split(" "));
            }
        } catch (IOException e) {
            System.out.println("Error occured: ");
            e.printStackTrace();
        }

        return list.toArray(new String[0][]);
    }

    public static void main (String[] args) {
            File toDoFile = new File ("toDoFile.txt");
            File completedFile = new File("completedFile.txt");
    
            if (!toDoFile.exists()) {
                try {
                    toDoFile.createNewFile();
                } catch (IOException e) {
                    System.out.println("Error occured: ");
                    e.printStackTrace();
                }
            }
    
            if (!completedFile.exists()) {
                try {
                    completedFile.createNewFile();
                } catch (IOException e) {
                    System.out.println("Error occured: ");
                }
            }
    
            if (toDoFile.length() != 0) {
                toDoList = loadArray("toDoFile.txt");
            }
    
            if (completedFile.length() != 0) {
                completedList = loadArray("completedFile.txt");
            }
            
            Menu();
    
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(toDoFile))) { // Writing into a to-do tasks file
                for (String[] row : toDoList) {
                    for (String item : row) {
                        writer.write(item + " ");
                    }
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Error Detected: ");
                e.printStackTrace();
            }
    
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(completedFile))) { // Writing into a completed tasks file
                for (String[] row : completedList) {
                    for (String item : row) {
                        writer.write(item + " ");
                    }
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Error Detected: ");
                e.printStackTrace();
            }
        }
    }
