package JFX.BSI.GesMed.Entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;



@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Pessoa implements Serializable{
        @Id
	private int ID;
	private String Nome;
	private String RG;
	private String CPF;
	private String DataNasc;
	
	@OneToOne(cascade={CascadeType.ALL}, orphanRemoval=true)
	private Telefone telefone;
	
	@OneToOne(cascade={CascadeType.ALL}, orphanRemoval=true)
	private Endereco endereco;
	
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getRG() {
		return RG;
	}
	public void setRG(String rG) {
		RG = rG;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getDataNasc() {
		return DataNasc;
	}
	public void setDataNasc(String dataNasc) {
		DataNasc = dataNasc;
	}
	public Telefone getTelefone() {
		return telefone;
	}
	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	

}
