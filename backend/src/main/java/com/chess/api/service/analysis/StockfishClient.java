package com.chess.api.service.analysis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.chess.api.model.analysis.StockfishEvaluation;

public class StockfishClient implements AutoCloseable {
  int DEPTH = 15;

  private Socket socket;
  private PrintWriter out;
  private BufferedReader in;
  private String engineVersion;

  protected StockfishClient(Socket socket) throws IOException {
    this.socket = socket;
    this.out = new PrintWriter(socket.getOutputStream(), true);
    this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
  }

  public void initializeUci() throws IOException {
    out.println("uci");

    Pattern pattern = Pattern.compile("Stockfish\\s[0-9.]+");
    Matcher matcher = pattern.matcher("");
    String line = null;
    while ((line = in.readLine()) != null) {
      if (engineVersion == null) {
        matcher.reset(line);
        if (matcher.find()) engineVersion = matcher.group(); 
      }

      if (line.contains("uciok")) return;
    }

    throw new IOException("Connection closed by remote host before receiving: uciok");
  }

  public void isReady() throws IOException {
    out.println("isready");
    waitFor("readyok");
  }

  public StockfishEvaluation evaluate(List<String> moves) throws IOException {
    System.out.println("Evaluating...");

    out.println("position startpos moves " + String.join(" ", moves));
    out.println("go depth " + DEPTH);

    String line;
    StockfishEvaluation eval = new StockfishEvaluation(DEPTH);

    while ((line = in.readLine()) != null) {
      System.out.println(line);
      if (line.contains("bestmove")) return eval;
      if (!line.contains("info depth 15")) continue;

      String[] evalParts = line.split(" ");
      for (int i = 0; i < evalParts.length; i++) {
        switch (evalParts[i]) {
          case "cp":
            int cp = Integer.parseInt(evalParts[i + 1]);
            eval.setCp(cp);
            break;
          case "pv":
            eval.setBestUci(evalParts[i + 1]);
            eval.setPvUci(new ArrayList<>(Arrays.asList(evalParts).subList(i + 1, evalParts.length)));
        }
      }
    }

    throw new IOException("Connection closed by remote host before receiving full evaluation");
  }

  public StockfishEvaluation evaluate(String fen) throws IOException {
    System.out.println("Evaluating...");

    out.println("position fen " + fen);
    out.println("go depth " + DEPTH);

    String line;
    StockfishEvaluation eval = new StockfishEvaluation(DEPTH);

    while ((line = in.readLine()) != null) {
      System.out.println(line);
      if (line.contains("bestmove")) return eval;
      if (!line.contains("info depth 15")) continue;

      String[] evalParts = line.split(" ");
      for (int i = 0; i < evalParts.length; i++) {
        switch (evalParts[i]) {
          case "cp":
            int cp = Integer.parseInt(evalParts[i + 1]);
            eval.setCp(cp);
            break;
          case "pv":
            eval.setBestUci(evalParts[i + 1]);
            eval.setPvUci(new ArrayList<>(Arrays.asList(evalParts).subList(i + 1, evalParts.length)));
        }
      }
    }

    throw new IOException("Connection closed by remote host before receiving full evaluation");
  }

  private void waitFor(String expected) throws IOException {
    String line;
    while ((line = in.readLine()) != null) {
      if (line.contains(expected)) return;
    }

    throw new IOException("Connection closed by remote host before receiving: " + expected);
  }

  public String getEngineVersion() {
    return engineVersion;
  }

  @Override
  public void close() throws IOException {
    try {
      if (out != null) out.println("quit");
    } finally {
      if (in != null) in.close();
      if (out != null) out.close();
      if (socket != null) socket.close();
    }
  }
}
