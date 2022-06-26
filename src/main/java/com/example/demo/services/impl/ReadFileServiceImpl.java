package com.example.demo.services.impl;


import com.example.demo.modules.Position;
import com.example.demo.services.interfaces.ReadFileService;
import com.example.demo.UtilHelpers;
import org.springframework.scheduling.annotation.Async;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class ReadFileServiceImpl implements ReadFileService {

    @Async
    public CompletableFuture<HashMap<Character, List<Position>>> readFile(String input) throws FileNotFoundException {
        final var filePath = (UtilHelpers.SRC_MAIN_RESOURCES + input);
        try (final var inputStream = new FileReader(filePath)) {
            final var hashMap = new HashMap<Character, List<Position>>();
            long rowNumber = 0;
            long colNumber = 0;
            int asciiCharacter;
            while ((asciiCharacter = inputStream.read()) != -1) {
                if (isASCIIDigit(asciiCharacter)) {
                    final Character currDigit = (char) asciiCharacter;
                    final var currPosition = new Position(rowNumber, colNumber);
                    final var currPositionList = generateNewPositionList(hashMap, currDigit, currPosition);
                    hashMap.put(currDigit, currPositionList);
                }
                colNumber++;
                if (isEndOfLine(asciiCharacter)) {
                    rowNumber++;
                    colNumber = 0;
                }
            }
            return CompletableFuture.completedFuture(hashMap);
        }
        catch (FileNotFoundException exception) {
            throw new FileNotFoundException("The current file does not exist.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Position> generateNewPositionList(HashMap<Character, List<Position>> hashMap, Character currDigit, Position currPosition) {
        var currPositionList = hashMap.get(currDigit);
        if (currPositionList == null) {
            currPositionList = new ArrayList<>(Collections.singleton(currPosition));
        } else {
            currPositionList.add(currPosition);
        }
        return currPositionList;
    }

    private Boolean isASCIIDigit(Integer asciiCharacter) {
        return asciiCharacter <= UtilHelpers.NINE_IN_ASCII && asciiCharacter >= UtilHelpers.ZERO_IN_ASCII;
    }

    private Boolean isEndOfLine(Integer asciiCharacter) {
        return Objects.equals(asciiCharacter, UtilHelpers.CARRIAGE_RETURN) || Objects.equals(asciiCharacter, UtilHelpers.NEW_LINE);
    }
}