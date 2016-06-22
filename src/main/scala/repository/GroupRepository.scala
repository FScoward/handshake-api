package repository

import io.getquill._
import io.getquill.naming.SnakeCase
import models.Group
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Fumiyasu on 2016/06/22.
  */
class GroupRepository {

  lazy val db = source(new MysqlAsyncSourceConfig[SnakeCase]("db"))

  def save(group: Group) = {

    val q = quote(query[Group].insert)
    db.run(q)(List(group))
  }
}
