#include <vector>
#include "client_ui.h"
#include "product.h"
#include "user.h"

ClientUI::ClientUI(ShoppingDB &db, std::ostream& os) : UI(db, os), current_user(nullptr) { }

void ClientUI::signup(std::string username, std::string password, bool premium) {
    // TODO: Problem 1.2
    db.add_user(username, password, premium);
    os<<"CLIENT_UI: "<<username<< " is signed up.";
    os<<std::endl;
}

void ClientUI::login(std::string username, std::string password) {
    // TODO: Problem 1.2
    if(current_user != nullptr) {
        os<<"CLIENT_UI: Please logout first.";
    } else {
        User* temp = db.login(username, password);
        if (temp->name == "" && temp->matchPassword("")) {
            os<<"CLIENT_UI: Invalid username or password.";
        } else {
            current_user = temp;
            os<<"CLIENT_UI: "<< current_user->name << " is logged in.";
        }
    }
    os<<std::endl;
}

void ClientUI::logout() {
    // TODO: Problem 1.2
    if(current_user != nullptr) {
        os<<"CLIENT_UI: "<< current_user->name <<" is logged out.";
        delete(current_user);
        os<<std::endl;
    } else {
        os<<"CLIENT_UI: There is no logged-in user.";
        os<<std::endl;
    }
}

void ClientUI::buy(std::string product_name) {
    // TODO: Problem 1.2

}

void ClientUI::add_to_cart(std::string product_name) {
    // TODO: Problem 1.2

}

void ClientUI::list_cart_products() {
    // TODO: Problem 1.2.

}

void ClientUI::buy_all_in_cart() {
    // TODO: Problem 1.2

}

void ClientUI::recommend_products() {
    // TODO: Problem 1.3

}
