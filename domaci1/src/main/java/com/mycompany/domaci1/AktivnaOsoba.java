/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.domaci1;


/**
 *
 * @author danil
 */

public abstract class AktivnaOsoba extends Thread {
    private String ime;
    private long najkraceVreme;
    private long najduzeVreme;
    protected final PostanskoSanduce sanduce;

    public String getIme() {
        return ime;
    }

    public AktivnaOsoba(String ime, long najkraceVreme, long najduzeVreme, PostanskoSanduce sanduce) {
        if (najkraceVreme < 0 || najduzeVreme < 0 || najkraceVreme > najduzeVreme) {
            throw new IllegalArgumentException("Neispravno zadati parametri za vreme izvršavanja.");
        }

        this.ime = ime;
        this.najkraceVreme = najkraceVreme;
        this.najduzeVreme = najduzeVreme;
        this.sanduce = sanduce;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.ime).append("\n").append(this.sanduce.toString());
        return result.toString();
    }
    
    public abstract void radi() throws InterruptedException;

    @Override
    public void run() { 
        try {
            while(!interrupted()){
            radi(); 
            Thread.sleep((long)(najkraceVreme + Math.random()*(najduzeVreme-najkraceVreme)));
            }
        } catch (InterruptedException e) {}
    }

}

