/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.gestordefacturas;

/**
 *
 * @author Diego Pérez
 */
public class Fecha {
    public int dia;
    public int mes;
    public int año;
    public Fecha(Object dia, Object mes, Object año){
        this.dia = (int)dia;
        this.mes = (int)mes;
        this.año = (int)año;
    }

    @Override
    public String toString(){
        return dia+"-"+mes+"-"+año;
    }
}
