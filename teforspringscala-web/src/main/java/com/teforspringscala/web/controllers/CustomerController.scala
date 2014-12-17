package com.teforspringscala.web.controllers

import org.springframework.hateoas.config.EnableHypermediaSupport
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Created by Bamsen on 2014-12-17.
 */

@Controller
@RequestMapping(value = Array("/api"))
@EnableHypermediaSupport(`type` = Array(EnableHypermediaSupport.HypermediaType.HAL))
class CustomerController {

}
