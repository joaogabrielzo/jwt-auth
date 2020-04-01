package com.zo.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{Directive1, Route}
import authentikat.jwt.JsonWebToken
import services.JwtAuth

class AuthorizationRoute extends JwtAuth {
    
    private def authenticated: Directive1[Map[String, Any]] =
        optionalHeaderValueByName("Authorization").flatMap {
            case Some(jwt) if isTokenExpired(jwt) =>
                complete(StatusCodes.Unauthorized -> "Token expired.")
            
            case Some(jwt) if JsonWebToken.validate(jwt, secretKey) =>
                provide(getClaims(jwt).getOrElse(Map.empty[String, Any]))
            
            case _ => complete(StatusCodes.Unauthorized)
        }
    
    def securedContent: Route =
        (path("content") & get) {
            authenticated { claims =>
                complete(s"User ${claims.getOrElse("user", "")} accessed secured content!")
            }
        }
}
