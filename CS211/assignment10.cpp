//CS211,Yuqian Zhang
#include <iostream>
using namespace std;
bool ok(int q[], int c){
	static int mp[3][3]={{0,2,1},{0,2,1},{1,2,0}};//set up man perference table
	static int wp[3][3]={{2,1,0},{0,1,2},{2,0,1}};//set up woman perference table
	for (int i=0;i<c;i++){
		//whether column c passes the row test
		if (q[c]==q[i])
			return false;
		//check mp[cm][nw]<mp[cm][cw]&&wp[nw][cm]<wp[nw][nm]
		if ((mp[i][q[c]]<mp[i][q[i]])&&(wp[q[c]][i]<wp[q[c]][c]))
			return false;
		//check mp[nm][cw]<mp[nm][nw]&&wp[cw][nm]<wp[cw][cm]
		if ((mp[c][q[i]]<mp[c][q[c]])&&(wp[q[i]][c]<wp[q[i]][i]))
			return false;
	}
	return true;//if there is no instability, return true
}

void print(int a[], int c){
	cout << "Solution #" << c << ": "<<endl;
	cout<<"MAN"<<"\t"<<"WOMAN"<<endl;
	for (int i=0;i<3;i++){
		cout<<i<<"\t"<<a[i]<<endl;
	}	
}//print the board

void backtrack(int &col){
	--col;
}//backtrack

int main(){
	int q[3],counter=0,c=0;
	q[0]=0;
	while(true){ //go through all the columns (NextCol)
		c++;
		if(c == 3){
			print(q,++counter);
			backtrack(c);
		} else q[c] = -1;
		
		while(true){//go through all the rows (NextRow)
			q[c]++; 
			if(q[c] == 3){
				backtrack(c);
				if (c == -1){
					return 0;//end program
				} 
				continue;//continue the loop
			}//end if
			if(ok(q,c)){ //call pass function to test for column c.
				break; //go out of the loop that go through all the rows and continue to the top of the while loop that goes through all the columns(goto NextCol)
			}
		}//end row while
   }//end col while
}