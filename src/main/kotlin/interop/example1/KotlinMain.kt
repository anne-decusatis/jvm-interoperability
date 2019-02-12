package interop.example1

import org.jruby.Ruby

fun main() {
    println("Initializing Kotlin!")
    val rb = Ruby.getGlobalRuntime()
    rb.executeScript("puts 'Hello from an inline Ruby script!'", "RubyMain.rb")
    println("Exiting Kotlin!")
}