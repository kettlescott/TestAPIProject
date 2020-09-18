package com.scott.api.helper;

public class Service {

  private String from_postcode, to_postcode, serviceCode;
  private int length, width, height, weight;

  public Service from(String from_postcode) {
    this.from_postcode = from_postcode;
    return this;
  }

  public Service to(String to_postcode) {
    this.to_postcode = to_postcode;
    return this;
  }

  public Service length(int length) {
    this.length = length;
    return this;
  }

  public Service width(int width) {
    this.width = width;
    return this;
  }

  public Service height(int height) {
    this.height = height;
    return this;
  }

  public Service weight(int weight) {
    this.weight = weight;
    return this;
  }

  public Service serviceCode(String serviceCode) {
    this.serviceCode = serviceCode;
    return this;
  }

  public String getServiceCode() {
    return serviceCode;
  }

  public String getFrom_postcode() {
    return from_postcode;
  }

  public String getTo_postcode() {
    return to_postcode;
  }

  public int getLength() {
    return length;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public int getWeight() {
    return weight;
  }
}
