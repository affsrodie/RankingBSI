package JFX.BSI.GesMed.Repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import JFX.BSI.GesMed.Entidades.Endereco;

public class EnderecoRepositorio {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public EnderecoRepositorio() {
		emf = Persistence.createEntityManagerFactory("GesmedJPA");
		em = emf.createEntityManager();
	}
	
	public void adicionar(Endereco endereco){
		em.getTransaction().begin();
		em.persist(endereco);
		em.getTransaction().commit();
	}
	
	public Endereco recuperar(int id){
		return em.find(Endereco.class, id);
	}
	
	public void atualizar(Endereco endereco){
		em.getTransaction().begin();
		em.merge(endereco);
		em.getTransaction().commit();
	}
	
	public void remover(Endereco endereco){
		em.getTransaction().begin();
		em.remove(endereco);
		em.getTransaction().commit();
	}	
	
	@SuppressWarnings("unchecked")
	public List<Endereco> recuperarTodos(){
		Query query = em.createQuery("SELECT e FROM Endereco e");
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
