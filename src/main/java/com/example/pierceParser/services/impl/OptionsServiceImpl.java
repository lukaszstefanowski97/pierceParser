package com.example.pierceParser.services.impl;

import com.example.pierceParser.entities.Option;
import com.example.pierceParser.services.OptionsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@AllArgsConstructor
public class OptionsServiceImpl implements OptionsService {

    private final List<Option> options = new LinkedList<>();

    @Override
    public List<Option> getOptions() {
        return options;
    }

    @Override
    public Option getOptionByCode(String code) {
        return options.stream().filter(item -> code.equals(item.getCode())).findFirst().orElse(null);
    }

    @Override
    public void saveOption(Option option) {
        this.options.add(option);
    }

    @Override
    public void saveOptions(List<Option> options) {
        this.options.addAll(options);
    }
}
