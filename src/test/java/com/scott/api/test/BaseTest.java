package com.scott.api.test;

import com.scott.api.config.ConfigManager;
import java.util.HashMap;
import org.testng.annotations.BeforeClass;

public class BaseTest {

  private String API_KEY;

  protected HashMap<String, String> headers;

  @BeforeClass
  public void setup() {
    API_KEY = ConfigManager.instance().getByKey("API_KEY");
    headers = new HashMap<>();
    headers.put("AUTH-KEY", API_KEY);
  }
}
