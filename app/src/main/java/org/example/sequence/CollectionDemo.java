package org.example.sequence;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.SequencedCollection;

public class CollectionDemo {
    public static void start() {
        // O(1) performance
        // Note: List now extends SequencedCollection
        SequencedCollection<String> seqList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            seqList.addFirst(i + "-element");
            seqList.addLast(i + "-element");
        }

        System.out.println("SequencedCollection:");
        System.out.println(seqList);

        // Alternative - O(n) access performance
        // now extends SequencedCollection
        LinkedList<String> liqList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            liqList.addFirst(i + "-element");
            liqList.addLast(i + "-element");
        }

        System.out.println("LinkedList:");
        System.out.println(liqList);

        // Alternative - Deque (now extends SequencedCollection)
        // cluttered with other operations, including a family of null-returning
        // operations (offer, peek, and poll),
        // stack operations (push and pop), and operations inherited from Queue
        Deque<String> deque = new ArrayDeque<>();
        for (int i = 0; i < 10; i++) {
            deque.addFirst(i + "-element");
            deque.addLast(i + "-element");
        }

        System.out.println("deque:");
        System.out.println(deque);
    }

    public static void main(String[] args) {
        start();
    }
}