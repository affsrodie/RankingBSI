package GesMED.bsi.Repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import GesMED.bsi.Entidades.Especializacao;

public class ConsultaRepositorio {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public ConsultaRepositorio() {
		emf = Persistence.createEntityManagerFactory("GesmedJPA");
		em = emf.createEntityManager();
	}
	
	public void adicionar(Especializacao especial){
		em.getTransaction().begin();
		em.persist(especial);
		em.getTransaction().commit();
	}
	
	public Especializacao recuperar(int id){
		return em.find(Especializacao.class, id);
	}
	
	public void atualizar(Especializacao especial){
		em.getTransaction().begin();
		em.merge(especial);
		em.getTransaction().commit();
	}
	
	public void remover(Especializacao especial){
		em.getTransaction().begin();
		em.remove(especial);
		em.getTransaction().commit();
	}	
	
	@SuppressWarnings("unchecked")
	public List<Especializacao> recuperarTodos(){
		Query query = em.createQuery("SELECT p FROM Especializacao p");
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
