package com.example.integradora.Clases;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Historial {

    private String tipoAccion;
    private int listado;
    private String objetoAfectado;
    private String reversion;

}
