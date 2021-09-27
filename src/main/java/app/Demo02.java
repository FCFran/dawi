package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo02 {

	
public static void main(String[] args) {
		
		//Similar a DAOFactory
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		
		//Similar a crear el objeto DAO
		EntityManager em = fabrica.createEntityManager();
		
		//proceso --> Actualizar un nuevo objeto
		
		Usuario u = new Usuario();
		u.setCodigo(120);
		u.setNombre("Mylan");
		u.setApellido("Lujan");
		u.setUsuario("takae@titan.pe");
		u.setClave("1234");
		u.setFnacim("2021/08/24");
		u.setTipo(1);
		u.setEstado(1);
		
		//
		
		em.getTransaction().begin();
		em.merge(u);  //<--- Actualiza si existe el codigo / sino los registra como nuevo
		em.getTransaction().commit();
		System.out.println("Usuario Actualizado");
		em.close();
	}

}
