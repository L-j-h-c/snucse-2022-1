#include "shopping_db.h"

ShoppingDB::ShoppingDB() {

}

void ShoppingDB::add_product(std::string name, int price) {
    // TODO: Problem 1.1
    Product* newProduct = new Product(name, price);
    products.push_back(newProduct);
}

int ShoppingDB::edit_product(std::string name, int price) {
    // TODO: Problem 1.1
    bool checker = false;

        for(Product* p : products) {
            if(p->name == name) {
                checker = true;
            }
        }

        // name이 존재하는지부터 검사. 만약 없으면 0을 반환.
        if (!checker) return 0;

        // 가격이 양수인 경우, 가격을 바꿔줌. 성공의 의미로 1을 반환
        if(price>0) {
            for(Product* p : products) {
                if(p->name == name) {
                    p->price = price;
                    return 1;
                }
            }
        }

        // 가격이 양수가 아니기 때문에, 실패의 의미인 2를 반환.
        return 2;
}

std::vector<Product*> ShoppingDB::list_products() {
    return products;
}

void ShoppingDB::add_user(std::string username, std::string password, bool premium) {
    // TODO: Problem 1.2
    if(premium) {
        PremiumUser* newUser = new PremiumUser(username, password, true);
        users.push_back(newUser);
    } else {
        NormalUser* newUser = new NormalUser(username, password, false);
        users.push_back(newUser);
    }
}

User* ShoppingDB::login(std::string username, std::string password) {

    for(User* u : users) {
        if(u->name == username && u->matchPassword(password)) {
            return u;
        }
    }

    return nullptr;
}

Product *ShoppingDB::findProduct(std::string name) {
    for(Product* p : products) {
        if(p->name == name) {
            return p;
        }
    }

    return nullptr;
}

std::vector<User*> ShoppingDB::getUsers() {
    return users;
}
