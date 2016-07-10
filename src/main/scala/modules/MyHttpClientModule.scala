package modules

import com.twitter.finatra.httpclient.modules.HttpClientModule

/**
 * Created by Fumiyasu on 2016/07/04.
 */
class MyHttpClientModule(host: String) extends HttpClientModule {
  override def dest: String = host
}
