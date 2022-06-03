#include "header.h"

void MergeArrays(int* arr1, int len1, int* arr2, int len2) {
    // TODO: problem 1.3
    int middleArr[len1+len2] ;
    for(int i=0,j=0; i+j<len1+len2;) {
        if(i==len1) {
            middleArr[i+j]=arr2[j];
            j++;
            if(i+j==len1+len2) {
                middleArr[i+j]=arr2[j];
                break;
            }
            continue;
        }
        if(j==len2) {
            middleArr[i+j]=arr1[i];
            i++;
            if(i+j==len1+len2) {
                middleArr[i+j]=arr2[i];
                break;
            }
            continue;
        }
        if(arr1[i]>=arr2[j]) {
            middleArr[i+j] = arr1[i];
            i++;
        } else {
            middleArr[i+j] = arr2[j];
            j++;
        }
    }

    int count=0;
    for(int i : middleArr) {
        arr1[count]=i;
        count++;
        if(count==len1+len2) break;
    }
}

