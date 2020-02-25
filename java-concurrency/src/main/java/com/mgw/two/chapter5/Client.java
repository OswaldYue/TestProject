package com.mgw.two.chapter5;

public class Client {

    public static void main(String[] args) {

        Gate gate = new Gate();
        User bj = new User("BaoBao", "Beijing", gate);
        User sh = new User("ShangLao", "ShangHai", gate);
        User gz = new User("GuangLao", "GuangZhou", gate);

        bj.start();
        sh.start();
        gz.start();

    }
}
