package com.teforspringscala.item.domain

import javax.persistence._

/**
 * Created by Bamsen on 2014-12-16.
 */

@Entity
@Table(name = "tefor_order")
class Order(private val name:String) {

  /*
  Java like defaultconstructor as required of
  JPA specification
  */
  private def this() = this(null)

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var id: Int = 0

  def getId = id

  def getName = name

  override def toString = id + " = " + name
}
