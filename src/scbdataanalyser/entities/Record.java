/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scbdataanalyser.entities;

import scbdataanalyser.entities.product.Vehicle;
import scbdataanalyser.dao.impl.CustomerImpl;

/**
 *
 * @author anuragahirrao
 */
public class Record {

    Integer recordNumber;
    CustomerImpl customer;
    Vehicle vehicle;
    String type;
    String date;
    String time;
    String comments;
    int total;

    public Record(int recordNum, String customerName, String car, String type, String date, String time, String comments, int total) {
        this.recordNumber = recordNum;
        this.customer = new CustomerImpl(customerName);
        this.customer.setName(customerName);
        this.vehicle = new Vehicle();
        this.vehicle.setName(car);
        this.type = type;
        this.date = date;
        this.time = time;
        this.comments = comments;
        this.total = total;

    }

    public enum RecordParams {

        RecordNumber, Customer, Car, Type, Date, Time, Comments, Total;
    }

    public Integer getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(Integer recordNumber) {
        this.recordNumber = recordNumber;
    }

    public CustomerImpl getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerImpl customer) {
        this.customer = customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
