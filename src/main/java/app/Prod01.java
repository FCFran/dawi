package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Producto;

public class Prod01 {
	
	public static void main(String[] args) {
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		Producto p = new Producto();
		
		p.setIdprod("P0040");
		p.setDescripcion("Prueba 40");
		p.setStock(100);
		p.setPrecio(0.90);
		p.setIdcategoria(1);
		p.setEstado(1);
		
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		em.close();
		
		
		
	}

}
