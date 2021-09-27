package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo03 {
	
public static void main(String[] args) {
		
		//Similar a DAOFactory
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		
		//Similar a crear el objeto DAO
		EntityManager em = fabrica.createEntityManager();
		
		//proceso --> Eliminar un nuevo objeto
		
		Usuario u = new Usuario();
		u.setCodigo(124);
		u.setNombre("Eren");
		u.setApellido("Lujan");
		u.setUsuario("tatakae@titan.pe");
		u.setClave("1234");
		u.setFnacim("2021/08/24");
		u.setTipo(1);
		u.setEstado(1);
		
		//
		
		em.getTransaction().begin();
		em.remove(u);  //<--- Eliminar
		em.getTransaction().commit();
		System.out.println("Usuario Eliminado");
		em.close();
	}
}
