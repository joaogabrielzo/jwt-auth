name := "jwt-auth"

version := "0.1"

scalaVersion := "2.12.11"

val akka = "2.5.23"
val akkaHttp = "10.1.9"
val akkaHttpCirce = "1.27.0"
val authentikatJwt = "0.4.5"
val scalaCheck = "1.14.0"
val scalaTest = "3.0.8"

libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-stream" % akka,
    "com.typesafe.akka" %% "akka-http" % akkaHttp,
    "com.jason-goodwin" %% "authentikat-jwt" % authentikatJwt,
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttp % Test,
    "com.typesafe.akka" %% "akka-testkit" % akka % Test,
    "org.scalacheck" %% "scalacheck" % scalaCheck % Test,
    "org.scalatest" %% "scalatest" % scalaTest % Test,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttp,
    "com.typesafe" % "config" % "1.4.0"
)