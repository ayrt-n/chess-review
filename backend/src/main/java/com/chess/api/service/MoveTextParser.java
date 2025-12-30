package com.chess.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoveTextParser {
  private static final Pattern MOVE_PATTERN = Pattern.compile("\\b(?:[KQRBN]?[a-h]?[1-8]?x?[a-h][1-8](?:=[QRBN])?|O-O(?:-O)?)[+#]?\\b");

  public static String parse(String moveText) {
    List<String> moves = new ArrayList<>();

    String cleanMoveText = moveText.replaceAll("\\{.*?\\}|\\(.*?\\)|;.*$", "");
    Matcher matcher = MOVE_PATTERN.matcher(cleanMoveText);

    while (matcher.find()) {
      moves.add(matcher.group());
    }
  
    return String.join(" ", moves);
  }
}
