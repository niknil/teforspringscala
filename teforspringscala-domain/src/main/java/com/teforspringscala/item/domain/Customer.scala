package com.teforspringscala.item.domain

import javax.persistence._

/**
 * Created by Bamsen on 2014-12-16.
 */

@Entity
@Table(name = "customer")
class Customer(private val accName:String) extends AbstractEntity{

  /*
  Java like defaultconstructor as required of
  JPA specification
  */
  private def this() = this(null)

  def getName = accName

  override def toString = accName



}

object CustomerBuilder {
  private var accName:Option[String] = None

  def withAccName(s:String) = {accName = Some(s); this}

  def build() = new Customer(accName.get)
}

