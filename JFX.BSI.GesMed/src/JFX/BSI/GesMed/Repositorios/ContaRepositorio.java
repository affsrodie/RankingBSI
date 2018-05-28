package JFX.BSI.GesMed.Repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import JFX.BSI.GesMed.Entidades.Conta;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContaRepositorio {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public ContaRepositorio() {
		emf = Persistence.createEntityManagerFactory("GesmedJPA");
		em = emf.createEntityManager();
	}
	
	public void adicionar(Conta conta){
		em.getTransaction().begin();
		em.merge(conta);
		em.getTransaction().commit();
	}
	
	public Conta recuperar(int id){
		return em.find(Conta.class, id);
	}
	
	public void atualizar(Conta conta){
		em.getTransaction().begin();
		em.merge(conta);
		em.getTransaction().commit();
	}
	
	public void remover(Conta conta){
		em.getTransaction().begin();
		em.remove(conta);
		em.getTransaction().commit();
	}
        
        public int[] getDataSplit(LocalDate Data){
            
            LocalDate dataQuery=Data;

            int[] dataInt = new int[3];
            int DIA = dataQuery.getDayOfMonth();
            int MES = dataQuery.getMonthValue();
            int ANO = dataQuery.getYear();
            
            dataInt[0]=ANO;
            dataInt[1]=MES;
            dataInt[2]=DIA;
            
            return dataInt;
            
        }
        //CONTROLE DE CAIXA - SOMAS PARA CAIXA
        
        @SuppressWarnings("unchecked")
        public double getSomaAnoEntradas(LocalDate DataQuery){
            int[] Data = getDataSplit(DataQuery);
            double Resultado;
            Query queryDouble = em.createQuery("SELECT c FROM Conta c Where year(DataPagamento)="+Data[0]+" "
                                + "                                 AND Tipo='Entrada'");
	    Resultado = (double) queryDouble.getSingleResult();
            return Resultado;
        }
        
        
        @SuppressWarnings("unchecked")
        public double getSomaMesEntradas(LocalDate DataQuery){
            int[] Data = getDataSplit(DataQuery);
            double Resultado;
            Query queryDouble = em.createQuery("SELECT SUM(Valor) FROM Conta c Where month(DataPagamento)="+Data[1]+" "
                        + "                                         AND year(DataPagamento)="+Data[0]+" "
                                + "                                 AND Tipo='Entrada'");
	    Resultado = (double) queryDouble.getSingleResult();
            return Resultado;
        }
        
        @SuppressWarnings("unchecked")
        public double getSomaDiaEntradas(LocalDate DataQuery){
            int[] Data = getDataSplit(DataQuery);
            double Resultado;
           Query queryDouble = em.createQuery("SELECT SUM{Valor} FROM Conta c Where month(DataPagamento)="+Data[1]+" "
                        + "                                         AND year(DataPagamento)="+Data[0]+" "
                                + "                                 AND day(DataPagamento)="+Data[2]+" "
                                + "                                 AND Tipo='Entrada'");
	    Resultado = (double) queryDouble.getSingleResult();
            return Resultado;
        }
        
                
        @SuppressWarnings("unchecked")
        public double getSomaAnoSaidas(LocalDate DataQuery){
            int[] Data = getDataSplit(DataQuery);
            double Resultado;
            Query queryDouble = em.createQuery("SELECT c FROM Conta c Where year(DataPagamento)="+Data[0]+" "
                                + "                                 AND Tipo='Saida'");
	    Resultado = (double) queryDouble.getSingleResult();
            return Resultado;
        }
        
        
        @SuppressWarnings("unchecked")
        public double getSomaMesSaidas(LocalDate DataQuery){
            int[] Data = getDataSplit(DataQuery);
            double Resultado;
            Query queryDouble = em.createQuery("SELECT SUM(Valor) FROM Conta c Where month(DataPagamento)="+Data[1]+" "
                        + "                                         AND year(DataPagamento)="+Data[0]+" "
                                + "                                 AND Tipo='Saida'");
	    Resultado = (double) queryDouble.getSingleResult();
            return Resultado;
        }
        
        @SuppressWarnings("unchecked")
        public double getSomaDiaSaidas(LocalDate DataQuery){
            int[] Data = getDataSplit(DataQuery);
            double Resultado;
           Query queryDouble = em.createQuery("SELECT SUM{Valor} FROM Conta c Where month(DataPagamento)="+Data[1]+" "
                        + "                                         AND year(DataPagamento)="+Data[0]+" "
                                + "                                 AND day(DataPagamento)="+Data[2]+" "
                                + "                                 AND Tipo='Saida'");
	    Resultado = (double) queryDouble.getSingleResult();
            return Resultado;
        }
        
        @SuppressWarnings("unchecked")
        public double getSomaMesAReceber(LocalDate DataQuery){
            int[] Data = getDataSplit(DataQuery);
            double Resultado;
            Query queryDouble = em.createQuery("SELECT c FROM Conta c Where month(DataPagamento)="+Data[1]+" "
                        + "                                         AND year(DataPagamento)="+Data[0]+" AND DataPagamento=NULL order by DataLancamento");
	    Resultado = (double) queryDouble.getSingleResult();
            return Resultado;
        }
        
        
        //CONTROLE DE CAIXA - RELATÓRIO
        
        @SuppressWarnings("unchecked")
	public List<Conta> getAnoContasEntradas(LocalDate DataQuery){
            
                int[] Data = getDataSplit(DataQuery);
                System.out.println("GET TODAS AS ENTRADAS DO ANO: "+Data[0]);
                //CONSULTAS PARA DATA[1] = MES E DATA[0]= ANO E PARA DATA[2] = DIA
		Query query = em.createQuery("SELECT c FROM Conta c Where year(DataPagamento)="+Data[0]+" "
                                + "                                 AND Tipo='Entrada' order by DataPagamento ");
		return query.getResultList();
	}
        
        @SuppressWarnings("unchecked")
	public List<Conta> getMesContasEntradas(LocalDate DataQuery){
                int[] Data = getDataSplit(DataQuery);
                System.out.println("GET TODAS AS ENTRADAS DO MÊS: "+Data[1]);
                //CONSULTAS PARA DATA[1] = MES E DATA[0]= ANO E PARA DATA[2] = DIA
		Query query = em.createQuery("SELECT c FROM Conta c Where month(DataPagamento)="+Data[1]+" "
                        + "                                         AND year(DataPagamento)="+Data[0]+" "
                                + "                                 AND Tipo='Entrada' order by DataPagamento");
		return query.getResultList();
	}
        
        @SuppressWarnings("unchecked")
	public List<Conta> getDiaContasEntradas(LocalDate DataQuery){
                int[] Data = getDataSplit(DataQuery);
                System.out.println("GET TODAS AS ENTRADAS DO DIA: "+Data[2]);
                //CONSULTAS PARA DATA[1] = MES E DATA[0]= ANO E PARA DATA[2] = DIA
		Query query = em.createQuery("SELECT c FROM Conta c Where month(DataPagamento)="+Data[1]+" "
                        + "                                         AND year(DataPagamento)="+Data[0]+" "
                                + "                                 AND day(DataPagamento)="+Data[2]+" "
                                + "                                 AND Tipo='Entrada' order by DataPagamento");
		return query.getResultList();
	}
        
        
        
        @SuppressWarnings("unchecked")
	public List<Conta> getAnoContasSaida(LocalDate DataQuery){
                int[] Data = getDataSplit(DataQuery);
                System.out.println("GET TODAS AS SAÍDAS DO ANO: "+Data[0]);
                //CONSULTAS PARA DATA[1] = MES E DATA[0]= ANO E PARA DATA[2] = DIA
		Query query = em.createQuery("SELECT c FROM Conta c Where year(DataPagamento)="+Data[0]+" "
                                + "                                 AND Tipo='Saida' order by DataPagamento");
		return query.getResultList();
	}
        
        @SuppressWarnings("unchecked")
	public List<Conta> getMesContasSaidas(LocalDate DataQuery){
                int[] Data = getDataSplit(DataQuery);
                System.out.println("GET TODAS AS SAÍDAS DO MÊS: "+Data[1]);
                //CONSULTAS PARA DATA[1] = MES E DATA[0]= ANO E PARA DATA[2] = DIA
		Query query = em.createQuery("SELECT c FROM Conta c Where month(DataPagamento)="+Data[1]+" "
                        + "                                         AND year(DataPagamento)="+Data[0]+" "
                                + "                                 AND Tipo='Saidas' order by DataPagamento");
		return query.getResultList();
	}
        
        @SuppressWarnings("unchecked")
	public List<Conta> geDiaContasSaida(LocalDate DataQuery){
                int[] Data = getDataSplit(DataQuery);
                System.out.println("GET TODAS AS SAÍDAS DO DIA: "+Data[2]);
                //CONSULTAS PARA DATA[1] = MES E DATA[0]= ANO E PARA DATA[2] = DIA
		Query query = em.createQuery("SELECT c FROM Conta c Where month(DataPagamento)="+Data[1]+" "
                        + "                                         AND year(DataPagamento)="+Data[0]+" "
                                + "                                 AND day(DataPagamento)="+Data[2]+" "
                                + "                                 AND Tipo='Saida' order by DataPagamento");
		return query.getResultList();
	}
        
        @SuppressWarnings("unchecked")
	public List<Conta> getAnoContasEntradaSaida(LocalDate DataQuery){
                int[] Data = getDataSplit(DataQuery);
                System.out.println("GET TODAS AS ENTRADAS E SAÍDAS DO ANO: "+Data[0]);
                //CONSULTAS PARA DATA[1] = MES E DATA[0]= ANO E PARA DATA[2] = DIA
		Query query = em.createQuery("SELECT c FROM Conta c Where year(DataPagamento)="+Data[0]+" order by DataPagamento");
		return query.getResultList();
	}
        
        @SuppressWarnings("unchecked")
	public List<Conta> getMesContasEntradaSaida(LocalDate DataQuery){
                int[] Data = getDataSplit(DataQuery);
                System.out.println("GET TODAS AS ENTRADAS E SAÍDAS DO MÊS: "+Data[1]);
                //CONSULTAS PARA DATA[1] = MES E DATA[0]= ANO E PARA DATA[2] = DIA
		Query query = em.createQuery("SELECT c FROM Conta c Where month(DataPagamento)="+Data[1]+" "
                        + "                                         AND year(DataPagamento)="+Data[0]+" order by DataPagamento");
		return query.getResultList();
	}
        
        @SuppressWarnings("unchecked")
	public List<Conta> getDiaContasEntradaSaida(LocalDate DataQuery){
                int[] Data = getDataSplit(DataQuery);
                System.out.println("GET TODAS AS ENTRADAS E SAÍDAS DO DIA: "+Data[2]);
                //CONSULTAS PARA DATA[1] = MES E DATA[0]= ANO E PARA DATA[2] = DIA
		Query query = em.createQuery("SELECT c FROM Conta c Where month(DataPagamento)="+Data[1]+" "
                        + "                                         AND year(DataPagamento)="+Data[0]+" "
                                + "                                 AND day(DataPagamento)="+Data[2]+" order by DataPagamento");
		return query.getResultList();
	}
        
        @SuppressWarnings("unchecked")
	public List<Conta> getMesContasAReceber(LocalDate DataQuery){
                int[] Data = getDataSplit(DataQuery);
                System.out.println("GET TODAS AS CONTAS A RECEBER DO MÊS: "+Data[0]);
                //CONSULTAS PARA DATA[1] = MES E DATA[0]= ANO E PARA DATA[2] = DIA
		Query query = em.createQuery("SELECT c FROM Conta c Where month(DataPagamento)="+Data[1]+" "
                        + "                                         AND year(DataPagamento)="+Data[0]+" AND DataPagamento=NULL order by DataLancamento");
		return query.getResultList();
	}
        
        @SuppressWarnings("unchecked")
	public List<Conta> geDiaContasAReceber(LocalDate DataQuery){
                int[] Data = getDataSplit(DataQuery);
                //CONSULTAS PARA DATA[1] = MES E DATA[0]= ANO E PARA DATA[2] = DIA
		Query query = em.createQuery("SELECT c FROM Conta c Where month(DataPagamento)="+Data[1]+" "
                        + "                                         AND year(DataPagamento)="+Data[0]+" "
                                + "                                 AND day(DataPagamento)="+Data[2]+" AND DataPagamento=NULL order by DataLancamento");
		return query.getResultList();
	}
        
        
	//MÉTODOS USADOS EM JANELA CONTAS E QUE ALGUMAS NÃO SÃO USADAS === VERIFICAR E APAGAR
	@SuppressWarnings("unchecked")
	public List<Conta> recuperarTodasContas(){
		Query query = em.createQuery("SELECT c FROM Conta c");
		return query.getResultList();
	}
        
        @SuppressWarnings("unchecked")
	public List<Conta> recuperarTodasContasPendentes(){
		Query query = em.createQuery("SELECT c FROM Conta c WHERE Status='Pendente'");
		return query.getResultList();
	}
        
        @SuppressWarnings("unchecked")
	public List<Conta> getTodasContasEntradas(){
		Query query = em.createQuery("SELECT c FROM Conta c WHERE Tipo='Entrada'");
		return query.getResultList();
	}
        
        @SuppressWarnings("unchecked")
	public List<Conta> recuperarTodasContasPagas(){
		Query query = em.createQuery("SELECT c FROM Conta c WHERE Status='Pago'");
		return query.getResultList();
	}
        
       public double getValorTotalEntradas(){
           double Valor;
           Valor = (double) em.createQuery("SELECT sum(c.Valor) FROM Conta c WHERE tipo='Entrada'").getSingleResult();
           return Valor;
       }
       
       public double getValorTotalSaida(){
           double Valor;
           Valor = (double) em.createQuery("SELECT sum(c.Valor) FROM Conta c WHERE tipo='Saida'").getSingleResult();
           return Valor;
       }
	
	public void encerrar(){
		em.close();
		emf.close();
	}
	

}
