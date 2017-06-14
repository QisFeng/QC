//CS211,Yuqian Zhang
#include <iostream>
#include <cstdlib>
using namespace std;
typedef char box[5][7];
box bb,wb,wq,bq;
box* board[8][8];

bool ok(int a[], int c){
	for (int i = 0; i<c; i++){
		if (a[c] == a[i] || (c - i) == abs(a[c] - a[i]))
			return false;//whether column c passes the row and diagonal tests
	}
	return true;
}

void backtrack(int &col){
	--col;
	if (col == -1){
		system("PAUSE");
		exit(1);
	}
}//backtrack

void print(int counter,int q[]){
	cout << "Solution #" << counter<< ":" << endl;
	//converting the queen in array into the wq and bq on chessboard
	int i, j,k,l;
	for (i = 0; i<8; i++){
		if ((q[i] + i) % 2 == 0) board[q[i]][i] =&bq;
		else board[q[i]][i] = &wq;
	}
	// print the board via the pointers in array board

	// first print upper border
	cout << " ";
	for (i = 0; i<7 * 8; i++)
		cout << '_';
	cout << endl;

	// now print the board
	for (i = 0; i<8; i++){
		for (k = 0; k<5; k++){
			cout << " " << char(179); //print left border
			for (j = 0; j<8; j++)
				for (l = 0; l<7; l++)
					cout << (*board[i][j])[k][l];
			cout << char(179) << endl; // at end of line print bar and then newline
		}
	}

	//before exiting print lower border
	cout << " ";
	for (i = 0; i<7 * 8; i++)
		cout << char(196);
	cout << endl;
	//fill board with pointers to bb and wb in alternate positions
	for (i = 0; i < 8; i++){
		for (j = 0; j < 8; j++){
			if ((i + j) % 2 == 0)
				board[i][j] = &bb;
			else
				board[i][j] = &wb;
		}
	}
}

int main(){
	int q[8] = { 0 }, count = 0, c = 0;
	int i, j;

	//fill in bb=black box and wb=whitebox
	for (i = 0; i<5; i++){
		for (j = 0; j<7; j++){
			wb[i][j] = ' ';
			bb[i][j] = char(219);
		}
	}
	//fill in wq=white queen
	for (i = 0; i<7; i++){
		wq[0][i] = ' ';
		wq[4][i] = ' ';
	}
	for (i = 0; i<7; i++){
		if (i % 2 == 1){
			wq[1][i] = char(219);
			wq[2][i] = char(219);
		}
		else {
			wq[1][i] = ' ';
			wq[2][i] = ' ';
		}
	}
	for (i = 0; i<7; i++){
		if (i == 0 || i == 6) wq[3][i] = ' ';
		else wq[3][i] = char(219);
	}
	//fill in bq=black queen
	for (i = 0; i<7; i++){
		bq[0][i] = char(219);
		bq[4][i] = char(219);
	}
	for (i = 0; i<7; i++){
		if (i % 2 == 1){
			bq[1][i] = ' ';
			bq[2][i] = ' ';
		}
		else {
			bq[1][i] = char(219);
			bq[2][i] = char(219);
		}
	}
	for (i = 0; i<7; i++){
		if (i == 0 || i == 6) bq[3][i] = char(219);
		else bq[3][i] = ' ';
	}
	//fill board with pointers to bb and wb in alternate positions
	for (i = 0; i<8; i++){
		for (j = 0; j<8; j++){
			if ((i + j) % 2 == 0)
				board[i][j] = &bb;
			else
				board[i][j] = &wb;
		}
	}

	while (true){ //go through all the columns (NextCol)
		c++;
		if (c == 8){
			print(++count, q);
			backtrack(c);
		}
		else q[c] = -1;

		while (true){//go through all the rows (NextRow)
			q[c]++;
			if (q[c] == 8){
				backtrack(c);
				continue;//continue the loop
			}//end if
			if (ok(q, c)){ //call pass function to test for column c.
				break; //go out of the loop that go through all the rows and continue to the top of the while loop that goes through all the columns(goto NextCol)
			}
		}//end row while
	}//end col while
}