package com.broaderator.mcserver.test;

import com.broaderator.mcserver.kernelcore.Serializer;
import com.broaderator.mcserver.kernelcore.namespace.Namespace;
import org.yaml.snakeyaml.Yaml;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Test {
    private static Namespace namespace = new Namespace("TestNamespace", false);

    public static void main(String[] args) {
        AbstractBase ab = new Class1("ThisIsClass1!");
        System.out.println(ab.a);
        ab = new Class2("ThisIsClass2!");
        System.out.println(ab.a);
    }


}


abstract class AbstractBase {
    public final String a;

    AbstractBase(String a) {
        this.a = a;
    }
    abstract void run();
}

class Class1 extends AbstractBase {

    public Class1(String a) {
        super(a);
    }

    @Override
    void run() {

    }
}

class Class2 extends AbstractBase {

    public Class2(String a) {
        super(a);
    }
}
