package com.example.pierceParser.services;

import com.example.pierceParser.entities.Attribute;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@NoArgsConstructor
public class AttributesService {

    private final List<Attribute> attributes = new LinkedList<>();

    public List<Attribute> getAllAttributes() {
        return attributes;
    }

    public Attribute getAttributeByCode(String code) {
        return attributes.stream().filter(item -> code.equals(item.getCode())).findFirst().orElse(null);
    }

    public void saveAttribute(Attribute attribute) {
        this.attributes.add(attribute);
    }

    public void saveAttributes(List<Attribute> attributes) {
        if (attributes != null) {
            this.attributes.addAll(attributes);
        }
    }
}
