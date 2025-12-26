package com.chess.api.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.chess.api.dto.Pgn;

public class PgnParser {
  private static final Pattern TAG_PATTERN = Pattern.compile("\\[(\\w+)\\s+\"([^\"]+)\"\\]");

  public static Pgn parse(String input) {
    HashMap<String, String> pgnMap = new HashMap<>();

    Matcher matcher = TAG_PATTERN.matcher(input);
    while (matcher.find()) {
      String key = matcher.group(1).toLowerCase().trim();
      String value = matcher.group(2).trim();
      pgnMap.put(key, value);
    }

    int lastBracketIndex = input.lastIndexOf(']');
    String rawMoveText = input.substring(lastBracketIndex + 1).trim();
    String moveText = Arrays.stream(rawMoveText.split("\\s+")).collect(Collectors.joining(" "));
    pgnMap.put("movetext", moveText);

    return mapToPgn(pgnMap);
  }

  private static Pgn mapToPgn(Map<String, String> map) {
    return new Pgn(
      map.get("white"),
      map.get("black"),
      tryParseInt(map.get("whiteelo")),
      tryParseInt(map.get("blackelo")),
      map.get("movetext")
    );
  }

  private static int tryParseInt(String val) {
    int res;
    try {
      res = Integer.parseInt(val);
    } catch (NumberFormatException | NullPointerException e) {
      res = 0;
    }
    return res;
  }
}
