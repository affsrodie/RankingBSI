/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFX.BSI.GesMed.Entidades;

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
        private String Nome;
        private String Referencia;
        private String CPF;
        private String CNPJ;
        private String Endereco;
        private double Valor;
        private String Tipo;
        @Temporal(value = TemporalType.DATE)
        private Date DataLancamento;
        @Temporal(value = TemporalType.DATE)
        private Date DataPagamento;
        private String Status;

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

        public int getIDConta() {
            return IDConta;
        }

        public void setIDConta(int IDConta) {
            this.IDConta = IDConta;
        }

        public String getReferencia() {
            return Referencia;
        }

        public void setReferencia(String Referencia) {
            this.Referencia = Referencia;
        }

        public String getCPF() {
            return CPF;
        }

        public void setCPF(String CPF) {
            this.CPF = CPF;
        }

        public String getCNPJ() {
            return CNPJ;
        }

        public void setCNPJ(String CNPJ) {
            this.CNPJ = CNPJ;
        }

        public String getEndereco() {
            return Endereco;
        }

        public void setEndereco(String Endereco) {
            this.Endereco = Endereco;
        }
        
        public double getValor() {
            return Valor;
        }

        public void setValor(double Valor) {
            this.Valor = Valor;
        }

        public String getTipo() {
            return Tipo;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }
  
        public void setTipo(String Tipo) {
            this.Tipo = Tipo;
        }

        public Date getDataLancamento() {
            return DataLancamento;
        }

        public void setDataLancamento(Date DataLancamento) {
            this.DataLancamento = DataLancamento;
        }

        public Date getDataPagamento() {
            return DataPagamento;
        }

        public void setDataPagamento(Date DataPagamento) {
            this.DataPagamento = DataPagamento;
        }
        
        
 
}
