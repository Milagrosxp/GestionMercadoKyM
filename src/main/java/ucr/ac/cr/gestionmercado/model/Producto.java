/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucr.ac.cr.gestionmercado.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author marce
 */

@Entity
public class Producto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int codeProduct;
    @Basic
    private String name;
    private String category;
    private boolean isPromotion;
    private double discount;
    private int units;
    @ManyToOne
    private Distributor distributor;
    @ManyToOne
    private Compra compra;

    public Producto(int codeProduct, String name, String category, boolean isPromotion, double discount, int units, Distributor distributor, Compra compra) {
        this.codeProduct = codeProduct;
        this.name = name;
        this.category = category;
        this.isPromotion = isPromotion;
        this.discount = discount;
        this.units = units;
        this.distributor = distributor;
        this.compra = compra;
    }

   
    
    public Producto(int codeProduct, String name, String category, boolean isPromotion, double discount, int units) {
        this.codeProduct = codeProduct;
        this.name = name;
        this.category = category;
        this.isPromotion = isPromotion;
        this.discount = discount;
        this.units = units;
    }
    

    public Producto() {
    }

    public int getCodeProduct() {
        return codeProduct;
    }

    public void setCodeProduct(int codeProduct) {
        this.codeProduct = codeProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isIsPromotion() {
        return isPromotion;
    }

    public void setIsPromotion(boolean isPromotion) {
        this.isPromotion = isPromotion;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public Distributor getDistributor() {
        return distributor;
    }

    public void setDistributor(Distributor distributor) {
        this.distributor = distributor;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }
}
