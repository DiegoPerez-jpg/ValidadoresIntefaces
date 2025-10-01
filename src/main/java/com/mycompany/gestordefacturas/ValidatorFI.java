package com.mycompany.gestordefacturas;
@FunctionalInterface
public interface ValidatorFI<T> {
    boolean check(T objetoAValidar);
}
