package org.example.sequence;

import java.util.LinkedHashSet;
import java.util.SequencedSet;
import java.util.TreeSet;

public class SetDemo {
    public static void start() {
        // SequencedSet
        SequencedSet<String> linkedSet = new LinkedHashSet<String>();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0)
                linkedSet.addFirst(i + "-element");
            else
                linkedSet.addLast(i + "-element");
        }

        System.out.println("SequencedCollection <LinkedHashSet>:");
        System.out.println(linkedSet); 

        // TreeSet throws UnsupportedOperationException
        SequencedSet<String> treeSet = new TreeSet<String>();
        for (int i = 0; i < 10; i++) {
            treeSet.add(i + "-element");
        }

        System.out.println("TreeSet:");
        System.out.println(treeSet);
        treeSet.removeFirst();
        treeSet.removeLast();
        System.out.println(treeSet);
        treeSet.addFirst("x-element"); // throws UnsupportedOperationException due to nature of the impl <sorted set>
    }

    public static void main(String[] args) {
        start();
    }
}
