#include <iostream>
#include "player.h"

void round();

int main() {

    Player a, b;
    a.add_monster(fireMon);
    a.add_monster(fireMon);
    a.add_monster(waterMon);
    b.add_monster(waterMon);
    b.add_monster(grassMon);
    b.add_monster(grassMon);

    std::cout << "Game start!" << std::endl;

    std::cout << "A's HP is "<<a.get_total_hp() << " (";
    //for(int j=0; j<a.get_num_monsters(); j++){std::cout<<*(a.get_monster()[j])<<"<"<<a.get_monster()[j]->get_hp()<<"> ";}
    std::cout << ") / B's HP is " << b.get_total_hp() << " ( ";
    //for(int j=0; j<b.get_num_monsters(); j++){std::cout<<*(b.get_monster()[j])<<"<"<<b.get_monster()[j]->get_hp()<<"> ";}
    std::cout<<")"<<std::endl;

    for (int i = 1;; i++) {
        round();
        std::cout << "Round " << i << ": " << "A's HP is "<<a.get_total_hp() << " (";
        //for(int j=0; j<a.get_num_monsters(); j++){std::cout<<*(a.get_monster()[j])<<"<"<<a.get_monster()[j]->get_hp()<<"> ";}
        std::cout << ") / B's HP is " << b.get_total_hp() << " ( ";
        //for(int j=0; j<b.get_num_monsters(); j++){std::cout<<*(b.get_monster()[j])<<"<"<<b.get_monster()[j]->get_hp()<<"> ";}
        std::cout<<")"<<std::endl;

        if (b.get_num_monsters() == 0) {
            std::cout << "Player A won the game!" << std::endl;
            break;
        } else if (a.get_num_monsters() == 0) {
            std::cout << "Player B won the game!" << std::endl;
            break;
        }
    }
    return 0;
}

void round() {
}