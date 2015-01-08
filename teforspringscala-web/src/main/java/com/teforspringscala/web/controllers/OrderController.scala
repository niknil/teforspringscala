package com.teforspringscala.web.controllers

import com.teforspringscala.domain.client.{ItemClient, OrderClient}
import com.teforspringscala.domain.entities.{Item, Order}
import com.teforspringscala.web.domainresource.{OrderResource, OrderResources}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.Link
import org.springframework.hateoas.config.EnableHypermediaSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder._
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMethod._
import org.springframework.web.bind.annotation.{PathVariable, RequestBody, RequestMapping, ResponseBody}

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
  def createOrder(@RequestBody orderFromJson: Order): OrderResource = {

    orderClient.post(orderFromJson)

    val link: Link = linkTo(methodOn(classOf[OrderController]).showOrder(orderFromJson.getId)).withSelfRel()
    val linkList: ArrayBuffer[Link] = ArrayBuffer(link)

    new OrderResource(orderFromJson, linkList)
  }

  @RequestMapping(value = Array("/orders/{orderId}"), method = Array(GET))
  @ResponseBody
  def showOrder(@PathVariable orderId: Int): OrderResource = {

    val order: Order = orderClient.get(orderId)


    val itemList = order.getItems
    val itemLinks: ArrayBuffer[Link] = new ArrayBuffer(itemList.size())

    for (item: Item <- itemList.asScala) {
      val link: Link = linkTo(methodOn(classOf[ItemController]).showItem(item.getId)).withRel(item.getName)
      itemLinks.append(link)
    }

    val link: Link = linkTo(methodOn(classOf[OrderController]).showOrder(orderId)).withSelfRel()
    val linkList: ArrayBuffer[Link] = ArrayBuffer(link)
    new OrderResource(order, linkList)

  }

  @RequestMapping(value = Array("/orders/{orderId}"), method = Array(DELETE))
  @ResponseBody
  def deleteOrder(@PathVariable orderId: Int) = {

    orderClient.delete(orderId)

  }






}
