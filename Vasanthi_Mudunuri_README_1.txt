Vasanthi_Mudunuri_program_1.sh is a Shellscript to copy count number of files from inputpath to outputpath 
It takes three arguments inputpath,outputpath and count

Execution steps:
~]$ sh Vasanthi_Mudunuri_program_1.sh /CS5433/PA1/Data /vmudunu/shellscript1 50     #copies 50 files from /CS5433/PA1/Data to /vmudunu/shellscript1

Outputpath can be any number of folders,subfolders under the parent username directory

Example:
~]$ sh Vasanthi_Mudunuri_program_1.sh /CS5433/PA1/Data /vmudunu/shellscript/out1 50

Chceking status:
~]$ hdfs dfs -ls /vmudunu/shellscript1  #displays all the contents in /vmudunu/shellscript1

To check on number of files copied:
~]$ hdfs dfs -ls /vmudunu/shellscript1 | grep ^- | wc -l 

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

5) Count provided is more than number of files in inputpath
In case if the count provided exceeds the number of files in the input path an error will be thrown saying 
"The number of files in the input path are less than the count given"
"There are 248 files in the input path"

The shellscript is commented to understand the implementation.

