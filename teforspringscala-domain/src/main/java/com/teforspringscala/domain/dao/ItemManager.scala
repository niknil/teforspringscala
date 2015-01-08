package com.teforspringscala.domain.dao

import java.util

import com.teforspringscala.domain.entities.Item
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

/**
 * Created by Bamsen on 2014-12-11.
 */
trait ItemManager {

  def getAll: java.util.List[Item]

  def persist(item: Item): Unit

  def get(entityId: Int): Option[Item]

  def delete(entityId: Int)
}

@Repository
@Transactional
class ItemRepoImpl @Autowired() (val sessionFactory: SessionFactory)  extends ItemManager {

  private def currentSession = sessionFactory.getCurrentSession

  def persist(item: Item): Unit = item.id match {
    case 0 => Int.unbox(currentSession.save(item).asInstanceOf[Object])
    case _ => currentSession.saveOrUpdate(item)
  }

  def delete(entityId: Int): Unit = currentSession.delete(get(entityId))


  def get(entityId: Int): Option[Item] = Option(currentSession.get(classOf[Item], Int.box(entityId)).asInstanceOf[Item])


  def getAll: java.util.List[Item] = currentSession.createCriteria(classOf[Item]).list().asInstanceOf[java.util.List[Item]]


}

