import sbt._
import Keys._
import spray.revolver.RevolverPlugin._

object Build extends Build {
  lazy val root =
    Project(id = "test-it", base = file("."))
      .configs(IntegrationTest)
      .settings(Revolver.settings)
      .settings(Defaults.itSettings: _*)
      .settings(
        name := "test-it",
        organization := "uk.gov.homeoffice",
        version := "1.0-SNAPSHOT",
        scalaVersion := "2.11.6",
        scalacOptions ++= Seq(
          "-feature",
          "-language:implicitConversions",
          "-language:higherKinds",
          "-language:existentials",
          "-language:reflectiveCalls",
          "-language:postfixOps",
          "-Yrangepos"),
        ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) },
        resolvers ++= Seq(
          "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
          "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
          "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
          "Kamon Repository" at "http://repo.kamon.io",
          "Artifactory Snapshot Realm" at "http://artifactory.registered-traveller.homeoffice.gov.uk/artifactory/libs-snapshot-local/"),
        libraryDependencies ++= Seq(
          "org.scalautils" %% "scalautils" % "2.1.5" withSources()),
        libraryDependencies ++= Seq(
          "org.specs2" %% "specs2" % "2.4.17" % "test, it" withSources()))
}