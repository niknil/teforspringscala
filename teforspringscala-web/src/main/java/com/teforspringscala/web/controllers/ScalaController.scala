package com.teforspringscala.web.controllers

import com.teforspringscala.item.dao.ItemRepo
import com.teforspringscala.item.domain.Item
import com.teforspringscala.web.domainresource.ItemResource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.Link
import org.springframework.hateoas.config.EnableHypermediaSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder._
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, ResponseBody}


/**
 * Created by Bamsen on 2014-12-07.
 */
@Controller
@EnableHypermediaSupport(`type` = Array(EnableHypermediaSupport.HypermediaType.HAL))
class ScalaController @Autowired ()  (val itemRepo: ItemRepo) {


  @RequestMapping(value = Array("/api/hellos"))
  @ResponseBody
  def hello = itemRepo.getAll

  @RequestMapping(value = Array("/api/create"))
  @ResponseBody
  def create():ItemResource = {

    val item = new Item
    val links:Link = linkTo(methodOn(classOf[HelloWorldController]).greeting).withRel("ordering");


    itemRepo.save(item)

    new ItemResource(item,links)
  }


}
