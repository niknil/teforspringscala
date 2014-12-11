package com.teforspringscala.controllers.control


import javax.ws.rs.POST

import com.teforspringscala.controllers.product.dao.ItemRepo
import com.teforspringscala.controllers.product.domain.Item
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.config.EnableHypermediaSupport
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, ResponseBody}


/**
 * Created by Bamsen on 2014-12-07.
 */
@Controller
@EnableHypermediaSupport(`type` = Array(EnableHypermediaSupport.HypermediaType.HAL))
class ScalaController @Autowired ()  (val itemRepo: ItemRepo) {


  @RequestMapping(value = Array("/api/hellos"))
  @ResponseBody
  def hello = itemRepo.getAll

  @RequestMapping(value = Array("/api/create"))
  @ResponseBody
  def create = {

    val item = new Item


    itemRepo.save(item)

  }


}
