package com.teforspringscala.controllers

import com.teforspringscala.controllers.domain.Hello
import com.teforspringscala.controllers.domainresource.HelloResource
import org.springframework.hateoas.Link
import org.springframework.hateoas.config.EnableHypermediaSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder._
import org.springframework.web.bind.annotation.{RequestMapping, ResponseBody}
import org.springframework.stereotype.Controller

/**
 * Created by Bamsen on 2014-12-07.
 */
@Controller
@EnableHypermediaSupport(`type` = Array(EnableHypermediaSupport.HypermediaType.HAL))
class ScalaController {

  @RequestMapping(value = Array("/api/hellos"))
  @ResponseBody
  def hellos(): HelloResource = {
    val hello: Hello = new Hello("hello", 1)
    val link1: Link = linkTo(methodOn(classOf[HelloWorldController]).greeting).withSelfRel
    val helloResource: HelloResource = new HelloResource(hello, link1)


    return helloResource



  }

}
