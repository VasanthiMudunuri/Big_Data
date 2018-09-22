import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.ContentSummary;
import org.apache.hadoop.fs.FileAlreadyExistsException;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import java.io.FileNotFoundException;

public class Vasanthi_Mudunuri_program_2 {

        public static void main(String[] args) {
                 if(args.length!=3)              //checking if all the 3 arguments are given, in all othercases throwing an exception
                {
                  throw new ArrayIndexOutOfBoundsException("Please provide all the three arguments: inputpath outputpath count");
                }
                String hdfsinputpath=args[0];     //inputpath stored in a variable hdfsinputpath
                String hdfsoutputpath=args[1];    //outputpath stored in a variable hdfsoutputpath
                String count=args[2];             // the number of files to be coiped (or) the user provided count is stored in a variable count
                Integer countgiven=0;             
                try                               // as the arguments are in an array of strings, conversion to an integer is required
                {
                        countgiven=Integer.parseInt(count);   // converting and checking if the count is a number otherwise throwing an exception
                }
                catch(NumberFormatException nfe)
                {
                        System.out.println("please enter a number to count the files");
                }
                Integer filescopied=0;        //variable filescopied to maintain the count of the number of files copied from inputpath to the outputpath
                Path inputfolder=new Path(hdfsinputpath);      //converting the string hdfsinputpath in to a Path
                Path outputfolder=new Path(hdfsoutputpath);    //converting the string hdfsoutputpath in to a path
                Configuration config=new Configuration();          //setting the configuration by providing URI
                config.set("fs.defaultFS", "hdfs://hadoop1:9000");
                try
                {
                FileSystem hdfs=FileSystem.get(config);            //providing configuration to the filesystem to connecct to hdfs
                FileStatus[] filestatus=hdfs.listStatus(inputfolder);  //FileStatus can be used to query on hdfs FileSyatem, liststatus is used to browse it 
                Path[] path=FileUtil.stat2Paths(filestatus); //converting an array of filestatus to an array of path
                        if(hdfs.exists(inputfolder))      //checking if the given inputpath exists else throwing an exception
                        {
                                ContentSummary cs=hdfs.getContentSummary(inputfolder);  //contentsummary is used to store the summary of content,file or a directory
                                long countoriginal=cs.getFileCount(); //retrieving filecount from content summary and storing in a variable countoriginal
	                            if(!(countoriginal<countgiven))  //checking if the files present in input are less than the count provided if yes throwing an error
                                {
                                        try
                                        {
                                                if(!(hdfs.exists(outputfolder))) //checking if the outputpath already exists in hdfs, if yes throwing an error
                                                {
                                                        hdfs.mkdirs(outputfolder); //if not, creating the new directories in the outputpath
                                                        for(Path filecopy:path)    // iterating through the array of paths
                                                        {
                                                                if(filescopied<countgiven) //condition on number of files to be copied 
                                                                {
                                                                        filescopied++;    //incrementing on every file copied
                                                                FileUtil.copy(hdfs,filecopy,hdfs,outputfolder,false,config); //FileUtil.copy a predefined method to copy files between filesystems
                                                                }
                                                        }
                                                }
                                                else
                                                {
                                                        System.out.println("Outputpath already exists");
                                                }
                                        }
                                        catch(FileAlreadyExistsException fae) //to handle exceptions in case a file with the same name already exists in the outputpath
                                        {
                                                System.out.println("The file already exists in the output path");
                                        }
                                }
                                else
                                {
                                        System.out.println("The number of files in the input path are less than given count");
                                        System.out.println("There are "+countoriginal+" files in the input path");
                                }

                        }
                }
catch(FileNotFoundException fnfe) //to handle exceptions in case if the file is not available
{
System.out.println("inputpath does not exist");
}
catch(Exception e) //to handle all the other exceptions 
{
e.printStackTrace();
}
}
}
