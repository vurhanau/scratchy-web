package com.scratchy.web;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class Scratchy {

  public static void main(String[] args) throws IOException {
    FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
    Configuration freeMarkerConfiguration = new Configuration();
    freeMarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(Scratchy.class, "/"));
    freeMarkerEngine.setConfiguration(freeMarkerConfiguration);

    get("/top/:n", (request, response) -> {
      response.status(200);
      response.type("text/html");
      Map<String, Object> attributes = new HashMap<>();
      int n = Integer.valueOf(request.params("n"));
      List<RankEntry> ranking = TopResource.list(n);
      attributes.put("topn", ranking); //Arrays.asList("1", "2", "3", "4"));//
      attributes.put("n", n);
      return freeMarkerEngine.render(new ModelAndView(attributes, "topn.ftl"));
    });
  }
}
