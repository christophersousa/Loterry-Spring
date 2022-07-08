package com.example.demo.model;

import com.example.demo.service.visitor.Visitor;

public abstract class Model {
    public abstract Model accept(Visitor visitor);
}
