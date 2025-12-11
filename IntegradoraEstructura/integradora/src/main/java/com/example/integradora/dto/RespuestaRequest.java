package com.example.integradora.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaRequest {

    private String mensaje;
    private Object data;


}
