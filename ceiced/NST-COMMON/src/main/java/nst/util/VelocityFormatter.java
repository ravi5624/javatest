package nst.util;

import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

import lombok.Data;
import nst.config.MyLogger;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

public class VelocityFormatter {

  static {
    Velocity.init();
  }

  public static void main(String[] args) {
    String format = formatFromResource("forms/sample.vm", getVelocityModel());
    System.out.println(format);
  }

  public static String format(String template, Map<String, Object> dataMap) {
    StringWriter writer = new StringWriter();
    VelocityContext context = new VelocityContext();
    try {
      dataMap.entrySet().stream()
          .forEach(entry -> context.put(StringUtils.trim(entry.getKey()), entry.getValue()));
      Velocity.evaluate(context, writer, "console", template);
      return writer.getBuffer().toString();
    } catch (Exception e) {
      MyLogger.logError(e);
    }
    return null;
  }

  public static String formatFromResource(String templatePath, Object data) {
    return format(ResourceReader.readAsString(templatePath), data);
  }

  public static String format(String template, Object data) {
    StringWriter writer = new StringWriter();
    VelocityContext context = new VelocityContext();
    VelocityEngine engine = new VelocityEngine();
    Properties properties = new Properties();
    properties.setProperty("resource.loader", "class");
    properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
    try {
      engine.init(properties);
      context.put("data", data);

      Template cssTemplate = engine.getTemplate("printView/header.vm");
      cssTemplate.merge(context, writer);

      Velocity.evaluate(context, writer, "console", template);
      return writer.getBuffer().toString();
    } catch (Exception e) {
      MyLogger.logError(e);
    }
    return null;
  }

  private static VelocityModel getVelocityModel() {
    VelocityModel customer = new VelocityModel();
    customer.setCustomerNumber("ABC123");
    customer.setName("Joe JavaRanch");
    customer.setAddress("123 Rancho Javo");
    customer.setCity("Bueno Ranch");
    customer.setState("CO");
    customer.setZip("63121");
    customer.setPhone("303-555-1212");
    return customer;
  }

  @Data
  public static class VelocityModel {

    String customerNumber;
    String name;
    String address;
    String city;
    String state;
    String zip;
    String phone;

    String[] names = new String[]{"A", "B", "C"};
  }
}
