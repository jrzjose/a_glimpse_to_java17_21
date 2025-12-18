package org.example.sequence;

import java.util.LinkedHashMap;
import java.util.SequencedMap;
import java.util.TreeMap;

public class MapDemo {
    public static void start() {
        // SequencedMap
        SequencedMap<Integer, String> linkedSet = new LinkedHashMap<>();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0)
                linkedSet.putFirst(i, i + "-element");
            else
                linkedSet.putLast(i, i + "-element");
        }

        System.out.println("SequencedMap <LinkedHashMap>:");
        System.out.println(linkedSet);

        // TreeMap throws UnsupportedOperationException on putFirst
        SequencedMap<Integer, String> treeMap = new TreeMap<>();
        for (int i = 0; i < 10; i++) {
            treeMap.put(i, i + "-element");
        }

        System.out.println("TreeSet:");
        System.out.println(treeMap);
        treeMap.putFirst(99, "x-element"); // throws UnsupportedOperationException due to nature of the impl <sorted set>
    }

    public static void main(String[] args) {
        start();
    }
}
