package com.scott.api.test;

import static io.restassured.RestAssured.given;

import com.google.gson.Gson;
import com.scott.api.constant.Request_URL;
import com.scott.api.helper.CsvReader;
import com.scott.api.json.error.RootError;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import java.net.URL;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NegativeCalculateCostTest extends BaseTest {

  @Test(dataProvider = "data-provider")
  public void dataDrivenTest(
      String from_code,
      String to_code,
      String length,
      String width,
      String height,
      String weight,
      String serviceCode,
      String error) {
    ResponseBody responseBody =
        given()
            .headers(headers)
            .accept(ContentType.JSON)
            .queryParam("from_postcode", from_code.trim())
            .queryParam("to_postcode", to_code.trim())
            .queryParam("length", Integer.parseInt(length.trim()))
            .queryParam("width", Integer.parseInt(width.trim()))
            .queryParam("height", Integer.parseInt(height.trim()))
            .queryParam("weight", Integer.parseInt(weight.trim()))
            .queryParam("service_code", serviceCode.trim())
            .when()
            .get(Request_URL.GET_COST)
            .getBody();
    String error_msg =
        new Gson().fromJson(responseBody.asString(), RootError.class).error.errorMessage;
    Assert.assertEquals(error_msg, error, "validate error message");
  }

  @DataProvider(name = "data-provider")
  public Object[][] data() throws Exception {
    URL url = getClass().getClassLoader().getResource("illegalcost.csv");
    CsvReader csvReader = new CsvReader(url.getFile());
    return csvReader.loadData();
  }
}
