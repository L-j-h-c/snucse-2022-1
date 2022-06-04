#include "RunLength.h"
#include "vector"

using namespace std;
RunLength::RunLength() : run_length_unit_(NULL), num_data_(0), data_bit_width_(0), length_bit_width_(0) {}
RunLength::~RunLength() {
    if(run_length_unit_){
        delete[] run_length_unit_;
    }
}
void RunLength::set_data_bit_width(int data_bit_width) {
    length_bit_width_ = data_bit_width;
}

void RunLength::Print(std::ostream& os) const{
    os << data_bit_width_ << " ";
    os << length_bit_width_ << " ";
    os << num_data_ << " ";
    for(int i=0; i<num_data_; i++){
        os<<run_length_unit_[i];
    }
    os<<endl;
}

std::ostream& operator<<(std::ostream& os, const RunLengthUnit& rlu){
    // TODO: problem 2.1

    os << "(" << rlu.data << " " << rlu.length << ")";
    return os;
}

void RunLength::Encode(const char* file_name){
    // TODO: problem 2.2
    std::ifstream fin;
    fin.open(file_name);

    string s;
    vector<struct RunLengthUnit> tempRLU, subTempRLU;
    int maxLength = pow(2,length_bit_width_);

    int count = 0;
    int checkRuns = 1;
    int lastNum = -1;
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

        getline(fin, s);
        if(fin.eof()) {
            if(s.empty()) {
                struct RunLengthUnit r = {lastNum,checkRuns};
                tempRLU.push_back(r);
                break;
            }
            if(!s.empty()&&std::stoi(s) == lastNum) checkRuns++;
            struct RunLengthUnit r = {std::stoi(s),checkRuns};
            tempRLU.push_back(r);
        } else {
            if(std::stoi(s) == lastNum) checkRuns++;
            else {
                struct RunLengthUnit r = {lastNum,checkRuns};
                if(lastNum!=-1) tempRLU.push_back(r);
                checkRuns=1;
            }
        }

        lastNum = std::stoi(s);
        count++;
    }

    vector<struct RunLengthUnit>::iterator itor = tempRLU.begin();

    count = 0;

    for (; itor != tempRLU.end(); itor++) {
        if(itor->length>maxLength) {
            int initData = itor->data;
            int initLength = itor->length;
            for( ;initLength>maxLength; initLength-=maxLength) {
                struct RunLengthUnit r = {initData,maxLength};
                subTempRLU.push_back(r);
            }
            struct RunLengthUnit r = {initData,initLength};
            subTempRLU.push_back(r);
            continue;
        }
        struct RunLengthUnit r = {itor->data,itor->length};
        subTempRLU.push_back(r);
    }

    vector<struct RunLengthUnit>::iterator itor2 = subTempRLU.begin();

    count = 0;
    run_length_unit_ = new RunLengthUnit[itor2->length];
    num_data_ = itor2->length;
    for (; itor2 != subTempRLU.end(); itor2++) {
        run_length_unit_[count++] = *itor2;
    }

    fin.close();
}

double RunLength::Evaluate(const char* file_name) {
    // TODO: problem 2.5

    return 0;
}
