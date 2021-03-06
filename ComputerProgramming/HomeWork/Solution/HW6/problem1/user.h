#ifndef PROBLEM1_USER_H
#define PROBLEM1_USER_H

#include <string>
#include <vector>
#include "product.h"
#include <map>
#include <queue>
#define DISCOUNT_RATE 0.1

class User {
public:
    User(std::string name, std::string password, bool premium);
    const std::string name;
    bool premium;
    void add_purchase_history(Product* product);
    bool matchPassword(std::string password);
    std::vector<Product*> cart;
    std::vector<Product*> finalRecommendList;
    std::vector<Product*> purchasHistory;
    std::map<std::string, int> productToCount;
    std::vector<Product*> makeRecommend();
    std::vector<Product*> makePremiumRecommend(std::vector<User*> users);
private:
    std::string password;
};

class NormalUser : public User {
public:
    NormalUser(std::string name, std::string password, bool premium);
};

class PremiumUser : public User {
public:
    PremiumUser(std::string name, std::string password, bool premium);
};

#endif //PROBLEM1_USER_H
