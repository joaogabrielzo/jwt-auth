package services

import java.util.concurrent.TimeUnit

import authentikat.jwt.{JsonWebToken, JwtClaimsSet, JwtClaimsSetMap, JwtHeader}
import com.typesafe.config.ConfigFactory

abstract class JwtAuth {
    
    val expirationInDays = 1
    val secretKey = ConfigFactory.load().getString("secretKey.key")
    val header = JwtHeader("HS256")
    
    def setClaims(username: String, expiryPeriodInDays: Long): JwtClaimsSetMap = JwtClaimsSet(
        Map("user" -> username,
            "expiredAt" -> (System.currentTimeMillis() + TimeUnit.DAYS
                                                                 .toMillis(expiryPeriodInDays)))
    )
    
    def getClaims(jwt: String): Option[Map[String, String]] = jwt match {
        case JsonWebToken(_, claims, _) => claims.asSimpleMap.toOption
        case _                          => None
    }
    
    def isTokenExpired(jwt: String): Boolean = getClaims(jwt) match {
        case Some(claims) =>
            claims.get("expiredAt") match {
                case Some(value) => value.toLong < System.currentTimeMillis()
                case None        => false
            }
        case None         => false
    }
    
}
