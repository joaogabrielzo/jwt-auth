package com.zo

import akka.actor.ActorSystem
import akka.http.scaladsl.server.HttpApp
import com.zo.routes.{AuthenticationRoute, AuthorizationRoute}

import scala.concurrent.ExecutionContextExecutor

object ApplicationServer extends HttpApp
                         with App {
    
    implicit val sys: ActorSystem = ActorSystem()
    implicit val ec: ExecutionContextExecutor = sys.dispatcher
    
    val authentication = new AuthenticationRoute().user
    val authorization = new AuthorizationRoute().securedContent
    
    val routes = authentication ~ authorization
    
    startServer("localhost", 9999)
    
}
