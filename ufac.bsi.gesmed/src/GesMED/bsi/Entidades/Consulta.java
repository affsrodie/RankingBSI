package GesMED.bsi.Entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Consulta {
	@Id
	private int IDConsulta;
	private String Data;
	private String Hora;
	private String Observacoes;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="AGENDA_PK")
	private Agendamento agendamento;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="MEDICO_PK")
	private Medico medico;
	
	@OneToMany(mappedBy="consulta")
	private List<Exame> exames = new ArrayList<Exame>();

	public int getIDConsulta() { 
		return IDConsulta;
	}

	public void setIDConsulta(int iDConsulta) {
		IDConsulta = iDConsulta;
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

	public void setHora(String hora) {
		Hora = hora;
	}

	public String getObservacoes() {
		return Observacoes;
	}

	public void setObservacoes(String observacoes) {
		Observacoes = observacoes;
	}

	public Agendamento getAgenda() {
		return agendamento;
	}

	public void setAgenda(Agendamento agenda) {
		this.agendamento = agenda;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public List<Exame> getExames() {
		return exames;
	}

	public void setExames(List<Exame> exames) {
		this.exames = exames;
	}
	
	public void addExames(Exame exame) {
		this.exames.add(exame);
	}
	
	public void delExames(Exame exame) {
		this.exames.remove(exame);	
	}

}
