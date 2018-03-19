/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GesMED.bsi.Entidades;

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

/**
 *
 * @author leoncio
 */
@Entity
public class ContaPaciente {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int IDPaciente;
        
        private double Valor;
        @Temporal(value = TemporalType.DATE)
	private Date DataLancamento;
        @Temporal(value = TemporalType.DATE)
	private Date DataVencimento;
        @Temporal(value = TemporalType.DATE)
        private Date DataPago;
        private String Status;
        
        
        @ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="PACIENTE_PK")
        private Paciente paciente;
        
        @ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="FAVORECIDO_PK")
        private Favorecido favorecido;


        public int getIDPaciente() {
            return IDPaciente;
        }

        public void setIDPaciente(int IDPaciente) {
            this.IDPaciente = IDPaciente;
        }

        public Paciente getPaciente() {
            return paciente;
        }

        public void setPaciente(Paciente paciente) {
            this.paciente = paciente;
        }

        public double getValor() {
            return Valor;
        }

        public void setValor(double Valor) {
            this.Valor = Valor;
        }

        public Date getDataLancamento() {
            return DataLancamento;
        }

        public void setDataLancamento(Date DataLancamento) {
            this.DataLancamento = DataLancamento;
        }

        public Date getDataVencimento() {
            return DataVencimento;
        }

        public void setDataVencimento(Date DataVencimento) {
            this.DataVencimento = DataVencimento;
        }

        public Date getDataPago() {
            return DataPago;
        }

        public void setDataPago(Date DataPago) {
            this.DataPago = DataPago;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }

        public Favorecido getFavorecido() {
            return favorecido;
        }

        public void setFavorecido(Favorecido favorecido) {
            this.favorecido = favorecido;
        }


        
    
}
