Vasanthi_Mudunuri_program_4.java is a java program to copy count number of files from inputpath to outputpath 
It takes three arguments inputpath,outputpath and count

Execution steps:
~]$ javac -cp $CLASSPATH:. Vasanthi_Mudunuri_program_4.java 
~]$ java -cp $CLASSPATH:. Vasanthi_Mudunuri_program_4 /CS5433/PA1/Data /vmudunu/java2 50     #creates 5 folders under /vmudunu/java2 and copies 50 files from /CS5433/PA1/Data to each folder

Folders created are /vmudunu/java2/1, /vmudunu/java2/2, /vmudunu/java2/3, /vmudunu/java2/4, /vmudunu/java2/5

Outputpath can be any number of folders,subfolders under the parent username directory

Example:
~]$ java -cp $CLASSPATH:. Vasanthi_Mudunuri_program_4 /CS5433/PA1/Data /vmudunu/java/out2 50

Chceking status:
~]$ hdfs dfs -ls /vmudunu/java2  #displays all the contents in /vmudunu/java2

To check on number of files copied:
~]$ hdfs dfs -ls /vmudunu/java2 | grep ^- | wc -l 

Exception Handling:

1) Input path does not exist
In case if the inputpath does not exist in hdfs then an error will be thrown saying "inputpath does not exist"

2) Output path already exists
In case if the outputpath already exists in hdfs then an error will be thrwon saying "outputpath already exists"

3) count should be a number
In case if the count provided is not a number then an error will be thrown saying "please enter a number to count files"

4) Number of arguments
In case all the three arguments are not provided then an error will be thrown saying 
"please provide all the three arguments: inputpath outputpath count"
"please enter a number to count files"

In case if there are files left over in the inputpath which are less than count provided these are copied to the last folder under the outputpath

The java program is commented to understand the implementation.

