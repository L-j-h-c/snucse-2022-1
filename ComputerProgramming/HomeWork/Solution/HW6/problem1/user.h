#ifndef PROBLEM1_USER_H
#define PROBLEM1_USER_H

#include <string>
#include <vector>
#include "product.h"
#define DISCOUNT_RATE 0.1

class User {
public:
    User(std::string name, std::string password);
    const std::string name;
    void add_purchase_history(Product* product);
    bool matchPassword(std::string password);
private:
    std::string password;
};

class NormalUser : public User {
public:
    NormalUser(std::string name, std::string password);
};

class PremiumUser : public User {
public:
    PremiumUser(std::string name, std::string password);
};

#endif //PROBLEM1_USER_H
