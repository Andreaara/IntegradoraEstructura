package com.example.integradora.SinglyLinkedListUsuario;


import com.example.integradora.Clases.Usuario;

public class Node {

        Usuario data;
        public Node next;

        public Node(Usuario data) {
            this.data = data;
            this.next = null;
        }
}
