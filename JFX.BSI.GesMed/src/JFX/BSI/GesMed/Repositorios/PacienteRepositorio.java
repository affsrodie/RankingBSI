package JFX.BSI.GesMed.Repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import JFX.BSI.GesMed.Entidades.Paciente;
import JFX.BSI.GesMed.Exception.DataBaseConstraintException;
import java.math.BigDecimal;
import javax.persistence.PersistenceException;


public class PacienteRepositorio {
	
	private final EntityManagerFactory emf;
	private final EntityManager em;
	
	public PacienteRepositorio() {

            emf = Persistence.createEntityManagerFactory("GesmedJPA");
	    em = emf.createEntityManager();
	}
	
	public void adicionar(Paciente paciente){
		em.getTransaction().begin();
		em.merge(paciente);
		em.getTransaction().commit();
	}
	public Paciente recuperar(int id){
		return em.find(Paciente.class, id);
	}
	
	public void atualizar(Paciente paciente){
		em.getTransaction().begin();
		em.merge(paciente);
		em.getTransaction().commit();
	}
	public void remover(Paciente paciente) throws PersistenceException, DataBaseConstraintException{
		em.getTransaction().begin();
		em.remove(paciente);
		em.getTransaction().commit();
	}	
	
	@SuppressWarnings("unchecked")
	public List<Paciente> recuperarTodos(){
		Query query = em.createQuery("SELECT p FROM Paciente p");
		return query.getResultList();
	}
        
         @SuppressWarnings("unchecked")
	public int ContarPacientes(){
		Query query = em.createQuery("SELECT p FROM Paciente p");
		List<Paciente> resultList = query.getResultList();
                int Count = resultList.size();
                return Count;
	}
	
	public int gerarID() {
		List<Paciente> todos = recuperarTodos();
		int NOVO_ID=1;
                if(todos.size()>=1){
                    Paciente paciente = todos.get(todos.size()-1);
                    NOVO_ID = paciente.getID() + 1;
                }
		return NOVO_ID;
	}
	
	@SuppressWarnings("unchecked")
	public List<Paciente> recuperarPacienteCPF(String CPF){
		Query query = em.createQuery("SELECT p FROM Paciente p WHERE CPF='"+CPF+"'");
		return query.getResultList();
	}
	
	public List<Paciente> recuperarPacientesNome(String Nome){
		Query query = em.createQuery("SELECT p FROM Paciente p WHERE Nome like '%"+Nome+"%'");
		return query.getResultList();
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
