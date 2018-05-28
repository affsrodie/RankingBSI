package JFX.BSI.GesMed.Repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import JFX.BSI.GesMed.Entidades.Telefone;

public class TelefoneRepositorio {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public TelefoneRepositorio() {
		emf = Persistence.createEntityManagerFactory("GesmedJPA");
		em = emf.createEntityManager();
	}
	
	public void adicionar(Telefone telefone){
		em.getTransaction().begin();
		em.persist(telefone);
		em.getTransaction().commit();
	}
	
	public Telefone recuperar(int id){
		return em.find(Telefone.class, id);
	}
	
	public void atualizar(Telefone telefone){
		em.getTransaction().begin();
		em.merge(telefone);
		em.getTransaction().commit();
	}
	
	public void remover(Telefone telefone){
		em.getTransaction().begin();
		em.remove(telefone);
		em.getTransaction().commit();
	}	
	
	@SuppressWarnings("unchecked")
	public List<Telefone> recuperarTodos(){
		Query query = em.createQuery("SELECT t FROM Telefone t");
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
