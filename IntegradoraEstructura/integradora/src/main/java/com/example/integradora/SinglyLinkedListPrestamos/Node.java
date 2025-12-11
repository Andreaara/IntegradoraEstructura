package com.example.integradora.SinglyLinkedListPrestamos;

import com.example.integradora.Clases.Prestamo;

public class Node {

    Prestamo data;
    Node next;

    public Node(Prestamo data) {
        this.data = data;
        this.next = null;

    }
}
