package GesMED.bsi.Repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import GesMED.bsi.Entidades.Conta;

public class ContaRepositorio {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public ContaRepositorio() {
		emf = Persistence.createEntityManagerFactory("GesmedJPA");
		em = emf.createEntityManager();
	}
	
	public void adicionar(Conta conta){
		em.getTransaction().begin();
		em.merge(conta);
		em.getTransaction().commit();
	}
	
	public Conta recuperar(int id){
		return em.find(Conta.class, id);
	}
	
	public void atualizar(Conta conta){
		em.getTransaction().begin();
		em.merge(conta);
		em.getTransaction().commit();
	}
	
	public void remover(Conta conta){
		em.getTransaction().begin();
		em.remove(conta);
		em.getTransaction().commit();
	}	
	
	@SuppressWarnings("unchecked")
	public List<Conta> recuperarTodasContas(){
		Query query = em.createQuery("SELECT c FROM Conta c");
		return query.getResultList();
	}
	
	public void encerrar(){
		em.close();
		emf.close();
	}
	

}
