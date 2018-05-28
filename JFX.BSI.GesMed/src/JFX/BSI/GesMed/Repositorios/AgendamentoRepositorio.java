package JFX.BSI.GesMed.Repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import JFX.BSI.GesMed.Entidades.Agendamento;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.math.BigDecimal;

import java.time.LocalDate;
import javax.persistence.PersistenceException;
import org.hibernate.exception.DataException;
;

public class AgendamentoRepositorio {
	
	private EntityManagerFactory emf;
	private EntityManager em;
        private String DataAgendamento;
        private List<Agendamento> listConsulta = new VirtualFlow.ArrayLinkedList<>();
	
	public AgendamentoRepositorio() {
		emf = Persistence.createEntityManagerFactory("GesmedJPA");
		em = emf.createEntityManager();
                LocalDate data = LocalDate.now();
                String Data = data.toString();
                DataAgendamento=Data;
                
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
	
         @SuppressWarnings("unchecked")
	public int ContarAgendamentos(){
		Query query = em.createQuery("SELECT a FROM Agendamento a");
		List<Agendamento> resultList = query.getResultList();
                int Count = resultList.size();
                return Count;
	}
        
        public List<Agendamento> PesquisarPorDataComSPC(String DataAgendamento, String Consulta, String Status, String Procedimento, String Convenio){
           
//            000 APENAS POR DATA
//            100 STATUS
//            010 PROCEDIMENTO
//            001 CONVENIO
//
//            110 STATUS|PROCEDIMENTO
//            101 STATUS|CONVENIO
//            011 PROCEDIMENTO|CONVENIO
//            111 STATUS|PROCEDIMENTO|CONVENIO

            this.DataAgendamento = DataAgendamento;
            
            String[] c = Consulta.split(",");
            
            if(c[0].equals("1")&&c[1].equals("0")&&c[2].equals("0")){
              listConsulta = listarPorStatus(Status);
            }else if(c[0].equals("0")&&c[1].equals("1")&&c[2].equals("0")){
              listConsulta = listarPorProcedimento(Procedimento);
            }else if(c[0].equals("0")&&c[1].equals("0")&&c[2].equals("1")){
              listConsulta = listarPorConventio(Convenio);
            }else if(c[0].equals("1")&&c[1].equals("1")&&c[2].equals("0")){
              listConsulta = listarPorStatusProcedimento(Status, Procedimento);
            }else if(c[0].equals("1")&&c[1].equals("0")&&c[2].equals("1")){
              listConsulta = listarPorStatusConvenio(Status, Convenio);
            }else if(c[0].equals("0")&&c[1].equals("1")&&c[2].equals("1")){
              listConsulta = listarPorProcedimentoConvenio(Procedimento, Convenio);
            }else if(c[0].equals("1")&&c[1].equals("1")&&c[2].equals("1")){
              listConsulta = listarPorSPC(Status, Procedimento, Convenio);
            }else if(c[0].equals("0")&&c[1].equals("0")&&c[2].equals("0")){
               listConsulta = getListaEsperaData(DataAgendamento);
            }
            
             return listConsulta;
            
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
        
        @SuppressWarnings("unchecked")
	public List<Agendamento> getListaEsperaHoje() throws DataException , PersistenceException {
            LocalDate data = LocalDate.now();
            String Data = data.toString();
            System.out.println("Data de Hoje:" +Data);
            Query query = em.createQuery("SELECT a FROM Agendamento a WHERE Data='"+Data+"' order by HoraInicio asc");
//            AND Status=(SELECT Status FROM Agendamento WHERE Status='Agendado' OR Status='Confirmado' OR Status='Chegou' OR Status='Em Andamento')
            return query.getResultList();
	}
        
        @SuppressWarnings("unchecked")
	public List<Agendamento> getContasPacientes(){
            LocalDate data = LocalDate.now();
            String Data = data.toString();
            Query query = em.createQuery("SELECT a FROM Agendamento a WHERE Status=(SELECT Status FROM Agendamento WHERE Status='Em Andamento' OR Status='Finalizado')");
            return query.getResultList();
	}
        
        @SuppressWarnings("unchecked")
	public List<Agendamento> getListaEsperaData(String Data){
            Query query = em.createQuery("SELECT a FROM Agendamento a WHERE Data='"+Data+"' AND Status=(SELECT Status FROM Agendamento WHERE Status='Agendado' OR Status='Confirmado' OR Status='Chegou' OR Status='Em Andamento') order by HoraInicio asc");
            return query.getResultList();
	}
	
        public List<Agendamento> listarPorData(String strData){
            Query query = em.createQuery("SELECT a FROM Agendamento a WHERE Data='"+strData+"' order by HoraInicio asc");
            return query.getResultList();
	}
        
        public List<Agendamento> listarPorStatus(String Status){
            Query query = em.createQuery("SELECT a FROM Agendamento a WHERE Data='"+DataAgendamento+"' AND  Status='"+Status+"' order by HoraInicio asc");
            return query.getResultList();
        }
        
        public List<Agendamento> listarparaAgendarContas(){
            Query query = em.createQuery("SELECT a FROM Agendamento a WHERE Status='Finalizado' OR  Status='Chegou' OR Status='Em Andamento' order by Status");
            return query.getResultList();
        }
        
        public List<Agendamento> listarPorProcedimento(String Procedimento){
            Query query = em.createQuery("SELECT a FROM Agendamento a WHERE Data='"+DataAgendamento+"' AND  Procedimento='"+Procedimento+"' order by HoraInicio asc");
            return query.getResultList();
        }
        
        public List<Agendamento> listarPorConventio(String Convenio){
            Query query = em.createQuery("SELECT a FROM Agendamento a WHERE Data='"+DataAgendamento+"' AND  Convenio='"+Convenio+"' order by HoraInicio asc");
            return query.getResultList();
        }
        
        public List<Agendamento> listarPorStatusProcedimento(String Status, String Procedimento){
            
            Query query = em.createQuery("SELECT a FROM Agendamento a WHERE Data='"+DataAgendamento+"' AND  Status='"+Status+"' AND Procedimento='"+Procedimento+"' order by HoraInicio asc");
            return query.getResultList();
        }
        
        public List<Agendamento> listarPorSPC(String Status, String Procedimento, String Convenio){
            
            Query query = em.createQuery("SELECT a FROM Agendamento a WHERE Data='"+DataAgendamento+"' AND  Status='"+Status+"' AND Procedimento='"+Procedimento+"' AND Convenio='"+Convenio+"' order by HoraInicio asc");
            return query.getResultList();
        }
        
        public List<Agendamento> listarPorStatusConvenio(String Status, String Convenio){
            LocalDate data = LocalDate.now();
            String Data = data.toString();
            Query query = em.createQuery("SELECT a FROM Agendamento a WHERE Data='"+DataAgendamento+"' AND Status='"+Status+"' AND Convenio='"+Convenio+"' order by HoraInicio asc");
            return query.getResultList();
        }
        
         public List<Agendamento> listarPorProcedimentoConvenio(String Procedimento, String Convenio){
            LocalDate data = LocalDate.now();
            String Data = data.toString();
            Query query = em.createQuery("SELECT a FROM Agendamento a WHERE Data='"+DataAgendamento+"' AND  Procedimento='"+Procedimento+"' AND Convenio='"+Convenio+"' order by HoraInicio asc");
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
