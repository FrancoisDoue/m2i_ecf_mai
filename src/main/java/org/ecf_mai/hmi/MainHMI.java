package org.ecf_mai.hmi;

public class MainHMI implements HMIInterface {

    public void start() {
        while (true) {
            System.out.println("""
                    - - - - ECF - 30 mai 2024 - - - -
                    1.
                    [0]. Quit
                    """);
            switch (promptInteger()) {
                case 1 -> {}
                case 0 -> {
                    return;
                }
            }
        }
    }
}
