if [ $# -ne 3 ]; then   #checking if all the three arguments are provided in all other cases throwing an error
echo "please provide all the three arguments: inputpath outputpath count"
fi
hdfsinputpath=$1     #storing the inputpath in a variable hdfsinputpath
hdfsoutputpath=$2    #storing the outputpath in a variable hdfsoutputpath
countgiven=$3        #storing the number of files to be copied in a variable count
if ! [[ "$countgiven" =~ ^[0-9]+$ ]]  #checking if the count provided is a number in all other cases throwing an error
then
echo "please enter a number to count files"
else
if hdfs dfs -test -d $hdfsinputpath  #checking if the inputpath exist in hdfs, else throwing an error
then
if hdfs dfs -test -d $hdfsoutputpath #checking if the outputpath already exisis, if yes throwing an error
then
echo "outputpath already exists"
else
totalfiles=$(hdfs dfs -ls $hdfsinputpath | grep ^- | wc -l) #retrieving total number of files from the inputpath
if [ $totalfiles -lt $countgiven ] #checking if the number of files in inputpath are less than count provided, if yes throwing an error
then
echo "The number of files in the input path are less than the count given"
echo "There are $totalfiles files in the inputpath"
else
 hdfs dfs -mkdir -p $hdfsoutputpath   #creating directories as specified in the outputpath argument
 hdfs dfs -ls $hdfsinputpath | grep ^- | head -n $countgiven| awk '{ print $8}' | while read LINE #reading count number of files one at a time from inputpath
 do
 hadoop fs -cp $LINE $hdfsoutputpath  #copying each file to the outputpath
 done
fi
fi
else
echo "inputpath does not exist"
fi
fi
