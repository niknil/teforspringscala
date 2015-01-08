package com.teforspringscala.web.controllers

import java.util

import com.teforspringscala.domain.client.{ItemClient, OrderClient}
import com.teforspringscala.domain.entities.{Order, Item}
import com.teforspringscala.web.domainresource.WelcomeResource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.Link
import org.springframework.hateoas.config.EnableHypermediaSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder._
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMethod._
import org.springframework.web.bind.annotation.{ResponseBody, RequestMapping}

/**
 * Created by Bamsen on 2014-12-15.
 */
@Controller
@RequestMapping(value = Array("/api"))
@EnableHypermediaSupport(`type` = Array(EnableHypermediaSupport.HypermediaType.HAL))
class WelcomeController @Autowired()(val orderClient: OrderClient,val itemClient:ItemClient) {

  @RequestMapping(value = Array("/"))
  @ResponseBody
  def showApi: WelcomeResource = {

    val linkList: List[Link] = List(linkTo(methodOn(classOf[ItemController]).showItems).withRel("api:items"),
      linkTo(methodOn(classOf[OrderController]).showOrders).withRel("api:order"))

    new WelcomeResource("", linkList)
  }


  @RequestMapping(value = Array("/test"), method = Array(GET))
  @ResponseBody
  def testOrder: Unit = {

    val order: Order = new Order()

    val item: Item = new Item("asd","the best item",20)
    val item1: Item = new Item("asdf","the next best item",15)
    val item2: Item = new Item("asdsd","the super Item",10)


    val listOfItems = new util.ArrayList[Item]()

    listOfItems.add(item)
    listOfItems.add(item1)
    listOfItems.add(item2)

    itemClient.post(item)
    itemClient.post(item1)
    itemClient.post(item2)

    order.addList(listOfItems)
    orderClient.post(order)

  }


}

