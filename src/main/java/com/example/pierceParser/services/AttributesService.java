package com.example.pierceParser.services;

import com.example.pierceParser.entities.Attribute;

import java.util.List;

public interface AttributesService {

    List<Attribute> getAllAttributes();

    Attribute getAttributeByCode(String code);

    void saveAttribute(Attribute attribute);

    void saveAttributes(List<Attribute> attributes);
}
