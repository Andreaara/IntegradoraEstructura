package com.example.integradora.ArrayQueue;

import com.example.integradora.Clases.Usuario;

public class ArrayQueue<T> implements IQueue<T> {
    
    private Object[] data;
    private int rear; //indice de inserccion
    private int front; //Indica el elemento al frente de la queue
    private int size; //numero de elemetnos en la queue

    private static final int INITIAL_CAPACITY=10; //Constante para el tamaño

    public ArrayQueue(){
        this.data = new Object[INITIAL_CAPACITY];
        this.rear = 0;
        this.front = 0;
        this.size = 0;
    }

    @Override
    public void offer(T element) {
       //Verificar la capacidad del array
       expandCapacity();
       data[rear] = element; //pone ele elemento en el indice asignado (rear)
       rear = (rear +1) % data.length;//recalcula rear , pero evita que se desborde
       size++; 
    }

    @SuppressWarnings("unchecked")
    @Override
    public T poll() {
        if(isEmpty()){
            System.out.println("La Queue esta vacia");
            return null;
        }
        T result = (T) data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        return result;
    }

   
    @Override
    public Object peek() {
        if(isEmpty()){
            System.out.println("La Queue esta vacia");
            return null;
        }
        return data [front];
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }

    private void expandCapacity(){
        Object[] newArr = new Object[data.length*2];//creacion de nuevo arreglo
        for (int i = 0; i < size; i++) {
            newArr[i] = data [(front + i )%data.length];//vaciado de la info comensazndo desde front

        }
        //reiniciamos los valores del arreglo para poder seguir usando 
        front = 0;//pone el frente en la primera posicion
        data = newArr;//Asignada el nuevo arreglo del atributo data
        rear =size;//indice de la siguiente insercion
    }

    @Override
    public void print() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[(front+i)%data.length]);
            if(i<size-1)sb.append("=>");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    @Override
    public boolean isEmpty() {
        return size == 0;//veroifica si el tamaño es 0
    }

    @Override
    public int getSize() {
        return size;
    }


    public int PosicionUsuarioId(int Id) {
        if (size == 0) {
            return -1;
        }
        for (int i = 0; i < size; i++) {

            int currentIndex = (front + i) % data.length;


            Usuario user = (Usuario) data[currentIndex];

            if (user.getId() == Id) {
                return i;
            }
        }
        return -1;
    }

    public boolean eliminarUsuarioReserva(int Id) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (((Usuario) data[i]).getId() == Id) {
                index = i;
                break;
            }
        }

        if (index == -1) return false;

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[--size] = null;
        return true;
    }
}