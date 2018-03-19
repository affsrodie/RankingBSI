package GesMED.bsi.Repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import GesMED.bsi.Entidades.ContaPaciente;

public class ContaPacienteRepositorio {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public ContaPacienteRepositorio() {
		emf = Persistence.createEntityManagerFactory("GesmedJPA");
		em = emf.createEntityManager();
	}
	
	public void adicionar(ContaPaciente conta){
		em.getTransaction().begin();
		em.merge(conta);
		em.getTransaction().commit();
	}
	
	public ContaPaciente recuperar(int id){
		return em.find(ContaPaciente.class, id);
	}
	
	public void atualizar(ContaPaciente conta){
		em.getTransaction().begin();
		em.merge(conta);
		em.getTransaction().commit();
	}
	
	public void remover(ContaPaciente conta){
		em.getTransaction().begin();
		em.remove(conta);
		em.getTransaction().commit();
	}	
	
	@SuppressWarnings("unchecked")
	public List<ContaPaciente> recuperarTodasContas(){
		Query query = em.createQuery("SELECT c FROM ContaPaciente c");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<ContaPaciente> recuperarTodasContasPagas(){
		Query query = em.createQuery("SELECT c FROM ContaPaciente c WHERE Status='Pago'");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<ContaPaciente> recuperarTodasContasPendentes(){
		Query query = em.createQuery("SELECT c FROM ContaPaciente c WHERE Status='Pendente'");
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
