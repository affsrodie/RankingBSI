package JFX.BSI.GesMed.Repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import JFX.BSI.GesMed.Entidades.PlanoSaude;

public class PlanoSaudeRepositorio {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public PlanoSaudeRepositorio() {
		emf = Persistence.createEntityManagerFactory("GesmedJPA");
		em = emf.createEntityManager();
	}
	
	public void adicionar(PlanoSaude ps){
		em.getTransaction().begin();
		em.persist(ps);
		em.getTransaction().commit();
	}
	
	public PlanoSaude recuperar(int id){
		return em.find(PlanoSaude.class, id);
	}
	
	public void atualizar(PlanoSaude ps){
		em.getTransaction().begin();
		em.merge(ps);
		em.getTransaction().commit();
	}
	
	public void remover(PlanoSaude ps){
		em.getTransaction().begin();
		em.remove(ps);
		em.getTransaction().commit();
	}	
	
	@SuppressWarnings("unchecked")
	public List<PlanoSaude> recuperarTodos(){
		Query query = em.createQuery("SELECT ps FROM PlanoSaude ps");
		return query.getResultList();
	}
	
	public void encerrar(){
		em.close();
		emf.close();
	}
	
//	@SuppressWarnings("unchecked")
//	public List<Paciente> recuperarPorStatus(String status)
//	{
//		Query query = em.createQuery("SELECT p FROM Endereco p "
//				+ "WHERE p.status LIKE :status");
//		query.setParameter("status", "%"+status+"%");
//		
//		return query.getResultList();
//	}
	
}
