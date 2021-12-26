/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author PC
 */
public class character {
    private String character = "";
    private int amount = 0;

    public character(String character, int amount) {
        this.character = character;
        this.amount = amount;
        
    }
    
    public int getAmount() {
        return amount;
    }

    public String getCharacter() {
        return character;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCharacter(String character) {
        this.character = character;
    }
    
    
}
