package com.teforspringscala.item.dao

import com.teforspringscala.item.domain.{Item, Order}
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

/**
 * Created by Bamsen on 2014-12-17.
 */
trait OrderManager {

  def getAll: java.util.List[Order]

  def persist(item: Order): Unit

  def update(item: Order): Unit

  def get(entityId: Int): Order

  def delete(entityId: Int): Unit
}



@Repository
@Transactional
class OrderRepoImpl @Autowired() (val sessionFactory: SessionFactory)  extends OrderManager {
  
  private def currentSession = sessionFactory.getCurrentSession

  def persist(order: Order): Unit = Int.unbox(currentSession.save(order).asInstanceOf[Object])


  def update(order: Order): Unit = currentSession.saveOrUpdate(order)


  def delete(orderId: Int): Unit = currentSession.delete(get(orderId))


  def get(orderId: Int): Order = currentSession.get(classOf[Order], Int.box(orderId)).asInstanceOf[Order]


  def getAll: java.util.List[Order] = currentSession.createCriteria(classOf[Order]).list().asInstanceOf[java.util.List[Order]]
}