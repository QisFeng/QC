//CS211 - Zhang, Yuqian
//Extra Credit - MergeSort Function
#include<iostream>
using namespace std;
//boolean function to check if an array is sorted
bool isSorted(int a[],int size){
	for(int i=1;i<size;i++)
		if (a[i]<a[i-1]) return false;
	return true;
}

void merge(int *a, int low, int high, int mid)
{
    int i, j, k, c[100];
    i = low;
    k = low;
    j = mid + 1;
    while (i <= mid && j <= high)
    {
        if (a[i] < a[j])
        {
            c[k] = a[i];
            k++;
            i++;
        }
        else
        {
            c[k] = a[j];
            k++;
            j++;
        }
    }
    while (i <= mid)
    {
        c[k] = a[i];
        k++;
        i++;
    }
    while (j <= high)
    {
        c[k] = a[j];
        k++;
        j++;
    }
    for (i = low; i < k; i++)
    {
        a[i] = c[i];
    }
}

void mergeSort(int *a, int low,int high){
    int mid = (low + high)/2;
	if (low < high){
        mergeSort(a,low,mid);
        mergeSort(a,mid+1,high);
		merge(a,low,high,mid);
    }		
	return;
	
}

int main(){
	int size;
	cout<<"Please give the size of the array:";
	cin>>size;
	int *A = new int[size];
	cout<<"Please input for the array:"<<endl;
	for(int i=0;i<size;i++){
		cin>>A[i];
	}
	cout<<"Original Array:"<<endl;
	for(int i=0;i<size;i++)
		cout<<A[i]<<" ";
	cout<<endl;
	
	if (!isSorted(A,size) )
		mergeSort(A,0,size-1);
	cout<<"Sorted Array:"<<endl;
	for(int i=0;i<size;i++)
		cout<<A[i]<<" ";
	cout<<endl;
	delete []A;
	return 0;
}