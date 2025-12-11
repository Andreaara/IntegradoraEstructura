package com.example.integradora.Clases;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accion {

    public String tipo;
    public int primerAccion;
    public int relacion;


}
