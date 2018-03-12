package GesMED.bsi.Entidades;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Exame {
	@Id
	private int IDExame;
	private String Nome;
	private double Valor;
	private String Descricao;
	private String Data;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="CONSULTA_PK")
	private Consulta consulta;

	
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public double getValor() {
		return Valor;
	}
	public void setValor(double valor) {
		Valor = valor;
	}
	public String getDescricao() {
		return Descricao;
	}
	public void setDescricao(String descricao) {
		Descricao = descricao;
	}
	public String getData() {
		return Data;
	}
	public void setData(String data) {
		Data = data;
	}
	
	

}
