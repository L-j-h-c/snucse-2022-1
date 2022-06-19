#include "user.h"
#include "map"

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
    // Map에 수 증가시키기
    if(productToCount.find(product->name) == productToCount.end()) {
        productToCount[product->name] = 0;
    } else {
        productToCount[product->name]++;
    }
    purchasHistory.push_back(product);
}

std::vector<Product*> User::makeRecommend() {
    std::vector<Product*> tempRecommendList;

    for(Product* p : purchasHistory) {
        if(tempRecommendList.empty()) {
            tempRecommendList.push_back(p);
        } else {
            bool checker = false;
            for(Product* pro : tempRecommendList) {
                if(pro->name == p->name) checker = true;
            }
            if(!checker) tempRecommendList.push_back(p);
        }
    }

    for(int i = 0; i<tempRecommendList.size(); i++) {
        for(int j = 0; j<tempRecommendList.size()-i-1; j++) {
            if(productToCount[tempRecommendList[j]->name]<productToCount[tempRecommendList[j+1]->name]) {
                Product* temp = tempRecommendList[j+1];
                tempRecommendList[j+1] = tempRecommendList[j];
                tempRecommendList[j] = temp;
            }
        }
    }

    tempRecommendList.resize(3, nullptr);

        if(tempRecommendList[2] != nullptr) {
            if(productToCount[tempRecommendList[0]->name] == productToCount[tempRecommendList[2]->name]) {
                Product* temp = tempRecommendList[2];
                tempRecommendList[2] = tempRecommendList[0];
                tempRecommendList[0] = temp;
            } else if (productToCount[tempRecommendList[0]->name] == productToCount[tempRecommendList[1]->name]) {
                Product* temp = tempRecommendList[1];
                tempRecommendList[1] = tempRecommendList[0];
                tempRecommendList[0] = temp;
            }
        } else if (tempRecommendList[1] != nullptr) {
            if (productToCount[tempRecommendList[0]->name] == productToCount[tempRecommendList[1]->name]) {
                Product* temp = tempRecommendList[1];
                tempRecommendList[1] = tempRecommendList[0];
                tempRecommendList[0] = temp;
            }
        }

        return tempRecommendList;
};

std::vector<Product*> User::makePremiumRecommend(std::vector<User*> users) {
    std::vector<Product*> tempRecommendList;
    std::vector<User*> tempUsers;
    std::map<std::string, int> userToCount;
    std::map<std::string, int> userToIndex;

    for(User* u : users) {
        if(u->name == name) continue;
        tempUsers.push_back(u);
        for(Product* p : u->purchasHistory) {
            for(Product* myP : purchasHistory) {
                if(p->name == myP->name) {
                    if(userToCount.find(u->name) == productToCount.end()) {
                        productToCount[u->name] = 1;
                    } else {
                        productToCount[u->name]++;
                    }
                }
            }
        }
    }

    for(int i = 0; i<tempUsers.size(); i++) {
        for(int j = 0; j<tempUsers.size()-i-1; j++) {
            if(userToCount[tempUsers[j]->name]<userToCount[tempUsers[j+1]->name]) {
                User* temp = tempUsers[j+1];
                tempUsers[j+1] = tempUsers[j];
                tempUsers[j] = temp;
            }
        }
    }

    tempUsers.resize(3, nullptr);

    if(tempUsers[2] != nullptr) {
        if(userToCount[tempUsers[0]->name] == userToCount[tempUsers[2]->name]) {
            for(User* u : tempUsers) {
                for(int i = 0; i<users.size(); i++) {
                    if(users[i]->name==u->name) {
                        userToIndex[u->name] = i;
                        break;
                    }
                }
            }
            if(userToIndex[tempUsers[0]->name] > userToIndex[tempUsers[1]->name]) {
                User* temp = tempUsers[1];
                tempUsers[1] = tempUsers[0];
                tempUsers[0] = temp;
            }
        } else if (productToCount[tempRecommendList[0]->name] == productToCount[tempRecommendList[1]->name]) {
            Product* temp = tempRecommendList[1];
            tempRecommendList[1] = tempRecommendList[0];
            tempRecommendList[0] = temp;
        }
    } else if (tempRecommendList[1] != nullptr) {
        if (productToCount[tempRecommendList[0]->name] == productToCount[tempRecommendList[1]->name]) {
            Product* temp = tempRecommendList[1];
            tempRecommendList[1] = tempRecommendList[0];
            tempRecommendList[0] = temp;
        }
    }

    return tempRecommendList;
};