package com.shmoozed.controller;

import java.util.List;

import com.shmoozed.model.Item;
import com.shmoozed.model.WalmartItem;
import com.shmoozed.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin // Allow All CORS Requests. See https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
@RestController
@RequestMapping(path = "/search")
public class SearchController {

  private Logger logger = LoggerFactory.getLogger(WalmartController.class);
  private SearchService searchService;

  @Autowired
  public SearchController(SearchService searchService) {

    this.searchService = searchService;
  }

  @GetMapping(
    path="/{searchterm}",
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody
  ResponseEntity<List<WalmartItem>> search(@PathVariable("searchterm") String searchTerm){
    logger.debug("Request to search for searchTerm={}", searchTerm);
    List<WalmartItem> items = searchService.search(searchTerm);
    return new ResponseEntity<>(items, HttpStatus.OK);

  }

}
