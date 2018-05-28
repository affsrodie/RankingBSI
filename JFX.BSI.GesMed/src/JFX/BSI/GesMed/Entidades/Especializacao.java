package JFX.BSI.GesMed.Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Especializacao {
	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
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
