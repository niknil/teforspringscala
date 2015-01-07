package com.teforspringscala.item.client

import com.teforspringscala.item.dao.{OrderManager}
import com.teforspringscala.item.domain.{Order}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Created by Bamsen on 2014-12-17.
 */
trait OrderClient {
  def post(item: Order): Unit

  def get(id: Int): Option[Order]

  def getList: java.util.List[Order]

  def delete(id: Int): Unit
}


@Component
class OrderClientImpl @Autowired()(val orderRepo: OrderManager) extends OrderClient {

  def post(order: Order): Unit = {
    orderRepo.persist(order)
  }

  def get(id: Int): Option[Order] = {
    orderRepo.get(id)
  }

  def getList: java.util.List[Order] = {
    orderRepo.getAll
  }

  def delete(id: Int): Unit = {
    orderRepo.delete(id)
  }

}




