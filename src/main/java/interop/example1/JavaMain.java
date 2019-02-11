package interop.example1;

public class JavaMain {
  public static void main(String[] args) {
    System.out.println("Initializing Java!");
    ScalaMain$.MODULE$.main(args);
    System.out.println("Exiting Java!");
  }
}
