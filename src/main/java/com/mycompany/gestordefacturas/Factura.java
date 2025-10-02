package com.mycompany.gestordefacturas;

public class Factura {
    public String asunto;
    public int cantidad ;
    public Fecha fecha ;
    public String tipo;
    public Factura(String asunto, int cantidad, Fecha fecha,String tipo){
        this.asunto = asunto;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.tipo = tipo;
    }

    public static Factura create(Object asunto, Object cantidad, Fecha fecha,Object tipo){
        return new Factura((String)asunto,Integer.parseInt((String)cantidad),fecha,(String)tipo);
    }

    @Override
    public String toString(){
        return asunto + ":" + tipo + ":" + cantidad + ":" + fecha;
    }
}
