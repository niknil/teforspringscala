package com.teforspringscala.web.controllers

import com.teforspringscala.item.client.{ItemClient, OrderClient}
import com.teforspringscala.item.domain.{Item, Order}
import com.teforspringscala.web.domainresource.{ItemResource, OrderResource, OrderResources}
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
class OrderController @Autowired()(val orderClient: OrderClient,val itemClient:ItemClient) {

  @RequestMapping(value = Array("/orders/"), method = Array(GET))
  @ResponseBody
  def showOrders: OrderResources = {

    val orderList = orderClient.getList
    val orderResourceList: ArrayBuffer[OrderResource] = new ArrayBuffer()
    val orderLinks: ArrayBuffer[Link] = new ArrayBuffer()
    var link: Link = null


    for (order: Order <- orderList.asScala) {

      val itemList = order.getItems
      val itemLinks: ArrayBuffer[Link] = new ArrayBuffer(itemList.size())

      for (item: Item <- itemList.asScala) {
        link = linkTo(methodOn(classOf[ItemController]).showItem(item.getId)).withRel(item.getName)
        itemLinks.append(link)
      }

        link = linkTo(methodOn(classOf[OrderController]).showOrder(order.getId)).withRel(order.getId.toString)
        orderLinks.append(link)
        orderResourceList.append(new OrderResource(order, itemLinks))
    }

    new OrderResources(orderResourceList, orderLinks)

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

    val order: Order = controlOrder(orderClient.get(orderId))
    val link: Link = linkTo(methodOn(classOf[OrderController]).showOrder(orderId)).withSelfRel()
    val linkList: ArrayBuffer[Link] = ArrayBuffer(link)
    new OrderResource(order, linkList)

  }



  private def controlOrder(generic: Option[Order]) = generic match {
    case Some(s) => s
    case None => throw new IllegalArgumentException
  }


}
