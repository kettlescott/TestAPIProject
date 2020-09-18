package com.scott.api.helper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

  private String file;

  public CsvReader(String file) {
    this.file = file;
  }

  public Object[][] loadData() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
    String line = null;
    List<String> lines = new ArrayList<>();
    while ((line = br.readLine()) != null) {
      lines.add(line);
    }
    Object[][] data = new Object[lines.size()][];
    for (int i = 1; i < lines.size(); ++i) {
      data[i] = lines.get(i).split(",");
    }
    return data;
  }
}
