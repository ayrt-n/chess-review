package com.chess.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MoveTextParserTests {
  @Test
  void parsesSimpleMoveText() {
    String moveText = "1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4 Nf6 5. Nc3 a6 6. Be2 e5 7. Nf3 Be7 8. O-O O-O 9. a4 h6 10. Re1 Be6 11. h3 Nbd7 12. Be3 Rc8 13. a5 Rxc3 14. bxc3 Nxe4 15. Qb1 Qc7 16. Bf1 Nxc3 17. Qb2 Rc8 18. Nd2 d5 19. f3 Bh4 20. Rec1 d4 21. Bf2 Bxf2+ 22. Kxf2 f5 23. Qb4 Nf6 24. Kg1 Nfd5 25. Qc4 Qe7 26. Qb3 Nf4 27. Qb6 Rc6 28. Qa7 Nfe2+ 29. Bxe2 Nxe2+ 30. Kh1 Nxc1 31. Rxc1 Qg5 32. Rd1 Rxc2 33. Qb8+ Kh7 34. Qxe5 Rxd2 35. Rxd2 Qxd2 0-1";

    String parsedMoveText = MoveTextParser.parse(moveText);
    String expected = "e4 c5 Nf3 d6 d4 cxd4 Nxd4 Nf6 Nc3 a6 Be2 e5 Nf3 Be7 O-O O-O a4 h6 Re1 Be6 h3 Nbd7 Be3 Rc8 a5 Rxc3 bxc3 Nxe4 Qb1 Qc7 Bf1 Nxc3 Qb2 Rc8 Nd2 d5 f3 Bh4 Rec1 d4 Bf2 Bxf2 Kxf2 f5 Qb4 Nf6 Kg1 Nfd5 Qc4 Qe7 Qb3 Nf4 Qb6 Rc6 Qa7 Nfe2 Bxe2 Nxe2 Kh1 Nxc1 Rxc1 Qg5 Rd1 Rxc2 Qb8 Kh7 Qxe5 Rxd2 Rxd2 Qxd2";

    assertEquals(expected, parsedMoveText);
  }

  @Test
  void parsesMultilineMoveTextWithComments() {
    String moveText = """
    1. e4 e5 2. Nf3 Nc6 3. Bb5 a6 4. Ba4 Nf6 5. O-O Be7 6. Re1 b5 (I made this comment up) 7. Bb3 O-O 8. d4 d6 ; Another made up comment
    9. c3 Bg4 10. Be3 exd4 11. cxd4 Na5 12. Bc2 c5 13. dxc5 dxc5 14. h3 Qxd1 15. Bxd1 Be6 16. Nbd2 Nc6 17. Ng5 c4 18. a3 Rad8 19. Bc2 Bc8 20. f4 Nd7 21. Ngf3 Nc5 22. e5 Nd3 23. Bxd3 Rxd3 24. Ne4 Bf5 25. Nd6 { 1/2-1/2 The game is a draw. } 1/2-1/2
    """;

    String parsedMoveText = MoveTextParser.parse(moveText);
    String expected = "e4 e5 Nf3 Nc6 Bb5 a6 Ba4 Nf6 O-O Be7 Re1 b5 Bb3 O-O d4 d6 c3 Bg4 Be3 exd4 cxd4 Na5 Bc2 c5 dxc5 dxc5 h3 Qxd1 Bxd1 Be6 Nbd2 Nc6 Ng5 c4 a3 Rad8 Bc2 Bc8 f4 Nd7 Ngf3 Nc5 e5 Nd3 Bxd3 Rxd3 Ne4 Bf5 Nd6";

    assertEquals(expected, parsedMoveText);
  }
}
