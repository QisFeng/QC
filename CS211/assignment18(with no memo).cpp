#include<iostream>
using namespace std;
const int rows = 5;
const int cols = 6;
int static weight[rows][cols]={
	{3,4,1,2,8,6},
	{6,1,8,2,7,4},
	{5,9,3,9,9,5},
	{8,4,1,3,2,6},
	{3,7,2,8,6,4}};
	
int Min(int a, int b){
	 if (a<b)
		 return a;
	 else return b;
 }

int main(){
	
	int min,left,up,down;
	
	//overwrite the weight array with path number
	for(int j=1;j<cols;j++){
		for( int i=0; i<rows; i++){
			
			left=weight[i][j-1];
			up=weight[(i-1+rows)%rows][j-1];
			down=weight[(i+1)%rows][j-1];
			
			min = Min(left,up);
			min = Min(min,down);
			
			if (min==left)
				weight[i][j]+=left;
			if (min==up)
				weight[i][j]+=up;
			if (min==down)
				weight[i][j]+=down;	
		}
	}
	
	
	int shortest = weight[0][cols-1];
	int lastpath;
	//find the smallest path on the right column and print the result
	for(int r=1;r<rows;r++){
		if(shortest > weight[r][cols-1]){
			shortest = weight[r][cols-1];
			lastpath = r;
		}
	}
	cout<<"the shortest path is of length "<<shortest<<endl;
	
	
	int a[6];//set up a helper array to save the route in the tracking order
	a[0]=-1;
	//backtrack the path and print out the route
	for(int j=cols-1;j>0;j--){
		for (int i=0;i<rows;i++){
			if(weight[i][j]==shortest){
				
				left = weight[i][j-1];
				up = weight[(i-1+rows)%rows][j-1];
				down = weight[(i+1)%rows][j-1];
				
				min = Min(left,up);
				min = Min(min,down);
				
				if (min==left){
					a[j]=i;
					shortest=left;
				}
				if (min==up){
					a[j]=(i-1+rows)%rows;
					shortest=up;
				}
				if (min==down){
					a[j]=(i+1)%rows;
					shortest=down;
				}
			}//end if
		}//end inner loop
	}//end outter loop
	
	//print out the route from left to the right
	cout<<"The path from left to right is:";
	for(int c=1;c<6;c++)
		cout<<a[c]<<" ";
	cout<<lastpath;
	cout<<endl;
	
	//system("PAUSE");
	return 0;
} 