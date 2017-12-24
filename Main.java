import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 * @author xiao lin
 *
 */
public class Main{
	public static void main(String[] args){
		File infile = new File(args[0]); //input file
		File output = new File(args[1]); //output file
		try{
			Scanner scan = new Scanner(infile);
			PrintWriter printer = new PrintWriter(output);
			
			MedianFilter medianFilter = new MedianFilter(scan);
			medianFilter.Print(printer);
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		
		
	}//end main
}//end class