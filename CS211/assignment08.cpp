//CS211,Yuqian Zhang
#include<iostream>
#include<cmath>
using namespace std;

bool ok (int b[],int c){
	static int help[8][8]={{-1},{0,-1},{0,1,-1},{0,2,-1},{1,2,-1},{1,2,3,4,-1},{2,3,5,-1},{4,5,6,-1}};//set up help_table
	//row test
	for(int i=0;i<c;i++)
		if (b[c]==b[i]) return false;
	//using help_table to test 
	for(int j=0;j<8;j++){
		int i = help[c][j];
		if(i==-1) return true;
		if (abs(b[c]-b[i])==1) return false;
	}
}//ok function to test if the number is in the rigth position

void backtrack(int&c){
	c--;
}//backtrack

void print(int a[],int counter){
	cout<<"Solution"<<counter<<":"<<endl;
	cout<<" "<<a[1]<<a[4]<<" "<<endl;
	cout<<a[0]<<a[2]<<a[5]<<a[7]<<endl;
	cout<<" "<<a[3]<<a[6]<<" "<<endl;
}//print out 8 number in cross


int main(){//refer to assignment06's 1D array code
	int b[8]={0},counter=0,c=0;
	
	while(true){ //go through all the columns (NextCol)
		c++;
		if(c == 8){
			print(b,++counter);
			backtrack(c);
		} else b[c] = -1;
		
		while(true){//go through all the rows (NextRow)
			b[c]++; 
			if(b[c] == 8){
				backtrack(c);
				if (c == -1){
					cout << counter << " solution found\n";
					return 0;//end program
				} 
				continue;//continue the loop
			}//end if
			if(ok(b,c)){ //call pass function to test for column c.
				break; //go out of the loop that go through all the rows and continue to the top of the while loop that goes through all the columns(goto NextCol)
			}
		}//end row while
   }//end col while
}//main()