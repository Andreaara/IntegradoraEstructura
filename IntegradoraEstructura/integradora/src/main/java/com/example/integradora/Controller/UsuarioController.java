package com.example.integradora.Controller;

import com.example.integradora.Clases.Usuario;
import com.example.integradora.dto.RespuestaRequest;
import com.example.integradora.service.Usuarios;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsuarioController {

    private final Usuarios usuarioService;

    public UsuarioController(Usuarios usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<RespuestaRequest> crearUsuario(@RequestBody Usuario nuevoUsuario) {
        Usuario createdUser = usuarioService.crearUsuario(nuevoUsuario);
        return new ResponseEntity<>(
                new RespuestaRequest( "Usuario creado", createdUser),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<RespuestaRequest> ListarUsuarios() {
        Object lista = usuarioService.ListarUsuarios();
        return new ResponseEntity<>(
                new RespuestaRequest("Lista de usuarios", lista),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaRequest> BuscarUsuario(@PathVariable int id) {
        Usuario user = usuarioService.buscarUsuario(id);
        if (user == null) {
            return new ResponseEntity<>(
                    new RespuestaRequest( "Usuario no encontrado", null),
                    HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(new RespuestaRequest("Usuario encontrado.", user), HttpStatus.OK);
    }
}
