package org.example.Domain;

import java.util.LinkedList;

public class FilaObj<T> {
    private LinkedList<T> fila;
    private int tamanho;

    public  FilaObj() {

        this.fila = new LinkedList<>();
       tamanho = 0;
    }


    public int tamanho() {
        return fila.size();
    }


    public void enfileirar(T elemento) {
     if(tamanho == 10){
         System.out.println("Fila atingiu o tamanho máximo. Não é possível adicionar mais elementos.");
     }else {
         fila.addLast(elemento);
         tamanho++;
     }
    }

    public T desenfileirar() {
        if (estaVazia()) {
            throw new IllegalStateException("A fila está vazia.");
        }
        tamanho--;
        return fila.removeFirst();

    }
    public T pegaPrimeiro() {
        if (estaVazia()) {
            throw new IllegalStateException("A fila está vazia.");
        }
        return fila.getFirst();
    }


    public boolean estaVazia() {
        return fila.isEmpty();
    }

    @Override
    public String toString() {
        return "FilaObj{" +
                "fila=" + fila +
                ", tamanho=" + tamanho +
                '}';
    }
}
