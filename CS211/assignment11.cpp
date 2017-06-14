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
}//ok function

void backtrack(int &col){
	--col;
}//backtrack

int main(){
	//input a number
	int n;
	cout<<"Input a number:";
	cin>>n;
	
	for(int i=1;i<=n;i++){
		//set up for i queens problem
		int *q = new int[i];
		int counter=0,c=0;
		q[0]=0;
		
		while(true){ //go through all the columns (NextCol)
			if (c==-1) break;//if c==-1,go out of this loop and i++
			c++;
			if(c == i){
				counter++;
				backtrack(c);
			} else q[c] = -1;
			
			while(true){//go through all the rows (NextRow)
				q[c]++; 
				if(q[c] == i){
					backtrack(c);
					if (c==-1){
					cout<<"There are "<<counter<<" solutions to the "<<i<<" queens problem."<<endl;
					break;//after print, go out of the loop
					}
					continue;//continue the loop
				}//end if
				if(ok(q,c)) //call pass function to test for column c.
					break; //go out of the loop that go through all the rows and continue to the top of the while loop that goes through all the columns(goto NextCol)
			}//end row while
		}//end col while
		
		delete []q;
	}//end for loop
}//end main