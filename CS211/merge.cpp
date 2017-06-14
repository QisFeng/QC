#include<iostream>
using namespace std;
void merge(int a[], int b[], int size){
	int indexa=0,indexb=0,indexc=0,c[size*2];
	while(indexa<size && indexb <size){
		int temp = a[indexa];
		if (b[indexb]<temp) {
			temp = b[indexb];
			indexb++;
		}else indexa++;
		c[indexc]=temp;
		indexc++;
	}
	for(int i=indexc;i<size*2;i++){
		if (indexa>=size) c[i]=b[indexb++];
		else if(indexb>=size) c[i]=a[indexa++];
	}
	for(int i=0;i<size*2;i++){
		cout<<c[i];
	}
}
int main(){
	int a[5]={6,13,16,20,49};
	int b[5]={1,2,3,4,8};
	merge(a,b,5);
	return 0;
}