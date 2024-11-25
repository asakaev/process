ThisBuild / scalaVersion := "3.5.2"
ThisBuild / organization := "ru.yandex"

lazy val root = (project in file("."))
  .settings(
    name                             := "process",
    libraryDependencies += "dev.zio" %% "zio-streams" % "2.1.13"
  )
