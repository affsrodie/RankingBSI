package GesMED.bsi.Repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import GesMED.bsi.Entidades.Agendamento;;

public class AgendamentoRepositorio {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public AgendamentoRepositorio() {
		emf = Persistence.createEntityManagerFactory("GesmedJPA");
		em = emf.createEntityManager();
	}
	
	public void adicionar(Agendamento agenda){
		em.getTransaction().begin();
		em.persist(agenda);
		em.getTransaction().commit();
	}
	
	public Agendamento recuperar(int id){
		return em.find(Agendamento.class, id);
	}
	
	public void atualizar(Agendamento agenda){
		em.getTransaction().begin();
		em.merge(agenda);
		em.getTransaction().commit();
	}
	public void remover(Agendamento agenda){
		em.getTransaction().begin();
		em.remove(agenda);
		em.getTransaction().commit();
	}	
	
	@SuppressWarnings("unchecked")
	public List<Agendamento> recuperarTodos(){
		Query query = em.createQuery("SELECT a FROM Agendamento a");
		return query.getResultList();
	}
	
	public void encerrar(){
		em.close();
		emf.close();
	}
	
//	@SuppressWarnings("unchecked")
//	public List<Paciente> recuperarPorStatus(String status)
//	{
//		Query query = em.createQuery("SELECT p FROM Agendamento p "
//				+ "WHERE p.status LIKE :status");
//		query.setParameter("status", "%"+status+"%");
//		
//		return query.getResultList();
//	}
	
}
