package com.teforspringscala.web.controllers

import com.teforspringscala.web.domainresource.WelcomeResource
import org.springframework.hateoas.Link
import org.springframework.hateoas.config.EnableHypermediaSupport
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, ResponseBody}
import org.springframework.web.bind.annotation.RequestMethod._
import org.springframework.hateoas.mvc.ControllerLinkBuilder._

/**
 * Created by Bamsen on 2014-12-04.
 */


@Controller
@EnableHypermediaSupport(`type` = Array(EnableHypermediaSupport.HypermediaType.HAL))
class HelloWorld {

  @RequestMapping(value = Array("/"), method = Array(GET))
  @ResponseBody
  def showApi: WelcomeResource = {

    val link: Link = linkTo(methodOn(classOf[WelcomeController]).showApi).withRel("teforspringscala:api")
    val linkList: List[Link] = List(link)
    new WelcomeResource("", linkList)
  }

}
