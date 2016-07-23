package controllers

import javax.inject.Inject

import com.twitter.finagle.http._
import com.twitter.finatra.http.Controller
import models.User
import twitter4j.{ Twitter, TwitterFactory }
import utils.Id64

import scalacache._
import scalacache.serialization.InMemoryRepr

/**
 * Created by Fumiyasu on 2016/07/04.
 */
class OAuth2Controller @Inject() (implicit val cache: ScalaCache[InMemoryRepr]) extends Controller {

  get("/api/v1/request") { request: Request =>

    info(s"============================> ${request.cookies}")
    request.cookies.get("userName").map(c => info(s"==========================> $c"))

    try {
      val twitter = TwitterFactory.getSingleton
      twitter.setOAuthAccessToken(null)
      val url = twitter.getOAuthRequestToken.getAuthenticationURL

      response.ok(url)
    } catch {
      case e => response.badRequest
    }
  }

  get("/callback") { request: Request =>
    val twitter = TwitterFactory.getSingleton

    request.containsParam("oauth_verifier") match {
      case true => {
        val oauthToken = request.getParam("oauth_token")
        val oauthVerifier = request.getParam("oauth_verifier")
        val accessToken = twitter.getOAuthAccessToken(oauthVerifier)
        val user = User(accessToken.getUserId, accessToken.getScreenName)
        val cookie = new Cookie("userName", user.userName)
        // TODO secureにする
        //        cookie.isSecure_=(true)

        val userCacheId = s"${user.userId}${Id64.nextAscId()}"

        sync.caching(userCacheId) {
          user
        }

        info(s"cache: ${sync.get(userCacheId)}")

        response.ok.cookie(cookie).cookie("userCacheId", userCacheId)
      }
      case false => response.ok
    }
  }
}
