import Dependencies._

ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

run / fork := true
connectInput := true // cf. https://kazuhira-r.hatenablog.com/entry/20130425/1366898136
run / javaOptions ++= Seq(
  "-Dyour.logging.params=something",
  "-Danother.logging.param=somethingelse"
)
scalacOptions ++= "-deprecation" :: "-feature" :: "-Xlint" :: "-Ywarn-unused" :: Nil

lazy val root = (project in file("."))
  .settings(
    name := "using-headless-browser-test",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += "com.machinepublishers" % "jbrowserdriver" % "1.1.0-RC1",
    libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
