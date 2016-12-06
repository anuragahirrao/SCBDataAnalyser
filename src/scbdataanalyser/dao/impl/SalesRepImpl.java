/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scbdataanalyser.dao.impl;

import scbdataanalyser.dao.SalesRepDao;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import scbdataanalyser.entities.user.Employee;
import scbdataanalyser.data.MasterReservationTable;
import scbdataanalyser.data.CustomersTable;
import scbdataanalyser.entities.Record;

/**
 *
 * @author anuragahirrao
 */
public class SalesRepImpl extends Employee implements SalesRepDao {

    private static final String reportPath = "/Users/anuragahirrao/Desktop/";
    private static final String reportFormat = ".pdf";
    private static final String lineSeparator = "-------------------------------";

    @Override
    public void printInvoice(String customerName) {
        Document document = new Document();
        PdfWriter writer = getPDFWriter("CustomerInvoice", document);
        if (writer != null) {
            document.open();
            addRecordToDocument(customerName, document);
            document.close();
            writer.close();
        }
        System.out.println("Invoice generated for " + customerName + " at " + reportPath);
    }

    @Override
    public void printInvoice() {
        Document document = new Document();
        PdfWriter writer = getPDFWriter("TotalInvoiceData.pdf", document);

        if (writer != null) {
            document.open();
            for (String name : MasterReservationTable.getMaster().getData().keySet()) {
                addRecordToDocument(name, document);
            }
            document.close();
            writer.close();
        }
        System.out.println("Invoice generated for full System at " + reportPath);
    }

    private void addRecordToDocument(String customerName, Document document) {
        Record record = MasterReservationTable.getMaster().getData().get(customerName);
        try {
            document.add(new Paragraph(record.getCustomer().getName() + " "
                    + record.getDate() + " "
                    + record.getTime() + " "
                    + record.getTotal()));
            document.add(new Paragraph(lineSeparator));
        } catch (DocumentException e) {
            //logger Unable to add to document
        }
    }

    private PdfWriter getPDFWriter(String outputFileName, Document document) {
        StringBuilder builder = new StringBuilder();
        String reportAbsolutePath = builder.append(reportPath)
                .append(outputFileName)
                .append(reportFormat)
                .toString();

        PdfWriter writer = null;
        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream(reportAbsolutePath));
        } catch (DocumentException | FileNotFoundException e) {
            //logger
        }
        return writer;
    }

    @Override
    public void readInvoiceLog() {

        JSONParser parser = new JSONParser();
        try {

            JSONArray fileArray = (JSONArray) parser.parse(new FileReader("/Users/anuragahirrao/Desktop/MasterRecordTable.json"));

            for (Object object : fileArray) {
                JSONObject jsonObject = (JSONObject) object;
                int recordNum = (Integer.valueOf(jsonObject.get(Record.RecordParams.RecordNumber.toString()).toString()));
                String customerName = (String) jsonObject.get(Record.RecordParams.Customer.toString());
                String car = (String) jsonObject.get(Record.RecordParams.Car.toString());
                String type = (String) jsonObject.get(Record.RecordParams.Type.toString());
                String date = (String) jsonObject.get(Record.RecordParams.Date.toString());
                String time = (String) jsonObject.get(Record.RecordParams.Time.toString());
                String comments = (String) jsonObject.get(Record.RecordParams.Comments.toString());
                int total = (Integer.valueOf(jsonObject.get(Record.RecordParams.Total.toString()).toString()));

                Record record = new Record(recordNum, customerName, car, type, date, time, comments, total);
                CustomerImpl cust = new CustomerImpl(customerName);
                CustomersTable.getTable().getData().put(customerName, cust);
                MasterReservationTable.getMaster().getData().put(customerName, record);
            }
            System.out.println("Read Invoices Successfully");
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (ParseException ex) {
            Logger.getLogger(SalesRepImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Operations() {
        int ch = Integer.MAX_VALUE;
        Scanner scanner = new Scanner(System.in);
        while (ch != 0) {
            System.out.println("1.Generate pdf Invoice for particular customer");
            System.out.println("2.Generate full Invoice List pdf");
            System.out.println("3.Read Invoice Log from WebApp DataSet");
            System.out.println("4.Exit");
            ch = scanner.nextInt();
            switch (ch) {
                case 1:
                    System.out.print("Please enter customer name :");
                    String chosenName = scanner.next();
                    printInvoice(chosenName);
                    break;
                case 2:
                    printInvoice();
                    break;
                case 3:
                    readInvoiceLog();
                    break;
                case 4:
                    ch = 0;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
