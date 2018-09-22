Vasanthi_Mudunuri_program_3.sh is a Shellscript to copy count number of files from inputpath to each folder in the outputpath 
It takes three arguments inputpath,outputpath and count

Execution steps:
~]$ sh Vasanthi_Mudunuri_program_3.sh /CS5433/PA1/Data /vmudunu/shellscript2 50     #creates 5 folders under /vmudunu/shellscript2 and copies 50 files from /CS5433/PA1/Data to each folder

Folders created are /vmudunu/shellscript2/1, /vmudunu/shellscript2/2, /vmudunu/shellscript2/3, /vmudunu/shellscript2/4, /vmudunu/shellscript2/5

Outputpath can be any number of folders,subfolders under the parent username directory

Example:
~]$ sh Vasanthi_Mudunuri_program_3.sh /CS5433/PA1/Data /vmudunu/shellscript/out2 50

Chceking status:
~]$ hdfs dfs -ls /vmudunu/shellscript2  #displays all the contents in /vmudunu/shellscript2

To check on number of files copied:
~]$ hdfs dfs -ls /vmudunu/shellscript2 | grep ^- | wc -l 

Exception Handling:

1) Input path does not exist
In case if the inputpath does not exist in hdfs then an error will be thrown saying "inputpath does not exist"

2) Output path already exists
In case if the outputpath already exists in hdfs then an error will be thrwon saying "outputpath already exists"

3) count should be a number
In case if the count provided is not a number then an error will be thrown saying "please enter a number to count files"

4) Number of arguments
In case all the three arguments are not provided then an error will be thrown saying "please provide all the three arguments: inputpath outputpath count"

In case if there are files left over in the inputpath which are less than count provided these are copied to the last folder under the outputpath

The shellscript is commented to understand the implementation.

