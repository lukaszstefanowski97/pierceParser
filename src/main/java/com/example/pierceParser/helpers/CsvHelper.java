package com.example.pierceParser.helpers;

import com.example.pierceParser.entities.Attribute;
import com.example.pierceParser.entities.Option;
import com.example.pierceParser.utils.Messages;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Slf4j
@Component
@AllArgsConstructor
public class CsvHelper {

    private ApplicationContext applicationContext;

    public List<Attribute> getAttributes() {

        try (Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/attributes.csv"))) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                List<String[]> csvLines = csvReader.readAll();
                String[] fieldNames = new String[0];
                List<Attribute> attributes = new ArrayList<>();

                if (csvLines.size() > 1) {

                    for (int i = 0; i < csvLines.size(); i++) {
                        String[] line = csvLines.get(i)[0].split(";");
                        String code = "";
                        Map<String, String> labels = new HashMap<>();

                        if (i == 0) {
                            fieldNames = line;
                            continue;
                        }

                        for (int j = 0; j < fieldNames.length; j++) {

                            if (fieldNames[j].contains("code")) {
                                code = line[j];
                            } else if (fieldNames[j].contains("label")) {
                                labels.put(fieldNames[j].split("-")[1], line[j]);
                            }
                        }

                        attributes.add(new Attribute(code, labels));
                    }

                    return attributes;
                }
            } catch (CsvException e) {
                handleException(e);
            }
        } catch (IOException e) {
            handleException(e);
        }

        return null;
    }

    public List<Option> getOptions() {

        try (Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/options.csv"))) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                List<String[]> csvLines = csvReader.readAll();
                String[] fieldNames = new String[0];

                if (csvLines.size() > 1) {
                    List<Option> options = new ArrayList<>();

                    for (int i = 0; i < csvLines.size(); i++) {
                        String[] line = csvLines.get(i)[0].split(";");
                        String code = "";
                        String attribute = "";
                        Integer sortOrder = 0;
                        Map<String, String> labels = new HashMap<>();

                        if (i == 0) {
                            fieldNames = line;
                            continue;
                        } else if (line.length != fieldNames.length) {
                            continue;
                        }

                        for (int j = 0; j < fieldNames.length; j++) {


                            if (fieldNames[j].equals("code")) {
                                code = line[j];
                            } else if (fieldNames[j].contains("label")) {
                                labels.put(fieldNames[j].split("-")[1], line[j]);
                            } else if (fieldNames[j].equals("attribute")) {
                                attribute = line[j];
                            } else if (fieldNames[j].equals("sort_order")) {
                                sortOrder = Integer.valueOf(line[j]);
                            }
                        }

                        options.add(new Option(code, attribute, sortOrder, labels));
                    }

                    options.sort((Comparator.comparing(Option::getSortOrder)));
                    return options;
                }
            } catch (CsvException e) {
                handleException(e);
            }
        } catch (IOException e) {
            handleException(e);
        }

        return null;
    }

    private void handleException(Exception e) {
        e.printStackTrace();
        log.error(Messages.PARSING_ERROR);
        SpringApplication.exit(applicationContext, () -> 1);
    }
}
