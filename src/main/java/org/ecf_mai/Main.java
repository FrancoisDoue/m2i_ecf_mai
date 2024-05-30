package org.ecf_mai;

import org.ecf_mai.hmi.MainHMI;

public class Main {
    public static void main(String[] args) {
        MainHMI hmi = new MainHMI();
        hmi.start();
    }
}