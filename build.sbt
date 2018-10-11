name := "bitbank-api-sandbox"

version := "0.1"

def commonSettings = Seq(
  organization := "com.uron",
  name := "bitbank-api-sandbox",
  version := "0.1.0",
  scalaVersion := "2.12.7",
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  )
)

lazy val root = (project in file("."))
  .settings(commonSettings: _*)
  .settings(
    name := "bitbank-api-sandbox"
  )

lazy val sandbox = (project in file("modules/sandbox"))
  .settings(commonSettings: _*)
  .settings(
    name := "sandbox",
    libraryDependencies ++= Seq(
      "com.pubnub" % "pubnub" % "3.7.11",
      "org.json4s" %% "json4s-jackson" % "3.5.2",
      "com.typesafe" % "config" % "1.3.2",
    )
  )