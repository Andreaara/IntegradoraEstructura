package com.example.integradora.Controller;


import com.example.integradora.dto.LibroRequest;
import com.example.integradora.dto.RespuestaRequest;
import com.example.integradora.service.GestionDeLibros;
import com.example.integradora.Clases.Libro;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class LibrosController {

    private final GestionDeLibros gestionDeLibros;

    public LibrosController(GestionDeLibros gestionDeLibros) {
        this.gestionDeLibros = gestionDeLibros;
    }


    @PostMapping
    public ResponseEntity<RespuestaRequest> agregarLibros(@RequestBody Libro nuevoLibro) {
        gestionDeLibros.agregarLibros(nuevoLibro);
        return new ResponseEntity<>(
                new RespuestaRequest( "Libro agregado con éxito.", nuevoLibro),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<RespuestaRequest> CatalogoLibros() {
        Object lista = gestionDeLibros.ConsultarCatalogo();
        return new ResponseEntity<>(
                new RespuestaRequest( "Lista de libros obtenida.", lista),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaRequest> BuscarLibroId(@PathVariable int id) {
        Libro book = gestionDeLibros.buscarLibroId(id);
        if (book == null) {
            return new ResponseEntity<>(
                    new RespuestaRequest( "Libro ID " + id + " no encontrado.", null),
                    HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(
                new RespuestaRequest( "Libro encontrado.", book),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaRequest> ActualizarLibroID(@PathVariable int id, @RequestBody LibroRequest libroactualizado) {
        String result = gestionDeLibros.actualizarLibroId(id, libroactualizado);
        if (result.startsWith("error")) {
            return new ResponseEntity<>(new RespuestaRequest( result, null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new RespuestaRequest( result, null), HttpStatus.OK);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<RespuestaRequest> ActualizarLibroEstado(@PathVariable int id, @RequestParam boolean estado) {
        String result = gestionDeLibros.ActualizarEstadoLibro(id, estado);
        if (result.startsWith("error")) {
            return new ResponseEntity<>(new RespuestaRequest( result, null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new RespuestaRequest( result, null), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<RespuestaRequest> busquedaLibroTitulo(@RequestParam String titulo) {
        Object results = gestionDeLibros.BuscarLibroTitulo(titulo);
        return new ResponseEntity<>(
                new RespuestaRequest( "Resultados de búsqueda: " + titulo, results),
                HttpStatus.OK
        );
    }
}
