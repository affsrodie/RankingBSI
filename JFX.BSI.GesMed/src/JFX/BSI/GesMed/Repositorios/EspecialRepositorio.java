package JFX.BSI.GesMed.Repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import JFX.BSI.GesMed.Entidades.Especializacao;


public class EspecialRepositorio {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
        public int gerarID() {
		List<Especializacao> todos = recuperarTodas();
		int NOVO_ID=1;
                if(todos.size()>=1){
                    Especializacao especial = todos.get(todos.size()-1);
                    NOVO_ID = especial.getID() + 1;
                }
		return NOVO_ID;
	}
        
	public EspecialRepositorio() {
		emf = Persistence.createEntityManagerFactory("GesmedJPA");
		em = emf.createEntityManager();
	}
	
	public void adicionar(Especializacao especial){
		em.getTransaction().begin();
		em.merge(especial);
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
	public List<Especializacao> recuperarTodas(){
		Query query = em.createQuery("SELECT e FROM Especializacao e");
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
