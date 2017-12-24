import java.io.PrintWriter;
import java.util.Scanner;

public class MedianFilter{
	
	int[][] mirrorFramedAry;//2d array to put original image
	int[][] tempAry;//2d array to put output image
	int[] neighborAry;//1d array to put all neighbor pixel
	int numRows;
	int numCols;
	int minVal;
	int maxVal;
	int newMin;
	int newMax;
	
	public MedianFilter(Scanner scan){
		if(scan.hasNext()){//get the first line 
			if(scan.hasNextInt()){//read row, col, min, max number from first line
				numRows=scan.nextInt();
			}
			if(scan.hasNextInt()){
				numCols=scan.nextInt();
			}
			if(scan.hasNextInt()){
				minVal=scan.nextInt();
			}
			if(scan.hasNextInt()){
				maxVal=scan.nextInt();
			}
		}
		
		newMin = maxVal;
		newMax = minVal;
		
		mirrorFramedAry=new int[numCols+2][numRows+2];//set mirrorFramedAry 
		tempAry=new int[numCols+2][numRows+2];//set tempAry
		neighborAry=new int[9];//set neighborAry
		
		loadImage(scan);
		mirrorFramed();
		
		for(int x=1;x<numCols+1;x++){//read image from top to bottom 
			for(int y=1;y<numRows+1;y++){//left to right 
				loadNeighbors(x,y);//load pixel's neighbor
				int median=Median3X3();//get median of neighbor
				if(median>newMax){//reset newMax and newMin 
					newMax=median;
				}
				if(median<newMin){
					newMin=median;
				}
				tempAry[x][y]=median;//record the median number to tempAry
			}
		}
				
		
	}
	
	public void loadImage(Scanner scan){
		int i=1;
		int j=1;
		while(j<numCols+1){
			if(scan.hasNext()){//get every line 
				while(i<numRows+1){
					if(scan.hasNextInt()){//get every number from each line
						mirrorFramedAry[j][i]=scan.nextInt();
					}
					i++;
				}
				i=1;
			}
			j++;
		}
		
		scan.close();
	}
	
	public void mirrorFramed(){//add mirror to mirrorFramedAry
		for(int k=0;k<numCols+2;k++){
			mirrorFramedAry[k][0]=mirrorFramedAry[k][1];
			mirrorFramedAry[k][numCols+1]=mirrorFramedAry[k][numCols];
		}
		for(int l=0;l<numRows+2;l++){
			mirrorFramedAry[0][l]=mirrorFramedAry[1][l];
			mirrorFramedAry[numRows+1][l]=mirrorFramedAry[numRows][l];
		}
		
	}//end mirrorFramed
	
	public void loadNeighbors(int x,int y){//load pixel's neighbor to 1d array
		neighborAry[0]=mirrorFramedAry[x-1][y-1];
		neighborAry[1]=mirrorFramedAry[x-1][y];
		neighborAry[2]=mirrorFramedAry[x-1][y+1];
		neighborAry[3]=mirrorFramedAry[x][y-1];
		neighborAry[4]=mirrorFramedAry[x][y];
		neighborAry[5]=mirrorFramedAry[x][y+1];
		neighborAry[6]=mirrorFramedAry[x+1][y-1];
		neighborAry[7]=mirrorFramedAry[x+1][y];
		neighborAry[8]=mirrorFramedAry[x+1][y+1];
	}
	
	public int Median3X3(){//use selection sort find the median number of 9 neighbor
		int min; 
		int temp;
		
		for(int i=0;i<neighborAry.length-1;i++){
			min=i;
			for(int j=i+1;j<neighborAry.length;j++){
				if(neighborAry[j]<neighborAry[min]){
					min=j;
				}
			}
			if(min!=i){
				temp = neighborAry[i];
				neighborAry[i]=neighborAry[min];
				neighborAry[min]=temp;
			}
		}
		
		return neighborAry[4];//return the fifth value
		
	}
	
	public void Print(PrintWriter printer){//print output
		printer.println(numCols+" "+numRows+" "+newMin+" "+newMax);
		for(int i=1;i<numCols+1;i++){
			for(int j=1;j<numRows+1;j++){
				printer.print(tempAry[i][j]+" ");
			}
			printer.println();
		}
		printer.close();
	}
}