package com.example.integradora.Clases;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prestamo {

    private int id;
    private Boolean estado;
    private int usuarioId;
    private int libroId;
    private String devolucion;


    public Prestamo(int id) {
        this.id = id;
    }
}
