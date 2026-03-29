package com.pizzeria;

import dataAcces.model.Empleado;
import dataAcces.model.Empleado_Cargo;
import dataAcces.repository.Empleado_Repository;

public class Main {
    static Empleado_Repository empleado_repository = new Empleado_Repository();
    
    
    public static void main(String[] args) {
        Empleado empleado = new Empleado(0, "0106862832", "Nicolas Cordero", "Del rosedal 4-92", "09995112970", "nccmac2003@hotmail.com", Empleado_Cargo.mesero);
        empleado_repository.Insert(empleado);
        System.out.println("Hello world!");
    }
}