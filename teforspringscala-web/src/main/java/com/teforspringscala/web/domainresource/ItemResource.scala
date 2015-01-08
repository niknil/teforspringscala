package com.teforspringscala.web.domainresource

import com.fasterxml.jackson.annotation.{JsonUnwrapped, JsonCreator}
import com.teforspringscala.domain.entities.Item
import org.springframework.hateoas.{Link, Resource}

import scala.collection.JavaConverters._
import scala.collection.mutable.ArrayBuffer

/**
 * Created by Bamsen on 2014-12-11.
 */
class ItemResource @JsonCreator()(@JsonUnwrapped content: Item, val links: ArrayBuffer[Link]) extends Resource[Item](content, links.asJava) {}
