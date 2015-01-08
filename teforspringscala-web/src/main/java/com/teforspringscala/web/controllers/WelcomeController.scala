package com.teforspringscala.web.controllers

import java.util

import com.teforspringscala.domain.client.OrderClient
import com.teforspringscala.domain.entities.{ItemBuilder, Item, Order}
import com.teforspringscala.web.domainresource.WelcomeResource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.Link
import org.springframework.hateoas.config.EnableHypermediaSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder._
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMethod._
import org.springframework.web.bind.annotation.{RequestMapping, ResponseBody}

/**
 * Created by Bamsen on 2014-12-15.
 */
@Controller
@RequestMapping(value = Array("/api"))
@EnableHypermediaSupport(`type` = Array(EnableHypermediaSupport.HypermediaType.HAL))
class WelcomeController @Autowired()(val orderClient: OrderClient) {

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
    val listOfItems = new util.ArrayList[Item]()

    listOfItems.add(ItemBuilder.withName("BestItem").withInfo("the best item").withStock(20).build)
    listOfItems.add(ItemBuilder.withName("NextBest").withInfo("the next best item").withStock(16).build)
    listOfItems.add(ItemBuilder.withName("SuperItem").withInfo("the super Item").withStock(20).build)

    order.addList(listOfItems)
    orderClient.post(order)

  }


}

