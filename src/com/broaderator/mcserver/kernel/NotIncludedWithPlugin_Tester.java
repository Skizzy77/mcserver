package com.broaderator.mcserver.kernel;

import com.broaderator.mcserver.kernelbase.Namespace;

import java.util.HashMap;
import java.util.Scanner;

public class NotIncludedWithPlugin_Tester {
    public static void main(String[] args){
        Namespace ns = new Namespace("Test");
        ns.createDirs("Loop");
        for(int i = 0; i < 1000000; i++){
            ns.put("Loop." + String.valueOf(i), String.valueOf(i) + String.valueOf(i*2));
            assert ns.get("Loop." + String.valueOf(i)) == String.valueOf(i) + String.valueOf(i*2);
            //if(i % 100000 == 0) System.out.println(i);
        }
        System.out.println(((HashMap)ns.storage.get("Loop")).size());
        ns = null;
    }
}
