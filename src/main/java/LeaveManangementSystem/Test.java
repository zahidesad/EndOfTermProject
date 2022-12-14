package LeaveManangementSystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author zahid
 */
public class Test {

    Login login;

    //Constructor for initializing objects
    public Test() {
        login = new Login();
    }

    public static void main(String[] args) {

        CEO ceo = new CEO();
        ceo.setUsername("admin");
        ceo.setPassword("123");
        Company.users.add(ceo);

        Test test = new Test();
        test.Display();

    }

    void Display() {
        Scanner scan = new Scanner(System.in);
        String username, password;
        int a = 0;
        int total = 0;

        System.out.println("\n\nSelect any one for login procedure\n\n" + "1.CEO \n" + "2.Manager \n" + "3.Employee");
        System.out.print(Colors.GREEN_BOLD + "\n\nPlease enter your choice : " + Colors.RESET);

        try {
            a = scan.nextInt();
            System.out.print("\nEnter Username : ");
            username = scan.next();
            System.out.print("Enter Password : ");
            password = scan.next();

            switch (a) {
                case 1:
                    CEO ceo = login.checkCeo(username, password); // Login verification for CEO
                    if (ceo == null) {
                        System.out.println(Colors.RED_BOLD + "\n\nWrong usernme or password. Try again!" + Colors.RESET);
                        Display();
                    }

                    while (ceo != null) {
                        System.out.print(Colors.GREEN + "\n----------Welcome " + username + "----------" + Colors.RESET);
                        System.out.println("""
                                           \n1)Press to add manager or employee
                                           2)Press to remove manager or employee
                                           3)Press to view Manager Requests
                                           4)Press to grant report manager
                                           5)Press to view list of managers
                                           6)Press to view list of employees
                                           7)Press to view all used leaves and statistics
                                           8)Press to logout""");

                        System.out.print(Colors.GREEN_BOLD + "\n\nPlease enter your choice : " + Colors.RESET);
                        a = scan.nextInt();

                        switch (a) {

                            case 1:
                                System.out.println("\n\nPress 1 to add Manager || Press 2 to add Employee\n");
                                System.out.print(Colors.GREEN_BOLD + "\n\nPlease enter your choice : " + Colors.RESET);

                                a = scan.nextInt();

                                switch (a) {
                                    case 1 -> { // Creates Manager
                                        System.out.print("\nPlease write your manager's name :  ");
                                        String firstName = scan.nextLine();
                                        firstName = scan.nextLine();

                                        System.out.print("Please write your manager's first last name : ");
                                        String lastName = scan.nextLine();

                                        String ManagerUsername = firstName + lastName;

                                        System.out.print("Please enter your manager's total annual leave entitlement : ");
                                        int totalDayLeaveValue = scan.nextInt();

                                        ceo.createManager(firstName, lastName, ManagerUsername, totalDayLeaveValue);
                                        Company.users.get(Company.users.size() - 1);
                                        System.out.println("\n\nYour manager's username is " + '"' + Colors.GREEN_BOLD + ManagerUsername + Colors.RESET + '"'
                                                + "\t\tYour manager's password is " + '"' + Colors.GREEN_BOLD + Company.users.get(Company.users.size() - 1).getPassword() + Colors.RESET + '"'
                                                + Colors.GREEN_BOLD + "\n\n!!!!!Please forward this informations to your manager so that he/she can log into the system!!!!!!"
                                                + Colors.RESET);

                                        continue;
                                    }
                                    case 2 -> { //Creates Employee

                                        int managerCount = 0;
                                        for (Users user : Company.users) {
                                            if (user instanceof Manager) {
                                                managerCount++;
                                            }
                                        }

                                        if (managerCount != 0) {
                                            System.out.print("\n\nPlease write your employee's name :  ");
                                            String firstName = scan.nextLine();
                                            firstName = scan.nextLine();

                                            System.out.print("Please write your employee's last name : ");
                                            String lastName = scan.nextLine();

                                            String EmployeeUsername = firstName + lastName;

                                            System.out.print("Please enter your employee's total annual leave entitlement : ");
                                            int totalDayLeaveValue = scan.nextInt();

                                            ceo.viewManagerWithoutPassword();

                                            System.out.print("\n\nSelect the manager who will be responsible for the employee : ");
                                            a = scan.nextInt();

                                            Manager manager = null;
                                            ArrayList<Manager> managers = new ArrayList();
                                            for (Users user : Company.users) {

                                                if (user instanceof Manager) {
                                                    managers.add((Manager) user);

                                                }

                                            }
                                            manager = managers.get(a - 1);

                                            Company.createEmployee(firstName, lastName, EmployeeUsername, manager, totalDayLeaveValue);
                                            System.out.println("\n\nYour employee's username is " + '"' + Colors.GREEN_BOLD + EmployeeUsername + Colors.RESET + '"'
                                                    + "\t\tYour employee's password is " + '"' + Colors.GREEN_BOLD + Company.users.get(Company.users.size() - 1).getPassword() + Colors.RESET + '"'
                                                    + Colors.GREEN_BOLD + "\n\n!!!!!Please forward this informations to your employee so that he/she can log into the system!!!!!"
                                                    + Colors.RESET);
                                            continue;
                                        } else {
                                            System.out.println(Colors.RED_BOLD + "\n\nEmployee cannot be added without a manager." + Colors.RESET);
                                            continue;
                                        }
                                    }

                                    default -> {
                                        System.out.println(Colors.RED_BOLD + "\n\nPlease enter valid choice" + Colors.RESET);
                                        continue;
                                    }
                                }
                            case 2:
                                System.out.println("\n\nPress 1 to remove Manager || Press 2 to remove Employee\n");
                                System.out.print(Colors.RED_BOLD + "\n\nPlease enter your choice : " + Colors.RESET);

                                a = scan.nextInt();

                                switch (a) {
                                    case 1: //Remove Manager

                                        int managerCount = 0;
                                        for (Users user : Company.users) {
                                            if (user instanceof Manager) {
                                                managerCount++;
                                            }
                                        }

                                        if (managerCount != 0) {
                                            String b;
                                            ceo.viewManagerWithPassword();

                                            System.out.print("\n\nPlease enter the username of the manager you want to fire : ");
                                            String removeUsername = scan.nextLine();
                                            removeUsername = scan.nextLine();

                                            System.out.println(Colors.RED + "\nDo you really want to fire your manager " + removeUsername
                                                    + "? This action cannot be undone.\n\nIf you are sure, please write YES." + Colors.RESET
                                                    + Colors.GREEN_BOLD + "\nIf you made a mistake, please write NO." + Colors.RESET);

                                            System.out.print("\n\nYour choice : ");
                                            b = scan.nextLine();
                                            if (b.equalsIgnoreCase("YES")) {

                                                Manager manager = null;
                                                ArrayList<Manager> managers = new ArrayList();
                                                boolean isUsernameMatch = false;

                                                for (Users user : Company.users) {
                                                    if (user.getUsername().equalsIgnoreCase(removeUsername)) {
                                                        isUsernameMatch = true;
                                                        manager = (Manager) user;
                                                    }
                                                    if (user instanceof Manager) {
                                                        managers.add((Manager) user);
                                                    }
                                                }

                                                if (isUsernameMatch && managers.size() > 1) {
                                                    ceo.removeManager(removeUsername);
                                                    System.out.println(Colors.RED_BOLD + "\n\nYou fired a manager, if there are employees under this manager, "
                                                            + "these employees are left without a manager. Please select the manager you want to assign these employees to. "
                                                            + Colors.RESET);
                                                    System.out.println(Colors.GREEN + "If there is no employee under this manager, please ignore this warning." + Colors.RESET);
                                                    for (Employee employee : manager.getWhoIsResponsibleEmployees()) {

                                                        ceo.viewManagerWithoutPassword();
                                                        System.out.println("\n\nName and surname : " + employee.getFirstName() + " " + employee.getLastName());
                                                        System.out.print(Colors.GREEN_BOLD + "\n\nSelect your choice : " + Colors.RESET);
                                                        a = scan.nextInt();
                                                        employee.setManager(managers.get(a));
                                                        managers.get(a).SetWhoIsResponsibleEmployees(employee);

                                                    }

                                                } else if (isUsernameMatch && managers.size() == 1) {
                                                    System.out.println(Colors.RED_BOLD
                                                            + "\n\nYou are deleting the only existing manager so employees under this manager will also be fired" + Colors.RESET);
                                                    System.out.println(Colors.RED_BOLD + "\nIf you still want to continue, write YES" + Colors.RESET);
                                                    System.out.println(Colors.GREEN_BOLD + "If you made a mistake, please write NO" + Colors.RESET);

                                                    System.out.print("\n\nYour choice : ");
                                                    b = scan.nextLine();

                                                    if (b.equalsIgnoreCase("YES")) {

                                                        Company.users.subList(1, Company.users.size()).clear();
                                                        System.out.println(Colors.GREEN_BOLD + "\n\nAction successful, you are redirected to the menu..." + Colors.RESET);

                                                        continue;
                                                    } else if (b.equalsIgnoreCase("NO")) {
                                                        System.out.println(Colors.GREEN_BOLD + "\n\nAction cancelled, you are redirected to the menu..." + Colors.RESET);
                                                        continue;
                                                    } else {
                                                        System.out.println(Colors.RED_BOLD + "\n\nPlease enter valid choice" + Colors.RESET);
                                                        continue;
                                                    }

                                                } else if (managers.size() == 0) {
                                                    System.out.println(Colors.RED + "\n\nYou don't have any managers." + Colors.RESET);

                                                }

                                            }
                                        }
                                        continue;

                                    case 2: //Remove Employee

                                        int employeeCount = 0;
                                        for (Users user : Company.users) {
                                            if (user instanceof Employee) {
                                                employeeCount++;
                                            }
                                        }
                                        if (employeeCount != 0) {
                                            String b;
                                            ceo.viewEmployeeWithPassword();

                                            System.out.print("\n\nPlease enter the username of the employee you want to fire : ");
                                            String removeUsername = scan.nextLine();
                                            removeUsername = scan.nextLine();

                                            System.out.println(Colors.RED + "\nDo you really want to fire your employee " + removeUsername
                                                    + "? This action cannot be undone.\n\nIf you are sure, please write YES." + Colors.RESET
                                                    + Colors.GREEN_BOLD + "\nIf you made a mistake, please write NO." + Colors.RESET);

                                            System.out.print("\n\nYour choice : ");
                                            b = scan.nextLine();
                                            if (b.equalsIgnoreCase("YES")) {
                                                ceo.removeEmployee(removeUsername);
                                                System.out.println(Colors.GREEN_BOLD + "\n\nAction successful, you are redirected to the menu..." + Colors.RESET);
                                                continue;
                                            } else if (b.equalsIgnoreCase("NO")) {
                                                System.out.println(Colors.GREEN_BOLD + "\n\nAction cancelled, you are redirected to the menu..." + Colors.RESET);
                                                continue;
                                            } else {
                                                System.out.println(Colors.RED_BOLD + "\n\nPlease enter valid choice" + Colors.RESET);
                                                continue;
                                            }

                                        } else {
                                            System.out.println(Colors.RED + "\n\nYou don't have any employees." + Colors.RESET);

                                        }

                                    default:
                                        System.out.println(Colors.RED_BOLD + "\n\nPlease enter valid choice" + Colors.RESET);
                                        continue;

                                }

                            case 3: // View leave request from manager
                                ceo.viewLeaveRequests();
                                continue;

                            case 4:

                                ceo.confirmationForManagerLeaves(ceo);
                                continue;

                            case 5: // View report of Manager
                                ceo.viewManagerWithPassword();

                                continue;

                            case 6: // View report of Employee
                                ceo.viewEmployeeWithPassword();

                                continue;
                            case 7:
                                System.out.print(Colors.BLUE_BOLD + "Total number of leaves used in the company : " + Colors.RESET + ceo.SummationOfAllLeaves());
                                System.out.println(Colors.BLUE_BOLD + "\n\n-------------Statistics-------------" + Colors.RESET);
                                ceo.viewLeaveRequestStatistics();

                                continue;

                            case 8: // Display is called for logout
                                Display();
                            default:
                            //continue;
                        }

                    }

                case 2: // Login verification for manager

                    Manager manager = login.checkManager(username, password);

                    if (manager != null) {
                        System.out.println(Colors.GREEN + "\nLogin successful you are redirected to the Manager screen..." + Colors.RESET);

                    } else {
                        System.out.println(Colors.RED_BOLD + "\n\nWrong usernme or password. Try again!" + Colors.RESET);
                        Display();

                    }

                    while (manager != null) {
                        int c = 0;
                        int request = 0;
                        int leaveReason = 0;
                        System.out.print(Colors.GREEN + "\n----------Welcome " + username + "----------" + Colors.RESET);
                        System.out.println("\n1)View Leaves \n2)Apply for leave \n3)View Report \n4)View Employee Requests \n5)Grant \n6)Logout");
                        System.out.print(Colors.GREEN_BOLD + "\n\nPlease enter your choice : " + Colors.RESET);
                        c = scan.nextInt();
                        switch (c) {
                            case 1 ->
                                manager.viewleave();
                            case 2 -> {
                                System.out.println(Colors.GREEN_BOLD + "\nYour total leaves are " + manager.getNumberOfLeavesLeft() + " days" + Colors.RESET);
                                System.out.print(Colors.BLUE_BOLD + "\n\nHow many leaves do you want : " + Colors.RESET);
                                request = scan.nextInt();

                                System.out.print(Colors.BLUE_BOLD + "\nFor what reason do you want leave?" + Colors.RESET);
                                System.out.println("\n\n1)DEATH" + "\n2)ILLNESS" + "\n3)MARRIAGE" + "\n4)OTHER");
                                System.out.print(Colors.GREEN + "\nYour Choice:" + Colors.RESET);
                                leaveReason = scan.nextInt();
                                manager.requestLeave(request, leaveReason);
                            }
                            case 3 -> // View his/her Employee's leave report
                                manager.viewEmployee(manager);
                            case 4 -> // View request of his/her allocated employee
                                manager.viewLeaveRequests();
                            case 5 ->
                                manager.confirmationForEmployeeLeaves(manager);
                            case 6 ->
                                Display();
                            default -> {
                            }

                        }

                    }
                case 3:
                    //Login verification for employee

                    Employee emp = login.checkEmployee(username, password);

                    if (emp != null) {
                        System.out.println(Colors.GREEN + "\nLogin successful you are redirected to the Employee screen..." + Colors.RESET);

                        while (true) {
                            int b = 0;
                            int request = 0;
                            int leaveReason = 0;
                            System.out.print(Colors.GREEN + "\n----------Welcome " + username + "----------" + Colors.RESET);
                            System.out.println("\n1)View  Leaves" + "\n" + "2)Apply for leave " + "\n" + "3)Logout");

                            System.out.print(Colors.GREEN_BOLD + "\n\nPlease enter your choice : " + Colors.RESET);

                            b = scan.nextInt();
                            switch (b) {

                                case 1 -> // Viewing his/her available leave
                                    emp.viewleave();

                                case 2 -> {
                                    // Requesting leave
                                    System.out.println(Colors.GREEN_BOLD + "\nYour total leaves are: " + emp.getNumberOfLeavesLeft() + Colors.RESET);
                                    System.out.print("\n\nHow many leaves do you want : ");
                                    request = scan.nextInt();
                                    System.out.print(Colors.BLUE_BOLD + "\nFor what reason do you want leave?" + Colors.RESET);
                                    System.out.println("\n\n1)DEATH" + "\n2)ILLNESS" + "\n3)MARRIAGE" + "\n4)OTHER");
                                    System.out.print(Colors.GREEN + "\nYour Choice:" + Colors.RESET);
                                    leaveReason = scan.nextInt();
                                    emp.requestLeave(request, leaveReason);

                                }
                                case 3 -> //Logout
                                    Display();
                                default -> {
                                }

                            }
                        }

                    } else {

                        System.out.println(Colors.RED_BOLD + "\n\nWrong usernme or password. Try again!" + Colors.RESET);
                        Display();
                    }
                default:
                    System.out.println(Colors.RED_BOLD + "Enter valid number " + Colors.RESET);
                    Display();

            }

        } catch (Exception e) {
            System.out.println(Colors.RED_BOLD + "Execption Occurred" + Colors.RESET);

            Display();

        }
        scan.close();
    }
}
