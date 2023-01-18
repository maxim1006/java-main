package com.example.service;

import com.example.java.enums.AbstractServiceViewModel;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AbstractServiceImpl1 implements AbstractService {
    @Override
    public int getModel() {
        return 2;
    }

    @Override
    public AbstractServiceViewModel getType() {
        return AbstractServiceViewModel.DATA;
    }
}
