package org.example.Domain;

import java.util.LinkedList;

public class PilhaObg<T> {
    private LinkedList<T> pilha;

    public PilhaObg() {

        this.pilha = new LinkedList<>();
    }

    public LinkedList<T> getPilha() {
        return pilha;
    }

    public void setPilha(LinkedList<T> pilha) {
        this.pilha = pilha;
    }

    public void empilhar(T elemento) {
        pilha.addLast(elemento);
    }

    public T desempilhar() {
        if (estaVazia()) {
            throw new IllegalStateException("A pilha está vazia.");
        }
        return pilha.removeLast();
    }
    public T trazerUltimo(){

        return pilha.getLast();
    }

    public boolean estaVazia() {
        return pilha.isEmpty();
    }
}
