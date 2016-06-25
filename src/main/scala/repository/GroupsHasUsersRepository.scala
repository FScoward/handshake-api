package repository

import com.google.inject.Inject
import io.getquill._
import models.GroupsHasUsers

import scala.concurrent.ExecutionContext.Implicits.global
import com.twitter.util.{Future => TwitterFuture}
import modules.QuillDatabaseModule.QuillDatabaseSource
import utils.TwitterConverters._


/**
  * Created by Fumiyasu on 2016/06/25.
  */
class GroupsHasUsersRepository @Inject()(db: QuillDatabaseSource) {

  def join(groupsHasUsers: GroupsHasUsers) = {
    val q = quote(query[GroupsHasUsers].insert)
    db.run(q)(List(groupsHasUsers))
  }
}
