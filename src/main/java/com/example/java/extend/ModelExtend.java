package com.example.java.extend;


import jakarta.inject.Inject;

public class ModelExtend {
    @Inject
    ModelExtendModel1 modelExtendModel11;

    private static ModelExtendModel1 modelExtendModel1;
    private static ModelExtendModel1 modelExtendModelStatic;

    public ModelExtend(ModelExtendModel1 modelExtendModel) {
//        ModelExtend.modelExtendModel1 = modelExtendModel;
        System.out.println(modelExtendModel11);
        ModelExtend.modelExtendModelStatic = modelExtendModel11;
    }

    public static void main(String[] args) {
//        ModelExtend.modelExtendModelStatic.f();
        ModelExtend.modelExtendModel1.f();
    }
}

interface ModelExtendModel {
    int f();
}

class ModelExtendModelImpl {
    int f() {return 1;}
}

interface ModelExtendModel1 extends ModelExtendModel {}

//ModelExtend modelExtendInstance = new ModelExtend(new ModelExtendModel1());
