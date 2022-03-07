name := "common-element"

version := "0.1"

scalaVersion := "2.12.6"


val sparkVersion = "2.4.0"
val Fs2Version = "2.5-15-e328d68"


libraryDependencies  ++= Seq(
    "co.fs2" %% "fs2-core" % Fs2Version,
    "co.fs2" %% "fs2-io" % Fs2Version,
    "com.github.pureconfig" %% "pureconfig" % "0.10.2",
    "com.github.scopt" %% "scopt" % "3.7.1",
    "org.scalatest" %% "scalatest" % "3.0.5" % Test

)
//assemblyMergeStrategy in assembly := {
//  case x => (assemblyMergeStrategy in assembly).value(x)
//}

Compile / run := Defaults.runTask(Compile / fullClasspath, Compile / run / mainClass, Compile / run / runner).evaluated
