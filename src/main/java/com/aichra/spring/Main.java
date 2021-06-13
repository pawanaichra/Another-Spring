package com.aichra.spring;

import com.aichra.spring.manager.BeanManager;

public class Main {

    public static void main(String[] args) {
        var manager = BeanManager.getInstance();
        var aichra = (Aichra) manager.getInstance(Aichra.class);
        aichra.fight();
    }
}
