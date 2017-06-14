//CS211,Yuqian Zhang
#include<iostream>
#include<cmath>
using namespace std;
int main(){
	//set up chessboard and initialize row and column and counter
	//make the left top blank be the queen
	int q[8],c=0,counter=0;
	q[0]=0;
	//go to next column 	
	NC:c++;
	   if (c==8) goto Print;
	   q[c]=-1;
	
	//go to next row
	NR:q[c]++;
	   if (q[c]==8) goto Backtrack;	
	   
	//all tests
	for (int i=0;i<c;i++){
		if (q[c]==q[i]||(c-i)==abs(q[c]-q[i]))
			goto NR;
	}
	//if pass all tests, goto next column
	goto NC;
	
	//trace back
	Backtrack:c--;
			if (c==-1){
				return 0;
			} 
			goto NR;
			
	//print the result 
	Print:  counter++;
			int b[8][8]={0};
			cout<<"Solution"<<counter<<":"<<endl;
			for (c=0;c<8;c++){
				b[q[c]][c]=1;
			}
			for(int i=0;i<8;i++){
				for (int j=0;j<8;j++){
					if(b[i][j]==1) cout<<"Q";
					else cout<<"-";
				}
				cout<<endl;
			} 
			goto Backtrack;
		
	}