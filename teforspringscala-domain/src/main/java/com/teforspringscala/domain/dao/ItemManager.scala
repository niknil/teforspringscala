package com.teforspringscala.domain.dao

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

  def get(id: Int): Item

  def delete(id: Int): Unit
}

@Repository
@Transactional
class ItemRepoImpl @Autowired() (val sessionFactory: SessionFactory)  extends ItemManager {

  private def currentSession = sessionFactory.getCurrentSession

  def persist(item: Item): Unit = item.id match {
    case 0 => Int.unbox(currentSession.save(item).asInstanceOf[Object])
    case _ => currentSession.saveOrUpdate(item)
  }

  def get(id: Int): Item = controlItem(Option(currentSession.get(classOf[Item], Int.box(id)).asInstanceOf[Item]))

  def delete(id: Int): Unit = currentSession.delete(get(id))

  def getAll: java.util.List[Item] = currentSession.createCriteria(classOf[Item]).list().asInstanceOf[java.util.List[Item]]

  private def controlItem(item: Option[Item]) = item match {
    case Some(s) => s
    case None => throw new IllegalArgumentException
  }


}

