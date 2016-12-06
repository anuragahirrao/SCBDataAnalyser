/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scbdataanalyser.data;

import java.util.HashMap;
import java.util.Map;
import scbdataanalyser.dao.impl.SalesRepImpl;

/**
 *
 * @author anuragahirrao
 */
public class SalesRepsTable {

    private final Map<String, SalesRepImpl> salesReps;
    private static SalesRepsTable salesRepTable = null;

    private SalesRepsTable() {
        salesReps = new HashMap<>();
    }

    public static SalesRepsTable getTable() {
        if (salesRepTable == null) {
            salesRepTable = new SalesRepsTable();
            return salesRepTable;
        } else {
            return salesRepTable;
        }
    }

    public Map<String, SalesRepImpl> getData() {
        return salesReps;
    }
}
