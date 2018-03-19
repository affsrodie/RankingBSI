package GesMED.bsi.Entidades;

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
public class Consulta {
	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int IDConsulta;
        private String NomeConsulta;
        @Temporal(value = TemporalType.DATE)
	private Date Data;
        @Temporal (value = TemporalType.TIME)
	private Date Hora;
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

        public void setIDConsulta(int IDConsulta) {
            this.IDConsulta = IDConsulta;
        }

        public String getNomeConsulta() {
            return NomeConsulta;
        }

        public void setNomeConsulta(String NomeConsulta) {
            this.NomeConsulta = NomeConsulta;
        }

        
        public Date getData() {
            return Data;
        }

        public void setData(Date Data) {
            this.Data = Data;
        }

        public Date getHora() {
            return Hora;
        }

        public void setHora(Date Hora) {
            this.Hora = Hora;
        }

        public String getObservacoes() {
            return Observacoes;
        }

        public void setObservacoes(String Observacoes) {
            this.Observacoes = Observacoes;
        }

        public Agendamento getAgendamento() {
            return agendamento;
        }

        public void setAgendamento(Agendamento agendamento) {
            this.agendamento = agendamento;
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



}
