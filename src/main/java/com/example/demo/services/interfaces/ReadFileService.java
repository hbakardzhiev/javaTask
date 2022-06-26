package com.example.demo.services.interfaces;

import com.example.demo.modules.Position;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public interface ReadFileService {
    public CompletableFuture<HashMap<Character, List<Position>>> readFile(String input) throws IOException;
}
