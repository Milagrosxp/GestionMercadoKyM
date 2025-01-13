/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucr.ac.cr.gestionmercado.persistence;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ucr.ac.cr.gestionmercado.model.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import ucr.ac.cr.gestionmercado.model.Distributor;
import ucr.ac.cr.gestionmercado.persistence.exceptions.NonexistentEntityException;

/**
 *
 * @author elkev
 */
public class DistributorJpaController implements Serializable {

    public DistributorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public DistributorJpaController(){
        emf = Persistence.createEntityManagerFactory("GestionMercadoPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Distributor distributor) {
        if (distributor.getListaProductos() == null) {
            distributor.setListaProductos(new ArrayList<Producto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArrayList<Producto> attachedListaProductos = new ArrayList<Producto>();
            for (Producto listaProductosProductoToAttach : distributor.getListaProductos()) {
                listaProductosProductoToAttach = em.getReference(listaProductosProductoToAttach.getClass(), listaProductosProductoToAttach.getCodeProduct());
                attachedListaProductos.add(listaProductosProductoToAttach);
            }
            distributor.setListaProductos(attachedListaProductos);
            em.persist(distributor);
            for (Producto listaProductosProducto : distributor.getListaProductos()) {
                Distributor oldDistributorOfListaProductosProducto = listaProductosProducto.getDistributor();
                listaProductosProducto.setDistributor(distributor);
                listaProductosProducto = em.merge(listaProductosProducto);
                if (oldDistributorOfListaProductosProducto != null) {
                    oldDistributorOfListaProductosProducto.getListaProductos().remove(listaProductosProducto);
                    oldDistributorOfListaProductosProducto = em.merge(oldDistributorOfListaProductosProducto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Distributor distributor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Distributor persistentDistributor = em.find(Distributor.class, distributor.getIdDistributor());
            ArrayList<Producto> listaProductosOld = persistentDistributor.getListaProductos();
            ArrayList<Producto> listaProductosNew = distributor.getListaProductos();
            ArrayList<Producto> attachedListaProductosNew = new ArrayList<Producto>();
            for (Producto listaProductosNewProductoToAttach : listaProductosNew) {
                listaProductosNewProductoToAttach = em.getReference(listaProductosNewProductoToAttach.getClass(), listaProductosNewProductoToAttach.getCodeProduct());
                attachedListaProductosNew.add(listaProductosNewProductoToAttach);
            }
            listaProductosNew = attachedListaProductosNew;
            distributor.setListaProductos(listaProductosNew);
            distributor = em.merge(distributor);
            for (Producto listaProductosOldProducto : listaProductosOld) {
                if (!listaProductosNew.contains(listaProductosOldProducto)) {
                    listaProductosOldProducto.setDistributor(null);
                    listaProductosOldProducto = em.merge(listaProductosOldProducto);
                }
            }
            for (Producto listaProductosNewProducto : listaProductosNew) {
                if (!listaProductosOld.contains(listaProductosNewProducto)) {
                    Distributor oldDistributorOfListaProductosNewProducto = listaProductosNewProducto.getDistributor();
                    listaProductosNewProducto.setDistributor(distributor);
                    listaProductosNewProducto = em.merge(listaProductosNewProducto);
                    if (oldDistributorOfListaProductosNewProducto != null && !oldDistributorOfListaProductosNewProducto.equals(distributor)) {
                        oldDistributorOfListaProductosNewProducto.getListaProductos().remove(listaProductosNewProducto);
                        oldDistributorOfListaProductosNewProducto = em.merge(oldDistributorOfListaProductosNewProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = distributor.getIdDistributor();
                if (findDistributor(id) == null) {
                    throw new NonexistentEntityException("The distributor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Distributor distributor;
            try {
                distributor = em.getReference(Distributor.class, id);
                distributor.getIdDistributor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The distributor with id " + id + " no longer exists.", enfe);
            }
            ArrayList<Producto> listaProductos = distributor.getListaProductos();
            for (Producto listaProductosProducto : listaProductos) {
                listaProductosProducto.setDistributor(null);
                listaProductosProducto = em.merge(listaProductosProducto);
            }
            em.remove(distributor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Distributor> findDistributorEntities() {
        return findDistributorEntities(true, -1, -1);
    }

    public List<Distributor> findDistributorEntities(int maxResults, int firstResult) {
        return findDistributorEntities(false, maxResults, firstResult);
    }

    private List<Distributor> findDistributorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Distributor.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Distributor findDistributor(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Distributor.class, id);
        } finally {
            em.close();
        }
    }

    public int getDistributorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Distributor> rt = cq.from(Distributor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
