/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.domaci1;

/**
 *
 * @author danil
 */
public class PostanskoSanduce {
    private final String adresa;
    private Posiljka[] posiljke;

    public PostanskoSanduce(String adresa,int kapacitetSanduceta) {
        this.adresa = adresa;
        this.posiljke = new Posiljka[kapacitetSanduceta];
        for(int i = 0; i < this.posiljke.length; i++){
            this.posiljke[i] = null;
        }
    }
    
    public boolean isPrazan(){
        boolean prazan = true;
        for (Posiljka p: this.posiljke){
            prazan = prazan && (p==null);
        }
        return prazan;
    }
    
    private boolean isPun(){
        boolean pun = true;
        for (Posiljka p: this.posiljke){
           pun = pun && (p!=null);
        }
        return pun;
    }
    
    public synchronized Posiljka vratiPrvi() throws InterruptedException{
        if (!isPrazan()){
            Posiljka prvaPosiljka = this.posiljke[0];

            for (int i = 0; i < this.posiljke.length - 1; i++) {
                this.posiljke[i] = this.posiljke[i + 1];
            }
            
            this.posiljke[this.posiljke.length -1]= null;
            System.out.println("Vratio prvi sa adresom " + prvaPosiljka.getAdresa());
            return prvaPosiljka;
        }else{
            System.out.println("Postansko sanduce sa adresom "+ this.getAdresa()+" je prazno");
            int sleepDuration = (int) (Math.random() * 2 + 1) * 1000; 
            Thread.sleep(sleepDuration);
            return null;
        }
    }
     
    public synchronized Posiljka vratiPoAdresi(String adresaZaDohvat) throws InterruptedException{
        if (!isPrazan()){
            Posiljka posiljkaSaAdresom;
            for (int i = 0;i <= this.posiljke.length-1;i++){
                if (this.posiljke[i]!=null && this.posiljke[i].getAdresa().equals(adresaZaDohvat)){
                    posiljkaSaAdresom=this.posiljke[i];
                    for (int j = i; j < this.posiljke.length - 1; j++) {
                        this.posiljke[j] = this.posiljke[j + 1];
                    }
                    this.posiljke[this.posiljke.length - 1] = null; 
                    System.out.println("Vracena posiljka sa adresom "+adresaZaDohvat);
                    return posiljkaSaAdresom;
                 }else{
                    posiljkaSaAdresom=this.posiljke[i];
                    
                    if (posiljkaSaAdresom!=null){
                        System.out.println("Ima jos posiljki za istu adresu");
                    }else{
                        System.out.println("Ne postoji posiljka sa adresom "+adresaZaDohvat);
                        return null;
                    }
                }
             }
        }else{
            System.out.println("Postansko sanduce sa adresom "+ this.getAdresa()+" je prazno");
            int vremeSpavanja = (int) (Math.random() * 2 + 1) * 1000; 
            Thread.sleep(vremeSpavanja);
            return null;
        }
        return null;
     }
    
    public synchronized void dodajPosiljku(Posiljka p) throws InterruptedException{
        if (!isPun()){
            for(int i = 0; i < this.posiljke.length; i++){
                if(this.posiljke[i]==null){
                    this.posiljke[i] = p;
                    System.out.println(" Uspesno je dodata posiljka " +p.toString() +" u sanduce sa adresom "+ this.getAdresa());
                    break;
                }
            }
        }else{
            System.out.println("Postansko sanduce sa adresom "+ this.getAdresa()+" je puno");
            int vremeSpavanja = (int) (Math.random() * 2 + 1) * 1000; 
            Thread.sleep(vremeSpavanja);
        }
    }

    public String getAdresa() {
        return adresa;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Adresa sanduceta: ").append(adresa).append("\n");
        
        for (Posiljka posiljka : posiljke) {
            if (posiljka!=null){
                result.append(posiljka.toString()).append("\n");
            }
            
        }

        return result.toString();
    }
    
    
}
