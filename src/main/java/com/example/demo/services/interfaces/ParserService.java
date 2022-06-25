package com.example.demo.services.interfaces;

import com.example.demo.modules.Position;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Service
public interface ParserService {
    public HashMap<Character, List<Position>> parseMap(String input) throws IOException;
}
