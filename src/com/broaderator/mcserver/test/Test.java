package com.broaderator.mcserver.test;

import com.broaderator.mcserver.kernelcore.namespace.Namespace;

import java.util.LinkedList;
import java.util.List;

public class Test {
    private static Namespace namespace = new Namespace("TestNamespace");

    public static void main(String[] args) {
        List<Integer> b = new LinkedList<>();
        b.add(2);
        b.add(5);
        b.add(83);
        b.add(133);
        b.add(2);
        b.add(53);
        b.add(2);
        remove(b, 2);
        System.out.println(b);
    }

    private static void remove(List<Integer> list, Integer c) {
        while (list.contains(c))
            list.remove(c);
    }
}
