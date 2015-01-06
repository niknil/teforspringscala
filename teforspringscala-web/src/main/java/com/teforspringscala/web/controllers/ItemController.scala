package com.teforspringscala.web.controllers


import com.teforspringscala.item.client.ItemClient
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import com.teforspringscala.item.domain.{ItemInfo, Order, Item}
import com.teforspringscala.web.domainresource.{OrderResource, ItemResource, ItemResources}
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

    val listItems = itemClient.getList

    var link: Link = null
    val itemResourceList: ArrayBuffer[ItemResource] = new ArrayBuffer(listItems.size())
    val linkList: ArrayBuffer[Link] = new ArrayBuffer(listItems.size())


    for (item: Item <- listItems.asScala) {
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

    val item: Item = itemClient.get(itemId)

    if (item == null) {
      throw new IllegalArgumentException("Did not find anything")
    }
    val link: Link = linkTo(methodOn(classOf[ItemController]).showItem(itemId)).withSelfRel()

    val linkList: ArrayBuffer[Link] = ArrayBuffer(link)

    new ItemResource(item, linkList)
  }


  @RequestMapping(value = Array("/items/test"), method = Array(GET))
  @ResponseBody
  def testOrder: ItemResource = {

    val itemInfo: ItemInfo = new ItemInfo("asd",2)
    val item: Item = new Item("asd",itemInfo)


    itemClient.post(item)



    val link: Link = linkTo(methodOn(classOf[ItemController]).showItem(item.getId)).withSelfRel()
    val linkList: ArrayBuffer[Link] = ArrayBuffer(link)

    new ItemResource(item, linkList)


  }
}
