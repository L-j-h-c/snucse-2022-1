#include "Delta.h"
#include "vector"
#include "math.h"

using namespace std;

Delta::Delta() : delta_unit_(NULL), num_data_(0), data_bit_width_(0), delta_bit_width_(0) {}

Delta::~Delta() {
    if(delta_unit_){
        delete[] delta_unit_;
    }
}

void Delta::Print(std::ostream& os) const{
    os << data_bit_width_ << " ";
    os << delta_bit_width_ << " ";
    os << num_data_ << " ";
    os << base_data_ << " ";
    for(int i=0; i<num_data_-1; i++){
        os << delta_unit_[i];
    }
    os<<endl;
}

std::ostream& operator<<(std::ostream& os, const DeltaUnit& du){
    // TODO: problem 2.3
    os << "(dt " << du.delta << ")";
    return os;
}

void Delta::Encode(const char* file_name){
    // TODO: problem 2.4
    std::ifstream fin;
    fin.open(file_name);

    string s;

    int deltaStandard;
    vector<int> tmpIntVec;

    int count = 0;
    int lastNum = 0;
    while (true) {

        if(count==0) {
            getline(fin, s);
            data_bit_width_ = std::stoi(s);
            count++;
            continue;
        }
        if(count==1) {
            getline(fin, s);
            count++;
            continue;
        }
        if(count==2) {
            getline(fin, s);
            deltaStandard = std::stoi(s);
            base_data_=deltaStandard;
            lastNum = deltaStandard;
            count++;
            continue;
        }

        getline(fin, s);
        if(fin.eof()) {
            break;
        } else {
            tmpIntVec.push_back(std::stoi(s)-lastNum);
            lastNum = std::stoi(s);
        }
        count++;
    }

    vector<int>::iterator itor = tmpIntVec.begin();

    count = 0;
    int tmpBit = 0;

    delta_unit_= new DeltaUnit[tmpIntVec.size()];
    num_data_ = tmpIntVec.size()+1;
    for (; itor != tmpIntVec.end(); itor++) {
        if(abs(*itor)>tmpBit) tmpBit=abs(*itor);
        delta_unit_[count++].delta = *itor;
    }
    delta_bit_width_ = ceil(log2(tmpBit+1))+1;

    fin.close();
}

double Delta::Evaluate(const char* file_name) {
    // TODO: problem 2.5

    return 0;
}
