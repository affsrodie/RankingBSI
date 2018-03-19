package GesMED.bsi.Repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import GesMED.bsi.Entidades.Atendente;
import GesMED.bsi.Entidades.Paciente;

public class AtendenteRepositorio {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public AtendenteRepositorio() {
		emf = Persistence.createEntityManagerFactory("GesmedJPA");
		em = emf.createEntityManager();
	}
	
	public void adicionar(Atendente atendente){
		em.getTransaction().begin();
		em.persist(atendente);
		em.getTransaction().commit();
	}
	
	public Atendente recuperar(int id){
		return em.find(Atendente.class, id);
	}
	
	public void atualizar(Atendente atendente){
		em.getTransaction().begin();
		em.merge(atendente);
		em.getTransaction().commit();
	}
	public void remover(Atendente atendente){
		em.getTransaction().begin();
		em.remove(atendente);
		em.getTransaction().commit();
	}	
	
	@SuppressWarnings("unchecked")
	public List<Atendente> recuperarTodos(){
		Query query = em.createQuery("SELECT p FROM Atendente p");
		return query.getResultList();
	}
	
	public int gerarID() {
		List<Atendente> todos = recuperarTodos();
		int NOVO_ID=1;
           if(todos.size()>=1){
              Atendente atendente = todos.get(todos.size());
                    NOVO_ID = atendente.getID() + 1;
            }
		return NOVO_ID;
	}
	
	public void encerrar(){
		em.close();
		emf.close();
	}
	
//	@SuppressWarnings("unchecked")
//	public List<Paciente> recuperarPorStatus(String status)
//	{
//		Query query = em.createQuery("SELECT p FROM Paciente p "
//				+ "WHERE p.status LIKE :status");
//		query.setParameter("status", "%"+status+"%");
//		
//		return query.getResultList();
//	}
	
}
