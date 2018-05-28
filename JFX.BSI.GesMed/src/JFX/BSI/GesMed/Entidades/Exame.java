package 
JFX.BSI.GesMed.Entidades;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Exame {
	@Id
	private int IDExame;
	private String QueixaPrincipal;
        private String Observacoes;
        @Temporal(value = TemporalType.DATE)
        private Date DataExame;
        @Temporal (value = TemporalType.TIME)
        private Date HoraExame;
        
        
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="CONSULTA_PK")
	private Consulta consulta;

    public int getIDExame() {
        return IDExame;
    }

    public void setIDExame(int IDExame) {
        this.IDExame = IDExame;
    }

    public String getQueixaPrincipal() {
        return QueixaPrincipal;
    }

    public void setQueixaPrincipal(String QueixaPrincipal) {
        this.QueixaPrincipal = QueixaPrincipal;
    }

    public String getObservacoes() {
        return Observacoes;
    }

    public void setObservacoes(String Observacoes) {
        this.Observacoes = Observacoes;
    }


    public Date getDataExame() {
        return DataExame;
    }

    public void setDataExame(Date DataExame) {
        this.DataExame = DataExame;
    }

    public Date getHoraExame() {
        return HoraExame;
    }

    public void setHoraExame(Date HoraExame) {
        this.HoraExame = HoraExame;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

	
	
	
	

}
