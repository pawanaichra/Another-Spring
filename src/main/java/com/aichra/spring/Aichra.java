package com.aichra.spring;

import com.aichra.spring.annotations.Autowired;
import com.aichra.spring.annotations.Component;

@Component
public class Aichra {

    @Autowired
    private Instrument instrument;

    public void fight() {
        instrument.use();
    }
}
