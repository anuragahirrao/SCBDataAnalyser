/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scbdataanalyser.dao.impl;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;
import scbdataanalyser.dao.CustomerDao;
import scbdataanalyser.data.MasterReservationTable;
import scbdataanalyser.entities.Record;
import scbdataanalyser.entities.user.Person;

/**
 *
 * @author anuragahirrao
 */
public class CustomerImpl extends Person implements CustomerDao {

    public CustomerImpl(String userName) {
        this.setName(userName);
        this.setPassword(userName);
    }

    public CustomerImpl() {

    }

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

    public void Operations(String userName) {
        Scanner scanner = new Scanner(System.in);
        int ch = Integer.MAX_VALUE;

        while (ch != 0) {
            System.out.println("1.Check your invoice");
            System.out.println("2.Exit");
            ch = scanner.nextInt();
            switch (ch) {
                case 1:
                    printInvoice(userName);
                    // Statements
                    break; // optional
                case 2:
                    ch = 0;
                    // Statements
                    break; // optional
                // You can have any number of case statements.
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
