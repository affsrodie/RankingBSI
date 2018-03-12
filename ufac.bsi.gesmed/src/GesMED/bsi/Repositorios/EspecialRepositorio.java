package GesMED.bsi.Repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import GesMED.bsi.Entidades.Consulta;

public class EspecialRepositorio {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public EspecialRepositorio() {
		emf = Persistence.createEntityManagerFactory("GesmedJPA");
		em = emf.createEntityManager();
	}
	
	public void adicionar(Consulta consulta){
		em.getTransaction().begin();
		em.persist(consulta);
		em.getTransaction().commit();
	}
	
	public Consulta recuperar(int id){
		return em.find(Consulta.class, id);
	}
	
	public void atualizar(Consulta consulta){
		em.getTransaction().begin();
		em.merge(consulta);
		em.getTransaction().commit();
	}
	
	public void remover(Consulta consulta){
		em.getTransaction().begin();
		em.remove(consulta);
		em.getTransaction().commit();
	}	
	
	@SuppressWarnings("unchecked")
	public List<Consulta> recuperarTodos(){
		Query query = em.createQuery("SELECT c FROM Consulta c");
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
