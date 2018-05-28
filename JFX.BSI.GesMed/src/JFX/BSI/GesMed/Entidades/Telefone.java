package JFX.BSI.GesMed.Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Telefone {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int IDTelefone;
	private String Telefone;
        private String Celular;
        private String Fixo;
        private String Trabalho;



	
	public String getTelefone() {
		return Telefone;
	}
	public void setTelefone(String telefone) {
		Telefone = telefone;
	}

        public int getIDTelefone() {
            return IDTelefone;
        }

        public void setIDTelefone(int IDTelefone) {
            this.IDTelefone = IDTelefone;
        }

        public String getCelular() {
            return Celular;
        }

        public void setCelular(String Celular) {
            this.Celular = Celular;
        }

        public String getFixo() {
            return Fixo;
        }

        public void setFixo(String Fixo) {
            this.Fixo = Fixo;
        }

        public String getTrabalho() {
            return Trabalho;
        }

        public void setTrabalho(String Trabalho) {
            this.Trabalho = Trabalho;
        }

	
}
