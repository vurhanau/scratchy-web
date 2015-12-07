package com.scratchy.web;

public class RankEntry {

  private int pos;

  private int count;

  private final String symbols;

  private final String url;

  public RankEntry(int pos, int count, String symbols, String url) {
    this.pos = pos;
    this.count = count;
    this.symbols = symbols;
    this.url = url;
  }

  public int getCount() {
    return count;
  }

  public String getSymbols() {
    return symbols;
  }

  public String getUrl() {
    return url;
  }

  public int getPos() {
    return pos;
  }
}
