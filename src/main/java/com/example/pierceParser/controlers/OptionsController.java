package com.example.pierceParser.controlers;

import com.example.pierceParser.dto.OptionDTO;
import com.example.pierceParser.entities.Option;
import com.example.pierceParser.services.AttributesService;
import com.example.pierceParser.services.OptionsService;
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
@RequestMapping("/options")
public class OptionsController {

    private final OptionsService optionsService;

    @GetMapping("")
    public ResponseEntity<List<OptionDTO>> getAllOptions(
            @RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) String language) {

        List<Option> options = optionsService.getOptions();
        List<OptionDTO> result = options.stream()
                .map(item -> new OptionDTO(
                        item.getCode(), item.getAttribute(), item.getSortOrder(), item.getLabels().get(language)))
                .collect(Collectors.toList());

        log.info(Messages.GETTING_OPTIONS);

        return options.isEmpty() ? new ResponseEntity<>(result, HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{code}")
    public ResponseEntity<OptionDTO> getOptionByCode(
            @PathVariable String code, @RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) String language) {

        Option option = optionsService.getOptionByCode(code);

        if (option == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        OptionDTO result = new OptionDTO(
                option.getCode(), option.getAttribute(), option.getSortOrder(), option.getLabels().get(language));

        log.info(Messages.GETTING_OPTION_BY_CODE + code);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/byAttribute/{attributeName}")
    public ResponseEntity<List<OptionDTO>> getOptionByAttributeName(
            @PathVariable String attributeName, @RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) String language) {

        List<Option> options = optionsService.getOptions().stream()
                .filter(item -> attributeName.equals(item.getAttribute()))
                .collect(Collectors.toList());

        List<OptionDTO> result = options.stream()
                .map(item -> new OptionDTO(item.getCode(), item.getAttribute(), item.getSortOrder(), item.getLabels().get(language)))
                .collect(Collectors.toList());

        log.info(Messages.GETTING_OPTION_BY_ATTRIBUTE + attributeName);

        return result.isEmpty() ? new ResponseEntity<>(result, HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(result, HttpStatus.OK);
    }
}
