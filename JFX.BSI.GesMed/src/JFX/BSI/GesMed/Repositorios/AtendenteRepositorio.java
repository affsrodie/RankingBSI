package JFX.BSI.GesMed.Repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import JFX.BSI.GesMed.Entidades.Atendente;
import javax.persistence.NoResultException;


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
	public Atendente recuperarAtendenteCPF(String CPF){
           try{
              Atendente atendente = (Atendente) em.createQuery("SELECT a FROM Atendente a WHERE CPF='"+CPF+"'").getSingleResult(); 
              return atendente;
           }catch(NoResultException nre){
               System.out.println("Entidade não encontrada \n "+nre);
           }
           return null;
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
              Atendente atendente = todos.get(todos.size()-1);
                    NOVO_ID = atendente.getID() + 1;
            }
            return NOVO_ID;
	}
        
        @SuppressWarnings("unchecked")
        public int getQuantAtendentes(){
            long RespDouble;
            int Result;
            Query queryDouble = em.createQuery("SELECT COUNT(*) FROM Atendente");
	    RespDouble = (long)queryDouble.getSingleResult();
            Result = (int) RespDouble;
            return Result;
        }
	
	public void encerrar(){
		em.close();
		emf.close();
	}
	
}
