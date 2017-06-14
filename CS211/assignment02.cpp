//CS211,Yuqian Zhang
#include <iostream>
using namespace std;

void shift(int a[], int size){
	int b[size];
	b[0] = a[size-1];
	for (int i=1;i<size;i++){
		b[i]=a[i-1];
	}
	for (int i=0;i<size;i++){
		a[i]=b[i];
	}
}

bool arrayeq(int a[],int b[],int size){
	for(int i=0;i<size;i++){
		if(a[i]!=b[i]) return false;
	}
	return true;
}

bool equivalent(int a[],int b[],int size){
	for (int i =0;i<size;i++){
		if (arrayeq(a,b,size)) return true;
		else shift(b,size);
	}
	return false;
}
	
int main(){
	int a[5]={4,7,9,2,1};
	int b[5]={2,1,4,7,9};
	int x = equivalent(a,b,5);
	if(x==0)
		cout<<"Not equivalent!"<<endl;
	else
		cout<<"Equivalent!"<<endl;
	return 0;
}