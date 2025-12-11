package com.example.integradora.Clases;

import com.example.integradora.ArrayQueue.ArrayQueue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Libro {

    private int id;
    private String titulo;
    private int Copias;
    private int CopiasDisponibles;
    private Boolean estadoLibro;
    private ArrayQueue<Usuario> reservas;


}
