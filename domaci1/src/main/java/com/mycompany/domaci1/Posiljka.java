/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.domaci1;

/**
 *
 * @author danil
 */
public class Posiljka {
    private static int ID = 0;
    private final int id_posiljke;
    private final String adresa;

    public Posiljka(String address) {
        this.id_posiljke = ++ID;
        this.adresa = address;
    }

    public String getAdresa() {
        return adresa;
    }
    
    @Override
    protected Posiljka clone() throws CloneNotSupportedException {
        Posiljka p=(Posiljka)super.clone();
        return p;
    }

    @Override
    public String toString() {
        StringBuilder poruka = new StringBuilder();
        poruka.append(id_posiljke).append("[");
        poruka.append(adresa);
        poruka.append("]");
        return poruka.toString();
    }
    
}
