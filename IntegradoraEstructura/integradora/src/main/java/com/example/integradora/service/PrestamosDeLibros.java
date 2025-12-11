package com.example.integradora.service;

import com.example.integradora.ArrayQueue.ArrayQueue;
import com.example.integradora.ArrayStack.ArrayStack;
import com.example.integradora.Clases.*;
import com.example.integradora.SinglyLinkedListPrestamos.SinglyLinkedListP;
import com.example.integradora.SinglylinkedListLibro.SinglyLinkedList;
import org.springframework.stereotype.Service;


@Service
public class PrestamosDeLibros {

    SinglyLinkedList Libros = new SinglyLinkedList();
    ArrayStack historials = new ArrayStack();
    SinglyLinkedListP Prestamos = new SinglyLinkedListP();


    private int nextLoanId = 1;


    public void prestamosPreterminados() {
        Prestamo p1 = new Prestamo(nextLoanId++, true, 1, 1, "No devuelto");
        Prestamo p2 = new Prestamo(nextLoanId++, true, 2, 1, "No devuelto");

       Prestamos.add(p1);
       Prestamos.add(p2);

    }

    public String nuevoPrestamo(int idlibro, int idUsuario) {



        Libro libro = Libros.buscarId(idlibro);

        if (libro == null) {
            return "Error: Libro no encontrado o dado de baja.";
        }

        if(libro.getCopiasDisponibles()>0) {

            Prestamo nuevoPrestamo = new Prestamo(nextLoanId++, true, idUsuario, idlibro, "Activo");
            Prestamos.add(nuevoPrestamo);
            libro.setCopiasDisponibles(libro.getCopiasDisponibles() - 1);


            Accion accion = new Accion("CREATE_LOAN", nuevoPrestamo.getId(), idlibro);
            historials.push(accion);

            return "Préstamo creado con éxito con el ID: " + nuevoPrestamo.getId();
        }else{

            Accion accion = new Accion("ADD_TO_WAITLIST", idlibro, idUsuario);
            historials.push(accion);

            return "Reserva creada con exitoo";
        }

    }

    public SinglyLinkedListP PrestamosActivos() {
        System.out.println("Prestamos Activos: ");
        return Prestamos.PrestamosActivos();
    }

   public SinglyLinkedListP prestamosUsuarioId(int usuarioId){
       System.out.println("Prestmos usuario id: ");
       return Prestamos.Prestamosuario(usuarioId);
   }

    public String devolverLibro(int prestamoId) {

        Prestamo prestamo = Prestamos.buscarIdPrestamo(prestamoId);

        if (prestamo == null || !prestamo.getEstado()) {
            return "Préstamo no encontrado o ya devuelto.";
        }

        int libroId = prestamo.getLibroId();


        prestamo.setEstado(false);
        prestamo.setDevolucion("DEVUELTO");

        Accion accionDevolver = new Accion("RETURN_LOAN", prestamoId, libroId);
        historials.push(accionDevolver);

        String resultado = "Devolución completada para el Préstamo ID " + prestamoId;

        Libro libro = Libros.buscarId(libroId);

        ArrayQueue<Usuario> listaReservas = libro.getReservas();
        libro.getCopiasDisponibles();


        if (listaReservas != null  && !listaReservas.isEmpty()) {

            int siguienteUsuarioId = listaReservas.poll().getId();


            Prestamo nuevoPrestamo = new Prestamo(nextLoanId++, true, siguienteUsuarioId, libroId, "ACTIVO");
            Prestamos.add(nuevoPrestamo);

            historials.push(new Accion("REMOVE_FROM_WAITLIST", libroId, siguienteUsuarioId));
            historials.push(new Accion("CREATE_LOAN", nuevoPrestamo.getId(), libroId));

            resultado += "Asignación automática: Nuevo préstamo creado para el Usuario " + siguienteUsuarioId ;

        } else {

            libro.setCopiasDisponibles(libro.getCopiasDisponibles() + 1);

            historials.push(new Accion("INCREMENT_COPIES", libroId, 0));

            resultado += "La copia se ha reincorporado al catálogo.";
        }

        return resultado;
    }


    public String deshacerAcion(){

        if (historials.isEmpty()) {
            return "Error: No hay acciones recientes que deshacer.";
        }

        Accion ultimaAccion = historials.pop();
        String tipo = ultimaAccion.getTipo();
        int primerId = ultimaAccion.getPrimerAccion();
        int relacionId = ultimaAccion.getRelacion();

        switch (tipo) {

            case "CREATE_LOAN":

                boolean removido = Prestamos.remove(new Prestamo(primerId));

                if (removido) {

                    Libro libro = Libros.buscarId(relacionId);
                    libro.setCopiasDisponibles(libro.getCopiasDisponibles() + 1);
                    return " Se eliminó el Préstamo ID " + primerId ;
                }
                return "fallido Préstamo ID " + primerId + " no encontrado o ya fue revertido";


            case "RETURN_LOAN":

                Prestamo prestamo = Prestamos.buscarIdPrestamo(primerId);
                if (prestamo != null) {
                    prestamo.setEstado(true);
                    prestamo.setDevolucion("Activo");

                    Libro libro = Libros.buscarId(relacionId);

                    return "Se reactivó el Préstamo ID " + primerId ;
                }
                return "Fallo el préstamo ID " + primerId ;

            case "ADD_TO_WAITLIST":

                Libro libro = Libros.buscarId(primerId);
                if (libro != null && libro.getReservas() != null) {

                    boolean removidoDeCola = libro.getReservas().eliminarUsuarioReserva(relacionId);
                    if(removidoDeCola) {
                        return "Se eliminó al Usuario " + relacionId + " de la lista de espera del Libro ";
                    }
                }
                return "Usuario o Libro no encontrado en la lista de espera";


            case "REMOVE_FROM_WAITLIST":

                Libro libroRes = Libros.buscarId(primerId);
                if (libroRes != null) {

                    Usuario usuarioReversa = new Usuario();
                    libroRes.getReservas().offer(usuarioReversa);
                    return "Se reinsertó al Usuario " + relacionId + " en la lista de espera del Libro " + primerId ;
                }
                return "UNDO fallido: Libro no encontrado.";


            case "INCREMENT_COPIES":

                Libro libroCopias = Libros.buscarId(primerId);
                if (libroCopias != null) {
                    libroCopias.setCopiasDisponibles(libroCopias.getCopiasDisponibles() - 1);
                    return "Se retiró una copia del Libro " + primerId ;
                }
                return " Libro no encontrado.";

            default:
                return "Esa acción no existe ";
        }
    }


    public SinglyLinkedList getLibros() {
        return this.Libros;
    }

    public ArrayStack<Accion> gethistorials() {
        return this.historials;
    }

}
