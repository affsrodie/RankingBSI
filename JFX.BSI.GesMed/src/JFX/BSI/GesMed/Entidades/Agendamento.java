package JFX.BSI.GesMed.Entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Agendamento {
	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int IDAgenda;
	@Temporal(TemporalType.DATE)
        private Date Data;
        @Temporal(TemporalType.TIME)
        private Date HoraInicio;
        @Temporal(TemporalType.TIME)
        private Date HoraFim;
        private String Procedimento;
        private String Convenio;
        private String Status;
        private String Observacoes;
	
	@ManyToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="ATENDENTE_PK")	
	private Atendente atendente;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="PACIENTE_PK")
	private Paciente paciente;
	
	@OneToMany(mappedBy="agendamento")
	private List<Consulta> consultas = new ArrayList<Consulta>();
	
	
	public int getIDAgenda() {
		return IDAgenda;
	}
	public void setIDAgenda(int iDAgenda) {
		IDAgenda = iDAgenda;
	}

        public String getData() {
            return Data.toString();
        }

        public void setData(Date Data) {
            this.Data = Data;
        }

        public String getHoraInicio() {
            return HoraInicio.toString();
        }

        public void setHoraInicio(Date HoraInicio) {
            this.HoraInicio = HoraInicio;
        }

        public String getHoraFim() {
            return HoraFim.toString();
        }

        public void setHoraFim(Date HoraFim) {

            this.HoraFim = HoraFim;
        }

        
        
        public String getProcedimento() {
            return Procedimento;
        }

        public void setProcedimento(String Procedimento) {
            this.Procedimento = Procedimento;
        }
        
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public Atendente getAtendente() {
		return atendente;
	}
	public void setAtendente(Atendente atendente) {
		this.atendente = atendente;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public List<Consulta> getConsultas() {
		return consultas;
	}
	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}
	
	public void addConsulta(Consulta consulta) {
		this.consultas.add(consulta);
	}
	
	public void delConsulta(Consulta consulta) {
		this.consultas.remove(consulta);
		
	}
        
        public String getConvenio() {
        return Convenio;
        }

        public void setConvenio(String Convenio) {
            this.Convenio = Convenio;
        }

        public String getObservacoes() {
            return Observacoes;
        }

        public void setObservacoes(String Observacoes) {
            this.Observacoes = Observacoes;
        }
		

}
