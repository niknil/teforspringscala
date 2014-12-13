package com.teforspringscala.web.controllers

import org.springframework.hateoas.config.EnableHypermediaSupport
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, ResponseBody}

/**
 * Created by Bamsen on 2014-12-04.
 */

@Controller
@EnableHypermediaSupport(`type` = Array(EnableHypermediaSupport.HypermediaType.HAL))
class HelloWorld {

  @RequestMapping(value = Array("/"))
  @ResponseBody
  def hello = "Put /api/ to explore the app"
}
