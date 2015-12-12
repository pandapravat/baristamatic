compiling the program (*nix based)
---------------------
1. Navigate to this location<br>
2. Run the below command <br>
mkdir -p binaries | find . -name "*.java" -print | xargs javac -d binaries

Running the program
-------------------
Run the below commands <br>

cd binaries<br>
java com.pkp.baristamatic.main.BaristaMatic

