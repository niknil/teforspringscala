package com.teforspringscala.item.domain

import javax.persistence._

import scala.beans.BeanProperty
/**
 * Created by Bamsen on 2014-12-09.
 */
@Entity
@Table(name = "product")
class Item(private val name:String) {


  private def this() = this(null)

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var id: Long = 0

  def getId = id

  def getName = name

  override def toString = id + " = " + name
}
