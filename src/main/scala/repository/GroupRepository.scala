package repository

import io.getquill._
import io.getquill.naming.Literal
import models.Groups

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Fumiyasu on 2016/06/22.
  */
class GroupRepository {

  lazy val db = source(new MysqlAsyncSourceConfig[Literal]("db"))

  def save(groups: Groups) = {

    val q = quote(query[Groups].insert)
    db.run(q)(List(groups))
  }
}
