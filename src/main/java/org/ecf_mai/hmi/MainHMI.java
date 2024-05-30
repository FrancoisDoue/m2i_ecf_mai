package org.ecf_mai.hmi;

public class MainHMI implements HMIInterface {

    public void start() {
        while (true) {
            System.out.println("""
                    - - - - ECF - 30 mai 2024 - - - -
                    1. Gestion des produits
                    2. Gestion des clients
                    [0]. Quit
                    """);
            switch (promptInteger()) {
                case 1 -> ProductHMI.getInstance().start();
                case 2 -> CustomerHMI.getInstance().start();
                case 0 -> {
                    return;
                }
            }
        }
    }
}
