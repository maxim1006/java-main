package com.example.service.impl;

import com.example.java.enums.AbstractServiceViewModel;

import com.example.service.api.AbstractService;
import jakarta.enterprise.context.ApplicationScoped;

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
