package scbdataanalyser;

import java.util.Scanner;
import scbdataanalyser.dao.impl.SalesRepImpl;
import scbdataanalyser.data.CustomersTable;
import scbdataanalyser.data.SalesRepsTable;
import scbdataanalyser.entities.user.Admin;
import scbdataanalyser.dao.impl.CustomerImpl;

/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 *
 * @author anuragahirrao
 */
public class SCBDataAnalyser {

    /**
     * @param args the command line arguments
     * @throws org.json.simple.parser.ParseException
     */
    public static void main(String[] args) throws org.json.simple.parser.ParseException {

        Admin admin = new Admin();
        SCBDataAnalyser.Operations(admin);

    }

    public static void Operations(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        String choice = "";
        CustomerImpl customer = null;
        SalesRepImpl salesRep = null;
        while (!choice.equals("Exit")) {

            System.out.print("Please enter your role (Admin/SalesRep/Customer): ");
            String selectedRole = scanner.next();
            System.out.print("Please Enter Username : ");
            String userName = scanner.next();
            System.out.print("Please Enter Password : ");
            String password = scanner.next();

            if (selectedRole.equalsIgnoreCase("Customer")) {
                customer = customerAuthorization(userName, password);
                if (customer != null) {
                    choice = "Customer";
                }
            } else if (selectedRole.equalsIgnoreCase("SalesRep")) {
                salesRep = salesRepAuthorization(userName, password);
                if (salesRep != null) {
                    choice = "SalesRep";
                }
            } else if (selectedRole.equalsIgnoreCase("Admin")) {
                if (adminAuthorization(userName, password)) {
                    choice = "Admin";
                }
            }

            switch (choice) {
                case "Admin":
                    Admin.Operations();
                    // Statements
                    break; // optional

                case "Customer":
                    customer.Operations(userName);

                    break; // optional

                case "SalesRep":
                    salesRep.Operations();
                    // Statements
                    break; // optional
                // You can have any number of case statements.
                default:
                // Statements
            }
        }
        scanner.close();
    }

    public static CustomerImpl customerAuthorization(String userName, String password) {
        CustomerImpl customer = null;
        if (CustomersTable.getTable().getData().containsKey(userName)) {
            if (CustomersTable.getTable().getData().get(userName).getPassword().equals(password)) {
                customer = CustomersTable.getTable().getData().get(userName);
                System.out.println("Customer retrieved " + customer.getName() + " " + customer.getPassword());
            } else {
                System.out.println("Wrong username or password");
            }
        } else {
            System.out.println("Username does not exist");
        }
        return customer;
    }

    public static SalesRepImpl salesRepAuthorization(String userName, String password) {
        SalesRepImpl salesRep = null;
        if (SalesRepsTable.getTable().getData().containsKey(userName)) {
            if (SalesRepsTable.getTable().getData().get(userName).getPassword().equals(password)) {
                salesRep = SalesRepsTable.getTable().getData().get(userName);
                System.out.println("SalesRep retrieved " + salesRep.getName() + " " + salesRep.getPassword());
            } else {
                System.out.println("Wrong username or password");
            }
        } else {
            System.out.println("Username does not exist");
        }
        return salesRep;
    }

    public static boolean adminAuthorization(String userName, String password) {
        boolean isAdmin = false;
        if (userName.equals("Admin") && password.equals("Admin")) {
            isAdmin = true;
            return isAdmin;
        }
        return isAdmin;
    }

}
