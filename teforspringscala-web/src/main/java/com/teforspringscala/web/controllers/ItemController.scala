package com.teforspringscala.web.controllers


import com.teforspringscala.item.client.ItemClient
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import com.teforspringscala.item.domain.Item
import com.teforspringscala.web.domainresource.{ItemResource, ItemResources}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.Link
import org.springframework.hateoas.config.EnableHypermediaSupport
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{PathVariable, RequestBody, ResponseBody, RequestMapping}
import org.springframework.web.bind.annotation.RequestMethod._
import scala.collection.mutable.ArrayBuffer
import scala.collection.JavaConverters._

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
  def createItem(@RequestBody itemJson: Item): ItemResource = {


    itemClient.post(itemJson)

    val link: Link = linkTo(methodOn(classOf[ItemController]).showItem(itemJson.getId)).withSelfRel()
    val linkList: ArrayBuffer[Link] = ArrayBuffer(link)

    new ItemResource(itemJson, linkList)
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

