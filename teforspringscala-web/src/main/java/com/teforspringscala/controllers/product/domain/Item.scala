package com.teforspringscala.controllers.product.domain

import javax.persistence._

import scala.beans.BeanProperty
/**
 * Created by Bamsen on 2014-12-09.
 */
@Entity
@Table(name = "product")
class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var id: Long = null

  @BeanProperty
  val name: String = null

  def getId: Long = id

  override def toString = id + " = " + name
}
