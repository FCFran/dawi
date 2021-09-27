package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.mysql.cj.x.protobuf.MysqlxCrud.Find;

import model.Usuario;



public class Practica {
	
	public static void main(String[] args) {
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		
		
		//proceso --> validar usando Usuario y clave: login sin procedimiento almacenado
		String sql = "SELECT u FROM Usuario u where u.usuario= :xusr and u.clave = :xcla";

		// prepara la consulta

		TypedQuery<Usuario> query = em.createQuery(sql, Usuario.class);

		// setea los parámetros

		query.setParameter("xusr", "U001@gmail.com");
		query.setParameter("xcla", "10001");

		// ejecuta la consulta y obtiene el resultado 

		Usuario u = query.getSingleResult();

		if(u==null) {
			System.out.println("USUARIO NO EXISTE");
		}else
			System.out.println("Bienvenido \n" +u.getNombre());
		
		
	em.close();
		
		
	}
	

}
