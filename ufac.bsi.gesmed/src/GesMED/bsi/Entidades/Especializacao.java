package GesMED.bsi.Entidades;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Especializacao {
	@Id
        private int ID;
	private String Titulo;
	private String Descricao;

        
        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }
	
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
