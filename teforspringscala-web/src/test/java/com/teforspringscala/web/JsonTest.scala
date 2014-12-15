package com.teforspringscala.web

/**
 * Created by Bamsen on 2014-12-15.
 */

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.teforspringscala.web.domain.ItemJson
import org.junit.Assert._
import org.hamcrest.CoreMatchers._
import org.junit.{After, Test}

class JsonTest {

  val mapper = new ObjectMapper()


  @Test
  def testJson {

    mapper.registerModule(DefaultScalaModule)
    val strings = """{"name": "asd"}"""
    val item: ItemJson = mapper.readValue[ItemJson](strings,classOf[ItemJson])

    println(item.name)

  }


}
