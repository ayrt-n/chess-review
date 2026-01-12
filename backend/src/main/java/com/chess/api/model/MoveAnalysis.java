package com.chess.api.model;

import java.io.Serializable;
import java.util.List;

import com.github.bhlangonijr.chesslib.Side;

public class MoveAnalysis implements Serializable {
  private String san;
  private String uci;
  private String fen;
  private Side side;

  private String bestUci;
  private String bestSan;
  private List<String> pvUci;
  private List<String> pvSan;

  private Integer evalCp;
  private Integer evalMate;

  private String commentary;
  private MoveClassification classification;

  public MoveAnalysis() {}

  public MoveAnalysis(String san, String uci) {
    this.san = san;
    this.uci = uci;
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

  public String getFen() {
    return fen;
  }

  public void setFen(String fen) {
    this.fen = fen;
  }

  public Side getSide() {
    return side;
  }

  public void setSide(Side side) {
    this.side = side;
  }

  public String getBestUci() {
    return bestUci;
  }

  public void setBestUci(String bestUci) {
    this.bestUci = bestUci;
  }

  public String getBestSan() {
    return bestSan;
  }

  public void setBestSan(String bestSan) {
    this.bestSan = bestSan;
  }

  public List<String> getPvUci() {
    return pvUci;
  }

  public void setPvUci(List<String> pvUci) {
    this.pvUci = pvUci;
  }

  public List<String> getPvSan() {
    return pvSan;
  }

  public void setPvSan(List<String> pvSan) {
    this.pvSan = pvSan;
  }

  public Integer getEvalCp() {
    return evalCp;
  }

  public void setEvalCp(Integer evalCp) {
    this.evalCp = evalCp;
  }

  public Integer getEvalMate() {
    return evalMate;
  }

  public void setEvalMate(Integer evalMate) {
    this.evalMate = evalMate;
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
