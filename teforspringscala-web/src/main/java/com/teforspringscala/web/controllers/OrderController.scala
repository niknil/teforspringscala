package com.teforspringscala.web.controllers

import com.teforspringscala.item.client.OrderClient
import com.teforspringscala.item.domain.{ItemInfo, Item, Order}
import com.teforspringscala.web.domainresource.{OrderResource, OrderResources}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.Link
import org.springframework.hateoas.config.EnableHypermediaSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder._
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{PathVariable, RequestBody, ResponseBody, RequestMapping}
import org.springframework.web.bind.annotation.RequestMethod._
import scala.collection.JavaConverters._

import scala.collection.mutable.ArrayBuffer

/**
 * Created by Bamsen on 2014-12-17.
 */

@Controller
@RequestMapping(value = Array("/api"))
@EnableHypermediaSupport(`type` = Array(EnableHypermediaSupport.HypermediaType.HAL))
class OrderController @Autowired()(val orderClient: OrderClient) {

  @RequestMapping(value = Array("/orders/"), method = Array(GET))
  @ResponseBody
  def showOrders: OrderResources = {

    val listItems = orderClient.getList

    var link: Link = null
    val itemResourceList: ArrayBuffer[OrderResource] = new ArrayBuffer(listItems.size())
    val linkList: ArrayBuffer[Link] = new ArrayBuffer(listItems.size())


    for (order: Order <- listItems.asScala) {
      link = linkTo(methodOn(classOf[OrderController]).showOrder(order.getId)).withRel(order.getId.toString)
      linkList.append(link)
      itemResourceList.append(new OrderResource(order, new ArrayBuffer[Link]()))
    }

    new OrderResources(itemResourceList, linkList)
  }

  @RequestMapping(value = Array("/orders/"), method = Array(POST))
  @ResponseBody
  def createOrder(@RequestBody itemJson: Order): OrderResource = {


    orderClient.post(itemJson)

    val link: Link = linkTo(methodOn(classOf[OrderController]).showOrder(itemJson.getId)).withSelfRel()
    val linkList: ArrayBuffer[Link] = ArrayBuffer(link)

    new OrderResource(itemJson, linkList)
  }

  @RequestMapping(value = Array("/orders/{orderId}"), method = Array(GET))
  @ResponseBody
  def showOrder(@PathVariable orderId: Int): OrderResource = {

    val order: Order = orderClient.get(orderId)

    if (order == null) {
      throw new IllegalArgumentException("Did not find anything")
    }
    val link: Link = linkTo(methodOn(classOf[OrderController]).showOrder(orderId)).withSelfRel()

    val linkList: ArrayBuffer[Link] = ArrayBuffer(link)

    new OrderResource(order, linkList)
  }


  @RequestMapping(value = Array("/orders/test"), method = Array(GET))
  @ResponseBody
  def testOrder: OrderResource = {

    val order: Order = new Order()
    val itemInfo: ItemInfo = new ItemInfo("asd", 2)
    order.addItem(new Item("name", itemInfo))

    val link: Link = linkTo(methodOn(classOf[OrderController]).showOrders).withSelfRel()
    val linkList: ArrayBuffer[Link] = ArrayBuffer(link)



    new OrderResource(order, linkList)
  }

}
