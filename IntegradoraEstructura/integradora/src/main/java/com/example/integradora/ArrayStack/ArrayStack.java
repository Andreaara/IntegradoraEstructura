package com.example.integradora.ArrayStack;

import com.example.integradora.Clases.Accion;

public class ArrayStack<T> implements IStack<Accion> {
    
    private Object[] data;
    private int top; //top = 0

    //Constructor 1
    public ArrayStack(){
        this(10);//invoca al constructor 2

    }

    //Constructor 2
    public ArrayStack(int initialCapacity){
        this.data = new Object[initialCapacity];
        this.top = 0;
    }

    @Override
    public void push(Accion element) {
        //nos vamos a asegurar que aun tenga espacio el array
        data[top++] = (T) element;
        

    }

    @SuppressWarnings("unchecked")
    @Override
    public Accion pop() {
        if(isEmpty()){
            System.out.println("La pila esta vacia");
            return null;
        }
        Accion value = (Accion) data[--top];
        data[top]=null;
        return value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Accion peek() {
        if(isEmpty()){
            System.out.println("La pila esta vacia");
            return null;
        }
        return (Accion) data[top -1];
    }

    @Override
    public void clear() {
        
    }

    @Override
    public int size() {
        return this.top;
    }

    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    @Override
    public void print() {
      String mensaje = ("[");
       
       for (int i = top-1; i >=0 ; i--) {
           mensaje += (data[i]);
           if(i!=0) mensaje += ("->");
           
       }
       mensaje += ("]");
       System.out.println(mensaje);
    }

}