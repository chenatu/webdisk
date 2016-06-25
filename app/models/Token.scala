package models

import slick.lifted.Tag
import slick.model.Table

/**
  * Created by chenatu on 2016/6/9.
  */
case class Token (id : String, userId: String, accessKey: String, secrectKey: String)
