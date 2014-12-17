package com.teforspringscala.item.client

import com.teforspringscala.item.dao.{CustomerManager, OrderManager, ItemManager}
import com.teforspringscala.item.domain.{Order, Item}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Created by Bamsen on 2014-12-17.
 */
trait OrderClient {
  def post(item: Order): Unit

  def get(id: Int): Order

  def getList: java.util.List[Order]

  def update(item: Order): Unit

  def delete(id: Int): Unit
}


@Component
class OrderClientImpl @Autowired()(val orderRepo: OrderManager) extends OrderClient {
  def post(order: Order): Unit = {
    orderRepo.persist(order)
  }

  def get(id: Int): Order = {
    orderRepo.get(id)
  }

  def getList: java.util.List[Order] = {
    orderRepo.getAll
  }

  def update(order: Order): Unit = {
    orderRepo.update(order)
  }

  def delete(id: Int): Unit = {
    orderRepo.delete(id)
  }


}




