package com.chess.api.service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.chess.api.dto.Pgn;

public class PgnParser {
  private static final Pattern TAG_PATTERN = Pattern.compile("\\[(\\w+)\\s+\"([^\"]+)\"\\]");
  private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

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
      map.get("movetext"),
      map.get("timecontrol"),
      parseResult(map.get("result")),
      tryParseDate(map.get("date"))
    );
  }

  private static String parseResult(String result) {
    if (result == null) return "*";
    
    return switch (result) {
      case "1-0", "0-1", "1/2-1/2", "*" -> result;
      default -> "*";
    };
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

  private static OffsetDateTime tryParseDate(String date) {
    if (date == null) return null;

    try {
      return LocalDate.parse(date, dateFormatter).atStartOfDay().atOffset(ZoneOffset.UTC);
    } catch (DateTimeParseException e) {
        return null;
    }
  }
}
