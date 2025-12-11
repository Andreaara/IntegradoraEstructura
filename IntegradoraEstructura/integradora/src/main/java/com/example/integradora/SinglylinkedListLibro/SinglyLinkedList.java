package com.example.integradora.SinglylinkedListLibro;


import com.example.integradora.Clases.Libro;

public class SinglyLinkedList {

    Node head;

    public void add(Libro data) {//Creación de un nuevo nodo
        Node newNode = new Node(data); //Verificación de lista vacia
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null) {//Recorrido de los odos, hasta encontrar el ultimo
            current = current.next;
        }
        current.next = newNode; //Insertar nuevo nodo

    }

    public Libro buscarId(int libroId) {
        Node current = head;

        while (current != null) {

            if (current.data.getId() == libroId) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public Libro buscarTitulo(String tituloLibro) {
        Node current = head;

        while (current != null) {

            if (current.data.getTitulo().equals(tituloLibro)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public Object[] ListaLibros() {

        int listSize = size();

        if (listSize == 0) {
            return new Object[0];
        }

        Object[] elemento = new Object[listSize];
       Node current = head;
        int i = 0;

        while (current != null) {

            elemento[i++] = current.data;//agrega ese elmento a la lista
            current = current.next;
        }
        return elemento;
    }

    public boolean contains(Libro data) {

        Node current = head;//current apunta al primer nodo

        while (current != null) {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void remove(Libro data) {
        if (head == null) {//pregunta si la lista esta vacia
            return;
        }
        if (head.data == data) {//valiuda que el dato este en el head
            return;
        }

        Node current = head;
        while (current.next != null && current.next.data != data) {//Recorrer hasta encontrar el valor
            current = current.next;
        }

        current.next = current.next.next;

    }

    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
        System.out.println("null");
    }

    public int Contador(Libro data) {
        int count = 0;
        Node current = head;

        while (current != null) {
            if (current.data == data) {
                count++;
            }
            current = current.next;
        }

        return count;
    }

    public int size() {// este me calculo el numero de nodos y deberia devolver
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

}