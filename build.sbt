name := """minesweeper-api"""
organization := "com.agusgambina"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.6"

libraryDependencies  ++= Seq(
  "org.mongodb.scala" %% "mongo-scala-driver" % "2.4.0",
  "com.github.nscala-time" %% "nscala-time" % "2.18.0",
  "com.softwaremill.macwire" %% "macros" % "2.3.0" % "provided",
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
  "org.scalamock" %% "scalamock" % "4.1.0" % Test,
  "org.mockito" % "mockito-core" % "2.7.19" % Test
)

