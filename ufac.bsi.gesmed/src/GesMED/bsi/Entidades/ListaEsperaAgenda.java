/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GesMED.bsi.Entidades;


public class ListaEsperaAgenda {
        private int ID;
        private int IDAgendamento;
        private String Nome;
        private String CPF;
        private String Celular;
        private String Status;
        private String Procedimento;
        private String Convenio;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDAgendamento() {
        return IDAgendamento;
    }

    public void setIDAgendamento(int IDAgendamento) {
        this.IDAgendamento = IDAgendamento;
    }

    
    
    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String Celular) {
        this.Celular = Celular;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getProcedimento() {
        return Procedimento;
    }

    public void setProcedimento(String Procedimento) {
        this.Procedimento = Procedimento;
    }

    public String getConvenio() {
        return Convenio;
    }

    public void setConvenio(String Convenio) {
        this.Convenio = Convenio;
    }


    
    
}
