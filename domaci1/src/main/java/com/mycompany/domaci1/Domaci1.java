/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.domaci1;

/**
 *
 * @author danil
 */
public class Domaci1 {

    public static void main(String[] args) throws InterruptedException {
        
        long endTimeMillis = System.currentTimeMillis() + 3000;
        Posiljka p1= new Posiljka("adresa1");
        Posiljka p2= new Posiljka("adresa2");
        Posiljka p3= new Posiljka("adresa3");
        Posiljka p4= new Posiljka("adresa3");
        Posiljka p5= new Posiljka("adresa2");
        Posiljka p6= new Posiljka("adresa1");
        Posiljka p7= new Posiljka("adresa1");
        PostanskoSanduce postSanduce = new PostanskoSanduce("adresaPostara1",7);
        postSanduce.dodajPosiljku(p1);
        postSanduce.dodajPosiljku(p2);
        postSanduce.dodajPosiljku(p3);
        postSanduce.dodajPosiljku(p4);
        postSanduce.dodajPosiljku(p5);
        postSanduce.dodajPosiljku(p6);
        postSanduce.dodajPosiljku(p7);
        Postar postar1 = new Postar("postar1",1,3,postSanduce,7);
        PostanskoSanduce zaPrimaoca1 = new PostanskoSanduce("adresa1",5);
        PostanskoSanduce zaPrimaoca2 = new PostanskoSanduce("adresa2",5);
        PostanskoSanduce zaPrimaoca3 = new PostanskoSanduce("adresa3",5);
        
        Primalac primalac1 = new Primalac(zaPrimaoca1,"primalac1",1,3);
        Primalac primalac2 = new Primalac(zaPrimaoca2,"primalac2",1,3);
        Primalac primalac3 = new Primalac(zaPrimaoca3,"primalac3",1,3);
        
        postar1.dodajNovogPrimaoca(zaPrimaoca1);
        postar1.dodajNovogPrimaoca(zaPrimaoca2);
        postar1.dodajNovogPrimaoca(zaPrimaoca3);
        
        postar1.start();
        primalac1.start();
        primalac2.start();
        primalac3.start();
        
        while (System.currentTimeMillis() < endTimeMillis) {
            // Check if threads are still alive
            if (!postar1.isAlive() && !primalac1.isAlive() && !primalac2.isAlive() && !primalac3.isAlive()) {
                break;
            }

            // Sleep for a short duration to avoid busy-waiting
            Thread.sleep(100);
        }

        // Interrupt all threads
        postar1.interrupt();
        primalac1.interrupt();
        primalac2.interrupt();
        primalac3.interrupt();

        System.out.println(postar1.toString());
        primalac1.join();
        primalac2.join();
        primalac3.join();
        postar1.join();
        
    }
}
