import java.io.File

import sbt.Keys._
import sbt._

object ClojurePlugin extends AutoPlugin {

  private object ClojureDefaults extends ClojureKeys {
    val settings = Seq(
      clojureVersion := "1.10.0",
      libraryDependencies ++= Seq[ModuleID](
        "org.clojure" % "clojure" % clojureVersion.value % Config.name
      )
    )
  }

  object clojure extends ClojureKeys {
    val settings = Seq(ivyConfigurations += Config) ++ ClojureDefaults.settings ++ Seq(
      clojureSource in Compile := (sourceDirectory in Compile).value / "clojure",
      unmanagedResourceDirectories in Compile += {(clojureSource in Compile).value},
      clojurec in Compile := {
        val s: TaskStreams = streams.value
        val sourceDirectory : File = (clojureSource in Compile).value
        val nb = (sourceDirectory ** "*.clj").get.size
//        if(nb > 0){
//          val s: TaskStreams = streams.value
          s.log.info("Start Compiling Clojure sources")
          val classpath : Seq[File] = update.value.select( configurationFilter(name = "*") ) ++ Seq((classDirectory in Compile).value)
          val stubDirectory : File = (sourceManaged in Compile).value
          val destinationDirectory : File = (classDirectory in Compile).value

          def clojureClazz(file : File) : File = {
            val p = file.getAbsolutePath()
            new File(destinationDirectory.getAbsolutePath() + p.substring(sourceDirectory.getAbsolutePath().length(), p.length() - ".clj".length()) + ".class")
          }

          (sourceDirectory ** "*.clj").get map (clojureClazz) foreach {f => if(f.exists()){IO.delete(f)}}

          new ClojureC(classpath, sourceDirectory, stubDirectory, destinationDirectory).compile
//        }
      },
      clojurec in Compile ~= (old => { (clojurec in Compile) dependsOn (compile in Compile) })
    )
  }
//
//  object testClojure extends TestKeys {
//    val settings = Seq(ivyConfigurations += Config) ++ inConfig(Config)(Defaults.testTasks ++ ClojureDefaults.settings ++ Seq(
////      definedTests <<= definedTests in Test,
////      definedTestNames <<= definedTestNames in Test,
////      fullClasspath <<= fullClasspath in Test,
//
//      clojureSource in Test := (sourceDirectory in Test).value / "clojure",
//      unmanagedResourceDirectories in Test += {(clojureSource in Test).value},
//      clojurec in Test := {
//        val sourceDirectory : File = (clojureSource in Test).value
//        val nb = (sourceDirectory ** "*.clj").get.size
////        if(nb > 0){
//          val s: TaskStreams = streams.value
//          s.log.info("Start Compiling Test Clojure sources")
//          val classpath : Seq[File] = update.value.select( configurationFilter(name = "*") ) ++ Seq((classDirectory in Test).value) ++ Seq((classDirectory in Compile).value)
//          val stubDirectory : File = (sourceManaged in Test).value
//          val destinationDirectory : File = (classDirectory in Test).value
//
//          def clojureClazz(file : File) : File = {
//            val p = file.getAbsolutePath()
//            new File(destinationDirectory.getAbsolutePath() + p.substring(sourceDirectory.getAbsolutePath().length(), p.length() - ".clj".length()) + ".class")
//          }
//
//          (sourceDirectory ** "*.clj").get map (clojureClazz) foreach {f => if(f.exists()){IO.delete(f)}}
//
//          new ClojureC(classpath, sourceDirectory, stubDirectory, destinationDirectory).compile
////        }
//      },
////      clojurec in Test <<= (clojurec in Test) dependsOn (compile in Test),
////      test in Test <<= (test in Test) dependsOn (clojurec in Test)
//    ))
//  }
}
