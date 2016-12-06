/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scbdataanalyser.data;

import java.util.HashMap;
import java.util.Map;
import scbdataanalyser.dao.impl.CustomerImpl;

/**
 *
 * @author anuragahirrao
 */
public class CustomersTable {

    private final Map<String, CustomerImpl> customers;
    private static CustomersTable custTable = null;

    private CustomersTable() {
        customers = new HashMap<>();
    }

    public static CustomersTable getTable() {
        if (custTable == null) {
            custTable = new CustomersTable();
            return custTable;
        } else {
            return custTable;
        }
    }

    public Map<String, CustomerImpl> getData() {
        return customers;
    }
}
