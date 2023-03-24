package com.example.pierceParser.controlers;

import com.example.pierceParser.dto.AttributeDTO;
import com.example.pierceParser.entities.Attribute;
import com.example.pierceParser.services.AttributesService;
import com.example.pierceParser.utils.Messages;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("/attributes")
public class AttributesController {

    private final AttributesService attributesService;

    @GetMapping("")
    public ResponseEntity<List<AttributeDTO>> getAllAttributes(
            @RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) String language) {

        List<Attribute> attributes = attributesService.getAllAttributes();
        List<AttributeDTO> result = attributes.stream()
                .map(item -> new AttributeDTO(item.getCode(), item.getLabels().get(language)))
                .collect(Collectors.toList());

        log.info(Messages.GETTING_ATTRIBUTES);

        return attributes.isEmpty() ? new ResponseEntity<>(result, HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{code}")
    public ResponseEntity<AttributeDTO> getOptionByCode(
            @PathVariable String code, @RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) String language) {

        Attribute attribute = attributesService.getAttributeByCode(code);

        if (attribute == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AttributeDTO result = new AttributeDTO(
                attribute.getCode(), attribute.getLabels().get(language));

        log.info(Messages.GETTING_ATTRIBUTE_BY_CODE + code);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
