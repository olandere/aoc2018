lazy val root = (project in file(".")).
  settings(
    name := "AoC2018",
    organization := "eo",
    version := "1.0",
    scalaVersion := "2.12.8",
    scalacOptions ++= Seq("-deprecation", "-feature"),
      initialCommands in console := """
                                      |import aoc2018._
                                      |""".stripMargin

  )
