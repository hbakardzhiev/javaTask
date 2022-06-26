package com.example.demo.controllers;

import com.example.demo.modules.Position;
import com.example.demo.services.interfaces.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping
public class ParserController {

  private ParserService parserService;

  @Autowired
  public ParserController(ParserService parserService) {
    this.parserService = parserService;
  }

  @PostMapping
  public HashMap<Character, List<Position>> parseFile(@RequestBody String input)
      throws IOException {
    return parserService.parseMap(input);
  }
}
