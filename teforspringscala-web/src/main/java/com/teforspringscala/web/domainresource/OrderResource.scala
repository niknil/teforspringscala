package com.teforspringscala.web.domainresource

import com.fasterxml.jackson.annotation.JsonCreator
import com.teforspringscala.domain.entities.Order
import org.springframework.hateoas.{Link, Resource}

import scala.collection.JavaConverters._
import scala.collection.mutable.ArrayBuffer
/**
 * Created by Bamsen on 2014-12-18.
 */
class OrderResource @JsonCreator()(val content: Order, val links: ArrayBuffer[Link]) extends Resource[Order](content, links.asJava) {}

