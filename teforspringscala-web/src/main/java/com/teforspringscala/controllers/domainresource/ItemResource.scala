package com.teforspringscala.controllers.domainresource

import com.teforspringscala.controllers.product.domain.Item
import org.springframework.hateoas.{Link, Resource}

import scala.collection.JavaConverters._

/**
 * Created by Bamsen on 2014-12-11.
 */
class ItemResource(val hej:Item,val ny:Link*) extends Resource[Item](hej, ny.asJava) {}
