package com.example.integradora.service;

import com.example.integradora.Clases.Usuario;
import com.example.integradora.SinglyLinkedListUsuario.SinglyLinkedList;
import org.springframework.stereotype.Service;


@Service
public class Usuarios {

   SinglyLinkedList usuarios = new SinglyLinkedList();

    public Usuarios() {
        Usuario u1 = new Usuario(1, "Aldo Garcia", "aldo@gmail.com");
        Usuario u2 = new Usuario(2, "Diana Vasquez", "diana@gmail.com");
        Usuario u3 = new Usuario(3, "Alonso Martinez", "alonso@gmail.com");
        Usuario u4 = new Usuario(4, "Alejandro Mar", "alejandro@gmail.com");

        usuarios.add(u1);
        usuarios.add(u2);
        usuarios.add(u3);
        usuarios.add(u4);
    }

    public Usuario crearUsuario (Usuario usuario) {

        usuarios.add(usuario);
        return usuario;
    }


    public Object ListarUsuarios(){
        return usuarios.obtenerLista();
    }

    public Usuario buscarUsuario (int idUsuario){

        Usuario usuario = usuarios.buscarId(idUsuario);
        if (usuario == null) {
            System.out.println("libro no encontrado");
        }
        return usuario;
    }


}