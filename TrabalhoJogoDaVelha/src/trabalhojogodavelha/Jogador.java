/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhojogodavelha;

import java.io.Serializable;

/**
 *
 * @author filipe
 */
public class Jogador implements Serializable{
    private String name;
    private char simbolo;
    private int pont;
    
    public Jogador(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(char simbolo) {
        this.simbolo = simbolo;
    }

    public int getPont() {
        return pont;
    }

    public void setPont(int pont) {
        this.pont += pont;
    }

    @Override
    public String toString() {
        return "Jogador{" + "name=" + name + ", pont=" + pont + '}';
    }

    void getPont(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
