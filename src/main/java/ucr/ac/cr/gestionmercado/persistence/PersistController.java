/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucr.ac.cr.gestionmercado.persistence;

import ucr.ac.cr.gestionmercado.model.Distributor;
import ucr.ac.cr.gestionmercado.model.User;

public class PersistController {
    UserJpaController userJpa = new UserJpaController();
    ClienteJpaController clienteJpa = new ClienteJpaController();
    CompraJpaController compraJpa = new CompraJpaController();
    DistributorJpaController distributorJpa = new DistributorJpaController();
    EmployeeJpaController employeeJpa = new EmployeeJpaController();
    ProductoJpaController productoJpa = new ProductoJpaController();
    
    public void crearUsuario(User usuario){
        userJpa.create(usuario);
    }
    
    public void crearDistributor(Distributor distributor){
        distributorJpa.create(distributor);
    }
   
       
}
