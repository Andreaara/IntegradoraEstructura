package com.example.integradora.ArrayQueue;

public interface IQueue<T> {

    void offer(T element);
    T poll();
    Object peek();
    void clear();
    void print();
    boolean isEmpty();
    int getSize();


}
