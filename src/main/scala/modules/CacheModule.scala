package modules

import com.google.inject.{ Provides, Singleton }
import com.twitter.inject.TwitterModule
import net.sf.ehcache.CacheManager

import scalacache._
import ehcache._

/**
 * Created by Fumiyasu on 2016/07/17.
 */
object CacheModule extends TwitterModule {

  val cacheManager = CacheManager.getInstance()

  @Provides @Singleton
  def provideCache() = {
    val underlying = cacheManager.getCache("sample") // xmlに定義した名前
    ScalaCache(EhcacheCache(underlying))
  }

}
