package co.edu.uptc.structures;

import java.util.EmptyStackException;

public class Stack<T> {

    private Node<T> top;
    private int size;

    public Stack() {
        top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.setNext(top);
        top = newNode;
        size++;
    }

    public T pop() {
        if (isEmpty()){
            throw new EmptyStackException();
        }
        T data = top.getData();
        top = top.getNext();
        size--;
        return data;
    }

    public T peek() {
        if (isEmpty()){
            throw new EmptyStackException();
        }
        return top.getData();
    }

    public int size() {
        return size;
    }
}
