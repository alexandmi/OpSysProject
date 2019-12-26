public class RoundRobin 
{ 
	// Method that calculates the waiting time for all processes 
	static int[] calcWaitingTime(int burstTime[], int quantum) 
	{
		int[] waitingTime = new int[burstTime.length];
        	for(int i=0;i<burstTime.length;i++){
            	int j=0;
            	int temp = 0;
            	int[] clone = burstTime.clone();
            	do {
                	if (clone[j] != 0) {
                    	if (clone[j] > quantum) {
                        	clone[j] = clone[j] - quantum;
                        	temp = temp + quantum;
                    	} else {
                        	temp = temp + clone[j];
                        	clone[j] = 0;
                    	}
                	}
                	j++;
                	if(j==4)
                    	j=0;
            	} while (clone[i] != 0);
            	waitingTime[i] = temp - burstTime[i];
        	}
        	return waitingTime;
	} 
	
	// Method that calculates turn around time for all processes
	static int[] calcTurnAroundTime(int burstTime[], int waitingTime[]) 
	{
		int[] turnAroundTime = new int[burstTime.length];
        	for(int i = 0; i < turnAroundTime.length; i++){
            	turnAroundTime[i] = burstTime[i] + waitingTime[i];
        	}
        	return turnAroundTime;
	} 
	
	// Method that prints the results and calculates the average waiting and
	// turnaround times
	static void printAvgTimes(int burstTime[], int quantum) 
	{
		int n = burstTime.length;
		int totalWaitingTime = 0;
		int totalTurnAroundTime = 0; 
	
		// Find waiting time of all processes 
		int[] waitingTime = calcWaitingTime(burstTime, quantum); 
	
		// Find turn around time for all processes 
		int[] turnAroundTime = calcTurnAroundTime(burstTime, waitingTime); 
	
		// Display processes along with all details 
		System.out.println("Process " + " Burst Time " + 
					" Waiting Time " + " Turnaround Time"); 
		System.out.println("=======  ==========  ============  ===============");
		// Calculate total waiting time and total turn 
		// around time 
		for (int i = 0; i < n; i++) { 
			totalWaitingTime += waitingTime[i]; 
			totalTurnAroundTime += turnAroundTime[i]; 
			System.out.println(i + "\t\t" + burstTime[i] +"\t " + 
							waitingTime[i] +"\t\t " + turnAroundTime[i]); 
		} 
	
		System.out.println("\nAverage waiting time = " + 
						(float)totalWaitingTime / (float)n); 
		System.out.println("Average turnaround time = " + 
						(float)totalTurnAroundTime / (float)n); 
	} 
	
	// Driver Method to test your algorithm with a simple example
	public static void main(String[] args) 
	{
		// Burst time of processes. The array index is the process ID.
		int burstTime[] = {5, 15, 4, 3}; 

		// Time quantum 
		int quantum = 3;
		
		printAvgTimes(burstTime, quantum); 
	} 
} 
