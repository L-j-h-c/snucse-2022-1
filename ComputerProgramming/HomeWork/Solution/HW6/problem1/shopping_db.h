#ifndef PROBLEM1_SHOPPING_DB_H
#define PROBLEM1_SHOPPING_DB_H

#include <string>
#include <vector>
#include "user.h"
#include "product.h"

class ShoppingDB {
public:
    ShoppingDB();
    void add_product(std::string name, int price);
    int edit_product(std::string name, int price);
    std::vector<Product*> list_products();
    void add_user(std::string username, std::string password, bool premium);
    User* login(std::string username, std::string password);
    Product* findProduct(std::string name);
    std::vector<User*> getUsers();
private:
    std::vector<User*> users;
    std::vector<Product*> products;
};

#endif //PROBLEM1_SHOPPING_DB_H
