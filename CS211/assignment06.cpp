//CS211,Yuqian Zhang
#include <iostream>
#include <cstdlib>
using namespace std;
bool ok(int a[], int c){
	for (int i=0;i<c;i++){
		if (a[c]==a[i]||(c-i)==abs(a[c]-a[i]))
			return false;//whether column c passes the row and diagonal tests
	}
	return true;
}

void print(int a[], int c){
	cout << "Solution #" << c << ": ";
	for (int i=0;i<8;i++){
		cout<<a[i];
	}	
	cout<<endl; 
}//print the board

void backtrack(int &col){
	--col;
	if (col==-1)
		exit(0);
}//backtrack

int main(){
	int q[8]={0},counter=0,c=0;
	
	while(true){ //go through all the columns (NextCol)
		c++;
		if(c == 8){
			print(q,++counter);
			backtrack(c);
		} else q[c] = -1;
		
		while(true){//go through all the rows (NextRow)
			q[c]++; 
			if(q[c] == 8){
				backtrack(c);
				continue;//continue the loop
			}//end if
			if(ok(q,c)){ //call pass function to test for column c.
				break; //go out of the loop that go through all the rows and continue to the top of the while loop that goes through all the columns(goto NextCol)
			}
		}//end row while
   }//end col while
}