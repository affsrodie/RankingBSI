package JFX.BSI.GesMed.Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity 
public class PlanoSaude {
	
	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int IDPlano;
	private String Titulo;
	private String Descricao;

        public int getIDPlano() {
            return IDPlano;
        }

        public void setIDPlano(int IDPlano) {
            this.IDPlano = IDPlano;
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
