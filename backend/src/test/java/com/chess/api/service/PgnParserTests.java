package com.chess.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.chess.api.dto.Pgn;

public class PgnParserTests {
  @Test
  void convertRawChessDotComStringToPgn() {
    String chessDotComExample = """
              
    [Event "Live Chess"]
    [Site "Chess.com"]
    [Date "2025.12.21"]
    [Round "?"]
    [White "vi_pranav"]
    [Black "MagnusCarlsen"]
    [Result "0-1"]
    [TimeControl "180"]
    [WhiteElo "3159"]
    [BlackElo "3366"]
    [Termination "MagnusCarlsen won by resignation"]
    [ECO "B92"]
    [EndTime "12:38:55 GMT+0000"]
    [Link "https://www.chess.com/game/live/146992475506?username=magnuscarlsen"]

    1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4 Nf6 5. Nc3 a6 6. Be2 e5 7. Nf3 Be7 8. O-O
    O-O 9. a4 h6 10. Re1 Be6 11. h3 Nbd7 12. Be3 Rc8 13. a5 Rxc3 14. bxc3 Nxe4 15.
    Qb1 Qc7 16. Bf1 Nxc3 17. Qb2 Rc8 18. Nd2 d5 19. f3 Bh4 20. Rec1 d4 21. Bf2 Bxf2+
    22. Kxf2 f5 23. Qb4 Nf6 24. Kg1 Nfd5 25. Qc4 Qe7 26. Qb3 Nf4 27. Qb6 Rc6 28. Qa7
    Nfe2+ 29. Bxe2 Nxe2+ 30. Kh1 Nxc1 31. Rxc1 Qg5 32. Rd1 Rxc2 33. Qb8+ Kh7 34.
    Qxe5 Rxd2 35. Rxd2 Qxd2 0-1

    """;

    Pgn pgnResult = PgnParser.parse(chessDotComExample);

    assertEquals("vi_pranav", pgnResult.white());
    assertEquals("MagnusCarlsen", pgnResult.black());
    assertEquals(3159, pgnResult.whiteElo());
    assertEquals(3366, pgnResult.blackElo());

    String cleanMoveText = "1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4 Nf6 5. Nc3 a6 6. Be2 e5 7. Nf3 Be7 8. O-O O-O 9. a4 h6 10. Re1 Be6 11. h3 Nbd7 12. Be3 Rc8 13. a5 Rxc3 14. bxc3 Nxe4 15. Qb1 Qc7 16. Bf1 Nxc3 17. Qb2 Rc8 18. Nd2 d5 19. f3 Bh4 20. Rec1 d4 21. Bf2 Bxf2+ 22. Kxf2 f5 23. Qb4 Nf6 24. Kg1 Nfd5 25. Qc4 Qe7 26. Qb3 Nf4 27. Qb6 Rc6 28. Qa7 Nfe2+ 29. Bxe2 Nxe2+ 30. Kh1 Nxc1 31. Rxc1 Qg5 32. Rd1 Rxc2 33. Qb8+ Kh7 34. Qxe5 Rxd2 35. Rxd2 Qxd2 0-1";
    assertEquals(cleanMoveText, pgnResult.moveText());
  }

  @Test
  void convertRawLichessStringToPgn() {
    String lichessExample = """
              
    [Event "38th Biel Chess Festival"]
    [Site "Biel SUI"]
    [Date "2005.07.17"]
    [Round "1"]
    [White "Hikaru Nakamura"]
    [Black "Magnus Carlsen"]
    [Result "1/2-1/2"]
    [WhiteElo "2660"]
    [BlackElo "2528"]
    [Variant "Standard"]
    [ECO "C91"]
    [Opening "Ruy Lopez: Closed, Bogoljubow Variation"]
    [StudyName "Magnus Carlsen vs Hikaru Nakamura"]
    [ChapterName "Hikaru Nakamura - Magnus Carlsen"]
    [ChapterURL "https://lichess.org/study/owmLJHe9/hO2QSrD3"]
    [Annotator "https://lichess.org/@/KaanSungu"]

    1. e4 e5 2. Nf3 Nc6 3. Bb5 a6 4. Ba4 Nf6 5. O-O Be7 6. Re1 b5 7. Bb3 O-O 8. d4 d6 9. c3 Bg4 10. Be3 exd4 11. cxd4 Na5 12. Bc2 c5 13. dxc5 dxc5 14. h3 Qxd1 15. Bxd1 Be6 16. Nbd2 Nc6 17. Ng5 c4 18. a3 Rad8 19. Bc2 Bc8 20. f4 Nd7 21. Ngf3 Nc5 22. e5 Nd3 23. Bxd3 Rxd3 24. Ne4 Bf5 25. Nd6 { 1/2-1/2 The game is a draw. } 1/2-1/2

    """;

    Pgn pgnResult = PgnParser.parse(lichessExample);

    assertEquals("Hikaru Nakamura", pgnResult.white());
    assertEquals("Magnus Carlsen", pgnResult.black());
    assertEquals(2660, pgnResult.whiteElo());
    assertEquals(2528, pgnResult.blackElo());

    String cleanMoveText = "1. e4 e5 2. Nf3 Nc6 3. Bb5 a6 4. Ba4 Nf6 5. O-O Be7 6. Re1 b5 7. Bb3 O-O 8. d4 d6 9. c3 Bg4 10. Be3 exd4 11. cxd4 Na5 12. Bc2 c5 13. dxc5 dxc5 14. h3 Qxd1 15. Bxd1 Be6 16. Nbd2 Nc6 17. Ng5 c4 18. a3 Rad8 19. Bc2 Bc8 20. f4 Nd7 21. Ngf3 Nc5 22. e5 Nd3 23. Bxd3 Rxd3 24. Ne4 Bf5 25. Nd6 { 1/2-1/2 The game is a draw. } 1/2-1/2";
    assertEquals(cleanMoveText, pgnResult.moveText());
  }
}
