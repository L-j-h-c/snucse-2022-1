#include <string>

bool IsPalindrome(std::string s) {
    // TODO: problem 1.1

    if(s.length()==0 || s.length()==1) return true;

    for(int i =0; i<s.length()-2*i-1; i++) {
        if(s[i] != s[s.length()-1-i]) return false;
    }

    return true;
}