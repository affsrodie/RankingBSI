package GesMED.bsi.Entidades;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Atendente extends Pessoa {
	
	private String DataAmissao;
	
	@OneToMany(mappedBy="atendente")
	private List<Agendamento> agendamentos = new ArrayList<Agendamento>();



	public String getDataAmissao() {
		return DataAmissao;
	}

	public void setDataAmissao(String dataAmissao) {
		DataAmissao = dataAmissao;
	}
	
	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}
	
	public void addAgendamento(Agendamento agenda) {
		this.agendamentos.add(agenda);
	}
	
	public void delAgendamento(Agendamento agenda) {
		this.agendamentos.remove(agenda);
	}
}
