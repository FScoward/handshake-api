package repository

import io.getquill._
import io.getquill.naming.Literal
import models.Groups

import scala.concurrent.ExecutionContext.Implicits.global

import com.twitter.util.{Future => TwitterFuture}
import utils.TwitterConverters._

/**
  * Created by Fumiyasu on 2016/06/22.
  */
class GroupRepository {

  lazy val db = source(new MysqlAsyncSourceConfig[Literal]("db"))

  def save(groups: Groups) = {
    val q = quote(query[Groups].insert)
    db.run(q)(List(groups))
  }

  def list(): TwitterFuture[List[Groups]] = {
    val q = quote(query[Groups])
    (for {
      r <- db.run(q)
    } yield r)
  }
}
