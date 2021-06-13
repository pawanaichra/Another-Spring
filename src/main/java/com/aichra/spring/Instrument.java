package com.aichra.spring;

import com.aichra.spring.annotations.Autowired;

@Autowired
public class Instrument {
    public void use() {
        System.out.println("Used instrument");
    }
}
