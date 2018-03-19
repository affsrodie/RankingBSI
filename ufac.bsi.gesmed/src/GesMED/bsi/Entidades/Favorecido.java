/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GesMED.bsi.Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author leoncio
 */
@Entity
public class Favorecido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IDFavorecido;
    private String Favorecido;
    private String Obeservacoes;

    public int getIDFavorecido() {
        return IDFavorecido;
    }

    public void setIDFavorecido(int IDFavorecido) {
        this.IDFavorecido = IDFavorecido;
    }

    public String getFavorecido() {
        return Favorecido;
    }

    public void setFavorecido(String Favorecido) {
        this.Favorecido = Favorecido;
    }

    public String getObeservacoes() {
        return Obeservacoes;
    }

    public void setObeservacoes(String Obeservacoes) {
        this.Obeservacoes = Obeservacoes;
    }
    
    
    
}
