//
// Created by Junho Lee on 2022/05/19.
//
#include <iostream>
using namespace std;

void three_swap(int *a, int *b, int *c) {
    int tmp;
    tmp = *a;
    *a = *b;
    *b = *c;
    *c = tmp;
}

void three_swap(int &a, int &b, int &c) {
    int tmp;
    tmp = a;
    a = b;
    b = c;
    c = tmp;
}

int main() {
    int a,b,c;
    cin >> a >> b >> c;
    three_swap(&a, &b, &c);
    cout << a << b << c << endl;
    three_swap(a, b, c);
    cout << a << b << c << endl;
    return 0;
}