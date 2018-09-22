import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.ContentSummary;
import org.apache.hadoop.fs.FileAlreadyExistsException;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import java.io.FileNotFoundException;

public class Vasanthi_Mudunuri_program_4 {

        public static void main(String[] args) {
                if(args.length!=3)          //checking if all the 3 arguments are given, in all othercases throwing an exception
                {
                 throw new ArrayIndexOutOfBoundsException("Please provide all the three arguments: inputpath outputpath count");
                }
                String hdfsinputpath=args[0];     //inputpath stored in a variable hdfsinputpath
                String hdfsoutputpath=args[1];    //outputpath stored in a variable hdfsoutputpath
                String count=args[2];             // the number of files to be coiped (or) the user provided count is stored in a variable count
                Integer countgiven=0;             
                try                               // as the arguments are in an array of strings, conversion to an integer is required
                {
                        countgiven=Integer.parseInt(count); // converting and checking if the count is a number otherwise throwing an exception
                }
                catch(NumberFormatException nfe)
                {
                        System.out.println("please enter a number to count the files");
                }
                Integer filescopied=0;       //variable filescopied to maintain the count of the number of files copied from inputpath to the outputpath
                Integer foldercreated=1;     //variable foldercreated to maintain the count on the number of folders created in the outputpath
                Integer directorycount=1;     //variable directorycount to maintain the directory count in which files are copied
                Path inputfolder=new Path(hdfsinputpath);     //converting the string hdfsinputpath in to a Path
                Path outputfolder=new Path(hdfsoutputpath);   //converting the string hdfsoutputpath in to a path
                Configuration config=new Configuration();     //setting the configuration by providing URI
                config.set("fs.defaultFS", "hdfs://hadoop1:9000");
                try
                {
                        FileSystem hdfs=FileSystem.get(config);    //providing configuration to the filesystem to connecct to hdfs
                        FileStatus[] filestatus=hdfs.listStatus(inputfolder);   //FileStatus can be used to query on hdfs FileSyatem, liststatus is used to browse it
                        Path[] path=FileUtil.stat2Paths(filestatus);   //converting an array of filestatus to an array of path
                        if(hdfs.exists(inputfolder))         //checking if the given inputpath exists else throwing an exception
                        {
						ContentSummary cs=hdfs.getContentSummary(inputfolder); //contentsummary is used to store the summary of content,file or a directory
                                long countoriginal=cs.getFileCount();  //retrieving filecount from content summary and storing in a variable countoriginal
                                try
                                {
                                        if(!(hdfs.exists(outputfolder)))  //checking if the outputpath already exists in hdfs, if yes throwing an error
                                        {
                                                hdfs.mkdirs(outputfolder);  //if not, creating the new directories in the outputpath as per count specified
                                                while(foldercreated<=(Math.ceil((float)countoriginal/countgiven))) //condition to create folders, ceil is taken to copy the left over files less than count to a folder
                                                {
                                                        Path createfolder=new Path(outputfolder+"/"+foldercreated.toString()); //setting path to create directories 
                                                                hdfs.mkdirs(createfolder);  //creating directory
                                                                foldercreated++; //incrementing on each folder creation
                                                }
                                                        for(Path filecopy:path) // iterating through the array of paths
                                                        {
                                                                if(filescopied<countgiven)  //condition on number of files to be copied
                                                                {
                                                                Path outputpath=new Path(outputfolder+"/"+directorycount); // creating outputpath to copy files 
                                                                FileUtil.copy(hdfs,filecopy,hdfs,outputpath,false,config); //FileUtil.copy a predefined method to copy files between filesystems
                                                                filescopied++;  //incrementing on every copy of a file
                                                                if(filescopied==countgiven)  // condition to stop copying files and iterate through next folder
                                                                {
                                                                        directorycount++; //incrementing directorycount to copy files in to the next directory
                                                                        filescopied=0; 
                                                                }
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


