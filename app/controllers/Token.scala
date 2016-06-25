package controllers

import javax.inject.Inject

import com.qiniu.util.Auth
import dao.TokenDao
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.{Action, Controller}
import play.api.libs.json.Json

/**
  * Created by chenatu on 2016/6/9.
  */
class Token @Inject()(tokenDao: TokenDao) extends Controller {
  def getTokenByUserId(userId: String) = Action.async(implicit request =>
    tokenDao.getByUserId(userId).map(tokens =>
      if (tokens.size > 1) {
        Ok("One user, one token")
      } else if (tokens.size == 0) {
        Ok("No such user")
      } else {
        var token = tokens(0)
        Ok(Json.stringify(Json.obj(
          "uptoken" -> Auth.create(token.accessKey, token.secrectKey).uploadToken("mrchenatu")
        )))
      }
    )
  )
}
