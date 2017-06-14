//CS211,Yuqian Zhang
#include <iostream>
#include <cstdlib>
#include<cmath>

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


void print(int q[]){
	static int counter =0;
	cout << "Solution #" << ++counter << ": "<<endl;
	cout<<"MAN"<<"\t"<<"WOMAN"<<endl;
	for (int i=0;i<3;i++){
		cout<<i<<"\t"<<q[i]<<endl;
	}	
}//print the board

//recursion move on 8 queens
void move(int * q,int i){
	if(i==3){
		print(q);
		return;
	}
	for(int j=0;j<3;j++){
		q[i]=j;
		if(ok(q,i))
			move(q,i+1);
	}
}

int main(){
	int q[3];
	move(q,0);
	return 0;
}