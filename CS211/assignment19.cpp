//CS211,Yuqian Zhang
//Assignment19 Bishop problem

#include <iostream>
#include <cstdlib>
using namespace std;
bool ok(int a[], int c,int n){
	//a[c]/n--->current col
	//a[c]%n--->current row
	for (int i=0;i<c;i++){
		if ((a[c]<a[i])||abs(a[c]%n-a[i]%n)==(a[c]/n-a[i]/n))
			return false;//whether the position of a[c] passes diagonal tests
	}
	return true;
}//ok function

void backtrack(int &col){
	--col;
}//backtrack

int main(){
	
	while(true){
		//input board size and bishop numbers
		int n;
		cout<<"Input board size: ";
		cin>>n;
		if(n==-1) return 0;
		int k;
		cout<<"Input how many bishops you want to put in: ";
		cin>>k;
		
		
		for(int i=1;i<=k;i++){
			//set up for i bishops problem
			int *q = new int[i];
			int counter=0,c=0;
			q[0]=0;
			
			while(true){ //go through all the bishops
				if (c==-1) break;//if c==-1,go out of this loop and i++
				c++;
				if(c == i){
					counter++;
					backtrack(c);
				} else q[c]=-1;
				
				while(true){//go through all the positions
					q[c]++; 
					if(q[c] == n*n){
						backtrack(c);
						if (c==-1){
						cout<<"There are "<<counter<<" solutions to the "<<i<<" bishop problems."<<endl;
						break;//after print, go out of the loop
						}
						continue;//continue the loop
					}//end if
					if(ok(q,c,n)) //call pass function to test for bishop's position
						break; //go out of the loop that go through all positions and continue to the top of the while loop that goes through all the bishops
				}//end positions while
			}//end bishops while
			
			delete []q;
		}//end for loop
	}//end while loop 
	
}//end main