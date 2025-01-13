/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucr.ac.cr.gestionmercado.model;

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
public class Distributor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String idDistributor;
    @Basic
    private String name;
    private String telephoneNumber;
    private String email;
}
