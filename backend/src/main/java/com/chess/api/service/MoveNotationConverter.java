package com.chess.api.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.move.Move;
import com.github.bhlangonijr.chesslib.move.MoveList;

@Service
public class MoveNotationConverter {
  public String uciToSan(String uci, String fen) {
    Board board = loadFen(fen);
    Move move = new Move(uci, board.getSideToMove());

    MoveList moveList = new MoveList(fen);
    moveList.add(move);

    return moveList.toSanArray()[0];
  }

  public List<String> uciToSan(List<String> uciMoves, String startingFen) {
    Board board = loadFen(startingFen);
    MoveList moveList = new MoveList(startingFen);

    for (String uci : uciMoves) {
      Move move = new Move(uci, board.getSideToMove());
      moveList.add(move);
      board.doMove(move);
    }

    return Arrays.asList(moveList.toSanArray());
  }

  private Board loadFen(String fen) {
    Board board = new Board();
    board.loadFromFen(fen);

    return board;
  }
}
