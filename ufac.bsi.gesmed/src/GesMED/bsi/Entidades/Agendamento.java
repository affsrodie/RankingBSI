	package GesMED.bsi.Entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Agendamento {
	@Id
	private int IDAgenda;
	private String Data;
        private String Hora;
        private String Procedimento;
	private String Status;

	
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
		return Data;
	}
	public void setData(String data) {
		Data = data;
	}
        
        public String getHora() {
            return Hora;
        }

        public void setHora(String Hora) {
            this.Hora = Hora;
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
		

}
