package repository

import javax.inject.{Inject, Singleton}

import io.getquill._
import models.Groups

import scala.concurrent.ExecutionContext.Implicits.global
import com.twitter.util.{Future => TwitterFuture}
import modules.QuillDatabaseModule.QuillDatabaseSource
import utils.TwitterConverters._

/**
  * Created by Fumiyasu on 2016/06/22.
  */
@Singleton
class GroupRepository @Inject()(db: QuillDatabaseSource) {

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
