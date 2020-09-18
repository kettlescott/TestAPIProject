package com.scott.api.helper;

public class Query {

  private String q;
  private String state;

  public Query(String q, String state) {
    this.q = q;
    this.state = state;
  }

  public String getQ() {
    return q;
  }

  public String getState() {
    return state;
  }
}
