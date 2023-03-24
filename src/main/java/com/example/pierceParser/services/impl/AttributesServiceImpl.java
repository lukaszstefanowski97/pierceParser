package com.example.pierceParser.services.impl;

import com.example.pierceParser.entities.Attribute;
import com.example.pierceParser.services.AttributesService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@NoArgsConstructor
public class AttributesServiceImpl implements AttributesService {

    private final List<Attribute> attributes = new LinkedList<>();

    @Override
    public List<Attribute> getAllAttributes() {
        return attributes;
    }

    @Override
    public Attribute getAttributeByCode(String code) {
        return attributes.stream().filter(item -> code.equals(item.getCode())).findFirst().orElse(null);
    }

    @Override
    public void saveAttribute(Attribute attribute) {
        this.attributes.add(attribute);
    }

    @Override
    public void saveAttributes(List<Attribute> attributes) {
        this.attributes.addAll(attributes);
    }
}
