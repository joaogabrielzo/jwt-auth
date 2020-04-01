val akka           = "2.5.23"
      val akkaHttp       = "10.1.9"
      val akkaHttpCirce  = "1.27.0"
      val authentikatJwt = "0.4.5"
      val circeGeneric   = "0.11.1"
      val scalaCheck     = "1.14.0"
      val scalaTest      = "3.0.8"
    }
    val akkaHttp       = "com.typesafe.akka" %% "akka-http"           % Version.akkaHttp
    val akkaHttpCirce  = "de.heikoseeberger" %% "akka-http-circe"     % Version.akkaHttpCirce
    val authentikatJwt = "com.jason-goodwin" %% "authentikat-jwt"     % Version.authentikatJwt
    val circeGeneric   = "io.circe"          %% "circe-generic"       % Version.circeGeneric
    // Testing libs
    val akkaHttpTest   = "com.typesafe.akka" %% "akka-http-testkit"   % Version.akkaHttp
    val akkaTestkit    = "com.typesafe.akka" %% "akka-testkit"        % Version.akka
    val scalaCheck     = "org.scalacheck"    %% "scalacheck"          % Version.scalaCheck
    val scalaTest      = "org.scalatest"     %% "scalatest"           % Version.scalaTest