package com.scratchy.web;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class Scratchy {
  public static void main(String[] args) throws IOException {
    FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
    Configuration freeMarkerConfiguration = new Configuration();
    freeMarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(Scratchy.class, "/"));
    freeMarkerEngine.setConfiguration(freeMarkerConfiguration);

    get("/", (request, response) -> {
      response.status(200);
      response.type("text/html");
      Map<String, Object> attributes = new HashMap<>();
      attributes.put("list", Arrays.asList("hello", "world"));
      return freeMarkerEngine.render(new ModelAndView(attributes, "topn.ftl"));
    });

  }
}
