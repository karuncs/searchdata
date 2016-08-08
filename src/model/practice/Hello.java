package practice;


	import java.io.*;
	import java.net.*;

	public class Hello {
		public static void main(String[] args) throws Exception {
	        URL yahoo = new URL("http://iwis.cs.aau.dk/home");
	        URLConnection yc = yahoo.openConnection();
	        BufferedReader in = new BufferedReader(
	                                new InputStreamReader(
	                                yc.getInputStream()));
	        String inputLine;

	        while ((inputLine = in.readLine()) != null) 
	            System.out.println(inputLine);
	        in.close();
	    }

		
	}


