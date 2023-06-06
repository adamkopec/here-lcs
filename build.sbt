Global / excludeLintKeys += idePackagePrefix

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.0"

lazy val root = (project in file("."))
  .settings(
    name := "here",
    idePackagePrefix := Some("pl.archiprogram.here")
  )

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.16"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.16" % "test"
libraryDependencies += "org.scalanlp" %% "breeze" % "2.1.0"
