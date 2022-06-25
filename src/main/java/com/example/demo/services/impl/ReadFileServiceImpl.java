package com.example.demo.services.impl;


import com.example.demo.modules.Position;
import com.example.demo.services.interfaces.ReadFileService;
import com.example.demo.UtilHelpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ReadFileServiceImpl implements ReadFileService {

    public HashMap<Character, List<Position>> readFile(String input) throws IOException {
        final var filePath = (UtilHelpers.SRC_MAIN_RESOURCES + input);

        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            final var hashMap = new HashMap<Character, List<Position>>();
            long rowNumber = 0;
            long colNumber = 0;
            int asciiCharacter;
            while ((asciiCharacter = inputStream.read()) != -1) {
                if (isEndOfLine(asciiCharacter)) {
                    rowNumber++;
                }

                if (isASCIIDigit(asciiCharacter)) {
                    final Character currDigit = (char) asciiCharacter;
                    final var currPosition = new Position(rowNumber, colNumber);
                    var currPositionList = hashMap.get(currDigit);
                    if (currPositionList == null) {
                        currPositionList = new ArrayList<Position>(Collections.singleton(currPosition));
                    } else {
                        currPositionList.add(currPosition);
                    }
                    hashMap.put(currDigit, currPositionList);
                }


                colNumber++;
            }
            return hashMap;
        }
    }

    private Boolean isASCIIDigit(Integer asciiCharacter) {
        return asciiCharacter <= UtilHelpers.NINE_IN_ASCII && asciiCharacter >= UtilHelpers.ZERO_IN_ASCII;
    }

    private Boolean isEndOfLine(Integer asciiCharacter) {
        return Objects.equals(asciiCharacter, UtilHelpers.CARRIAGE_RETURN) || Objects.equals(asciiCharacter, UtilHelpers.NEW_LINE);
    }
}
