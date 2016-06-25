package modules

import com.github.mauricio.async.db.mysql.MySQLConnection
import io.getquill._
import com.twitter.inject.TwitterModule
import io.getquill.naming.Literal
import io.getquill.sources.async.MysqlAsyncSource
import io.getquill.sources.sql.idiom.MySQLDialect
import com.google.inject.{Provides, Singleton}

/**
  * Created by Fumiyasu on 2016/06/25.
  */
object QuillDatabaseModule extends TwitterModule {
  type QuillDatabaseSource = MysqlAsyncSource[MySQLDialect, Literal, MySQLConnection]

  @Provides @Singleton
  def provideDataBaseSource() = source(new MysqlAsyncSourceConfig[Literal]("db"))
}
