package GesMED.bsi.Entidades;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Especializacao {
	@Id
	private String Titulo;
	private String Descricao;
	
	
	public String getTitulo() {
		return Titulo;
	}
	public void setTitulo(String titulo) {
		Titulo = titulo;
	}
	public String getDescricao() {
		return Descricao;
	}
	public void setDescricao(String descricao) {
		Descricao = descricao;
	}
	
	

}
