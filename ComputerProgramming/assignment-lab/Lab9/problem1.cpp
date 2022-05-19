//
// Created by Junho Lee on 2022/05/12.
//
#include <iostream>
using namespace std;

#define PI 3.141519
#define AREA(r) r*r*PI

int main() {
    float radius;
    std::cin >> radius;
    cout << AREA(radius) << endl;
    return 0;
}