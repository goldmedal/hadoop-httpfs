package hadoop.demo.httpfs.test;

import java.net.*;
import java.nio.file.Files;
import java.io.*;

import org.json.*;

public class FileUploader 
{
    public static void main( String[] args ) throws IOException
    {
    	FileUploader uploader = new FileUploader();
    	String inputRoot = "C:\\Users\\Jax\\Desktop\\";
    	String inputFileName = "aaa.txt";
    	
    	String outputRoot = "/demo/";
    	
        // uploader.uploadFile(inputRoot + inputFileName, outputRoot + inputFileName);
    	uploader.listFile(outputRoot);
    }
    
   public void uploadFile(String inputPath, String outputPath) throws IOException
    {
    	String url = "http://140.116.86.246:924/webhdfs/v1" + outputPath + "?user.name=hduser&op=CREATE&data=true";
    	System.out.println(url);
    	String charset = "UTF-8";
    	File textFile = new File(inputPath);
    	String CRLF = "\r\n"; // Line separator required by multipart/form-data.

    	HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
    	
    	try 
    	{
        	connection.setDoOutput(true);
        	connection.setRequestMethod("PUT");
        	connection.setRequestProperty("Content-Type", "application/octet-stream");
        	connection.setDoOutput(true);
        	
        	DataOutputStream output = new DataOutputStream( connection.getOutputStream() );
    	    Files.copy(textFile.toPath(), output);
    	    output.flush(); // Important before continuing with writer!
    	    
    	} catch(Exception e) {
    		e.printStackTrace();
    	}

    	// Request is lazily fired whenever you need to obtain information about response.
    	int responseCode = ((HttpURLConnection) connection).getResponseCode();
    	System.out.println(responseCode); // Should be 200    	
    	
    }
   
   public void readFile(String filePath) throws IOException{
	   
		String url = "http://140.116.86.246:924/webhdfs/v1" + filePath + "?user.name=hduser&op=OPEN";
	   	System.out.println(url);
	   	String charset = "UTF-8";
	   	String CRLF = "\r\n"; // Line separator required by multipart/form-data.
	
	   	HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
	   	
	   	try
	   	{
	   		connection.setDoOutput(true);
	       	connection.setRequestMethod("GET");
	       	connection.setRequestProperty("Content-Type", "application/octet-stream");
	       	connection.setDoOutput(true);
	       	
	       	connection.connect();
	       	InputStream is = connection.getInputStream();
	       	BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	       	
	       	StringBuilder response = new StringBuilder();
	       	String line;
	       	
	       	while((line = rd.readLine()) != null){
	       		
	       		response.append(line);
	       		response.append(CRLF);
	       		
	       	}
	       	
	       	System.out.println(response.toString());
  	    
	   	} catch(Exception e) {
	   		e.printStackTrace();
	   	}
	
	   	// Request is lazily fired whenever you need to obtain information about response.
	   	int responseCode = ((HttpURLConnection) connection).getResponseCode();
	   	System.out.println(responseCode); // Should be 200   
       
   }
   
   public void listFile(String filePath) throws IOException{
	   
		String url = "http://140.116.86.246:924/webhdfs/v1" + filePath + "?user.name=hduser&op=LISTSTATUS";
	   	System.out.println(url);
	   	String charset = "UTF-8";
	   	String CRLF = "\r\n"; // Line separator required by multipart/form-data.
	
	   	HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
	   	
	   	try
	   	{
	   		connection.setDoOutput(true);
	       	connection.setRequestMethod("GET");
	       	connection.setRequestProperty("Content-Type", "application/octet-stream");
	       	connection.setDoOutput(true);
	       	
	       	connection.connect();
	       	InputStream is = connection.getInputStream();
	       	BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	       	
	       	StringBuilder response = new StringBuilder();
	       	String line;
	       	
	       	while((line = rd.readLine()) != null){
	       		
	       		response.append(line);
	       		response.append(CRLF);
	       		
	       	}
	       	
	       	System.out.println(response.toString());
 	    
	   	} catch(Exception e) {
	   		e.printStackTrace();
	   	}
	
	   	// Request is lazily fired whenever you need to obtain information about response.
	   	int responseCode = ((HttpURLConnection) connection).getResponseCode();
	   	System.out.println(responseCode); // Should be 200   
      
  }
   
   
    
}
