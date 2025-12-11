package com.example.integradora.service;


import com.example.integradora.ArrayQueue.ArrayQueue;
import com.example.integradora.Clases.Accion;
import com.example.integradora.Clases.Libro;
import com.example.integradora.Clases.Reserva;
import com.example.integradora.Clases.Usuario;
import org.springframework.stereotype.Service;

@Service
public class Reservas {

    private final PrestamosDeLibros prestamosLibros;

    public Reservas(PrestamosDeLibros prestamosLibros) {
        this.prestamosLibros = prestamosLibros;
    }


    public String consultarReservas(int libroId, Integer usuarioId) {


        Libro libro = prestamosLibros.getLibros().buscarId(libroId);

        if (libro == null) {
            return " Libro no encontrado";
        }

        ArrayQueue<Usuario> reservas = libro.getReservas();

        if (reservas == null || reservas.isEmpty()) {
            return "No hay reservas activas para este libro";
        }

        if (usuarioId != null) {
            int posicion = reservas.PosicionUsuarioId(usuarioId.intValue());

            if (posicion >= 0) {
                return "Usuario " + usuarioId + "su posición en la lista de espera es de: " + (posicion + 1);
            } else {
                return "El Usuario " + usuarioId + " no está en la lista de espera";
            }
        } else {
            return "Total de personas en la lista de espera: " + reservas.getSize();
        }
    }


    public String cancelarEliminarReserva(int usuarioId, int libroId) {

        Libro libro = prestamosLibros.getLibros().buscarId(libroId);

        if (libro == null) {
            return "Libro no encontrado";
        }

        ArrayQueue<Usuario> reservas = libro.getReservas();

        if (reservas == null || reservas.isEmpty()) {
            return "El libro no tiene lista de espera activa";
        }

        boolean usuarioRemovido = reservas.eliminarUsuarioReserva(usuarioId);

        if (usuarioRemovido) {
            Accion accion = new Accion("REMOVE_FROM_WAITLIST", libroId, usuarioId);
            prestamosLibros.gethistorials().push(accion);

            return "Reserva del Usuario " + usuarioId + " para el libro " + libroId;
        } else {
            return "El Usuario " + usuarioId + " no tenía una reserva para el libro";
        }
    }




}
