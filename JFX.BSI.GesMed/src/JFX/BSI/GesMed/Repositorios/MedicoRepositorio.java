package JFX.BSI.GesMed.Repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import JFX.BSI.GesMed.Entidades.Medico;
import javax.persistence.NoResultException;

public class MedicoRepositorio {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public MedicoRepositorio() {
		emf = Persistence.createEntityManagerFactory("GesmedJPA");
		em = emf.createEntityManager();
	}
	
	public void adicionar(Medico medico){
		em.getTransaction().begin();
		em.merge(medico);
		em.getTransaction().commit();
	}
	
	public Medico recuperar(int id){
		return em.find(Medico.class, id);
	}
	
	public void atualizar(Medico medico){
		em.getTransaction().begin();
		em.merge(medico);
		em.getTransaction().commit();
	}
	public void remover(Medico medico){
		em.getTransaction().begin();
		em.remove(medico);
		em.getTransaction().commit();
	}
	
	public int gerarID() {
		List<Medico> todos = recuperarTodos();
		int NOVO_ID=1;
                if(todos.size()>=1){
                    Medico medico = todos.get(todos.size()-1);
                    NOVO_ID = medico.getID() + 1;
                }
		return NOVO_ID;
	}
	
	@SuppressWarnings("unchecked")
	public List<Medico> recuperarTodos(){
		Query query = em.createQuery("SELECT p FROM Medico p");
		return query.getResultList();
	}
        
        @SuppressWarnings("unchecked")
	public Medico recuperarMedicoCPF(String CPF){
            try{
               Medico medico = (Medico) em.createQuery("SELECT m FROM Medico m WHERE CPF='"+CPF+"'").getSingleResult();
               return medico; 
            }catch(NoResultException nre){
               System.out.println("Entidade não encontrada \n "+nre);
            }
            return null;
	}
	
	public void encerrar(){
		em.close();
		emf.close();
	}
	
}
