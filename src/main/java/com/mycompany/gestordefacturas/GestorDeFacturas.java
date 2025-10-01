/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestordefacturas;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

/**
 *
 * @author m
 */
public class GestorDeFacturas {
    ArrayList<ValidableObj<?>> validadores; 
    ArrayList<Factura> facturas; 
    JList lista;
    Vista vista;
    

    public GestorDeFacturas(Vista vista){
        this.vista = vista;
        this.facturas = new ArrayList<>();
        validadores = new ArrayList<>();
        validadores.add(
            new ValidableObj<String>(
            s -> s != null && s.length() >= 1 && s.length() <= 10,
            "el mensaje debe estar entre 1 y 10 caracteres",
            "j1")

            );
        // validadores.add(
        //     new ValidableObj<String>(
        //     s -> s != null && s.length() >= 1 && s.length() <= 31,
        //     "el dia debe ser correcto",
        //     "diaBoton")

        //     );
        // validadores.add(
        //     new ValidableObj<String>(
        //     s -> s != null && s.length() >= 1 && s.length() <= 12,
        //     "el mes debe ser correcto",
        //     "mesBoton")

        //     );
        // validadores.add(
        //     new ValidableObj<String>(
        //     s -> s != null && s.length() >= 1925 && s.length() <= 2025,
        //     "el año debe ser correcto",
        //     "añoBoton")

        //     );
        // validadores.add(
        //     new ValidableObj<String>(
        //     s -> s != null && s.length() >= 0,
        //     "la cantidad debe ser superior a 0",
        //     "cantidadBoton")

        //     );
    }
    @SuppressWarnings("unchecked")
    public <Q> ValidableObj<Q> searchValidator(String v){
        return (ValidableObj<Q>) validadores.stream()
                                    .filter(s -> s.id.equals(v))
                                    .findFirst()
                                    .orElse(null);
        }

    public Object getResult(String id){
        return searchValidator(id).condition;
    }


    public void go(){
        for (ValidableObj<?> validableObj : validadores) {
            if(!validableObj.check()){
                vista.changeErrorMesage(true,validableObj.errorMesage);
                return;
            }
        }
        if(!facturas.stream().anyMatch(s->s.asunto.equals(getResult("asuntoBoton")))){
            vista.changeErrorMesage(true,"Esta factura ya existe");
            return;
        }
        vista.changeErrorMesage(false,"Correctamente añadido");
        Fecha fecha = new Fecha(getResult("diaBoton"),getResult("mesBoton"),getResult("añoBoton"));
        facturas.add(Factura.create(getResult("asuntoBoton"),getResult("cantidadBoton"),fecha,getResult("cantidadBoton")));
    }


    public Factura buscarFactura(){
        return facturas.stream().find(f->f.toString().equals(s)).orElse(null);
    }
    public void updateText(){
        lista.clear();
        facturas.forEach(lista.add(f->f.toString()));
    }

    public boolean deleteFactura(String s){
        Factura factura = buscarFactura(s);
        if(factura==null){
            return false;
        }
        facturas.remove(factura);
        updateText();
        return true;
    }

    public boolean editarFactura(String s){
        Factura factura = buscarFactura(s);
        if(factura==null){
            return false;
        }
        facturas.remove(factura);
        updateText();
        JTextField label = null;
        //repetir en clas
        label.setText(factura.asunto);
        //repetir en clas


        return true;
    }
}
