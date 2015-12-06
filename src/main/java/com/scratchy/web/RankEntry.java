package com.scratchy.web;

public class RankEntry {

  private final int count;

  private final String symbols;

  private final String url;

  public RankEntry(int count, String symbols, String url) {
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
}
