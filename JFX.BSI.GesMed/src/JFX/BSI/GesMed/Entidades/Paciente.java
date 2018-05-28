package JFX.BSI.GesMed.Entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
	
@Entity
public class Paciente extends Pessoa {
            
	private String TipoSangue;
	private String 	Observacao;
	
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="PLANO_SAUDE_PK")
	private PlanoSaude planoSaude;

	@OneToMany(mappedBy="paciente")
	private List<Agendamento> agendamentos = new ArrayList<Agendamento>();
        
 
	
	public String getTipoSangue() {
		return TipoSangue;
	}
	public void setTipoSangue(String tipoSangue) {
		TipoSangue = tipoSangue;
	}

	public String getObservacao() {
		return Observacao;
	}
	
	public void setObservacao(String observacao) {
		Observacao = observacao;
	}
	public PlanoSaude getPlanoSaude() {
		return planoSaude;
	}
	public void setPlanoSaude(PlanoSaude planoSaude) {
		this.planoSaude = planoSaude;
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
