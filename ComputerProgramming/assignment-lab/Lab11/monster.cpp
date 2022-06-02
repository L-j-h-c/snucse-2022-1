#include "monster.h"

Monster::Monster(string name, hp_t hp, hp_t damage, MonsterType type, MonsterType critical_to)
        : name(name), hp(hp), damage(damage), type(type), critical_to(critical_to) {
    id = num_monsters++;
    speed = id*10;
}

WaterMon::WaterMon() : Monster("Squirtle", 500, 10, waterMon, fireMon) {}
FireMon::FireMon() : Monster("Charmander", 300, 20, fireMon, grassMon) {}
GrassMon::GrassMon() : Monster("Bulbasaur", 600, 10, grassMon, waterMon) {}

hp_t Monster::get_hp() const {
    return hp;
}

MonsterType Monster::get_type() const {
    return type;
}

string Monster::get_name() const {
    return name;
}

void Monster::decrease_health(hp_t attack_damage) {
    hp -= attack_damage;
    if(hp<0) hp = 0;
}

void Monster::attack(Monster *attacked_monster) {
    if (attacked_monster->get_type() == critical_to) {
        critical_attack(attacked_monster);
    } else {
        attacked_monster->decrease_health(damage);
    }
}

void Monster::critical_attack(Monster *attacked_monster) {
    std::cout<<"Normal critical"<<std::endl;
}