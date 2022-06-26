package com.example.demo;

public final class UtilHelpers {

  /** Pattern to catch HTML and XML markup such as <html></html> */
  public static final String REGEX_PATTERN_FIND_MARKUP = "/<\\/?[^>]+(>|$)/g,";

  public static final String SRC_MAIN_RESOURCES = "src/main/resources/";

  public static final Integer ZERO_IN_ASCII = 48;
  public static final Integer NINE_IN_ASCII = 57;
  // new line ASCII characters
  public static final Integer CARRIAGE_RETURN = 13;
  public static final Integer NEW_LINE = 10;

  private UtilHelpers() {}
}
