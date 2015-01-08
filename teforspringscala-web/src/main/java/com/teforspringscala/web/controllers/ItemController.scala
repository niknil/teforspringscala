package com.teforspringscala.web.controllers

import com.teforspringscala.domain.client.ItemClient
import com.teforspringscala.domain.entities.Item
import com.teforspringscala.web.domainresource.{ItemResource, ItemResources}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.Link
import org.springframework.hateoas.config.EnableHypermediaSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder.{linkTo, methodOn}
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMethod._
import org.springframework.web.bind.annotation.{PathVariable, RequestBody, RequestMapping, ResponseBody}

import scala.collection.JavaConverters._
import scala.collection.mutable.ArrayBuffer

/**
 * Created by Bamsen on 2014-12-14.
 */
@Controller
@RequestMapping(value = Array("/api"))
@EnableHypermediaSupport(`type` = Array(EnableHypermediaSupport.HypermediaType.HAL))
class ItemController @Autowired()(val itemClient: ItemClient) {

  @RequestMapping(value = Array("/items/"), method = Array(GET))
  @ResponseBody
  def showItems: ItemResources = {

    val itemList = itemClient.getList

    var link: Link = null
    val itemResourceList: ArrayBuffer[ItemResource] = new ArrayBuffer(itemList.size())
    val linkList: ArrayBuffer[Link] = new ArrayBuffer(itemList.size())


    for (item: Item <- itemList.asScala) {
      link = linkTo(methodOn(classOf[ItemController]).showItem(item.getId)).withRel(item.getName)
      linkList.append(link)
      itemResourceList.append(new ItemResource(item,new ArrayBuffer[Link]()))
    }

    new ItemResources(itemResourceList, linkList)
  }

  @RequestMapping(value = Array("/items/"), method = Array(POST))
  @ResponseBody
  def createItem(@RequestBody itemFromJson: Item): ItemResource = {

    itemClient.post(itemFromJson)

    val link: Link = linkTo(methodOn(classOf[ItemController]).showItem(itemFromJson.id)).withSelfRel()
    val linkList: ArrayBuffer[Link] = ArrayBuffer(link)

    new ItemResource(itemFromJson, linkList)
  }

  @RequestMapping(value = Array("/items/{itemId}"), method = Array(GET))
  @ResponseBody
  def showItem(@PathVariable itemId: Int): ItemResource = {

    val item: Item = show(itemClient.get(itemId))

    val link: Link = linkTo(methodOn(classOf[ItemController]).showItem(itemId)).withSelfRel()

    val linkList: ArrayBuffer[Link] = ArrayBuffer(link)

    new ItemResource(item, linkList)
  }

  private def show(item: Option[Item]) = item match {
    case Some(s) => s
    case None => throw new IllegalArgumentException
  }

}

