package GesMED.bsi.Repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import GesMED.bsi.Entidades.Agendamento;

import java.time.LocalDate;
;

public class AgendamentoRepositorio {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public AgendamentoRepositorio() {
		emf = Persistence.createEntityManagerFactory("GesmedJPA");
		em = emf.createEntityManager();
	}
	
	public void adicionar(Agendamento agenda){
		em.getTransaction().begin();
		em.merge(agenda);
		em.getTransaction().commit();
	}
	
	public Agendamento recuperar(int id){
		return em.find(Agendamento.class, id);
	}
	
	public void atualizar(Agendamento agenda){
		em.getTransaction().begin();
		em.merge(agenda);
		em.getTransaction().commit();
	}
	public void remover(Agendamento agenda){
		em.getTransaction().begin();
		em.remove(agenda);
		em.getTransaction().commit();
	}	
	
	@SuppressWarnings("unchecked")
	public List<Agendamento> recuperarTodos(){
		Query query = em.createQuery("SELECT a FROM Agendamento a");
		return query.getResultList();
	} 
	
    
   public void atualizarStatusPacientes(List<Agendamento> listAgendamento){
    	for(Agendamento agenda : listAgendamento) {
    		em.getTransaction().begin();
    		em.merge(agenda);
    		em.getTransaction().commit();
    	}
    }
	
    @SuppressWarnings("unchecked")
	public List<Agendamento> listarPorDiaHoje(){
            
            LocalDate data = LocalDate.now();
            String Data = data.toString();
            Query query = em.createQuery("SELECT a FROM Agendamento a WHERE Data='"+Data+"' order by HoraInicio asc");
            return query.getResultList();
	}
	
        public List<Agendamento> listarPorData(String strData){
            Query query = em.createQuery("SELECT a FROM Agendamento a WHERE Data='"+strData+"' order by HoraInicio asc");
            return query.getResultList();
	}
        
        public List<Agendamento> listarPorStatus(String Status){
            LocalDate data = LocalDate.now();
            String Data = data.toString();
            Query query = em.createQuery("SELECT a FROM Agendamento a WHERE Data='"+Data+"' AND  Status='"+Status+"' order by HoraInicio asc");
            return query.getResultList();
        }
        
        public List<Agendamento> listarPorProcedimento(String Procedimento){
            LocalDate data = LocalDate.now();
            String Data = data.toString();
            Query query = em.createQuery("SELECT a FROM Agendamento a WHERE Data='"+Data+"' AND  Procedimento='"+Procedimento+"' order by HoraInicio asc");
            return query.getResultList();
        }
        
        public List<Agendamento> listarPorConventio(String Convenio){
            LocalDate data = LocalDate.now();
            String Data = data.toString();
            Query query = em.createQuery("SELECT a FROM Agendamento a WHERE Data='"+Data+"' AND  Convenio='"+Convenio+"' order by HoraInicio asc");
            return query.getResultList();
        }
        
        public List<Agendamento> listarPorStatusProcedimento(String Status, String Procedimento){
            LocalDate data = LocalDate.now();
            String Data = data.toString();
            Query query = em.createQuery("SELECT a FROM Agendamento a WHERE Data='"+Data+"' AND  Status='"+Status+"' AND Procedimento='"+Procedimento+"' order by HoraInicio asc");
            return query.getResultList();
        }
        
        public List<Agendamento> listarPorSPC(String Status, String Procedimento, String Convenio){
            LocalDate data = LocalDate.now();
            String Data = data.toString();
            Query query = em.createQuery("SELECT a FROM Agendamento a WHERE Data='"+Data+"' AND  Status='"+Status+"' AND Procedimento='"+Procedimento+"' AND Convenio='"+Convenio+"' order by HoraInicio asc");
            return query.getResultList();
        }
        
        public List<Agendamento> listarPorStatusConvenio(String Status, String Convenio){
            LocalDate data = LocalDate.now();
            String Data = data.toString();
            Query query = em.createQuery("SELECT a FROM Agendamento a WHERE Data='"+Data+"' AND Status='"+Status+"' AND Convenio='"+Convenio+"' order by HoraInicio asc");
            return query.getResultList();
        }
        
         public List<Agendamento> listarPorProcedimentoConvenio(String Procedimento, String Convenio){
            LocalDate data = LocalDate.now();
            String Data = data.toString();
            Query query = em.createQuery("SELECT a FROM Agendamento a WHERE Data='"+Data+"' AND  Procedimento='"+Procedimento+"' AND Convenio='"+Convenio+"' order by HoraInicio asc");
            return query.getResultList();
        }
        
        
        
        
        
        
	public void encerrar(){
		em.close();
		emf.close();
	}
	
//	@SuppressWarnings("unchecked")
//	public List<Paciente> recuperarPorStatus(String status)
//	{
//		Query query = em.createQuery("SELECT p FROM Agendamento p "
//				+ "WHERE p.status LIKE :status");
//		query.setParameter("status", "%"+status+"%");
//		
//		return query.getResultList();
//	}
	
}
