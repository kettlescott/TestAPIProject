package com.scott.api.test;

import static io.restassured.RestAssured.given;

import com.google.gson.Gson;
import com.scott.api.constant.Request_URL;
import com.scott.api.helper.CsvReader;
import com.scott.api.helper.Httphelper;
import com.scott.api.helper.Query;
import com.scott.api.helper.Service;
import com.scott.api.json.cost.RootCost;
import com.scott.api.json.service.RootService;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import java.net.URL;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EndToEndPositiveTest extends BaseTest {

  @Test(dataProvider = "data-provider")
  public void dataDrivenTest(
      String from,
      String fromstate,
      String to,
      String toState,
      String height,
      String weight,
      String length,
      String width,
      String cost) {
    Service service = pre_setup(from, fromstate, to, toState, height, weight, length, width);

    // verify post cost
    ResponseBody responseBody =
        given()
            .headers(headers)
            .accept(ContentType.JSON)
            .queryParam("from_postcode", service.getFrom_postcode())
            .queryParam("to_postcode", service.getTo_postcode())
            .queryParam("length", service.getLength())
            .queryParam("width", service.getWidth())
            .queryParam("height", service.getHeight())
            .queryParam("weight", service.getWeight())
            .queryParam("service_code", service.getServiceCode())
            .when()
            .get(Request_URL.GET_COST)
            .getBody();

    RootCost rootCost = new Gson().fromJson(responseBody.asString(), RootCost.class);
    Assert.assertEquals(rootCost.postage_result.total_cost, cost);
  }

  @DataProvider(name = "data-provider")
  public Object[][] data() throws Exception {
    URL url = getClass().getClassLoader().getResource("data.csv");
    CsvReader csvReader = new CsvReader(url.getFile());
    return csvReader.loadData();
  }

  private Service pre_setup(
      String from,
      String fromstate,
      String to,
      String toState,
      String height,
      String weight,
      String length,
      String width) {

    Httphelper httphelper = new Httphelper(headers);

    HashMap<String, String> post =
        httphelper.getPostCode(new Query(from, fromstate), new Query(to, toState));

    Service service =
        new Service()
            .from(post.get("From"))
            .to(post.get("To"))
            .height(Integer.parseInt(height.trim()))
            .weight(Integer.parseInt(weight.trim()))
            .length(Integer.parseInt(length.trim()))
            .width(Integer.parseInt(width.trim()));

    RootService rootService = httphelper.getServiceType(service);

    Assert.assertTrue(
        rootService.services.service.size() != 0, "could not find valid service code");
    System.out.println(rootService.services.service.get(0).code);
    service.serviceCode(rootService.services.service.get(0).code);

    return service;
  }
}
