import ClojurePlugin.clojure
import sbt._

name := "interop"

version := "0.1"

scalaVersion := "2.12.8"

resolvers += DefaultMavenRepository

libraryDependencies ++= Seq(
  "org.python" % "jython" % "2.7.0",
  "org.clojure" % "clojure" % "1.10.0",
  "org.jetbrains.kotlin" % "kotlin-stdlib" % "1.3.21"
)

enablePlugins(JythonPlugin)
enablePlugins(ClojurePlugin)
Seq(clojure.settings: _*)