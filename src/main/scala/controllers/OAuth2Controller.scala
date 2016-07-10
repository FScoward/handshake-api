package controllers

import com.twitter.finagle.http._
import com.twitter.finatra.http.Controller
import models.User
import twitter4j.{ Twitter, TwitterFactory }

/**
 * Created by Fumiyasu on 2016/07/04.
 */
class OAuth2Controller extends Controller {

  get("/api/v1/request") { request: Request =>

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

        User(accessToken.getUserId, accessToken.getScreenName)
      }
      case false => response.ok

    }

  }
}
