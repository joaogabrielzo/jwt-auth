package com.zo.routes

import akka.http.scaladsl.model.headers.RawHeader
import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives.{entity, _}
import akka.http.scaladsl.server.Route
import authentikat.jwt.JsonWebToken
import services.{JwtAuth, User, UserProtocol}

class AuthenticationRoute extends JwtAuth with UserProtocol {
    
    var usersDatabase: Map[String, String] = Map(
        "zo" -> "zo",
        "admin" -> "admin"
    )
    
    def user: Route =
        (path("signin") & post) {
            entity(as[User]) { login =>
                if (usersDatabase(login.username) == login.password) {
                    val claims = setClaims(login.username, expirationInDays)
                    respondWithHeader(RawHeader("Acces-Token", JsonWebToken(header, claims, secretKey))) {
                        complete(StatusCodes.OK)
                    }
                } else if (usersDatabase(login.username) != login.password) {
                    complete(HttpResponse(StatusCodes.Unauthorized, entity = "Password is incorrect."))
                } else {
                    complete(HttpResponse(StatusCodes.NotFound, entity = "You are not signed up."))
                }
            }
        } ~
        (path("signup") & post) {
            entity(as[User]) { subscribe =>
                usersDatabase += (subscribe.username -> subscribe.password)
                complete(HttpResponse(StatusCodes.Created, entity = "User created."))
            }
        }
}
