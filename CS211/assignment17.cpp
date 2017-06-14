//CS211 - Zhang, Yuqian
//Assignment17 - Shortest Path Problem  Part (A)

#include<iostream>
using namespace std;
const int rows = 5;
const int cols = 6;

static int weight[rows][cols]={
	{3,4,1,2,8,6},
	{6,1,8,2,7,4},
	{5,9,3,9,9,5},
	{8,4,1,3,2,6},
	{3,7,2,8,6,4}};
	
static int memo[rows][cols]={
	{0,0,0,0,0,0},
	{0,0,0,0,0,0},
	{0,0,0,0,0,0},
	{0,0,0,0,0,0},
	{0,0,0,0,0,0}};
	
static int memopath[rows][cols]={
	{-1,-1,-1,-1,-1,-1},
	{-1,-1,-1,-1,-1,-1},
	{-1,-1,-1,-1,-1,-1},
	{-1,-1,-1,-1,-1,-1},
	{-1,-1,-1,-1,-1,-1},};	
	
int Min(int a, int b){
	 if (a<b)
		 return a;
	 else return b;
 }
 
int cost(int i, int j){ // i is the row, j is the column

	int left=0,up=0,down=0;
	//base case
	if(j==0)
	return memo[i][j]= weight[i][0];
	
	// recursive call
	if (memo[i][j-1]!=0)
		left =memo[i][j-1];
	else memo[i][j-1]= left = cost(i,j-1);
	
	if (memo[(i-1+rows)%rows][j-1]!=0)
		up =memo[(i-1+rows)%rows][j-1];
	else memo[(i-1+rows)%rows][j-1]= up = cost((i-1+rows)%rows,j-1);
	
	if (memo[(i+1)%rows][j-1]!=0)
		down =memo[(i+1)%rows][j-1];
	else memo[(i+1)%rows][j-1] =down = cost((i+1)%rows,j-1);

	// find the value of the shortest path through cell (i,j)
	int min = 0;
	min = Min(left,up);
	min = Min(min,down);
	
	if (min==left)
		memopath[i][j]=i;
	if (min==up)
		memopath[i][j]=(i-1+rows)%rows;
	if (min==down)
		memopath[i][j]=(i+1)%rows;	
	
	return memo[i][j]= min + weight[i][j];
 }
 

int main(){
	int ex[rows];

	// get the shortest path out of each cell on the right
	for( int i=0; i<rows; i++)
		ex[i]=cost(i,cols-1);

	// now find the smallest of them
	int min = ex[0];
	int lastpath;
	for(int r=1;r<rows;r++){
		if(min > ex[r]){
			min = ex[r];
			lastpath = r;
		}
	}
	cout<<"the shortest path is of length "<<min<<endl;
	
	//test the path memo to check whether it is right
	/*for(int i=0;i<rows;i++){
		for(int j=0;j<cols;j++)
			cout<<memopath[i][j];
		cout<<endl;
	}*/
	cout<<"the path is:";
	
	//with the help of memo array find the shortest route in memopath array
	int left,up,down;
	int a[6];
	for(int j=cols-1;j>=0;j--){
		for (int i=0;i<rows;i++){
			if(memo[i][j]==min)
				a[j]= memopath[i][j];//save the path in array by order
			left = memo[i][j-1];
			up = memo[(i-1+rows)%rows][j-1];
			down = memo[(i+1)%rows][j-1];
			
			if (left+weight[i][j]==min)
				min=left;
			if (up+weight[i][j]==min)
				min=up;
			if (down+weight[i][j]==min)
				min=down;
		}
	}
	//print out the array
	for(int c=1;c<6;c++)
		cout<<a[c]<<" ";
	cout<<lastpath;
	cout<<endl;
	//system("PAUSE");
	//return 0;


} 