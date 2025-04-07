import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TaskSix {
    static Scanner input = new Scanner(System.in);
    static String[][] toDoList = new String[100][5];
    static String[][] completedList = new String[100][5];
    static int toDoCount, completedCount;

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
                \nView Menu
                ==================================
                1. To-do List
                2. Completed list
                3. Search by subject
                4. Return

                Enter your choice:\s""");
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                displayToDoList();
                Menu();
                break;
            case 2:
                displayCompletedList();
                Menu();
                break;
            case 3:
                search("key");
                Menu();
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
            \nUpdate Menu
            ==================================
            1. Add item to list
            2. Mark task as completed
            3. Remove item from list
            4. Sort List
            5. Return

            Enter your choice:\s""");

        int choice = input.nextInt();

        switch(choice) {
            case 1:
                // Add item to list
                System.out.print("Enter the amount of tasks you want to add: ");
                int amount = input.nextInt();

                for (int i = toDoCount; i < toDoCount + amount; i++) {

                    input.nextLine();
                    System.out.print("Enter the subject: ");
                    toDoList[i][0] = input.nextLine();
                    System.out.print("Enter the task name: ");
                    toDoList[i][1] = input.nextLine();
                    System.out.print("Enter the task's description: ");
                    toDoList[i][2] = input.nextLine();
                    System.out.print("Enter the task's urgency: ");
                    toDoList[i][3] = input.nextLine();
                    System.out.print("Enter the task's due date (dd/mm/yyyy): ");
                    do {
                        toDoList[i][4] = input.nextLine();
                        if (!dateValidation(toDoList[i][4])) {
                            System.out.print("Invalid date input. Please re-enter the due date (dd/mm/yyyy): ");
                        }
                    } while (!dateValidation(toDoList[i][4]));
                }

                toDoCount += amount;
                Menu();
                break;
            case 2:
                markAsDone();
                Menu();
                break;
            case 3:
                displayToDoList();
                System.out.print("Enter the task index you want to remove: ");
                int deleteIndex = input.nextInt();
                toDoList = removeTask(toDoList, deleteIndex);
                System.out.println("Task removed");
                System.out.println();
                Menu();
                break;
            case 4:
                sortList();
                Menu();
                break;
            case 5:
                Menu();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    public static void displayToDoList() {
        System.out.printf("%-5s %-10s %-10s %-15s %-10s %-10s\n", "ID", "Subject", "Task", "Description", "Urgency", "Due Date");
        System.out.println("----------------------------------------------------------------");
        for (int i = 0; i < toDoCount; i++) {
            System.out.printf("%-5d %-10s %-10s %-15s %-10s %-10s\n", i + 1, toDoList[i][0], toDoList[i][1], toDoList[i][2], toDoList[i][3], toDoList[i][4]);    
        }
        System.out.println();
    }

    public static void displayCompletedList() {
        System.out.printf("%-5s %-10s %-10s %-15s %-10s %-10s\n", "ID", "Subject", "Task", "Description", "Urgency", "Due Date");
        System.out.println("----------------------------------------------------------------");
        for (int i = 0; i < completedCount; i++) {
            System.out.printf("%-5d %-10s %-10s %-15s %-10s %-10s\n", i + 1, completedList[i][0], completedList[i][1], completedList[i][2], completedList[i][3], completedList[i][4]);    
        }
        System.out.println();
    }

    public static void sortList() {
        System.out.print("""
            Menu
            ==================================
            1. Sort by duration
            2. Sort by subject (A-Z)
            3. Return

            Enter your choice:\s
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        Arrays.sort(toDoList, 0, toDoCount, Comparator.comparing(column -> LocalDate.parse(column[4], formatter)));

        System.out.printf("%-5s %-10s %-10s %-15s %-10s %-10s\n", "ID", "Subject", "Task", "Description", "Urgency", "Due Date");
        System.out.println("----------------------------------------------------------------");
        for (int i = 0; i < toDoCount; i++) {
            System.out.printf("%-5d %-10s %-10s %-15s %-10s %-10s\n", i + 1, toDoList[i][0], toDoList[i][1], toDoList[i][2], toDoList[i][3], toDoList[i][4]);    
        }
        System.out.println();        
    }

    public static void sortBySubject() {

    }

    public static void search(String key) {
        for (int i = 0; i < toDoCount; i++) {
            if (toDoList[i][0].equalsIgnoreCase(key)) {
                System.out.printf("%-5d %-10s %-10s %-15s %-10s %-10s\n", i + 1, toDoList[i][0], toDoList[i][1], toDoList[i][2], toDoList[i][3], toDoList[i][4]);    
            }
        }
        System.out.println();
    }

    public static void markAsDone () {

        System.out.printf("%-5s %-10s %-10s %-15s %-10s %-10s\n", "ID", "Subject", "Task", "Description", "Urgency", "Due Date");
        System.out.println("----------------------------------------------------------------");
        for (int i = 0; i < toDoCount; i++) {
            System.out.printf("%-5d %-10s %-10s %-15s %-10s %-10s\n", i + 1, toDoList[i][0], toDoList[i][1], toDoList[i][2], toDoList[i][3], toDoList[i][4]);    
        }
        System.out.println();

        System.out.print("Enter the task index you want to mark as done: ");
        int index = input.nextInt();
        index--;
        if (index < 0 || index > toDoCount) {
            System.out.println("Invalid Index");
            return;
        }

        completedList[completedCount] = toDoList[index];
        completedCount++;
        
        for (int i = index; i < toDoCount -1; i++) {
            toDoList[i] = toDoList[i + 1];
        }

        toDoCount--;
        toDoList[toDoCount] = null;
    }

    public static String[][] loadArray (String filename) {
        List<String[]> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line.split(","));
            }
        } catch (IOException e) {
            System.out.println("Error occurred: ");
            e.printStackTrace();
        }

        return list.toArray(new String[0][]);
    }

    public static String[][] removeTask(String[][] toDoList, int rowIndex) {
        if (rowIndex < 0 || rowIndex > toDoCount) {
            throw new IllegalArgumentException("Invalid Index");
        }

        String[][] result = new String[100][5];
        int row = 0;
        for (int i = 0; i < result.length; i++) {
            if (i == rowIndex) {
                continue;
            }
            result[row] = toDoList[i];
            row++;
        }
        toDoCount--;
        return result;
    }

    public static boolean dateValidation (String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            LocalDate parsedDate = LocalDate.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static void main (String[] args) {
        File toDoFile = new File("toDoFile.txt");
        File completedFile = new File("completedFile.txt");
        File countFile = new File("countfile.txt");

        if (!toDoFile.exists()) {
            try {
                toDoFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Error occurred: ");
                e.printStackTrace();
            }
        }

        if (!completedFile.exists()) {
            try {
                completedFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Error occurred: ");
                e.printStackTrace();
            }
        }

        if(!countFile.exists()) {
            try{
                countFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Error occurred: ");
                e.printStackTrace();
            }
        }

        if (toDoFile.length() != 0) {
            toDoList = loadArray("toDoFile.txt");
        }

        if (completedFile.length() != 0) {
            completedList = loadArray("completedFile.txt");
        }

        if (countFile.length() != 0) {
            try (Scanner fileScanner = new Scanner(new File("countFile.txt"))) {
                if (fileScanner.hasNextInt()) {
                    toDoCount = fileScanner.nextInt();
                }

                if (fileScanner.hasNextInt()) {
                    completedCount = fileScanner.nextInt();
                }
            } catch (IOException e) {
                System.out.println("Error occurred: ");
                e.printStackTrace();
            }
        } else {
            toDoCount = 0;
            completedCount = 0;
        }

        Menu();

        try (PrintWriter writer = new PrintWriter(new FileWriter(toDoFile))) { // Writing into a to-do tasks file
            for (String[] row : toDoList) {
                for (String item : row) {
                    writer.print(item + ",");
                }
                writer.println();
            }
        } catch (IOException e) {
            System.out.println("Error Detected: ");
            e.printStackTrace();
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(completedFile))) { // Writing into a completed tasks file
            for (String[] row : completedList) {
                for (String item : row) {
                    writer.print(item + ",");
                }
                writer.println();
            }
        } catch (IOException e) {
            System.out.println("Error Detected: ");
            e.printStackTrace();
        }

        try (PrintWriter writer = new PrintWriter(new File("countFile.txt"))) {
            writer.println(toDoCount);
            writer.println(completedCount);
        } catch (IOException e) {
            System.out.print("Error occurred: ");
            e.printStackTrace();
        }
    }
}
