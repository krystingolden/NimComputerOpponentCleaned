package com.pluralsight;

public class Piles {

    int count;
    String displayLetter;
    String image;


    public Piles(String displayLetter, int count) {
        this.displayLetter = displayLetter;
        this.count = count;
    }

    public int getCount(){
        return count;
    }

    public boolean setCount(String currentPile, int reduce){
        if(currentPile.equalsIgnoreCase(this.displayLetter)) {
            this.count -= reduce;
            return true;
        }
        else {
            return false;
        }

    }

    public String getDisplayLetter(){
        return displayLetter;
    }

    public void getImage(int currentLine){
        if (this.count >= currentLine) {
            this.image = "*";
        } else {
            this.image = " ";
        }
        System.out.print(image);
    }

    public boolean validNotZero (String currentPile){
        if (currentPile.equalsIgnoreCase(this.displayLetter) && (this.count != 0)){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean validNotTooMuch (String currentPile, int reduce){
        if (currentPile.equalsIgnoreCase(this.displayLetter) && (this.count)-reduce >= 0){
            return true;
        }
        else {
            return false;
        }
    }


}
