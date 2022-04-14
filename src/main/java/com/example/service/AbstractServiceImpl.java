package com.example.service;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AbstractServiceImpl implements AbstractService {
    @Override
    public int getModel() {
        return 1;
    }

    @Override
    public String getType() {
        return "Str";
    }
}
