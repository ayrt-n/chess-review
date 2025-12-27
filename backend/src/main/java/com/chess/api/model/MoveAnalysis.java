package com.chess.api.model;

import java.io.Serializable;
import java.util.List;

public class MoveAnalysis implements Serializable {
  private String san;
  private String uci;

  private String best_san;
  private List<String> pvSan;
  private int evalCp;
  private int evalCpDelta;

  private String commentary;
  private MoveClassification classification;

  public MoveAnalysis() {}

  public MoveAnalysis(String san) {
    this.san = san;
  }

  public String getSan() {
    return san;
  }

  public void setSan(String san) {
    this.san = san;
  }

  public String getUci() {
    return uci;
  }

  public void setUci(String uci) {
    this.uci = uci;
  }

  public String getBestSan() {
    return best_san;
  }

  public void setBestSan(String best_san) {
    this.best_san = best_san;
  }

  public List<String> getPvSan() {
    return pvSan;
  }

  public void setPvSan(List<String> pv_san) {
    this.pvSan = pv_san;
  }

  public int getEvalCp() {
    return evalCp;
  }

  public void setEvalCp(int eval_cp) {
    this.evalCp = eval_cp;
  }

  public int getEvalCpDelta() {
    return evalCpDelta;
  }

  public void setEvalCpDelta(int eval_cp_delta) {
    this.evalCpDelta = eval_cp_delta;
  }

  public String getCommentary() {
    return commentary;
  }

  public void setCommentary(String commentary) {
    this.commentary = commentary;
  }

  public MoveClassification getClassification() {
    return classification;
  }

  public void setClassification(MoveClassification classification) {
    this.classification = classification;
  }
}
