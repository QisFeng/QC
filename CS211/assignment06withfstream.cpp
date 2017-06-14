//CS211,Yuqian Zhang
#include <iostream>
#include<fstream>
#include <cstdlib>
using namespace std;

bool pass(int a[], int c){
	for (int i=0;i<c;i++){
		if (a[c]==a[i]||(c-i)==abs(a[c]-a[i]))
			return false;
	}
	return true;
}//whether column c passes the row and diagonal tests

void backtrack(int &col){
	--col;
}//trace back

int main(){
	int q[8]={0},counter=0,c=0;
	ofstream myfile;
	myfile.open("8queen.txt");//create a text file
	
	while(true){ //go through all the columns (NextCol)
		c++;
		if(c == 8){
			//if c==8,print the solution to the text file
			myfile << "Solution #" << ++counter << ": "<<endl;
			int b[8][8]={0};
			for (c=0;c<8;c++){
				b[q[c]][c]=1;
			}
			for(int i=0;i<8;i++){
				for (int j=0;j<8;j++){
					if(b[i][j]==1) myfile<<"Q";
					else myfile<<"-";
				}
				myfile<<endl;
			}	
			//after printed, trace one column back
			backtrack(c);
		} else q[c] = -1;
		
		while(true){//go through all the rows (NextRow)
			q[c]++; 
			if(q[c] == 8){
				backtrack(c);
				if (c == -1){
					cout << counter << " solution found,please check:8queen.txt\n";
					 myfile.close();//close file
					return 0;//end program
				} 
				continue;//go back to the top of NextRow loop and go through the rest of the rows
			}//end if
			if(pass(q,c)) //call pass function to test for column c.
				break; //go out of NextRow loop and continue to the top of NextCol loop)
		}//end row while	
   }//end col while
}