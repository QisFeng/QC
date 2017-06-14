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

void print(int a[]){
	static int c =0;
    cout << "solution#"<<++c<< endl;
	cout<<" "<<a[1]<<a[4]<<" "<<endl;
	cout<<a[0]<<a[2]<<a[5]<<a[7]<<endl;
	cout<<" "<<a[3]<<a[6]<<" "<<endl;
}//print out 8 number in cross

void move(int * q,int i){
	if(i==8){
		print(q);
		return;
	}
	for(int j=0;j<8;j++){
		q[i]=j;
		if(ok(q,i))
			move(q,i+1);
	}
}

int main(){
	int q[8];
	move(q,0);
	return 0;
}//main()