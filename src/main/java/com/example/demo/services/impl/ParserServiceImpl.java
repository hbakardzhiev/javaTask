package com.example.demo.services.impl;

import com.example.demo.modules.Position;
import com.example.demo.services.interfaces.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.services.interfaces.ReadFileService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ParserServiceImpl implements ParserService {
    private ReadFileService readFileService;

    @Autowired
    public ParserServiceImpl(ReadFileService readFileServiceInput) {
        this.readFileService = readFileServiceInput;
    }

    public HashMap<Character, List<Position>> parseMap(String input) throws IOException {
        final var map = readFileService.readFile(input);
        return map;
    }


}
