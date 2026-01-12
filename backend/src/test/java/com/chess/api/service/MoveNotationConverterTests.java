package com.chess.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveNotationConverterTests {
  private static final String STARTING_FEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

  private MoveNotationConverter converter;

  @BeforeEach
  void setUp() {
    converter = new MoveNotationConverter();
  }

  @Test
  void convertsPawnMoveFromStartingPosition() {
    String san = converter.uciToSan("e2e4", STARTING_FEN);
    assertEquals("e4", san);
  }

  @Test
  void convertsKnightMoveFromStartingPosition() {
    String san = converter.uciToSan("g1f3", STARTING_FEN);
    assertEquals("Nf3", san);
  }

  @Test
  void convertsCaptureMove() {
    String fen = "rnbqkbnr/ppp1pppp/8/3p4/4P3/8/PPPP1PPP/RNBQKBNR w KQkq d6 0 2";
    String san = converter.uciToSan("e4d5", fen);
    assertEquals("exd5", san);
  }

  @Test
  void convertsKingsideCastling() {
    String fen = "rnbqk2r/ppppbppp/5n2/4p3/4P3/5N2/PPPPBPPP/RNBQK2R w KQkq - 4 4";
    String san = converter.uciToSan("e1g1", fen);
    assertEquals("O-O", san);
  }

  @Test
  void convertsQueensideCastling() {
    String fen = "r3kbnr/pppqpppp/2n5/3p1b2/3P1B2/2N5/PPPQPPPP/R3KBNR w KQkq - 6 5";
    String san = converter.uciToSan("e1c1", fen);
    assertEquals("O-O-O", san);
  }

  @Test
  void convertsPawnPromotion() {
    String fen = "8/P7/8/8/8/8/8/4K2k w - - 0 1";
    String san = converter.uciToSan("a7a8q", fen);
    assertEquals("a8=Q+", san);  // Includes check
  }

  @Test
  void convertsKnightPromotionWithCapture() {
    String fen = "1n6/P7/8/8/8/8/8/4K2k w - - 0 1";
    String san = converter.uciToSan("a7b8n", fen);
    assertEquals("axb8=N", san);
  }

  @Test
  void convertsListOfMovesFromStartingPosition() {
    List<String> uciMoves = List.of("e2e4", "e7e5", "g1f3", "b8c6", "f1b5");
    List<String> sanMoves = converter.uciToSan(uciMoves, STARTING_FEN);

    assertEquals(List.of("e4", "e5", "Nf3", "Nc6", "Bb5"), sanMoves);
  }

  @Test
  void convertsCheckmate() {
    // Fool's Mate: 1. f3 e5 2. g4 Qh4#
    String fen = "rnbqkbnr/pppp1ppp/8/4p3/6P1/5P2/PPPPP2P/RNBQKBNR b KQkq - 0 2";
    String san = converter.uciToSan("d8h4", fen);
    assertEquals("Qh4#", san);
  }

  @Test
  void convertsAmbiguousPieceMove() {
    String fen = "4k3/8/8/8/8/2N1N3/8/4K3 w - - 0 1";
    String san = converter.uciToSan("c3d5", fen);
    assertEquals("Ncd5", san);
  }

  @Test
  void convertsListWithCastling() {
    List<String> uciMoves = List.of("e2e4", "e7e5", "g1f3", "b8c6", "f1c4", "g8f6", "e1g1");
    List<String> sanMoves = converter.uciToSan(uciMoves, STARTING_FEN);

    assertEquals(List.of("e4", "e5", "Nf3", "Nc6", "Bc4", "Nf6", "O-O"), sanMoves);
  }
}
