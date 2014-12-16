package com.teforspringscala.web.controllers


import java.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.teforspringscala.item.dao.ItemRepo
import com.teforspringscala.item.domain.Item
import com.teforspringscala.web.domainresource.{ItemResources, ItemResource}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.Link
import org.springframework.hateoas.config.EnableHypermediaSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder._
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation._
import org.springframework.web.bind.annotation.RequestMethod._
import scala.collection.JavaConverters._
import scala.collection.mutable.ArrayBuffer

/**
 * Created by Bamsen on 2014-12-14.
 */
@Controller
@RequestMapping(value = Array("/api"))
@EnableHypermediaSupport(`type` = Array(EnableHypermediaSupport.HypermediaType.HAL))
class ItemController @Autowired ()  (val itemRepo: ItemRepo) {

    @RequestMapping(value = Array("/items/"), method = Array(GET))
    @ResponseBody
    def showItems:ItemResources = {

      val listItems = itemRepo.getAll

      var link:Link = null
      val itemResourceList: ArrayBuffer[ItemResource] = new ArrayBuffer(listItems.size())
      val linkList: ArrayBuffer[Link] = new ArrayBuffer(listItems.size())

      val linkLists: ArrayBuffer[Link] = new ArrayBuffer(listItems.size())

      for(item:Item <- listItems.asScala){
        link = linkTo(methodOn(classOf[ItemController]).showItem(item.getId)).withSelfRel()
        linkList.append(link)
        itemResourceList.append(new ItemResource(item,linkList))
      }

      new ItemResources(itemResourceList,new ArrayBuffer[Link]())
    }
    @RequestMapping(value = Array("/items/"),method = Array(POST))
    @ResponseBody
    def createItem(@RequestBody itemJson:Item):ItemResource = {


      itemRepo.save(itemJson)

      val link:Link = linkTo(methodOn(classOf[ItemController]).showItem(itemJson.getId)).withSelfRel()
      val linkList:ArrayBuffer[Link] = ArrayBuffer(link)

      new ItemResource(itemJson,linkList)
    }

  @RequestMapping(value = Array("/items/{itemId}"), method = Array(GET))
  @ResponseBody
  def showItem(@PathVariable itemId: Long):ItemResource = {

    val item:Item = itemRepo.get(itemId)

    if(item == null){
      throw new IllegalArgumentException("Did not find anything")
    }
    val link:Link = linkTo(methodOn(classOf[ItemController]).showItem(itemId)).withSelfRel()

    val linkList:ArrayBuffer[Link] = ArrayBuffer(link)

    new ItemResource(item,linkList)
  }


  @RequestMapping(value = Array("/items/test"),method = Array(POST))
  @ResponseBody
  def testCreateItem(@RequestBody content:String):ItemResource = {

    val mapper = new ObjectMapper()
    mapper.registerModule(DefaultScalaModule)
    val item = mapper.readValue[Item](content, classOf[Item])
    itemRepo.save(item)
    val links:Link = linkTo(methodOn(classOf[ItemController]).showItem(item.getId)).withSelfRel()
    val linkList:ArrayBuffer[Link] = ArrayBuffer(links)
    new ItemResource(item,linkList)

  }



}
