#include <stdio.h>
//gg first line G last ine, = format
// gg = G
void merge(int a[], int L, int mid, int R, int aux[]){
    int i = L;
    int j = mid + 1;
    int k = L;
    for(;k <= R; k++){
        if(i > mid) aux[k] = a[j++];
        else if(j > R) aux[k] = a[i++];
        else if(a[i] <= a[j]) aux[k] = a[i++];
        else aux[k] = a[j++];
    } 

    for(k = L; k <= R; k++){
        a[k] = aux[k];
    }
}

void mergeSort(int a[], int L, int R, int aux[]){
    if(L >= R) return;
    int mid = L+R/2;
    mergeSort(a, L, mid, aux);
    mergeSort(a, mid+1, R, aux);
    merge(a, L, mid, R, aux);
}

int main(){
    int a[] = {2,5,7,1,9,3};
    int aux[] = {0,0,0,0,0,0};
    mergeSort(a,0,5,aux);
    for(int i = 0; i < 6 ; i++){
        printf("%d", a[i]);
    }
}


//how to debug code in vi
//sudo apt-get install gdb
//breakpoint - b main
//run - r
