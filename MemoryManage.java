//														Free Memory Manage
//														**** ****** ******
// 			#1 First Fit
//			#2.Next  Fit
//			#3.Worst Fit
//			#4.Best  Fit

//import libraries
import java.util.*;

// Main Memory Manage Class 
public class MemoryManage
{
	// Array of holes(free memory)  
	static int holes[]={10,4,20,18,7,9,12,15};
	//used for mapping (true | false)
	static boolean selected[] = new boolean[holes.length];
	//Array of queue(request for free memory)
	static int queue[]={25,40,49};
	//count the wasted memory
	static int space;
		
	// Soul of Source Code(Execution start from here)
	public static void main(String...args)
	{
		//set the flag
		boolean flag=true;
		
		//Repeat till condition false
		do{
			//display message
			System.out.println("\nFree Memory Management");
			System.out.println("==== ====== ==========\n");
			System.out.println("  #1.firstFit");
			System.out.println("  #2.nextFit");
			System.out.println("  #3.worstFit");
			System.out.println("  #4.bestFit");
			
			//used for user input
			Scanner sc = new Scanner(System.in);
			System.out.print("\n#Enter Your Choice :");
			int ch=sc.nextInt();
			
			//redirect to the method
			switch(ch){
				case 1:
					firstFit();
					break;
				case 2:
					nextFit();
					break;
				case 3:
					worstFit();
					break;
				case 4:
					bestFit();
					break;
				//if no match found
				default:
					System.out.println("#You Entered wrong Choice...");
				//stop the loop
				case 0:
					flag=false;
					break;
			}
		}while(flag);
	}
	
	public static void firstFit()
	{
		System.out.println("\n\t\tFirst Fit ...");
		
		//display free holes along with its default value
		System.out.println("\n\nFree holes ...\n");
		for(int i=0;i<holes.length;i++)
				System.out.println("\t" + holes[i] + "\t" + selected[i]);
			
		//display queue
		System.out.println("\nRequest holes ...\n");	
		for(int i=0;i<queue.length;i++)
				System.out.println("\t" + queue[i]);
		
		//logic of first fit
		for(int i=0;i<queue.length;i++)
			for(int j=0;j<holes.length;j++)
			{
				if(queue[i] <= holes[j])
				{					
					if(selected[j] == false)
					{
						space = space + (holes[j] - queue[i]);
						
						holes[j] = queue[i];
						selected[j] = true;
						break;
					}
				}
			}
				
			//display output 
			System.out.println("\nAllocated holes ...\n");
			for(int i=0;i<holes.length;i++)
				System.out.println("\t" + holes[i] + "\t" + selected[i]);
			
			//display total space wasted
			System.out.println("\nTotal Space Wasted : " + space + "\n");
	}
	
	public static void nextFit()
	{
		System.out.println("\n\t\tNext Fit ...");
		
		System.out.println("\n\nFree holes ...\n");
		for(int i=0;i<holes.length;i++)
				System.out.println("\t" + holes[i] + "\t" + selected[i]);
			
		System.out.println("\nRequest holes ...\n");	
		for(int i=0;i<queue.length;i++)
				System.out.println("\t" + queue[i]);
	
		int i=0;
		
		//logic of next fit
		for(int j=0;j<holes.length;j++)
		{
			if(queue[i]<=holes[j])
			{
				space = space + (holes[j] - queue[i]);
				
				holes[j]=queue[i];
				selected[j]=true;
				i++;
			}
			if(i == queue.length)
				break;
		}
		
		//display output
		System.out.println("\nAllocated holes ...\n");
		for(i=0;i<holes.length;i++)
			System.out.println("\t" + holes[i] + "\t" + selected[i]);
		
		//display total space wasted
		System.out.println("\nTotal Space Wasted: " + space + "\n");
	}
	
	public static void worstFit()
	{
		System.out.println("\n\t\tWorst Fit ...");
	
		System.out.println("\n\nFree holes ...\n");
		for(int i=0;i<holes.length;i++)
				System.out.println("\t" + holes[i] + "\t" + selected[i]);
			
		System.out.println("\nRequest holes ...\n");	
		for(int i=0;i<queue.length;i++)
				System.out.println("\t" + queue[i]);
		
		//temp variable used to store the temporary value
		int temp;
		
		//logic of worst fit(arrange in ascending order)
		for(int i=0;i<holes.length;i++)
			for(int j=i+1;j<holes.length;j++)
			{
				if(holes[i] < holes[j])
				{
					temp=holes[i];
					holes[i]=holes[j];
					holes[j]=temp;
				}
			}
			
		System.out.print("\nDescending Order\n\n\t");
		for(int i=0;i<holes.length;i++)
			System.out.print(holes[i] + " ");
		
		//logic of worst fit (assign value)
		for(int i=0;i<queue.length;i++)
		{	
			if(queue[i] > holes[i])
				continue;
			
			space = space + (holes[i] - queue[i]);
			
			holes[i]=queue[i];
			selected[i]=true;
		}
		
		System.out.println("\n\nAllocated holes ...\n");
		for(int i=0;i<holes.length;i++)
			System.out.println("\t" + holes[i] + "\t" + selected[i]);
	
		System.out.println("\nTotal Space Wasted: " + space + "\n");
	}
	
	public static void bestFit() {
		
		boolean on = false;
		System.out.println("\n\t\tBest Fit ...");
		
		System.out.println("\n\nFree holes ...\n");
		for(int i=0;i<holes.length;i++)
				System.out.println("\t" + holes[i] + "\t" + selected[i]);
			
		System.out.println("\nRequest holes ...\n");	
		for(int i=0;i<queue.length;i++)
				System.out.println("\t" + queue[i]);
		
		//logic of best fit
		for(int i=0;i<queue.length;i++) 
		{
			for(int j=0;j<holes.length;j++)
			{
				if(queue[i] == holes[j])
				{
					holes[j]=queue[i];
					selected[j] = true;
					break;
				}
				if(j == holes.length-1)
				{
					//min store minimum value, id store id of element
					int min=holes[0],temp=0,id=0;
					
					for(int k=0;k<holes.length;k++)
					{
						if(queue[i] < holes[k])
						{
							temp = holes[k] - queue[i];
							
							if(min > temp)
							{
								min=temp;
								id=k;
								on=true;
							}
						}
					}
					if(on == true) 
					{
						holes[id] = queue[i];
						selected[id] = true;
						
						System.out.println("\nTotal Space Wasted : " + temp);
					}
				}
			}
		}
	
		//display final output
		System.out.println("\n\nAllocated holes ...\n");
		for(int i=0;i<holes.length;i++)
			System.out.println("\t" + holes[i] + "\t" + selected[i]);
	
	}
}