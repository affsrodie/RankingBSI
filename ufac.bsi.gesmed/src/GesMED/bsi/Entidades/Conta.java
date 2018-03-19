/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GesMED.bsi.Entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author leoncio
 */
@Entity
public class Conta {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int IDConta;
        private String Titulo;
        private double Valor;
        @Temporal(value = TemporalType.DATE)
	private Date DataLancamento;
        @Temporal(value = TemporalType.DATE)
        private Date DataPago;
        private String Status;

        public int getIDConta() {
            return IDConta;
        }

        public void setIDConta(int IDConta) {
            this.IDConta = IDConta;
        }

        public String getTitulo() {
            return Titulo;
        }

        public void setTitulo(String Titulo) {
            this.Titulo = Titulo;
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

}
