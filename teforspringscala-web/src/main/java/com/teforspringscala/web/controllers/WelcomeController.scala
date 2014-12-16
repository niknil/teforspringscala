package com.teforspringscala.web.controllers

import com.teforspringscala.web.domainresource.{WelcomeResource}
import org.springframework.hateoas.Link
import org.springframework.hateoas.config.EnableHypermediaSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder._
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{ResponseBody, RequestMapping}

/**
 * Created by Bamsen on 2014-12-15.
 */
@Controller
@RequestMapping(value = Array("/api"))
@EnableHypermediaSupport(`type` = Array(EnableHypermediaSupport.HypermediaType.HAL))
class WelcomeController {

  @RequestMapping(value = Array("/"))
  @ResponseBody
  def showApi:WelcomeResource = {

    val link: Link = linkTo(methodOn(classOf[ItemController]).showItems).withRel("api:items")
    val linkList:List[Link] = List(link)
    new WelcomeResource("",linkList)
}

}
