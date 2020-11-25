ThisBuild / version := "0.1.0"
ThisBuild / organization := "io.github.tomykaira"
ThisBuild / homepage     := Some(url(s"https://github.com/tomykaira/${name.value}"))
ThisBuild / licenses     := Seq("MIT" -> url("http://opensource.org/licenses/MIT"))
ThisBuild / description  := "sbt plugin to configure remoteCache using git history tree"
ThisBuild / developers   := List(
    Developer("tomykaira", "Megumi Tomita", "tomykaira@gmail.com", url("https://github.com/tomykaira"))
)
ThisBuild / scmInfo      := Some(ScmInfo(url(s"https://github.com/sbt/${name.value}"), s"git@github.com:sbt/${name.value}.git"))

lazy val root = (project in file("."))
  .enablePlugins(SbtPlugin)
  .settings(
    name := "sbt-remote-cache-git-tree",
    scalacOptions := Seq("-deprecation", "-unchecked"),
    libraryDependencies ++= Seq(
      "org.scalactic" %% "scalactic" % "3.2.0",
      "org.scalatest" %% "scalatest" % "3.2.2" % Test,
    ),
    publishArtifact in (Compile, packageBin) := true,
    publishArtifact in (Test, packageBin) := false,
    publishArtifact in (Compile, packageDoc) := false,
    publishArtifact in (Compile, packageSrc) := true,
  )
