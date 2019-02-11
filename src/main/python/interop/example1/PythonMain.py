from interop.example1.JavaMain import main as jmain

# Run with:
# sbt:interop> runMain PythonMain$py

def main():
    print("Initializing python!")
    jmain([])
    print("Exiting python!")

if __name__ == "__main__":
    main()