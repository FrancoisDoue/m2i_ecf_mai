package org.ecf_mai;

import org.ecf_mai.hmi.MainHMI;
import org.ecf_mai.util.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        HibernateUtil.getFactory();
        MainHMI hmi = new MainHMI();
        hmi.start();

        HibernateUtil.close();
    }
}