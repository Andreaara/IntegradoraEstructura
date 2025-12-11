package com.example.integradora.service;

import com.example.integradora.ArrayQueue.ArrayQueue;
import com.example.integradora.Clases.Libro;
import com.example.integradora.SinglylinkedListLibro.SinglyLinkedList;
import com.example.integradora.dto.LibroRequest;
import org.springframework.stereotype.Service;

@Service
public class GestionDeLibros {

    SinglyLinkedList lista = new SinglyLinkedList();
    ArrayQueue cola = new ArrayQueue();//reservas

public  GestionDeLibros(){
    Libro l1 = new Libro(1, "El principito", 2,1,true, cola);
    Libro l2 = new Libro(2, "Don Quijote", 3,3,false, cola);
    Libro l3 = new Libro(3, "Caperucita roja", 2,1,true, cola);

    lista.add(l1);
    lista.add(l2);
    lista.add(l3);
}


    public Libro agregarLibros(Libro nuevoLibro){


        nuevoLibro.setReservas(cola);
        lista.add(nuevoLibro);
        return nuevoLibro;

    }


    public Object ConsultarCatalogo(){
        return this.lista.ListaLibros();
    }


    public Libro buscarLibroId(int idlibro){

        Libro libro = lista.buscarId(idlibro);

        if (libro == null) {
            System.out.println("libro no encontrado");
        }
        return libro;

    }


        public String actualizarLibroId(int idlibro, LibroRequest libroActualizado) {

            Libro libroExistente = lista.buscarId(idlibro);

            if (libroExistente == null) {
                System.out.println("Libro no encontrado");
                return "Libro con ID no encontrado para actualizar";
            }

            libroExistente.setTitulo(libroActualizado.getTitulo());
            libroExistente.setCopias(libroActualizado.getCopias());

            libroExistente.setCopiasDisponibles(libroActualizado.getCopias());

            return "Libro actualizado con exito.";
        }



    public String ActualizarEstadoLibro(int idLibro, boolean libroActualizado) {

        Libro libroExistente = lista.buscarId(idLibro);

        if (libroExistente == null) {
            System.out.println("libro no encontrado");
        }
        libroExistente.setEstadoLibro(libroActualizado);

        String estadoTexto = libroActualizado ? "ACTIVO" : "INACTIVO";

        return "Libro actualizado con exito ahora su estado es " + estadoTexto;
    }


    public Libro BuscarLibroTitulo(String tituloLibro){

        Libro libro = lista.buscarTitulo(tituloLibro);
        if (libro == null) {
            System.out.println("libro encontrado");
        }
        return libro;
    }




}
