package com.example.demo;

public final class UtilHelpers {

    /**
     * Pattern to catch HTML and XML markup  such as <html></html>
     */
    public static final String REGEX_PATTERN_FIND_MARKUP = "/<\\/?[^>]+(>|$)/g,";

    public static final String SRC_MAIN_RESOURCES = "src/main/resources/";

    public static final Integer CHARACTERS_TO_CACHE = 500;

    private UtilHelpers() {}
}
