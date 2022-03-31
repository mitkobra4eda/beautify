package com.beautify.demo;

public class SaloonNotFoundException extends Exception{

    public SaloonNotFoundException(){
        super("Saloon not found!");
    }
}
