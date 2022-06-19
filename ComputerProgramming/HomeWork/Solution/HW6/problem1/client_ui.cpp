#include <vector>
#include "client_ui.h"
#include "product.h"
#include "user.h"
#include "cmath"

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
        if (temp == nullptr) {
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
        current_user->cart.clear();
        current_user = nullptr;
        os<<std::endl;
    } else {
        os<<"CLIENT_UI: There is no logged-in user.";
        os<<std::endl;
    }
}

bool ClientUI::isLoggedIn() {
    if(current_user == nullptr) {
        os<<"CLIENT_UI: Please login first.";
        os<<std::endl;
        return false;
    } else return true;
}

void ClientUI::buy(std::string product_name) {
    // TODO: Problem 1.2
    if(isLoggedIn()) {
        Product* temp = db.findProduct(product_name);

        if(temp== nullptr) {
            os<< "CLIENT_UI: Invalid product name.";
        } else {
            current_user->add_purchase_history(temp);
            if(current_user->premium) {
                os << "CLIENT_UI: Purchase completed. Price: " << makeDiscount(temp->price)<<".";
            } else {
                os << "CLIENT_UI: Purchase completed. Price: " << temp->price<<".";
            }
        }

        os <<std::endl;
    }
}

void ClientUI::add_to_cart(std::string product_name) {
    // TODO: Problem 1.2
    if(isLoggedIn()) {
        Product* temp = db.findProduct(product_name);

        if(temp== nullptr) {
            os<< "CLIENT_UI: Invalid product name.";
        } else {
            current_user->cart.push_back(temp);
            os<< "CLIENT_UI: "<<temp->name<<" is added to the cart.";
        }

        os <<std::endl;
    }
}

int ClientUI::makeDiscount(int price) {
    double realPrice = price;
    realPrice *= 0.009;
    realPrice = round(realPrice);
    realPrice *= 100;
    return (int)realPrice;
}

void ClientUI::list_cart_products() {
    // TODO: Problem 1.2.
    if(isLoggedIn()) {
        std::vector products = current_user->cart;
        if(products.size()==0) {
            os << "CLIENT_UI: Products: []";
        } else {
            int size = 0;
            os << "CLIENT_UI: Cart: ";
            os<<"[";

            for(Product* p : products) {
                size++;
                if(current_user->premium) {
                    os << "(" << p->name << ", " << makeDiscount(p->price) << ")";
                } else {
                    os << "(" << p->name << ", " << p->price << ")";
                }

                if(!(size==products.size())) {
                    os<<", ";
                }
            }
            os<<"]";
            os<<std::endl;
        }
    }
}

void ClientUI::buy_all_in_cart() {
    // TODO: Problem 1.2
    if(isLoggedIn()) {
        std::vector products = current_user->cart;
        if(products.size()==0) {
            os << "ADMIN_UI: Products: []";
        } else {
            int totalPrice = 0;
            if(current_user->premium) {
                for(Product* p : products) {
                    current_user->add_purchase_history(p);
                    totalPrice += makeDiscount(p->price);
                }
            } else {
                for(Product* p : products) {
                    current_user->add_purchase_history(p);
                    totalPrice += p->price;
                }
            }
            os << "CLIENT_UI: Cart purchase completed. Total price: " << totalPrice << ".";
            os << std::endl;
        }
    }
}

void ClientUI::recommend_products() {
    // TODO: Problem 1.3
    if(isLoggedIn()) {
        if(current_user->premium) {
            std::vector<Product*> pros = current_user->makeRecommend();
            if(pros[0]== nullptr) {
                os << "CLIENT_UI: Recommended Products: []";
                os << std::endl;
            } else {
                os<<"CLIENT_UI: Recommended Products: [";
                int size = 0;
                for(Product* p : pros) {
                    if(p== nullptr) break;
                    size++;
                    os << "(" << p->name << ", " << makeDiscount(p->price) << ")";
                    if(pros[size]!= nullptr) os<<", ";
                }
                os<<"]";
                os<<std::endl;
            }
        } else {
            std::vector<Product*> pros = current_user->makeRecommend();
            if(pros[0]== nullptr) {
                os << "CLIENT_UI: Recommended Products: []";
                os << std::endl;
            } else {
                os<<"CLIENT_UI: Recommended Products: [";
                int size = 0;
                for(Product* p : pros) {
                    if(p== nullptr) break;
                    size++;
                    os << "(" << p->name << ", " << makeDiscount(p->price) << ")";
                    if(pros[size]!= nullptr) os<<", ";
                }
                os<<"]";
                os<<std::endl;
            }
        }
    }
}
