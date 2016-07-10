package helloworld

import com.twitter.finagle.http.{ Request, Response }
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.{ CommonFilters, LoggingMDCFilter, TraceIdMDCFilter }
import com.twitter.finatra.http.routing.HttpRouter
import controllers.{ GroupController, OAuth2Controller }
import modules.QuillDatabaseModule

object HelloWorldServerMain extends HelloWorldServer

class HelloWorldServer extends HttpServer {

  /*
   * Since Heroku only supports a single port per service,
   * we disable the Admin HTTP Server
   */
  override val disableAdminHttpServer = true

  override val defaultFinatraHttpPort = ":9000"
  override val defaultHttpPort = 9001

  override def modules = Seq(QuillDatabaseModule)

  override def configureHttp(router: HttpRouter) {
    router
      .filter[LoggingMDCFilter[Request, Response]]
      .filter[TraceIdMDCFilter[Request, Response]]
      .filter[CommonFilters]
      .add[HelloWorldController]
      .add[GroupController]
      .add[OAuth2Controller]
  }
}
