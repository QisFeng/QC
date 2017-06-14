//memorized fibonacci number
#include<iostream>
using namespace std;

long long fib(int n){
	long long static memo[100] = { 0 };
	if(n==1||n==2) 
		memo[n]=1;
	if(memo[n]==0)
		memo[n]=fib(n-1)+fib(n-2);
	return memo[n];
}

int main(){
	cout << "Inout a number:";
	int size;
	cin >> size;
	for (int i = 1; i <= size;i++)
		cout<<fib(i)<<endl;
	system("PAUSE");
	return 0;
}