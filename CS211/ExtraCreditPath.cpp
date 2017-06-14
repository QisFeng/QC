//CS211 - Zhang, Yuqian
//Extra Credit

#include<iostream>
using namespace std;

int Min(int a, int b){
	 if (a<b)
		 return a;
	 else return b;
}	

int main(){
	
	//The program asks the user for the size of the grid n
	int n;
	cout<<"Input the size of N: ";
	cin>>n;
	
	//For every row, the program asks the user to input values for that row in the grid until all rows have been filled
	int *k=new int [n*n];
	for (int i=0;i<n;i++){
		cout<<"fill in row"<<i+1<<":"<<endl;
		for(int j=0;j<n;j++)
			cin>>k[i*n+j];
	}
	
	//save the input path data into a 2D array
	int weight[n][n];
	for (int i=0;i<n;i++){
		for(int j=0;j<n;j++)
			weight[i][j]=k[i*n+j];
	}
	
	int min,left,up,dia;
	
	//overwrite the weight array with path number
	for( int i=1; i<n; i++)//set up first column
		weight[i][0]+=weight[i-1][0];
	
	for( int i=1; i<n; i++)//set up first row
		weight[0][i]+=weight[0][i-1];
	
	for(int j=1;j<n;j++){
		for( int i=1; i<n; i++){
			
			left=weight[i][j-1];
			up=weight[i-1][j];
			dia=weight[i-1][j-1];
			
			min = Min(left,up);
			min = Min(min,dia);
			
			
			if (min==left)
				weight[i][j]+=left;
			if (min==up)
				weight[i][j]+=up;
			if (min==dia)
				weight[i][j]+=dia;	
		}
	}
	//Print out the memo of the total cost array
	cout<<"The memo of the total cost array:"<<endl;
	for (int i=0;i<n;i++){
		for(int j=0;j<n;j++)
			cout<<weight[i][j]<<" ";
		cout<<endl;
	}
	
	//Print out the cost of the shortest path
	int shortest = weight[n-1][n-1];
	cout<<"the cost of the shortest path is: "<<shortest<<endl;
	
	delete []k;
	return 0;
}
	
	
	
	