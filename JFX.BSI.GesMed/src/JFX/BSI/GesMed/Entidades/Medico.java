package JFX.BSI.GesMed.Entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Medico extends Pessoa {
		
	private String CRM;
        private String Senha;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="ESPECIAL_PK")
	private Especializacao especializacao;
	
	@OneToMany(mappedBy="medico")
	private List<Consulta> consultas= new ArrayList<Consulta>();
	
	public String getCRM() {
		return CRM;
	}

	public void setCRM(String cRM) {
		CRM = cRM;
	}
	
	public Especializacao getEspecializacao() {
		return especializacao;
	}

	public void setEspecializacao(Especializacao especializacao) {
		this.especializacao = especializacao;
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

        public String getSenha() {
            return Senha;
        }

        public void setSenha(String Senha) {
            this.Senha = Senha;
        }
        
        
}
