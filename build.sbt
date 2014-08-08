name := """hummingbird-wrapper"""

version := "1.0"

scalaVersion := "2.11.1"

resolvers += "spray repo" at "http://repo.spray.io"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest"      % "2.1.6" % "test",
  "org.scalamock" %% "scalamock-core" % "3.1.2" % "test",
  "org.scaldi"    %% "scaldi-akka"    % "0.4",
  "io.spray"      %% "spray-can"      % "1.3.1"
)
