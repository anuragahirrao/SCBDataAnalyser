/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scbdataanalyser.entities.user;

import java.util.Scanner;
import scbdataanalyser.dao.impl.SalesRepImpl;
import scbdataanalyser.data.SalesRepsTable;

/**
 *
 * @author anuragahirrao
 */
public class Admin extends Employee {

    public Admin() {
        this.setName("Admin");
        this.setPassword("Admin");
    }

    public static void Operations() {
        Scanner scanner = new Scanner(System.in);
        int ch = Integer.MAX_VALUE;

        while (ch != 0) {
            System.out.println("1.Add SalesRep");
            System.out.println("2.Exit");
            ch = scanner.nextInt();
            switch (ch) {
                case 1:
                    SalesRepImpl salesRep = new SalesRepImpl();
                    System.out.print("Enter Name for SalesRep : ");
                    String username = scanner.next();
                    System.out.print("Enter password for SalesRep : ");
                    String password = scanner.next();
                    salesRep.setName(username);
                    salesRep.setPassword(password);
                    SalesRepsTable.getTable().getData().put(username, salesRep);
                    System.out.println("SalesRep created successfully");
                    break;
                case 2:
                    ch = 0;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

}
