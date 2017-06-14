//NonRecursive Towers of Hanoi
//CS211 - Zhang, Yuqian
#include<iostream>
#include<vector>
using namespace std;
int main(){
	vector<int>t[3];
	int n, candidate, to, from, move = 0;
	cout << "Please give a number of rings to move:";
	cin >> n;

	//initialize and setting 3 Towers
	for (int i = n + 1; i >= 1; i--)
		t[0].push_back(i);
	t[1].push_back(n + 1);
	t[2].push_back(n + 1);

	//initialize the tower and candidate
	from = 0;
	if (n % 2 == 1)
		to = 1;
	else to = 2;
	candidate = 1;
	
	//non-recursive while loop
	while (t[1].size() < n + 1){
		cout << "move number " << ++move << ": Transfer ring" << candidate << " from Tower " << char(from + 65) << " to Tower" << char(to + 65) << endl;
		t[to].push_back(candidate);
		t[from].pop_back();
		//compare left & right towers to find get next "from tower"
		if (t[(to + 1) % 3].back() < t[(to + 2) % 3].back())
			from = (to + 1) % 3;
		else from = (to + 2) % 3;
		//set the next candidate
		candidate = t[from].back();
		//get next "to tower"
		if (n % 2 == 1){
			if (candidate % 2 == 1)
				to = (from + 1) % 3;
			else to = (from + 2) % 3;
		}
		else {
			if (candidate % 2 == 1)
				to = (from + 2) % 3;
			else to = (from + 1) % 3;
		}
	}
}