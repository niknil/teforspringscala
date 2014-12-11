package com.teforspringscala.controllers.product.dao

import com.teforspringscala.controllers.product.domain.Item
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

/**
 * Created by Bamsen on 2014-12-11.
 */
trait ItemRepo {
  def getAll: java.util.List[Item]

  def save(customer: Item): Long

  def update(customer: Item)

  def get(customerId: Long): Item

  def delete(customerId: Long)
}

@Repository
@Transactional
class ItemRepoImpl @Autowired() (val sessionFactory: SessionFactory)  extends ItemRepo {

  private def currentSession = sessionFactory.getCurrentSession


  def save(customer: Item): Long = Long.unbox(currentSession.save(customer).asInstanceOf[Object])


  def update(customer: Item): Unit = currentSession.saveOrUpdate(customer)


  def delete(customerId: Long): Unit = currentSession.delete(get(customerId))


  def get(customerId: Long): Item = currentSession.get(classOf[Item], Long.box(customerId)).asInstanceOf[Item]


  def getAll: java.util.List[Item] = currentSession.createCriteria(classOf[Item]).list().asInstanceOf[java.util.List[Item]]
}