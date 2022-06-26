package com.example.demo.configuration;

import com.example.demo.services.interfaces.ParserService;
import com.example.demo.services.interfaces.ReadFileService;
import com.example.demo.services.impl.ParserServiceImpl;
import com.example.demo.services.impl.ReadFileServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class ParserConfiguration {

  @Bean
  ParserService parserServiceCreate(ReadFileService readFileService) {
    return new ParserServiceImpl(readFileService);
  }

  @Bean
  ReadFileService readFileServiceCreate() {
    return new ReadFileServiceImpl();
  }
}
