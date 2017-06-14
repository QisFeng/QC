//CS211,Yuqian Zhang
#include<iostream>
#include <cstdlib>
using namespace std;
typedef double(*FUNC)(double);
double line(double x){
	return x;
}
double square(double x){
	return x*x;
}
double cube(double x){
	return x*x*x;
}
double integrate(FUNC f,double a,double b){
	int n=100000;
	double unit =(b-a)/n;
	double area =0;
	for(int i=0;i<n;i++)
		area+=f(a+(i+0.5)*unit)*unit;
	return area;
}
int main(){
	cout<<"The integral of f(x)=x between 1 and 5 is:"<<integrate(line,1,5)<<endl;
	cout<<"The integral of f(x)=x^2 between 1 and 5 is:"<<integrate(square,1,5)<<endl;
	cout<<"The integral of f(x)=x^3 between 1 and 5 is:"<<integrate(cube,1,5)<<endl;
}