package com.chess.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.chess.api.dto.Pgn;

public class PgnHasherTests {
  @Test
  void pgnHashesToCorrectSha256Hash() {
    Pgn pgn = new Pgn("White", "Black", 2345, 2025, "1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4 Nf6");
    String pgnHash = PgnHasher.hash(pgn);
    String expectedHash = "393f8687b0a2c6d11e8e6a25af416ccc90e16b183074fcce7f3dcb03084915e7";

    assertEquals(expectedHash, pgnHash);
  }

  @Test
  void differentWhitePgnChangesHash() {
    Pgn pgn1 = new Pgn("White1", "Black", 2345, 2025, "1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4 Nf6");
    Pgn pgn2 = new Pgn("White", "Black", 2345, 2025, "1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4 Nf6");

    String pgn1Hash = PgnHasher.hash(pgn1);
    String pgn2Hash = PgnHasher.hash(pgn2);

    assertNotEquals(pgn1Hash, pgn2Hash);
  }

  @Test
  void differentBlackPgnChangesHash() {
    Pgn pgn1 = new Pgn("White", "Bl ack", 2345, 2025, "1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4 Nf6");
    Pgn pgn2 = new Pgn("White", "Black", 2345, 2025, "1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4 Nf6");

    String pgn1Hash = PgnHasher.hash(pgn1);
    String pgn2Hash = PgnHasher.hash(pgn2);

    assertNotEquals(pgn1Hash, pgn2Hash);
  }

  @Test
  void differentWhiteEloPgnChangesHash() {
    Pgn pgn1 = new Pgn("White", "Black", 2344, 2025, "1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4 Nf6");
    Pgn pgn2 = new Pgn("White", "Black", 2345, 2025, "1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4 Nf6");

    String pgn1Hash = PgnHasher.hash(pgn1);
    String pgn2Hash = PgnHasher.hash(pgn2);

    assertNotEquals(pgn1Hash, pgn2Hash);
  }

  @Test
  void differentBlackEloPgnChangesHash() {
    Pgn pgn1 = new Pgn("White1", "Black", 2345, 2026, "1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4 Nf6");
    Pgn pgn2 = new Pgn("White", "Black", 2345, 2025, "1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4 Nf6");

    String pgn1Hash = PgnHasher.hash(pgn1);
    String pgn2Hash = PgnHasher.hash(pgn2);

    assertNotEquals(pgn1Hash, pgn2Hash);
  }

  @Test
  void differentMoveTextPgnChangesHash() {
    Pgn pgn1 = new Pgn("White1", "Black", 2345, 2026, "1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4 h4");
    Pgn pgn2 = new Pgn("White", "Black", 2345, 2025, "1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4 Nf6");

    String pgn1Hash = PgnHasher.hash(pgn1);
    String pgn2Hash = PgnHasher.hash(pgn2);

    assertNotEquals(pgn1Hash, pgn2Hash);
  }

  @Test
  void identicalPgnsHaveEqualHashes() {
    Pgn pgn1 = new Pgn("White", "Black", 2345, 2025, "1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4 Nf6");
    Pgn pgn2 = new Pgn("White", "Black", 2345, 2025, "1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4 Nf6");

    String pgn1Hash = PgnHasher.hash(pgn1);
    String pgn2Hash = PgnHasher.hash(pgn2);

    assertEquals(pgn1Hash, pgn2Hash);
  }
}
