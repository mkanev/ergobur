package com.ergobureau.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Controller
@RequestMapping("/blog")
public class BlogController extends BasePage {

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String record(@PathVariable("id") Long recordId) {
    return "blog/blog" + recordId;
  }

}
