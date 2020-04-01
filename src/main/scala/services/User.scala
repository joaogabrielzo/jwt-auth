package services

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

case class User(username: String, password: String)

trait UserProtocol extends SprayJsonSupport with DefaultJsonProtocol {
    
    implicit val userFormat: RootJsonFormat[User] = jsonFormat2(User)
}
