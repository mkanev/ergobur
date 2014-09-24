package com.ergobureau.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Controller
@RequestMapping("/")
public class HomeController {

  @RequestMapping
  public String home() {
    return "index";
  }
}
