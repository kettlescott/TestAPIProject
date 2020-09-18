package com.scott.api.test;

import static io.restassured.RestAssured.given;

import com.scott.api.constant.Request_URL;
import com.scott.api.helper.CsvReader;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import java.net.URL;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NegativeLocationTest extends BaseTest {

  @Test(dataProvider = "data-provider")
  public void dataDrivenTest(String from, String fromstate) {
    ResponseBody responseBody =
        given()
            .headers(headers)
            .accept(ContentType.JSON)
            .queryParam("q", from)
            .queryParam("state", fromstate)
            .when()
            .get(Request_URL.GET_POSTCODE)
            .getBody();
    Assert.assertEquals(responseBody.asString(), "{\"localities\":\"\"}");
  }

  @DataProvider(name = "data-provider")
  public Object[][] data() throws Exception {
    URL url = getClass().getClassLoader().getResource("illegallocation.csv");
    CsvReader csvReader = new CsvReader(url.getFile());
    return csvReader.loadData();
  }
}
