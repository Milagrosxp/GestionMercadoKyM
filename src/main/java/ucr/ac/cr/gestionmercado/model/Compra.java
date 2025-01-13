/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucr.ac.cr.gestionmercado.model;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author marce
 */
@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int invoiceNumber;
      @Basic
    private double subTotal;
    private double total;
    private double discount;
    private Date InvoiceDate;

    public Compra(int invoiceNumber, double subTotal, double total, double discount, Date InvoiceDate) {
        this.invoiceNumber = invoiceNumber;
        this.subTotal = subTotal;
        this.total = total;
        this.discount = discount;
        this.InvoiceDate = InvoiceDate;
    }

    public Compra() {
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Date getInvoiceDate() {
        return InvoiceDate;
    }

    public void setInvoiceDate(Date InvoiceDate) {
        this.InvoiceDate = InvoiceDate;
    }
    
    
}
