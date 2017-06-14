//CS211,Yuqian Zhang
#include <iostream>
using namespace std;
int main(){
	long long i;
	for (i=0;i*i<=2147483647;i++){
		int N = i*i;
		if (N%2==1&&(N/10)%2==1){
			cout<<"The first perfect square whose last two digits are both odd:"<<N<<endl;
			cout<<"The number who has the perfect square is:"<<i<<endl;
			return 0;
		}
	}
	cout<<"No result"<<endl;
	return 0;
}