package com.example.demo.services.interfaces;

import com.example.demo.modules.Position;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Service
public interface ReadFileService {
    public HashMap<Character, List<Position>> readFile(String input) throws IOException;
}
