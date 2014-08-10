name := """hummingbird-wrapper"""

version := "1.0"

scalaVersion := "2.11.1"

resolvers += "spray repo" at "http://repo.spray.io"

libraryDependencies ++= Seq(
  "org.scalatest"       %% "scalatest"      % "2.1.6" % "test",
  "org.scalamock"       %% "scalamock-core" % "3.1.2" % "test",
  "com.typesafe.akka"   %% "akka-actor"     % "2.3.2",
  "org.scaldi"          %% "scaldi-akka"    % "0.4",
  "io.spray"            %% "spray-can"      % "1.3.1",
  "io.spray"            %% "spray-json"     % "1.2.6",
  "io.spray"            %% "spray-client"   % "1.3.1"
)
