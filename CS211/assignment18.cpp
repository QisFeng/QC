//CS211 - Zhang, Yuqian
//Assignment17 - Shortest Path Problem  Part (B)

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
	
int static memo[rows][cols]={
	{3,0,0,0,0,0},
	{6,0,0,0,0,0},
	{5,0,0,0,0,0},
	{8,0,0,0,0,0},
	{3,0,0,0,0,0}};
	
int static memopath[rows][cols]={
	{-1,-1,-1,-1,-1,-1},
	{-1,-1,-1,-1,-1,-1},
	{-1,-1,-1,-1,-1,-1},
	{-1,-1,-1,-1,-1,-1},
	{-1,-1,-1,-1,-1,-1},};		
	
int Min(int a, int b, int c){
	if (a<b){
		if(a<c)	return a;
	}
	else if (b<c)	return b;
	else return c;
}

int main(){
	
	int min,left,up,down;
	
	//fill int the memo& memopath array from left to right
	for(int j=1;j<cols;j++){
		for( int i=0; i<rows; i++){
			
			left=memo[i][j-1];
			up=memo[(i-1+rows)%rows][j-1];
			down=memo[(i+1)%rows][j-1];
			
			min = Min(left,up,down);
			
			
			if (min==left)
			memopath[i][j]=i;
			if (min==up)
			memopath[i][j]=(i-1+rows)%rows;
			if (min==down)
			memopath[i][j]=(i+1)%rows;	
			
			memo[i][j]=min+weight[i][j];
		}
	}
	/*test out the memo array
	for(int i=0;i<rows;i++){
		for(int j=0;j<cols;j++)
			cout<<memo[i][j];
		cout<<endl;
	}*/
	
	/*test out the memopath array
	for(int i=0;i<rows;i++){
		for(int j=0;j<cols;j++)
			cout<<memopath[i][j];
		cout<<endl;
	}*/
	
	//find the smallest path with the help of memo array and print the result
	int ex[rows];
	for( int i=0; i<rows; i++)
		ex[i]=memo[i][cols-1];
	
	int shortest = ex[0];
	int lastpath;
	for(int r=1;r<rows;r++){
		if(shortest > ex[r]){
			shortest = ex[r];
			lastpath = r;
		}
	}
	cout<<"the shortest path is of length "<<shortest<<endl;
	
	//with the help of memo array find the shortest route in memopath array
	int a[6];
	for(int j=cols-1;j>=0;j--){
		for (int i=0;i<rows;i++){
			if(memo[i][j]==shortest)
				a[j]= memopath[i][j];//save the path in array by order
			left = memo[i][j-1];
			up = memo[(i-1+rows)%rows][j-1];
			down = memo[(i+1)%rows][j-1];
			
			if (left+weight[i][j]==shortest)
				shortest=left;
			if (up+weight[i][j]==shortest)
				shortest=up;
			if (down+weight[i][j]==shortest)
				shortest=down;
		}
	}
	//print out the array
	cout<<"The path from left to right is:";
	for(int c=1;c<6;c++)
		cout<<a[c]<<" ";
	cout<<lastpath;
	cout<<endl;
	
	//system("PAUSE");
	return 0;


} 