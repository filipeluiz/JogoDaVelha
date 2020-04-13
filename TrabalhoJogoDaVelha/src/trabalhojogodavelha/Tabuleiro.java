package trabalhojogodavelha;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author filipe
 */
public class Tabuleiro implements Serializable {
    private static final long serialVersionUID = 1L;
    private char[][] borda;   
    private Jogador[] jogadores;
    private char simbolo; 
    private String novaMessage;
    private String PotJogador1;
    private String PotJogador2;
    private Random sorteio;
    
    Tabuleiro(){
        this.borda = new char[3][3];
        this.jogadores = new Jogador[2];      
    }
    
    Tabuleiro(Jogador jogador1, Jogador jogador2) {
        this.borda = new char[3][3];
        this.jogadores = new Jogador[2];
        jogadores[0] = jogador1;
        jogadores[1] = jogador2;
        this.sorteio = new Random();
        
        jogadores[0].setSimbolo('X');  
        jogadores[1].setSimbolo('O');         
        
        if(this.sorteio.nextInt(2) == 0) { 
            this.novaMessage = jogadores[0].getName() + " - X" + " começa!";
            System.out.println(jogadores[0].getName() + " - X" + " começa!");
            this.simbolo = jogadores[0].getSimbolo();
        }
        else{
            this.novaMessage = jogadores[1].getName() + " - O" + " começa!";
            System.out.println(jogadores[1].getName() + " - O" + " começa!");       
            this.simbolo = jogadores[1].getSimbolo();
        }
        
        this.PotJogador1 = jogadores[0].getName() + ": " + jogadores[0].getPont();
        this.PotJogador2 = jogadores[1].getName() + ": " + jogadores[1].getPont();
        System.out.println(jogadores[0].getName() + ": " + jogadores[0].getPont());
        System.out.println(jogadores[1].getName() + ": " + jogadores[1].getPont());
        limpar();         
    }
    
    public char getSimbolo() {
        return simbolo;
    }    

    public void setBorda(char[][] borda) {
        this.borda = borda;
    }
    
    public Jogador[] getJogadores() {
        return jogadores;
    }    
    
    public void setJogadores(Jogador[] jogadores) {
        this.jogadores = jogadores;
    }

    public char[][] getBorda() {
        return borda;
    }
    
    public void setSimbolo(char simbolo) {
        this.simbolo = simbolo;
    }  

    public String getNovaMessage() {
        return novaMessage;
    }

    public void setNovaMessage(String novaMessage) {
        this.novaMessage = novaMessage;
    }

    public String getPotJogador1() {
        return PotJogador1;
    }

    public void setPotJogador1(String PotJogador1) {
        this.PotJogador1 = PotJogador1;
    }

    public String getPotJogador2() {
        return PotJogador2;
    }

    public void setPotJogador2(String PorJogador2) {
        this.PotJogador2 = PorJogador2;
    }
    
    
    
    public char getBordaGrafico(int i) {
        if(i == 0){
            return borda[0][0];        
        }
        if(i == 1){
            return borda[0][1];        
        }    
        if(i == 2){
            return borda[0][2];        
        }  
        if(i == 3){
            return borda[1][0];        
        }        
        if(i == 4){
            return borda[1][1];        
        }   
        if(i == 5){
            return borda[1][2];        
        }  
        if(i == 6){
            return borda[2][0];        
        }        
        if(i == 7){
            return borda[2][1];        
        }        
        if(i == 8){
            return borda[2][2];        
        }    
        return borda[0][0];
    }

    private boolean isEmpty() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++){
                if(borda[i][j] == ' '){
                    return true;
                }
            }
        }
        return false;        
    }
    
    public void limpar() {
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++) {
                borda[i][j] = ' ';
            }
        }
    }

    public boolean cheio() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++){
                if(isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }   
    
    public Jogador getJogadorAtual() {
        if(this.simbolo == 'X') {
            return this.jogadores[0];
        }
        else {
            return this.jogadores[1];
        }
    }
    
    public Jogador getJogadorProximo() {
        if(this.simbolo == 'X') {
            return this.jogadores[1];
        }
        else {
            return this.jogadores[0];
        }
    }    
    
    public void jogada(int i) {
        if(!cheio() && !verificarVitoria()){
            switch(i) {
                case 0:
                    setNovaMessage("É sua vez " + getJogadorProximo().getName() + " " + getJogadorProximo().getSimbolo());                    
                    borda[0][0] = simbolo;                    
                    mostreBordaTeste();
                    break;
                case 1: 
                    setNovaMessage("É sua vez " + getJogadorProximo().getName() + " " + getJogadorProximo().getSimbolo());
                    borda[0][1] = simbolo;                    
                    mostreBordaTeste();
                    break;              
                case 2:
                    setNovaMessage("É sua vez " + getJogadorProximo().getName() + " " + getJogadorProximo().getSimbolo());
                    borda[0][2] = simbolo;                    
                    mostreBordaTeste();
                    break; 
                case 3:
                    setNovaMessage("É sua vez " + getJogadorProximo().getName() + " " + getJogadorProximo().getSimbolo());                        
                    borda[1][0] = simbolo;
                    mostreBordaTeste();
                    break;  
                case 4:
                    setNovaMessage("É sua vez " + getJogadorProximo().getName() + " " + getJogadorProximo().getSimbolo());
                    borda[1][1] = simbolo;
                    mostreBordaTeste();
                    break; 
                case 5:
                    setNovaMessage("É sua vez " + getJogadorProximo().getName() + " " + getJogadorProximo().getSimbolo());
                    borda[1][2] = simbolo;
                    mostreBordaTeste();
                   break; 
                case 6:
                    setNovaMessage("É sua vez " + getJogadorProximo().getName() + " " + getJogadorProximo().getSimbolo());
                    borda[2][0] = simbolo;
                    mostreBordaTeste();
                    break; 
                case 7:
                    setNovaMessage("É sua vez " + getJogadorProximo().getName() + " " + getJogadorProximo().getSimbolo());    
                    borda[2][1] = simbolo;
                    mostreBordaTeste();
                    break;   
                case 8:
                    setNovaMessage("É sua vez " + getJogadorProximo().getName() + " " + getJogadorProximo().getSimbolo());
                    borda[2][2] = simbolo;
                    mostreBordaTeste();
                    break;
            }            
        } 

        if(verificarVitoria()){
            getJogadorAtual().setPont(1);

            setNovaMessage(getJogadorAtual().getName() + " " + getJogadorAtual().getSimbolo() + " venceu!");
            setPotJogador1(jogadores[0].getName() + ": " + jogadores[0].getPont());
            setPotJogador2(jogadores[1].getName() + ": " + jogadores[1].getPont());
            
            System.out.println(getJogadorAtual().getName() + " " + getJogadorAtual().getSimbolo() + " venceu!");
            System.out.println(jogadores[0].getName() + ": " + jogadores[0].getPont());
            System.out.println(jogadores[1].getName() + ": " + jogadores[1].getPont());            
        }  
    }
    
    public void vezJogador() {
        if (simbolo == 'O') {
            simbolo = jogadores[0].getSimbolo();
        }
        else {
            simbolo = jogadores[1].getSimbolo();
        }       
    }    

    public boolean verificarVitoria() {
        if(verificarLinha() || verificarColuna() || verificarDiagonal()){
            return true;
        }
        return false;
    }
    
    private boolean verificarDiagonal() {
        if(verificar(borda[0][0], borda[1][1], borda[2][2]) || verificar(borda[0][2], borda[1][1], borda[2][0])){
            return true;
        }
        return false;
    }    
    
    private boolean verificarColuna(){
        for(int i = 0; i < 3; i++){
            if(verificar(borda[0][i], borda[1][i], borda[2][i])){
                return true;
            }
        }
        return false;
    }

    private boolean verificarLinha(){
        for(int i = 0; i < 3; i++){
            if(verificar(borda[i][0], borda[i][1], borda[i][2])){
                return true;
            }
        }
        return false;
    }
    
    public boolean verificar(char lc1,char lc2,char lc3) {
        if((lc1 != ' ') && (lc1 == lc2) && (lc2 == lc3)){
            return true;
        }
        return false;
    }
       
    public void mostreBordaTeste() {
        int i, j;

        for (i=0; i < 3; i++) {
            for (j = 0; j < 2; j++) {
                if(borda[i][j] == ' ' && i<2){
                	System.out.print("_|");
                } else
                	System.out.print(borda[i][j] + "|");
            }
            if (borda[i][j] == ' ' && i<2)
            	System.out.print("_");
            else
            	System.out.print(borda[i][j]);
                System.out.println();
        }
    }     

}
