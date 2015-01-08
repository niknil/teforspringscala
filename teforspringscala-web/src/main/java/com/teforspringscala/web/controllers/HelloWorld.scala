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
 * Created by Bamsen on 2014-12-04.
 */


@Controller
@EnableHypermediaSupport(`type` = Array(EnableHypermediaSupport.HypermediaType.HAL))
class HelloWorld @Autowired()(val orderClient: OrderClient) {

  @RequestMapping(value = Array("/"), method = Array(GET))
  @ResponseBody
  def showApi: WelcomeResource = {

    val order: Order = new Order()
    val listOfItems = new util.ArrayList[Item]()

    listOfItems.add(ItemBuilder.withName("BestItem").withInfo("the best item").withStock(20).build)
    listOfItems.add(ItemBuilder.withName("NextBest").withInfo("the next best item").withStock(16).build)
    listOfItems.add(ItemBuilder.withName("SuperItem").withInfo("the super Item").withStock(20).build)

    order.addList(listOfItems)
    orderClient.post(order)


    val link: Link = linkTo(methodOn(classOf[WelcomeController]).showApi).withRel("teforspringscala:api")
    val linkList: List[Link] = List(link)
    new WelcomeResource("", linkList)
  }


}
