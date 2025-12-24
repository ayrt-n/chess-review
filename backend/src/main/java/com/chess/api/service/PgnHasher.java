package com.chess.api.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

import com.chess.api.dto.Pgn;

public class PgnHasher {
  public static String hash(Pgn pgn) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] encodedHash = digest.digest(pgn.toString().getBytes(StandardCharsets.UTF_8));

      return HexFormat.of().formatHex(encodedHash);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("Error: SHA-256 algorithm not found", e);
    }
  }
}
