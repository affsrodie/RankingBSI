/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GesMED.bsi.Interface;

/**
 *
 * @author leoncio
 */
public class Receita {
    
    private String Medicamento;
    private String Quant;
    private String Observacoes;

    public String getMedicamento() {
        return Medicamento;
    }

    public void setMedicamento(String Medicamento) {
        this.Medicamento = Medicamento;
    }

    public String getQuant() {
        return Quant;
    }

    public void setQuant(String Quant) {
        this.Quant = Quant;
    }

    public String getObservacoes() {
        return Observacoes;
    }

    public void setObservacoes(String Observacoes) {
        this.Observacoes = Observacoes;
    }
    
    
}
