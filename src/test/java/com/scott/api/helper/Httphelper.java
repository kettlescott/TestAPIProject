package com.scott.api.helper;

import static io.restassured.RestAssured.given;

import com.google.gson.Gson;
import com.scott.api.constant.Request_URL;
import com.scott.api.json.location.RootLocation;
import com.scott.api.json.service.RootService;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import java.util.HashMap;

public class Httphelper {

  private HashMap<String, String> headers;

  private Gson gson;

  public Httphelper(HashMap<String, String> headers) {
    this.headers = headers;
    gson = new Gson();
  }

  public HashMap<String, String> getPostCode(Query... query) {
    HashMap<String, String> ret = new HashMap<>();
    int cnt = 0;
    for (Query q : query) {
      ResponseBody responseBody =
          given()
              .headers(headers)
              .accept(ContentType.JSON)
              .queryParam("q", q.getQ())
              .queryParam("state", q.getState())
              .when()
              .get(Request_URL.GET_POSTCODE)
              .getBody();

      RootLocation localities = gson.fromJson(responseBody.asString(), RootLocation.class);
      int postcode = localities.localities.locality.postcode;
      String tag = cnt % 2 == 0 ? "From" : "To";
      ret.put(tag, String.valueOf(postcode));
      cnt++;
    }
    return ret;
  }

  public RootService getServiceType(Service service) {
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
            .when()
            .get(Request_URL.GET_SERVICE)
            .getBody();
    return gson.fromJson(responseBody.asString(), RootService.class);
  }
}
