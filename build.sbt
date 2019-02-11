name := "interop"

version := "0.1"

scalaVersion := "2.12.8"

resolvers += DefaultMavenRepository

libraryDependencies ++= Seq(
  "org.python" % "jython" % "2.7.0"
)

enablePlugins(JythonPlugin)