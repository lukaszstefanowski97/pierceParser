package com.example.pierceParser.listeners;

import com.example.pierceParser.helpers.CsvHelper;
import com.example.pierceParser.services.AttributesService;
import com.example.pierceParser.services.OptionsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class StartupListener {

    private final AttributesService attributesService;

    private final OptionsService optionsService;

    private final CsvHelper csvHelper;

    @EventListener(ApplicationReadyEvent.class)
    public void onStartup() {
        attributesService.saveAttributes(csvHelper.getAttributes());
        optionsService.saveOptions(csvHelper.getOptions());
    }
}
