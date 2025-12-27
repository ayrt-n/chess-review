package com.chess.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoveTextParser {
  private static final Pattern MOVE_PATTERN = Pattern.compile("\\b(?:[KQRBN]?[a-h]?[1-8]?x?[a-h][1-8](?:=[QRBN])?|O-O(?:-O)?)[+#]?\\b");

  public static List<String> parse(String moveText) {
    ArrayList<String> parsedMoveText = new ArrayList<>();

    String cleanMoveText = moveText.replaceAll("\\{.*?\\}|\\(.*?\\)|;.*$", "");
    Matcher matcher = MOVE_PATTERN.matcher(cleanMoveText);

    while (matcher.find()) {
      parsedMoveText.add(matcher.group());
    }
  
    return parsedMoveText;
  }
}
