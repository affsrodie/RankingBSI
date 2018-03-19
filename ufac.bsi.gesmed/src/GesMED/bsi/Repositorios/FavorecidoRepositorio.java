package GesMED.bsi.Repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import GesMED.bsi.Entidades.Favorecido;

public class FavorecidoRepositorio {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public FavorecidoRepositorio() {
		emf = Persistence.createEntityManagerFactory("GesmedJPA");
		em = emf.createEntityManager();
	}
	
	public void adicionar(Favorecido favorecido){
		em.getTransaction().begin();
		em.persist(favorecido);
		em.getTransaction().commit();
	}
	
	public Favorecido recuperar(int id){
		return em.find(Favorecido.class, id);
	}
	
	public void atualizar(Favorecido favorecido){
		em.getTransaction().begin();
		em.merge(favorecido);
		em.getTransaction().commit();
	}
	
	public void remover(Favorecido favorecido){
		em.getTransaction().begin();
		em.remove(favorecido);
		em.getTransaction().commit();
	}	
	
	@SuppressWarnings("unchecked")
	public List<Favorecido> recuperarTodasContas(){
		Query query = em.createQuery("SELECT f FROM Favorecido f");
		return query.getResultList();
	}
	
	public void encerrar(){
		em.close();
		emf.close();
	}
	

}
