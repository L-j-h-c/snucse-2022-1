#include "user.h"

User::User(std::string name, std::string password, bool premium): name(name), password(password), premium(premium) {

}

bool User::matchPassword(std::string password) {
    return (password==this->password);
}

NormalUser::NormalUser(std::string name, std::string password, bool premium): User(name, password, premium) {

}

PremiumUser::PremiumUser(std::string name, std::string password, bool premium): User(name, password, premium) {

}

void User::add_purchase_history(Product* product){
    // TODO: Problem 1.2

}