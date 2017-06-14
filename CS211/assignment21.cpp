//CS211 - Zhang,Yuqian - Assignment21

#include<iostream>
using namespace std;


//part 1
double fraction(int a[],int n){
	double sum = a[n-2];
	for(int i=n-3;i>=0;i--){
		sum=1/sum+a[i];
	}
	return sum;
}

//part 2
int *fractionArray(int a[],int n){
	static int v[2]={a[n-2],1};
	for(int i=n-3;i>=0;i--){
		int t =v[0];
		v[0]=t*a[i]+v[1];//p
		v[1]=t;//q
	}
	return v;
}

//part 3
int *fractionRecur(int *a){
	if(*(a+1)==-1){//last postive#
        int *v=new int[2];//two element int array
        v[0]=*a;
        v[1]=1;
        return v;
	}//base case
	int *v=fractionRecur(a+1);
	int p=v[0];
	int q=v[1];
	v[0]=*a*p+q;
	v[1]=p;//recursive 
	return v;
}



int main(){
	
	int A[5]={2,5,3,4,-1};
	cout<<"The value of fraction is:"<<fraction(A,5)<<endl;
	int *b = fractionArray(A,5);
	cout<<"The p/q is:"<<b[0]<<"/"<<b[1]<<endl;
	int*c=fractionRecur(A);
	cout<<"The p/q is:"<<c[0]<<"/"<<c[1]<<endl;
	
}