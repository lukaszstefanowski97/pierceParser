package com.example.pierceParser.services;

import com.example.pierceParser.entities.Option;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
public class OptionsService {

    private final List<Option> options = new LinkedList<>();

    public List<Option> getOptions() {
        return options;
    }

    public Option getOptionByCode(String code) {
        return options.stream().filter(item -> code.equals(item.getCode())).findFirst().orElse(null);
    }

    public void saveOption(Option option) {
        this.options.add(option);
    }

    public void saveOptions(List<Option> options) {
        if (options != null) {
            this.options.addAll(options);
        }
    }
}
