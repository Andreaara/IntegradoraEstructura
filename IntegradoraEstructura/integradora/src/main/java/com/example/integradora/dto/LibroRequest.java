package com.example.integradora.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroRequest {
    private String titulo;
    private int copias;
    private boolean estadoLibro;
}
