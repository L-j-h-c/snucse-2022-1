#include "admin_ui.h"

AdminUI::AdminUI(ShoppingDB &db, std::ostream& os): UI(db, os) { }

void AdminUI::add_product(std::string name, int price) {
    // TODO: Problem 1.1
    if(price>0) {
        os << "ADMIN_UI: " << name << " is added to the database." << std::endl;
        db.add_product(name, price);
    } else {
        os << "ADMIN_UI: " << name << " is added to the database." << std::endl;
    }
}

void AdminUI::edit_product(std::string name, int price) {
    // TODO: Problem 1.1
    if(db.edit_product(name, price)==0) {
        os << "ADMIN_UI: Invalid product name." << std::endl;
    } else if(db.edit_product(name, price)==1) {
        os << "ADMIN_UI: " << name << " is modified from the database." << std::endl;
    } else {
        os << "ADMIN_UI: Invalid price" << std::endl;
    }
}

void AdminUI::list_products() {
    // TODO: Problem 1.1
    std::vector products = db.list_products();
    if(products.size()==0) {
        os << "ADMIN_UI: Products: []";
    } else {
        int size = 0;
        os << "ADMIN_UI: Products: ";
        os<<"[";
        for(Product* p : products) {
            os<<*p;
            size++;
            if(!(size==products.size())) {
                os<<", ";
            }
        }
        os<<"]";
        os<<std::endl;
    }
}
