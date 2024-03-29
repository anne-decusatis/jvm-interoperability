
// https://github.com/tomaszym/sbt-clojure/blob/master/src/main/scala/Keys.scala
import java.io.File

import sbt._

trait ClojureKeys {

  lazy val Config = config("clojure") extend(Compile) hide
  lazy val clojureVersion = settingKey[String]("Clojure version")
  lazy val clojureSource = settingKey[File]("Default Clojure source directory")
  lazy val clojurec = taskKey[Unit]("Compile Clojure sources")

}

trait TestKeys extends ClojureKeys {
  override lazy val Config = config("test-clojure") extend(Test) hide
}

trait IntegrationTestKeys extends TestKeys {
  override lazy val Config = config("it-clojure") extend(IntegrationTest) hide
}
