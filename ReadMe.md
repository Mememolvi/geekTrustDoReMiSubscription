# Pre-requisites
* Java 1.8/1.11/1.15
* Maven

# Coding Challange Links :
https://www.geektrust.com/coding/detailed/doremi-subscription

# How to run the code

We have provided scripts to execute the code. 

Use `run.sh` if you are Linux/Unix/macOS Operating systems and `run.bat` if you are on Windows.  Both the files run the commands silently and prints only output from the input file `sample_input/input1.txt`. You are supposed to add the input commands in the file from the appropriate problem statement. 

Internally both the scripts run the following commands 

 * `mvn clean install -DskipTests assembly:single -q` - This will create a jar file `geektrust.jar` in the `target` folder.
 * `java -jar target/geektrust.jar sample_input/input1.txt` - This will execute the jar file passing in the sample input file as the command line argument

 We expect your program to take the location to the text file as parameter. Input needs to be read from a text file, and output should be printed to the console. The text file will contain only commands in the format prescribed by the respective problem.

 Use the pom.xml provided along with this project. Please change the main class entry (`<mainClass>com.example.geektrust.Main</mainClass>`) in the pom.xml if your main class has changed.

 # Running the code for multiple test cases

 Please fill `input1.txt` and `input2.txt` with the input commands and use those files in `run.bat` or `run.sh`. Replace `java -jar target/geektrust.jar sample_input/input1.txt` with `java -jar target/geektrust.jar sample_input/input2.txt` to run the test case from the second file. 

 # How to execute the unit tests

 `mvn clean test` will execute the unit test cases.

# Input Commands & Format
 
START_SUBSCRIPTION DD-MM-YYYY <br />
ADD_SUBSCRIPTION SUBSCRIPTION_CATEGORY PLAN_NAME <br />
ADD_TOPUP TOP_UP_NAME NO_OF_MONTHS <br />
PRINT_RENEWAL_DETAILS <br />
Examples :<br />
START_SUBSCRIPTION 20-02-2022 <br />
ADD_SUBSCRIPTION MUSIC  PERSONAL <br />
ADD_SUBSCRIPTION VIDEO PREMIUM <br />
ADD_TOPUP ADD_TOPUP <br />
PRINT_RENEWAL_DETAILS <br />


INPUT : <br />
START_SUBSCRIPTION20-02-2022 <br />
ADD_SUBSCRIPTIONMUSIC PERSONAL <br />
ADD_SUBSCRIPTIONVIDEO PREMIUM <br />
ADD_SUBSCRIPTIONPODCAST FREE <br />
ADD_TOPUPFOUR_DEVICE 3 <br />
PRINT_RENEWAL_DETAILS	RENEWAL_REMINDERMUSIC 10-03-2022 <br />

OUTPUT: <br />
RENEWAL_REMINDERVIDEO 10-05-2022<br />
RENEWAL_REMINDERPODCAST 10-03-2022<br />
RENEWAL_AMOUNT750<br />
