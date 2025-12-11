package com.example.integradora.Controller;

import com.example.integradora.dto.PrestamoRequest;
import com.example.integradora.dto.RespuestaRequest;
import com.example.integradora.service.PrestamosDeLibros;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
public class PrestamoController {

    private final PrestamosDeLibros prestamosService;

    public PrestamoController(PrestamosDeLibros prestamosService) {
        this.prestamosService = prestamosService;
    }

    @PostMapping
    public ResponseEntity<RespuestaRequest> CrearPrestamo(@RequestBody PrestamoRequest request) {

        String resultMessage = prestamosService.nuevoPrestamo(
                request.getIdLibro(), request.getIdUsuario());

        return new ResponseEntity<>(
                new RespuestaRequest( "se logro" +resultMessage , null),
                HttpStatus.OK
        );
    }

    @PostMapping("/{prestamoId}/return")
    public ResponseEntity<RespuestaRequest> Devolver(@PathVariable int prestamoId) {

        String resultMessage = prestamosService.devolverLibro(prestamoId);

        if (resultMessage.startsWith("error")) {
            return new ResponseEntity<>(new RespuestaRequest("error"+ resultMessage, null), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new RespuestaRequest("exito"+resultMessage, null), HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<RespuestaRequest> Obtenerprestamosactivos() {
        Object activeLoans = prestamosService.PrestamosActivos();
        return new ResponseEntity<>(new RespuestaRequest( "Prestamos activos obtenidos", activeLoans), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<RespuestaRequest> PrestamosporUsuario(@PathVariable int userId) {
        Object userLoans = prestamosService.prestamosUsuarioId(userId);
        return new ResponseEntity<>(new RespuestaRequest("Pretamos del usuario " + userId, userLoans), HttpStatus.OK);
    }
}