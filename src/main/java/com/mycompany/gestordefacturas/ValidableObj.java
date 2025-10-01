package com.mycompany.gestordefacturas;

public  class ValidableObj<T> {


    ValidatorFI<T> validador;
    String errorMesage;
    public T condition;
    public String id;

    public ValidableObj(ValidatorFI<T> validador,
    String errorMesage,
    String id){
        this.validador = validador;
        this.errorMesage = errorMesage;
        this.id = id;
    }

    void setCondition(Object condition){
        this.condition = (T)condition;
    }
    boolean check(){
        return validador.check(condition);
    }
}
