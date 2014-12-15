package com.teforspringscala.web.controllers


import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.{DefaultScalaModule}
import com.teforspringscala.item.dao.ItemRepo
import com.teforspringscala.item.domain.Item
import com.teforspringscala.web.domain.ItemJson
import com.teforspringscala.web.domainresource.ItemResource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.Link
import org.springframework.hateoas.config.EnableHypermediaSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder._
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation._
import org.springframework.web.bind.annotation.RequestMethod._

/**
 * Created by Bamsen on 2014-12-14.
 */
@Controller
@RequestMapping(value = Array("/api"))
@EnableHypermediaSupport(`type` = Array(EnableHypermediaSupport.HypermediaType.HAL))
class ItemController @Autowired ()  (val itemRepo: ItemRepo) {

    @RequestMapping(value = Array("/items/"), method = Array(GET))
    @ResponseBody
    def showItems:java.util.List[Item] = itemRepo.getAll

    @RequestMapping(value = Array("/items/"),method = Array(POST))
    @ResponseBody
    def createItem(@RequestBody items:ItemJson):ItemResource = {

      val jsonItem = items
      val mapper = new ObjectMapper()
      mapper.registerModule(DefaultScalaModule)
      val item = mapper.readValue[Item](jsonItem.toString, classOf[Item])
      val links:Link = new Link("");

      itemRepo.save(item)

      new ItemResource(item,links)
    }

  @RequestMapping(value = Array("/items/{itemId}"), method = Array(GET))
  @ResponseBody
  def showItem(@PathVariable itemId: Long):ItemResource = {

    val item:Item = itemRepo.get(itemId)

    if(item == null){
      throw new IllegalArgumentException("Did not find anything")
    }
    val links:Link = linkTo(methodOn(classOf[ItemController]).showItems).withRel("ordering")

    new ItemResource(item,links)
  }


  @RequestMapping(value = Array("/items/test"),method = Array(POST))
  @ResponseBody
  def testcreateItem(@RequestBody content:String):ItemResource = {

    val mapper = new ObjectMapper()
    mapper.registerModule(DefaultScalaModule)


    val item = mapper.readValue[Item](content, classOf[Item])



    val links:Link = linkTo(methodOn(classOf[ItemController]).showItems).withRel("ordering")

    itemRepo.save(item)

    new ItemResource(item,links)
  }
}
