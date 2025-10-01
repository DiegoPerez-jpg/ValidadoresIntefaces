/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestordefacturas;

import java.util.ArrayList;

import javax.swing.JLabel;

/**
 *
 * @author m
 */
public class GestorDeFacturas {
    ArrayList<ValidableObj<?>> validadores; 
    JLabel errorLabel;
    

    public GestorDeFacturas(JLabel errorLabel){
        this.errorLabel = errorLabel;
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

    public void go(){
        for (ValidableObj<?> validableObj : validadores) {
            if(!validableObj.check()){
                errorLabel.setText(validableObj.errorMesage);
                return;
            }
        }
        errorLabel.setText("");
    }
  
}
