(ns interop.example1.core)

(defn -main
  "I can say 'Hello World'."
  ([] (println "Initializing Clojure!")
   (interop.example1.KotlinMainKt/main)
   (println "Exiting Clojure!")
   )
  )