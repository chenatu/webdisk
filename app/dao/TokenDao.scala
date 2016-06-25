package dao

import javax.inject.Inject

import models.Token
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

import scala.concurrent.Future

/**
  * Created by chenatu on 2016/6/9.
  */
class TokenDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._

  private val Tokens = TableQuery[TokenTable]

  def all(): Future[Seq[Token]] = db.run(Tokens.result)

  def getByUserId(userId : String) : Future[Seq[Token]] = db.run(Tokens.filter(_.userId === userId).result)

  def insert(token: Token): Future[Unit] = db.run(Tokens += token).map { _ => () }

  private class TokenTable(tag: Tag) extends Table[Token](tag, "token") {

    def id = column[String]("ID", O.PrimaryKey)
    def userId = column[String]("USER_ID")
    def accessKey = column[String]("ACCESS_KEY")
    def secretKey = column[String]("SECRET_KEY")
    def * = (id, userId, accessKey, secretKey) <> ((Token.apply _).tupled, Token.unapply _)
  }

}
