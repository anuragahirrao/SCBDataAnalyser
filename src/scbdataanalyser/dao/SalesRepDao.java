/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scbdataanalyser.dao;

/**
 *
 * @author anuragahirrao
 */
public interface SalesRepDao {

    public void printInvoice(String customerName);

    public void printInvoice();

    public void readInvoiceLog();
}
