if [ $# -ne 3 ]; then    #checking if all the three arguments are provided in all other cases throwing an error
echo "please provide all the three arguments: inputpath outputpath count"
fi
hdfsinputpath=$1    #storing the inputpath in a variable hdfsinputpath
hdfsoutputpath=$2   #storing the outputpath in a variable hdfsoutputpath
countgiven=$3       #storing the number of files to be copied in a variable count
folder=1            #varibale folder to maintain the count on the number of folders created in the outputpath
filescopied=0       #variable filescopied to maintain the count of the number of files copied from inputpath to the outputpath
directory=1         #variable directorycount to maintain the directory count in which files are copied
if ! [[ "$countgiven" =~ ^[0-9]+$ ]]  #checking if the count provided is a number in all other cases throwing an error
then
echo "please enter a number to count files"
else
totalfiles=$(hdfs dfs -ls $hdfsinputpath | grep ^- | wc -l) #retrieving total number of files from the inputpath
if [ $((totalfiles%countgiven)) -eq 0 ]   #In order to copy the left over files less than count to a folder
then
totalfolders=$((totalfiles/countgiven))
else
totalfolders=$((totalfiles/countgiven))
totalfolders=$(($totalfolders+1))
fi
if hdfs dfs -test -d $hdfsinputpath   #checking if the inputpath exist in hdfs, else throwing an error
then
if hdfs dfs -test -d $hdfsoutputpath  #checking if the outputpath already exisis, if yes throwing an error
then
echo "outputpath already exists"    
else
 hdfs dfs -mkdir -p $hdfsoutputpath   #creating folders as specified in the outputpath argument
 while [ $folder -le $totalfolders ]  #condition on number of folders to be created
 do
 createfolder=$hdfsoutputpath"/"$folder #creating outputpath
 hdfs dfs -mkdir $createfolder     #creating folder
 ((folder++))      #incrementing on each folder created
 done
 hdfs dfs -ls $hdfsinputpath | grep ^- | awk '{ print $8}' | while read LINE #reading files one at a time from inputpath
 do
 if [ $filescopied -lt $countgiven ] #condition on the number of files to be copied
 then
 filepath=$hdfsoutputpath"/"$directory  #creaing outputpath
 hadoop fs -cp $LINE $filepath   #copying file one at a time
 ((filescopied++))  #incrementing on each file copied
 if [ $filescopied -eq $countgiven ]  #condition to stop copying files and iterate through next folder
 then
 directory=$(($directory+1)) #incrementing directorycount to copy files in to the next directory
 filescopied=0
 fi
 fi
 done
fi
else
echo "inputpath does not exist"
fi
fi
