package com.example.integradora.Controller;

import com.example.integradora.dto.RespuestaRequest;
import com.example.integradora.service.Reservas;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservasController {

    private final Reservas reservasService;

    public ReservasController(Reservas reservasService) {
        this.reservasService = reservasService;
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<RespuestaRequest> ReservaEstado(
            @PathVariable int libroId,
            @RequestParam(required = false) Integer UsuarioId) {

        String resultMessage = reservasService.consultarReservas(libroId, UsuarioId);

        return new ResponseEntity<>(new RespuestaRequest("exito"+ resultMessage, null), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<RespuestaRequest> cancelarReserva(
            @RequestParam int usuarioId,
            @RequestParam int libroId) {

        String resultMessage = reservasService.cancelarEliminarReserva(usuarioId, libroId);

        if (resultMessage.startsWith("error")) {
            return new ResponseEntity<>(new RespuestaRequest("error"+ resultMessage, null), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new RespuestaRequest( resultMessage, null), HttpStatus.OK);
    }
}
