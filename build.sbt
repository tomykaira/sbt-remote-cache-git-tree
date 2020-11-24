ThisBuild / version := "0.1.0"
ThisBuild / organization := "io.github.tomykaira"

lazy val root = (project in file("."))
  .enablePlugins(SbtPlugin)
  .settings(
    name := "sbt-remote-cache-git-tree",
    description := "sbt plugin to configure remoteCache using git history tree",
    licenses := Seq("MIT License" -> url("https://github.com/tomykaira/sbt-remote-cache-git-tree/blob/master/LICENSE")),
    bintrayVcsUrl := Some("git@github.com:tomykaira/sbt-remote-cache-git-tree.git"),
    scalacOptions := Seq("-deprecation", "-unchecked"),
    libraryDependencies ++= Seq(
      "org.scalactic" %% "scalactic" % "3.2.0",
      "org.scalatest" %% "scalatest" % "3.2.2" % Test,
    ),
    publishArtifact in (Compile, packageBin) := true,
    publishArtifact in (Test, packageBin) := false,
    publishArtifact in (Compile, packageDoc) := false,
    publishArtifact in (Compile, packageSrc) := true
  )
