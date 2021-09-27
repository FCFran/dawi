package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Usuario;

public class Demo09 {

	public static void main(String[] args) {
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		
		
		//proceso --> validar usando Usuario y clave: login sin procedimiento almacenado
		String sql = "{call usp_validaAcceso(?,?)}";

		// prepara la consulta

		Query query = em.createNativeQuery(sql, Usuario.class);

		// setea los parámetros

		query.setParameter(1, "U001@gmail.com");
		query.setParameter(2, "10001");

		// ejecuta la consulta y obtiene el resultado 

		Usuario u = (Usuario) query.getSingleResult();

		if(u==null) {
			System.out.println("USUARIO NO EXISTE");
		}else
			System.out.println("Bienvenido \n" +u.getNombre());
		
		
	em.close();
		
	}
}
