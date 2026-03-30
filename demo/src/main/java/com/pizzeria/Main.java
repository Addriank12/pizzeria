package com.pizzeria;

import dataAcces.model.Cliente;
import dataAcces.model.Empleado;
import dataAcces.model.Empleado_Cargo;
import dataAcces.repository.Cliente_Repository;
import dataAcces.repository.Empleado_Repository;

public class Main {
    static Cliente_Repository cliente_repository = new Cliente_Repository();
    
    
    public static void main(String[] args) {
        Cliente cliente = new Cliente(0, "0106862832", "Nicolas Cordero", "Del rosedal 4-92", "09995112970", "nccmac2003@hotmail.com");
        cliente_repository.Insert(cliente);
        System.out.println("Hello world!");
    }
}