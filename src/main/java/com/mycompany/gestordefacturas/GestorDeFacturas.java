/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestordefacturas;

import java.awt.Color;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;
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
         validadores.add(
             new ValidableObj<Integer>(
             s -> s != null && s >= 1 && s <= 31,
             "el dia debe ser correcto",
             "diaBoton")

             );
         validadores.add(
             new ValidableObj<Integer>(
             s -> s != null && s >= 1 && s <= 12,
             "el mes debe ser correcto",
             "mesBoton")

             );
         validadores.add(
             new ValidableObj<Integer>(
             s -> s != null && s >= 1925 && s <= 2025,
             "el año debe ser correcto",
             "añoBoton")

             );
         
        validadores.add(
            new ValidableObj<String>(
            s -> {
                try {
                    Integer.parseInt(s);
                    return true;
                } catch (Exception e) {
                    return false;
                }
                },
            "la cantidad debe ser un numero",
            "cantidadBoton"));
        validadores.add(
             new ValidableObj<String>(
             s -> s != null && Integer.parseInt(s) >= 0,
             "la cantidad debe ser superior a 0",
             "cantidadBoton"));
        validadores.add(
            new ValidableObj<String>(
            (s) -> facturas.stream().noneMatch(f -> f.asunto.equals(s)),
            "El asunto no puede repetirse",
            "j1")
        );
        validadores.add(
                    new ValidableObj<String>(
                    (s)->{return true;},
                    "El asunto no puede repetirse",
                    "empresaBoton")
                );
    }
    @SuppressWarnings("unchecked")
    public <Q> ValidableObj<Q> searchValidator(String v){
        return (ValidableObj<Q>) validadores.stream()
                                    .filter(s -> s.id.equals(v))
                                    .findFirst()
                                    .orElse(null);
        }
    @SuppressWarnings("unchecked")
    public <Q> ArrayList<ValidableObj<Q>> searchValidators(String v){
        return  validadores.stream()
                                    .filter(s -> s.id.equals(v))
                                    .map(s -> (ValidableObj<Q>) s)
                                    .collect(Collectors.toCollection(ArrayList::new));
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
        vista.changeErrorMesage(false,"Correctamente añadido");
        Fecha fecha = new Fecha(getResult("diaBoton"),getResult("mesBoton"),getResult("añoBoton"));
        facturas.add(Factura.create(getResult("j1"),getResult("cantidadBoton"),fecha,getResult("empresaBoton")));
        updateText();
    }


    public Factura buscarFactura(String s){
        return facturas.stream().filter(f->f.toString().equals(s)).findAny().orElse(null);
    }
    public void updateText(){
        vista.updateText(facturas.stream().map(f->f.toString()).collect(Collectors.toCollection(ArrayList::new)));
    }

    public boolean deleteFactura(String s){
        Factura factura = buscarFactura(s);
        if(factura==null){
            System.out.println(s);
            return false;
        }
        facturas.remove(factura);
        updateText();
        return true;
    }

    public void changeCondition(String id, Object condition){
        searchValidators(id).stream().forEach(s->s.condition=condition);
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
        vista.año.setValue(factura.fecha.año);
        vista.mes.setValue(factura.fecha.mes);
        vista.dia.setValue(factura.fecha.dia);
        vista.empresas.setSelectedItem(factura.tipo);
        vista.jTextField1.setText(factura.asunto);
        vista.cantidad.setText(factura.cantidad+"");
        //repetir en clas


        return true;
    }
}
