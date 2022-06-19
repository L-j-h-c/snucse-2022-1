#include "product.h"

Product::Product(std::string name, int price): name(name), price(price) {

}

std::ostream& operator << (std::ostream &os, const Product &product)  // 출력 연산자 오버로딩
{
    os << "(" << product.name << ", " << product.price << ")";

    return os;
}