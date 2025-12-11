package com.example.integradora.SinglyLinkedListUsuario;


import com.example.integradora.Clases.Libro;
import com.example.integradora.Clases.Usuario;

public class SinglyLinkedList {

    Node head;

    public void add(Usuario data){//Creación de un nuevo nodo
        Node newNode = new Node(data); //Verificación de lista vacia
        if(head == null){
            head=newNode;
            return;
        }
        Node current=head;
        while(current.next !=null){//Recorrido de los odos, hasta encontrar el ultimo
            current=current.next;
        }
        current.next=newNode; //Insertar nuevo nodo

    }

    public Usuario buscarId(int UsuarioId) {

        Node current = head;

        while (current != null) {

            if (current.data.getId() == UsuarioId) { //el nodo es lo mismo que suario
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public boolean contains(Usuario data){

        Node current = head;

        while(current != null){
            if(current.data == data){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void remove(Usuario data){
        if(head == null){//pregunta si la lista esta vacia
            return;
        }
        if(head.data==data){//valiuda que el dato este en el head
            return;
        }

        Node current = head;
        while(current.next != null && current.next.data !=data){//Recorrer hasta encontrar el valor
            current=current.next;
        }

        current.next = current.next.next;

    }

    public void printList(){
        Node current= head;
        while(current != null){
            System.out.println(current.data);
            current =current.next;
        }
        System.out.println("null");
    }

    public int Contador(Usuario data) {
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

    public Object[] obtenerLista() {

        int listSize = size();//saber el total de elementos

        if (listSize == 0) {
            return new Object[0];
        }

        Object[] elemento = new Object[listSize];//se define pq la lis
        Node current = head;
        int i = 0;

        while (current != null) {

            elemento[i++] = current.data;//agrega ese elmento a la lista
            current = current.next;
        }
        return elemento;
    }

    public int size() {// este me calculo el numero de nodos y deberia devolver el nmero
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}
