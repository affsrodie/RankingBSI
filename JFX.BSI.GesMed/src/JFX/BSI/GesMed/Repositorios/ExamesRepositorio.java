package JFX.BSI.GesMed.Repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import JFX.BSI.GesMed.Entidades.Exame;

public class ExamesRepositorio {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public ExamesRepositorio() {
		emf = Persistence.createEntityManagerFactory("GesmedJPA");
		em = emf.createEntityManager();
	}
        
       public int gerarID() {
		List<Exame> todos = recuperarTodos();
		int NOVO_ID=1;
                if(todos.size()>=1){
                    Exame exame = todos.get(todos.size()-1);
                    NOVO_ID = exame.getIDExame()+ 1;
                }
		return NOVO_ID;
	}
	
	public void adicionar(Exame exane){
		em.getTransaction().begin();
		em.persist(exane);
		em.getTransaction().commit();
	}
	
	public Exame recuperar(int id){
		return em.find(Exame.class, id);
	}
	
	public void atualizar(Exame exame){
		em.getTransaction().begin();
		em.merge(exame);
		em.getTransaction().commit();
	}
	public void remover(Exame exame){
		em.getTransaction().begin();
		em.remove(exame);
		em.getTransaction().commit();
	}	
	
	@SuppressWarnings("unchecked")
	public List<Exame> recuperarTodos(){
		Query query = em.createQuery("SELECT ex FROM Exame ex");
		return query.getResultList();
	}
	
	public void encerrar(){
		em.close();
		emf.close();
	}
	
//	@SuppressWarnings("unchecked")
//	public List<Paciente> recuperarPorStatus(String status)
//	{
//		Query query = em.createQuery("SELECT ex FROM Exame ex "
//				+ "WHERE p.status LIKE :status");
//		query.setParameter("status", "%"+status+"%");
//		
//		return query.getResultList();
//	}
	
}
