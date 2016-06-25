package controllers

import play.api.mvc._

class Upload extends Controller {

  def index = Action {
    Ok(views.html.upload())
  }

}