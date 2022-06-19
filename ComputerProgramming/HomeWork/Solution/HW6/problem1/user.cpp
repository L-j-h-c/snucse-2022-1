#include "user.h"

User::User(std::string name, std::string password): name(name), password(password) {

}

bool User::matchPassword(std::string password) {
    return (password==this->password);
}

NormalUser::NormalUser(std::string name, std::string password): User(name, password) {

}

PremiumUser::PremiumUser(std::string name, std::string password): User(name, password) {

}

void User::add_purchase_history(Product* product){
    // TODO: Problem 1.2

}