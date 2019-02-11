from interop.example1.JavaMain import main as jmain

# Run with:
# sbt:interop> runMain PythonMain$py

def main():
    print("Initializing Python!")
    jmain([])
    print("Exiting Python!")

if __name__ == "__main__":
    main()