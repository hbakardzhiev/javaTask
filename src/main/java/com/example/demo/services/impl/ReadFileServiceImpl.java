package com.example.demo.services.impl;


import com.example.demo.modules.Position;
import com.example.demo.services.interfaces.ReadFileService;
import com.example.demo.UtilHelpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ReadFileServiceImpl implements ReadFileService {

    public HashMap<Character, List<Position>> readFile(String input) throws IOException {
        final var filePath = (UtilHelpers.SRC_MAIN_RESOURCES + input);
        FileInputStream inputStream = new FileInputStream(filePath);

        final var hashMap = new HashMap<Character, List<Position>>();
        Scanner sc = null;
        try {
            sc = new Scanner(inputStream, StandardCharsets.UTF_8);
            long rowNumber = 0;
            while (sc.hasNextLine()) {

                final var stringLine = sc.nextLine();
                for (int start = 0; start < stringLine.length(); start += UtilHelpers.CHARACTERS_TO_CACHE) {
                    final var choppedString = stringLine.substring(start, Math.min(stringLine.length(), start + UtilHelpers.CHARACTERS_TO_CACHE));
                    final var charArray = choppedString.toCharArray();
                    final var digitsWithPosition = getDigitAndPosition(charArray, rowNumber);
                    digitsWithPosition.forEach(element -> {
                        final var currDigit = element.getKey();
                        final var currPosition = element.getValue();

                        var currPositionList = hashMap.get(currDigit);
                        if (currPositionList == null) {
                            currPositionList = new ArrayList<Position>(Collections.singleton(currPosition));
                        } else  {
                            currPositionList.add(currPosition);
                        }
                        hashMap.put(currDigit, currPositionList);
                    });
                }

            rowNumber++;
            }
            if (sc.ioException() != null) {
                throw sc.ioException();
            }

            return hashMap;
        } finally {
            inputStream.close();
            if (sc != null) {
                sc.close();
            }
        }
    }

    private Stream<AbstractMap.SimpleImmutableEntry<Character, Position>> getDigitAndPosition(char[] charArray, long finalRowNumber) {
        return IntStream.range(0, charArray.length)
                .parallel()
                .filter(elementIndex -> {
                    final var currentCharElement = (int) charArray[elementIndex];
                    // In asci numbers (0-9) reside between 47 and 58
                    return currentCharElement <= 57 && currentCharElement >= 48;
                })
                .mapToObj(element -> new AbstractMap.SimpleImmutableEntry<Character, Position>(
                        charArray[element], new Position(finalRowNumber, element)
                ));
    }

}
