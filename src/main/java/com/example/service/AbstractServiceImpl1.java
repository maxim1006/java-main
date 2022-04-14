package com.example.service;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AbstractServiceImpl1 implements AbstractService {
    @Override
    public int getModel() {
        return 2;
    }

    @Override
    public String getType() {
        return "AbstractServiceImpl1";
    }
}
