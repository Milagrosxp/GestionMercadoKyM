/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import ucr.ac.cr.gestionmercado.model.Distributor;
import ucr.ac.cr.gestionmercado.model.User;
import ucr.ac.cr.gestionmercado.persistence.PersistController;


public class Controlador {
    PersistController persist = new PersistController();
    
    public void crearUsuario(User usuario){
        persist.crearUsuario(usuario);
    }
    
    public void crearDistributor(Distributor distributor){
        persist.crearDistributor(distributor);
    }
}
