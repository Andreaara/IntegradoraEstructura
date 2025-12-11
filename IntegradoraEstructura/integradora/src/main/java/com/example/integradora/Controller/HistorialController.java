package com.example.integradora.Controller;

import com.example.integradora.Clases.Historial;
import com.example.integradora.dto.RespuestaRequest;
import com.example.integradora.service.PrestamosDeLibros;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/history")
public class HistorialController {

    private final PrestamosDeLibros prestamosService;

    public HistorialController(PrestamosDeLibros prestamosService) {
        this.prestamosService = prestamosService;

    }


    @GetMapping
    public ResponseEntity<RespuestaRequest> ObtenerHistorial() {

        Object historyList = prestamosService.gethistorials();
        return new ResponseEntity<>(
                new RespuestaRequest( "Listado de historial", historyList),
                HttpStatus.OK
        );
    }

    @PostMapping("/undo")
    public ResponseEntity<RespuestaRequest> UNDO() {

        String resultMessage = prestamosService.deshacerAcion();

        if (resultMessage.startsWith("error")) {
            return new ResponseEntity<>(new RespuestaRequest( resultMessage, null), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new RespuestaRequest(resultMessage, null), HttpStatus.OK);
    }
}
