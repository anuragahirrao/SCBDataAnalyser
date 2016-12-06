/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scbdataanalyser.data;

import scbdataanalyser.entities.Record;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author anuragahirrao
 */
public class MasterReservationTable {

    private final Map<String, Record> masterData;
    private static MasterReservationTable master = null;

    private MasterReservationTable() {
        masterData = new HashMap<>();
    }

    public static MasterReservationTable getMaster() {
        if (master == null) {
            master = new MasterReservationTable();
            return master;
        } else {
            return master;
        }
    }

    public Map<String, Record> getData() {
        return masterData;
    }

}
