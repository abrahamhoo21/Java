package Control;

import Entity.Report;
import Entity.Tutor;
import Adt.ArrayListADT;
import static Dao.TutorInitialize.initialTutor;
import java.util.*;
import java.util.Scanner;

/**
 *
 * @author Abraham Hoo Weng Lek
 */
public class TutorManagementSystem {

    static ArrayListADT<Tutor> tutorList = new ArrayListADT<>();
    static ArrayListADT<Report> reportList = new ArrayListADT<>();
    static Scanner scanner = new Scanner(System.in);
    static Scanner scanner2 = new Scanner(System.in);

    public static void main(String[] args) {
        tutorList = initialTutor();
        menu();
    }

    public static void menu() {
        int choice = 0;
        boolean error = false;
        do {
            error = false;
            System.out.println("===============================================");
            System.out.println("||         TUTOR MANAGEMENT SYSTEM           ||");
            System.out.println("===============================================");
            System.out.println("||   1. Add a new tutor                      ||");
            System.out.println("||   2. Remove a tutor                       ||");
            System.out.println("||   3. Find a tutor                         ||");
            System.out.println("||   4. Amend tutor details                  ||");
            System.out.println("||   5. Display all tutors                   ||");
            System.out.println("||   6. Filter tutors based on criteria      ||");
            System.out.println("||   7. Generate Report                      ||");
            System.out.println("||   8. Exit the program                     ||");
            System.out.println("===============================================");
            System.out.print("Enter choice : ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 8) {
                    switch (choice) {
                        case 1:
                            addTutor();
                            break;
                        case 2:
                            removeTutor();
                            break;
                        case 3:
                            findTutor();
                            break;
                        case 4:
                            updateTutor();
                            break;
                        case 5:
                            displayTutor();
                            break;
                        case 6:
                            filterTutor();
                            break;
                        case 7:
                            generateReport();
                            break;
                        default:
                            System.out.println("\nExiting program.");
                    }
                } else {
                    System.out.println("Invalid number. Please try again.");
                    error = true;
                }
            } catch (Exception e) {
                System.out.println(e);
                error = true;
            }
        } while (error || (choice >= 1 && choice <= 7 && choice != 8));
    }

    public static void addTutor() {
        boolean error = false;
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter gender: ");
        String gender = scanner.nextLine();

        System.out.print("Enter date of birth: ");
        String dob = scanner.nextLine();

        System.out.print("Enter course: ");
        String course = scanner.nextLine();

        int yearInBoard = -1;
        do {
            error = false;
            System.out.print("Year In Boarding: ");
            try {
                yearInBoard = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                error = true;
                System.out.println(e);
            }
        } while (error);
        //Create a Tutor object to store data and add to the tutorList
        Tutor tutor = new Tutor(name, gender, getAgeFromDob(dob), course, yearInBoard);

        boolean isAdded = tutorList.add(tutor);
        if (isAdded) {
            System.out.println("\nTutor " + name + " is added successfully.\n");
            addToReport(tutor, "Add Tutor"); //use add tutor action
        } else {
            System.out.println("\nTutor " + name + " is failed to add.\n");
        }
    }

    public static void addToReport(Tutor tutor, String action) {
        Report report = new Report(tutor, action, new Date());
        reportList.add(report);
    }

    public static void removeTutor() {
        displayTutor();
        System.out.print("Enter number that want to be removed: ");
        int removeIndex = Integer.parseInt(scanner.nextLine());
        Tutor removedTutor = tutorList.remove(removeIndex);

        if (removedTutor != null) {
            System.out.println("\nTutor " + removedTutor.getName() + " is removed successfully.\n");
            addToReport(removedTutor, "Remove Tutor"); //use action remove tutor and add to report
        } else {
            System.out.println("\nNo tutor found at index " + removeIndex + " is failed to remove.\n");
        } 
    }

    public static void findTutor() {
        boolean found = false;
        while (!found) {
            System.out.print("Please enter number or name to find Tutor: ");
            String findTutor = scanner.nextLine();
            try {
                int num = Integer.parseInt(findTutor); //if variable cannot convert successfully then will hit error and run catch
                System.out.println("-----------------------------------------------------------------------------------------");
                System.out.printf("%-6s%-20s%-15s%-10s%-25s%-17s%n", "No.", "Name", "Gender", "Age", "Course", "Year In Board");
                System.out.println("-----------------------------------------------------------------------------------------");
                for (int i = 0; i < tutorList.totalSize(); i++) {
                    if (num == i + 1) {
                        System.out.printf("%-6s", i + 1 + ". ");
                        System.out.println(tutorList.search(i));
                        found = true; //match found by number
                    }
                }
                System.out.println("-----------------------------------------------------------------------------------------\n");
                if (found) {
                    System.out.println("Tutor successfully found by number.\n");
                }
            } catch (Exception e) {
                String name = findTutor;
                System.out.println("-----------------------------------------------------------------------------------------");
                System.out.printf("%-6s%-20s%-15s%-10s%-25s%-17s%n", "No.", "Name", "Gender", "Age", "Course", "Year In Board");
                System.out.println("-----------------------------------------------------------------------------------------");
                for (int i = 0; i < tutorList.totalSize(); i++) {
                    if (tutorList.search(i).getName().equals(name)) {
                        System.out.printf("%-6s", i + 1 + ". ");
                        System.out.println(tutorList.search(i));
                        found = true; //match found by name
                    }
                }
                System.out.println("-----------------------------------------------------------------------------------------\n");
                if (found) {
                    System.out.println("Tutor successfully found by name.\n");
                }
            }
            if (!found) {
                System.out.println("No tutor found with the given number or name.");
            }
        }
    }

    public static void updateTutor() {
        int input = 0;
        boolean error = false;
        int tutorNumber = -1;
        do {
            displayTutor();
            System.out.print("Enter number to update: ");
            try {
                tutorNumber = Integer.parseInt(scanner.nextLine());
                if (tutorNumber < 1 || tutorNumber > tutorList.totalSize()) {
                    System.out.println("Invalid tutor number. Please enter a valid number.");
                    error = true;
                } else {
                    error = false;
                }
            } catch (Exception e) {
                error = true;
                System.out.println(e);
            }
        } while (error);

        do {
            error = false;
            System.out.println("---------------------------------");
            System.out.println("|         Update Tutor          |");
            System.out.println("---------------------------------");
            System.out.println("|   1. Name                     |");
            System.out.println("|   2. Gender                   |");
            System.out.println("|   3. Enter date of birth      |");
            System.out.println("|   4. Course                   |");
            System.out.println("|   5. Year in board            |");
            System.out.println("|   6. Exit if nothing to update|");
            System.out.println("---------------------------------");
            System.out.print("Enter choice: ");
            input = Integer.parseInt(scanner.nextLine());

            if (input >= 1 && input <= 6) {
                switch (input) {
                    case 1:
                        System.out.print("Enter name: ");
                        String updateName = scanner2.nextLine();
                        try {
                            tutorList.search(tutorNumber - 1).setName(updateName);
                            System.out.println("Tutor " + tutorNumber + " is renamed to " + updateName + " successfully.\n");
                            addToReport(tutorList.search(tutorNumber - 1), "Update Name");
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        break;
                    case 2:
                        System.out.print("Enter gender: ");
                        String updateGender = scanner2.nextLine();
                        tutorList.search(tutorNumber - 1).setGender(updateGender);
                        try {
                            tutorList.search(tutorNumber - 1).setGender(updateGender);
                            System.out.println("\nGender of tutor " + tutorNumber + " is updated to " + updateGender + " successfully.\n");
                            addToReport(tutorList.search(tutorNumber - 1), "Update Gender");
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        break;
                    case 3:
                        System.out.print("Enter date of birth: ");
                        String updateDOB = scanner2.nextLine();
                        tutorList.search(tutorNumber - 1).setAge(getAgeFromDob(updateDOB));
                        try {
                            tutorList.search(tutorNumber - 1).setAge(getAgeFromDob(updateDOB));
                            System.out.println("\nDate of birth of tutor " + tutorNumber + " is updated to " + updateDOB + " successfully.\n");
                            addToReport(tutorList.search(tutorNumber - 1), "Update Age");
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        break;
                    case 4:
                        System.out.print("Enter course: ");
                        String updateCourse = scanner2.nextLine();
                        tutorList.search(tutorNumber - 1).setCourse(updateCourse);
                        try {
                            tutorList.search(tutorNumber - 1).setCourse(updateCourse);
                            System.out.println("\nCourse of tutor " + tutorNumber + " is updated to " + updateCourse + " successfully.\n");
                            addToReport(tutorList.search(tutorNumber - 1), "Update Course");
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        break;
                    case 5:
                        int updateYIB = -1;
                        do {
                            System.out.print("Enter year in board: ");
                            try {
                                updateYIB = Integer.parseInt(scanner2.nextLine());
                                tutorList.search(tutorNumber - 1).setYearInBoard(updateYIB);
                                System.out.println("\nYear In Board of tutor " + tutorNumber + " is updated to " + updateYIB + " successfully.\n");
                                addToReport(tutorList.search(tutorNumber - 1), "Update Year");
                                break; //Exit the loop on successful input
                            } catch (Exception e) {
                                error = true;
                                System.out.println(e);
                            }
                        } while (error);
                        break;
                    default:
                        System.out.println("\nExiting program.");
                        break; //Exit the loop and go to menu
                    }
            } else {
                System.out.println("Invalid number.Please try again.");
                error = true;
            }
        } while (error || (input < 1 || input > 6));
    }

    public static int getAgeFromDob(String dob) {
        Calendar date = new GregorianCalendar(); //Create a Calendar object named 'data'
        int yearNow = date.get(Calendar.YEAR); //Get the current
        //Taking the last four characters of the 'dob' String
        int yearDOB = Integer.parseInt(dob.substring(dob.length() - 4));
        // Calculate the age by subtracting the year of birth from the current year
        return (yearNow - yearDOB);
    }

    public static void displayTutor() {
        System.out.println("\nList of Tutors: ");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.printf("%-6s%-20s%-15s%-10s%-25s%-17s%n", "No.", "Name", "Gender", "Age", "Course", "Year In Board");
        System.out.println("-----------------------------------------------------------------------------------------");
        for (int i = 0; i < tutorList.totalSize(); i++) {
            System.out.printf("%-6s", i + 1 + ". ");
            System.out.println(tutorList.search(i));
        }
        System.out.println("-----------------------------------------------------------------------------------------\n");

    }

    public static void filterTutor() {
        boolean exitFilter = false;

        while (!exitFilter) {
            System.out.println("----------------------");
            System.out.println("|    Filter Tutor    |");
            System.out.println("----------------------");
            System.out.println("|  1. Name           |");
            System.out.println("|  2. Gender         |");
            System.out.println("|  3. Age            |");
            System.out.println("|  4. Course         |");
            System.out.println("|  5. Year in board  |");
            System.out.println("|  6. Exit Filter    |");
            System.out.println("----------------------");
            int choice = 0;
            boolean error = false;
            boolean found = false;

            do {
                error = false;
                System.out.print("Please select a category to filter (or enter 6 to exist): ");
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println(e);
                    error = true;
                }

                if (choice >= 1 && choice <= 6) {
                    switch (choice) {
                        case 1:
                            System.out.print("Enter name: ");
                            String filterName = scanner.nextLine();
                            for (int i = 0; i < tutorList.totalSize(); i++) {
                                if (filterName.equalsIgnoreCase(tutorList.search(i).getName())) {
                                    System.out.println(tutorList.search(i));
                                    found = true; //when a match is found
                                }
                            }
                            if (!found) {
                                System.out.println("Not found with the given name.");
                            }
                            break;
                        case 2:
                            System.out.print("Enter gender: ");
                            String filterGender = scanner.nextLine();
                            for (int i = 0; i < tutorList.totalSize(); i++) {
                                if (filterGender.equalsIgnoreCase(tutorList.search(i).getGender())) {
                                    System.out.println(tutorList.search(i));
                                    found = true; //when a match is found
                                }
                            }
                            if (!found) {
                                System.out.println("Not found with the given gender.");
                            }
                            break;
                        case 3:
                            System.out.print("Enter age: ");
                            try {
                                int filterAge = Integer.parseInt(scanner.nextLine());
                                for (int i = 0; i < tutorList.totalSize(); i++) {
                                    if (filterAge == tutorList.search(i).getAge()) {
                                        System.out.println(tutorList.search(i));
                                        found = true;
                                    }
                                }
                                if (!found) {
                                    System.out.println("Not found with the given age.");
                                }
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                            break;
                        case 4:
                            System.out.print("Enter course: ");
                            String filterCourse = scanner.nextLine();
                            for (int i = 0; i < tutorList.totalSize(); i++) {
                                if (filterCourse.equalsIgnoreCase(tutorList.search(i).getCourse())) {
                                    System.out.println(tutorList.search(i));
                                    found = true;
                                }
                            }
                            if (!found) {
                                System.out.println("Not found with the given course.");
                            }
                            break;
                        case 5:
                            int filterYIB = -1;
                            do {
                                System.out.println("Enter Year In Board: ");
                                try {
                                    filterYIB = Integer.parseInt(scanner.nextLine());
                                    for (int i = 0; i < tutorList.totalSize(); i++) {
                                        if (filterYIB == tutorList.search(i).getYearInBoard()) {
                                            System.out.println(tutorList.search(i));
                                        }
                                    }
                                    if (!found) {
                                        System.out.println("Not found with the given year in board.");
                                    }
                                    break;
                                } catch (Exception e) {
                                    error = true;
                                    System.out.println(e);
                                }
                            } while (error);
                        case 6:
                            exitFilter = true; //exit the filter loop
                            break;
                        default:
                            System.out.println("\nExiting filter");
                            break;
                    }
                } else {
                    System.out.println("Invalid number. Please try again");
                }
            } while (error || (choice < 1 && choice > 6 && choice != 0));
        }
    }

    public static void generateReport() {
        System.out.println("\nReport of Tutors: ");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-6s%-20s%-15s%-10s%-25s%-17s%-15s%-25s%n", "No.", "Tutor", "Gender", "Age", "Course", "Year In Board", "Action", "Date Time");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < reportList.totalSize(); i++) {
            System.out.printf("%-6s", i + 1 + ". ");
            System.out.println(reportList.search(i));
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------\n");
    }
}
