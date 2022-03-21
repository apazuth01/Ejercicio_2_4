package com.example.activity_firma_digital.Clases;

public class Firmas {
    public int id;
    public  String descripcion;
    public byte[] firmadigital;
    public Firmas(int id,String descripcion, byte[] firmadigital){
        this.id=id;
        this.descripcion=descripcion;
        this.firmadigital=firmadigital;
    }
    public Firmas(){

    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public byte[] getFirmadigital() {
        return firmadigital;
    }
}
