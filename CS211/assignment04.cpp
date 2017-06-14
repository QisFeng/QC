//CS211,Yuqian Zhang
#include<iostream>
using namespace std;
int main(){
	//set up chessboard and initialize row and column and counter
	//make the left top blank be the queen
	int b[8][8]={0},r,c=0,counter=0;
	b[0][0]=1;
	
	//go to next column 	
	NC:c++;
	   if (c==8) goto Print;
	   r=-1;
	   
	//go to next row
	NR:r++;
	   if (r==8) goto Backtrack;	
	   
	//row test
	for (int i=0;i<c;i++)
		if (b[r][i]==1) goto NR;
	//up diagonal test
	for (int i=1;(r-i)>=0&&(c-i)>=0;i++)
		if (b[r-i][c-i]==1) goto NR;
	//down diagonal test
	for (int i=1;(c-i)>=0&&(r+i)<8;i++)
		if (b[r+i][c-i]==1) goto NR;
	
	//if pass all the tests above, put a queen there and go to next colomn	
	b[r][c]=1;
	goto NC;
	
	//trace back 		
	Backtrack:c--;
			if (c==-1){
				return 0;//if c==-1,exit 
			} 
			//find where the queen is, remove it and go to next row 
			r=0;
			while(b[r][c]==0)
				r++;
			b[r][c]=0;
			goto NR;
			
	//print chessboard		
	Print:  counter++;
			cout<<"Solution"<<counter<<":"<<endl;
			for (r=0;r<8;r++){
				for (c=0;c<8;c++){
					if(b[r][c]==1) cout<<"Q";
					else cout<<"-";
				}
				cout<<endl;
			}	
			cout<<endl; 
			goto Backtrack;//after printing the chessboard,go to backtrack for other solutions
		
	}