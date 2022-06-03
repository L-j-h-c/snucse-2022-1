#include "header.h"

int* PascalTriangle(int N) {
    // TODO: problem 1.4
    int* arr = new int[N-1];
    int* brr = new int[N-1];
    arr[0]=1;
    brr[0]=1;
    for(int i=0; i<N; i++) {
        for(int j=0; j<=i; j++) {
            if(j==0) {
                brr[j]=1;
                continue;
            }
            if(j==i) {
                brr[j]=1;
                break;
            }
            brr[j] = arr[j-1] + arr[j];
        }
        for(int j=0; j<=i; j++) {
            arr[j] = brr[j];
        }
    }
    return arr;
}

