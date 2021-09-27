package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo05 {

public static void main(String[] args) {
		
		//Similar a DAOFactory
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		
		//Similar a crear el objeto DAO
		EntityManager em = fabrica.createEntityManager();
		
		//proceso --> consultar los datos de un Usuario
		
		Usuario u = em.find(Usuario.class, 120);
		
		
		if(u == null) {
			System.out.println("Usuario No Existe");
		}else {
			
			System.out.println("Usuario existe \n" + u);
			em.getTransaction().begin();
			em.remove(u);  //<--- Eliminar
			em.getTransaction().commit();
			System.out.println("Usuario Eliminado");
		}
			
		em.close();
	}
	
	
}
