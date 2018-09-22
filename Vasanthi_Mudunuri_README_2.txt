Vasanthi_Mudunuri_program_2.java is a java program to copy count number of files from inputpath to outputpath 
It takes three arguments inputpath,outputpath and count

Execution steps:
~]$ javac -cp $CLASSPATH:. Vasanthi_Mudunuri_program_2.java 
~]$ java -cp $CLASSPATH:. Vasanthi_Mudunuri_program_2 /CS5433/PA1/Data /vmudunu/java1 50     #copies 50 files from /CS5433/PA1/Data to /vmudunu/java1

Outputpath can be any number of folders,subfolders under the parent username directory

Example:
~]$ java -cp $CLASSPATH:. Vasanthi_Mudunuri_program_2 /CS5433/PA1/Data /vmudunu/java/out1 50

Chceking status:
~]$ hdfs dfs -ls /vmudunu/java1  #displays all the contents in /vmudunu/java1

To check on number of files copied:
~]$ hdfs dfs -ls /vmudunu/java1 | grep ^- | wc -l 

Exception Handling:

1) Input path does not exist
In case if the inputpath does not exist in hdfs then an error will be thrown saying "inputpath does not exist"

2) Output path already exists
In case if the outputpath already exists in hdfs then an error will be thrwon saying "outputpath already exists"

3) count should be a number
In case if the count provided is not a number then an error will be thrown saying "please enter a number to count files"

4) Number of arguments
In case all the three arguments are not provided then an error will be thrown saying "please provide all the three arguments: inputpath outputpath count"

5) Count provided is more than number of files in inputpath
In case if the count provided exceeds the number of files in the input path an error will be thrown saying 
"The number of files in the input path are less than the count given"
"There are 248 files in the input path"

The java program is commented to understand the implementation.

