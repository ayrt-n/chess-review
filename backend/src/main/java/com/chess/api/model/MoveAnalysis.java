package com.chess.api.model;

import java.io.Serializable;
import java.util.List;

public class MoveAnalysis implements Serializable {
  private String san;
  private String uci;

  private String best_san;
  private List<String> pv_san;
  private int eval_cp;
  private int eval_cp_delta;

  private String commentary;
  private MoveClassification classification;

  public MoveAnalysis() {}

  public MoveAnalysis(String san, String uci, String best_san, List<String> pv_san, int eval_cp, int eval_cp_delta, String commentary, MoveClassification classication) {
    this.san = san;
    this.uci = uci;
    this.best_san = best_san;
    this.pv_san = pv_san;
    this.eval_cp = eval_cp;
    this.eval_cp_delta = eval_cp_delta;
    this.commentary = commentary;
    this.classification = classication;
  }
}
