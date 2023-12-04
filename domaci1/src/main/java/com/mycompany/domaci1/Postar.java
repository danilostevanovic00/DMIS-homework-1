/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.domaci1;

import java.util.ArrayList;


/**
 *
 * @author danil
 */
public class Postar extends AktivnaOsoba{
    private final PostanskoSanduce[] regionPostara;
    

    public Postar(String ime, long najkraceVreme, long najduzeVreme, PostanskoSanduce sanduce, int velicinaSanduceta) {
        super(ime, najkraceVreme, najduzeVreme, sanduce);
        this.regionPostara = new PostanskoSanduce[velicinaSanduceta];
        for(int i = 0; i < this.regionPostara.length; i++){
            this.regionPostara[i] = null;
        }
    }
    
    public void dodajNovogPrimaoca(PostanskoSanduce p){
        for (int i = 0; i< regionPostara.length;i++){
            if (this.regionPostara[i]==null){
                this.regionPostara[i]=p;
                System.out.println("Dodeljen je novi primalac sa adresom " + p.getAdresa()+" postaru " + this.getIme());
                break;
            }
        } 
    }
    
    public boolean isPrazan(){
        boolean prazan = true;
        for (PostanskoSanduce p: this.regionPostara){
            prazan = prazan && (p==null);
        }
        return prazan;
    }
    //
    @Override
    public void radi() throws InterruptedException {
        if (this.sanduce.isPrazan()){
            System.out.println("Nema vise posiljki za slanje!" );
            Thread t = Thread.currentThread();
            t.setPriority(Thread.MIN_PRIORITY);
        }else {
            Posiljka posiljkaZaDostavu = sanduce.vratiPrvi();
            String adresaSlanja = posiljkaZaDostavu.getAdresa();
            System.out.println("Uzet je prvi pa se proveravaju ostali "+posiljkaZaDostavu.getAdresa() );
            ArrayList<Posiljka> zaDostavu = new ArrayList<>();
            zaDostavu.add(0,posiljkaZaDostavu);
            int index=1;
            while(true){
                Posiljka p = this.sanduce.vratiPoAdresi(adresaSlanja);
                if (p==null){
                    break;
                }else{
                    zaDostavu.add(index++, p);
                }
            }
            PostanskoSanduce lokacijaDostave = null;
            for (int i = 0; i < this.regionPostara.length; i++) {
                if(!isPrazan() & this.regionPostara[i].getAdresa().equals(adresaSlanja)){
                    lokacijaDostave=this.regionPostara[i];
                    break;
                }
            } 
            for (int i = 0; i < zaDostavu.size(); i++) {
                lokacijaDostave.dodajPosiljku(zaDostavu.get(i));
                //sThread t = Thread.currentThread();
                //t.notifyAll();
                synchronized (lokacijaDostave) {
                    lokacijaDostave.notify();
                }
            }
        }
    }
    
}
