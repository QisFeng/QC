#include <iostream>
using namespace std;
int main( ){
	int b[3][2];
	cout<<sizeof(b)<<endl;//24
	cout<<sizeof(b+0)<<endl;//8,size of pointer depends on computer 
	cout<<sizeof(*(b+0))<<endl;//8
	// the next line prints 0012FF68
	cout<<"The address of b is: "<<b<<endl; //0012FF68
	cout<<"The address of b+1 is: "<<b+1<<endl;//0012FF76
	cout<<"The address of &b is: "<<&b<<endl;//0012FF68
	cout<<"The address of &b+1 is: "<<&b+1<<endl<<endl;//0012FF68+18(hex)
	return 0;
}