package com.teforspringscala.web.domainresource

import com.fasterxml.jackson.annotation.JsonCreator
import com.teforspringscala.item.domain.Item
import org.springframework.hateoas.{Link, Resource}

import scala.collection.JavaConverters._
import scala.collection.mutable.ArrayBuffer

/**
 * Created by Bamsen on 2014-12-11.
 */
class ItemResource @JsonCreator()(val content:Item,val links:ArrayBuffer[Link]) extends Resource[Item](content, links.asJava) {}
