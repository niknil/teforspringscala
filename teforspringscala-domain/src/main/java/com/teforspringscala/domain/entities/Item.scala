package com.teforspringscala.domain.entities

import java.util
import javax.persistence._



/**
 * Created by Bamsen on 2014-12-09.
 */
@Entity
class Item(private val name:String, private val info:String,private val stock:Int) extends AbstractEntity {

  /*
  Java like defaultconstructor as required of
  JPA specification
  */
  private def this() = this(null,null,0)


  @ManyToMany
  var order: java.util.List[Order] = new java.util.ArrayList[Order]

  def getOrders = order

  def addOrder(item: Order) = {
    if(order == null){
      order = new util.ArrayList[Order]()
    }

    order.add(item)
  }

  def addList(itemsList: java.util.List[Order]) = {
    order = itemsList
  }



  def getName = name
  def getInfo = info
  def getStock = stock


  override def toString = name
}

object ItemBuilder {
  private var name:Option[String] = None
  private var info:Option[String] = None
  private var stock:Option[Int] = None


  def withName(s:String) = {name = Some(s); this}
  def withInfo(s:String) = {info = Some(s); this}
  def withStock(s:Int) = {stock = Some(s); this}


  def build = new Item(name.get,info.get,stock.get)
}
