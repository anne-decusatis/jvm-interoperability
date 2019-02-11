package interop.example1

import clojure.java.api.Clojure

object ScalaMain {

  def main(args: Array[String]): Unit = {
    println("Initializing Scala!")
    // call clojure
    // https://blog.michielborkent.nl/2016/07/26/clojure-from-scala/
    // first initialize ability to require things
    val core = Clojure.`var`("clojure.core", "require")
    // then require my namespace
    core.invoke(Clojure.read("interop.example1.core"))
    // then find my function
    val clojureMain = Clojure.`var`("interop.example1.core", "-main")
    // then run the function
    clojureMain.invoke()
    println("Exiting Scala!")
  }

}
