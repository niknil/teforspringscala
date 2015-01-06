package com.teforspringscala.web.controllers

import javax.transaction.Transactional

import com.teforspringscala.item.client.{ItemClient, OrderClient}
import com.teforspringscala.item.domain.{Order}
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
class OrderController @Autowired()(val orderClient: OrderClient,val itemClient:ItemClient) {

  @RequestMapping(value = Array("/orders/"), method = Array(GET))
  @ResponseBody
  def showOrders: OrderResources = {

    val orderList = orderClient.getList

    var link: Link = null
    val orderResourceList: ArrayBuffer[OrderResource] = new ArrayBuffer(orderList.size())
    val linkList: ArrayBuffer[Link] = new ArrayBuffer(orderList.size())


    for (order: Order <- orderList.asScala) {
      link = linkTo(methodOn(classOf[OrderController]).showOrder(order.getId)).withRel(order.getId.toString)
      linkList.append(link)
      orderResourceList.append(new OrderResource(order, new ArrayBuffer[Link]()))
    }

    new OrderResources(orderResourceList, linkList)

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


  @RequestMapping(value = Array("/orders/test"), method = Array(POST))
  @ResponseBody
  def testOrder: OrderResource = {

    val order: Order = new Order()


    order.addItem(itemClient.get(1))
    orderClient.post(order)

    val link: Link = linkTo(methodOn(classOf[OrderController]).showOrder(order.getId)).withSelfRel()
    val linkList: ArrayBuffer[Link] = ArrayBuffer(link)

    new OrderResource(order, linkList)
  }

}
