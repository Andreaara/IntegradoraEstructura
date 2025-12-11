package com.example.integradora.SinglyLinkedListPrestamos;


import com.example.integradora.Clases.Prestamo;

public class SinglyLinkedListP {

    Node head;

    public void add(Prestamo data){//Creación de un nuevo nodo
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


    public Prestamo buscarIdPrestamo(int idPrestamo) {
        Node current = head;

        while (current != null) {

            if (current.data.getId() == idPrestamo) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public boolean contains(Prestamo data){

        Node current = head;

        while(current != null){
            if(current.data == data){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean remove(Prestamo data){
        if(head == null){//pregunta si la lista esta vacia
            return false;
        }
        if(head.data==data){//valiuda que el dato este en el head
            return false;
        }

        Node current = head;
        while(current.next != null && current.next.data !=data){//Recorrer hasta encontrar el valor
            current=current.next;
        }

        current.next = current.next.next;

        return false;
    }

    public void printList(){
        Node current= head;
        while(current != null){
            System.out.println(current.data);
            current =current.next;
        }
        System.out.println("null");
    }

    public SinglyLinkedListP PrestamosActivos() {

        SinglyLinkedListP activeList = new SinglyLinkedListP();//inilizacion de mi lista

        Node current = head;

        while (current != null) {

            Prestamo prestamo = current.data;//obtengo lo del prestamo del nodo

            if (prestamo.getEstado() == true) {
                activeList.add(prestamo);
            }


            current = current.next;
        }

        return activeList;
    }

    public SinglyLinkedListP Prestamosuario(int usuarioId) {

        SinglyLinkedListP Usuarios = new SinglyLinkedListP();

        Node current = this.head;

        while (current != null) {

            Prestamo prestamo = current.data;

            if (prestamo.getUsuarioId() == usuarioId) {
                Usuarios.add(prestamo);
            }


            current = current.next;
        }

        return Usuarios;
    }



    public int Contador(Prestamo data) {
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
}
