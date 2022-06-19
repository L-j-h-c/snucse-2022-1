#include "admin_ui.h"

AdminUI::AdminUI(ShoppingDB &db, std::ostream& os): UI(db, os) { }

void AdminUI::add_product(std::string name, int price) {
    // TODO: Problem 1.1
    if(price>0) {
        std::cout << "ADMIN_UI: " << name << " is added to the database." << std::endl;
        db.add_product(name, price);
    } else {
        std::cout << "ADMIN_UI: " << name << " is added to the database." << std::endl;
    }
}

void AdminUI::edit_product(std::string name, int price) {
    // TODO: Problem 1.1
    if(db.edit_product(name, price)==0) {
        std::cout << "ADMIN_UI: Invalid product name." << std::endl;
    } else if(db.edit_product(name, price)==1) {
        std::cout << "ADMIN_UI: " << name << " is modified from the database" << std::endl;
    } else {
        std::cout << "ADMIN_UI: Invalid price" << std::endl;
    }
}

void AdminUI::list_products() {
    // TODO: Problem 1.1

}
