package com.example.pierceParser.services;

import com.example.pierceParser.entities.Option;

import java.util.List;

public interface OptionsService {

    List<Option> getOptions();

    Option getOptionByCode(String code);

    void saveOption(Option option);

    void saveOptions(List<Option> options);
}
