

int HammingDistance(int x, int y) {
    // TODO: problem 1.2

    int xArr[32], yArr[32];
    for(int i=0; i<=31; i++) {
        xArr[i] = 0;
        yArr[i] = 0;
    }

    int newX = x, newY = y;

    for(int i=0; newX>0; ) {
        xArr[i]=newX%2;
        newX/=2;
        if(newX==1) {
            xArr[i+1] = 1;
            break;
        }
    }

    for(int i=0; newY>0; ) {
        yArr[i]=newY%2;
        newY/=2;
        if(newY==1) {
            yArr[i+1] = 1;
            break;
        }
    }

    int count = 0;
    for(int i=0; i<=31; i++) {
        if(xArr[i] != yArr[i]) count++;
    }

    return count;
}

