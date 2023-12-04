/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.domaci1;

/**
 *
 * @author danil
 */
public class Primalac extends AktivnaOsoba{

    public Primalac(PostanskoSanduce sanduce, String ime, long najkraceVreme, long najduzeVreme) {
        super(ime, najkraceVreme, najduzeVreme, sanduce);
    }

    @Override
    public void radi() throws InterruptedException {
        synchronized (super.sanduce) {
            while (super.sanduce.isPrazan()) {
                super.sanduce.wait();
            }
            Posiljka p = sanduce.vratiPrvi();
            
            System.out.println("Primalac " +this.getIme()+" je primio posiljku " + p.toString());
            
        }
        
    }
    
    
    
    
}
