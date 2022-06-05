#include "header.h"

void Labeling(uint8_t* input_image, uint8_t* output_image, int width, int height){
    // TODO: problem 1.5

    int currentLabel = 1; // 1에서 6까지 메기고 다시 0에서 5으로 계산
    bool existed = false;
    for(int i =0; i<height; i++) {

        for(int j =0; j<width; j++) {

            existed = false;
            // 0인 경우 어떤 라벨을 달지 판단
            if((int)input_image[width*i+j]==0) {
                // 인접한 값이 있으면 그 값으로 만들어줌
                for(int checkY=i-1; checkY<i+2; checkY++) {
                    if(checkY==-1) continue;
                    if(checkY==height) continue;
                    for(int checkX=j-1; checkX<j+2; checkX++) {
                        if(checkX==-1) continue;
                        if(checkX==width) continue;
                        if ((int)output_image[width*checkY+checkX] != 0 && (int)output_image[width*checkY+checkX] != 255) {
                            output_image[width*i+j]=output_image[width*checkY+checkX];
                            existed = true;
                            break;
                        }
                    }
                    if(existed) break;
                }
                // 인접한 값이 없으면 새로운 라벨 등록
                if(!existed) {
                    output_image[width*i+j] = currentLabel++;
                }
            } // 0이 아닌 경우 255를 넣어 주면 됨
            else {
                output_image[width*i+j]=255;
            }
        }
    }

    for(int i =height-1; i>=0; i--) {

        for(int j =width-1; j>=0; j--) {

            existed = false;
            // 0인 경우 어떤 라벨을 달지 판단
            if((int)input_image[width*i+j]==0) {
                // 인접한 값이 있으면 그 값으로 만들어줌
                for(int checkY=i+1; checkY>i-2; checkY--) {
                    if(checkY==-1) continue;
                    if(checkY==height) continue;
                    for(int checkX=j+1; checkX>j-2; checkX--) {
                        if(checkX==-1) continue;
                        if(checkX==width) continue;
                        if ((int)output_image[width*checkY+checkX] != 0 && (int)output_image[width*checkY+checkX] != 255) {
                            output_image[width*i+j]=output_image[width*checkY+checkX];
                            existed = true;
                            break;
                        }
                    }
                    if(existed) break;
                }
            } // 0이 아닌 경우 255를 넣어 주면 됨
        }
    }

    for(int i =0; i<height; i++) {
        for (int j = 0; j < width; j++) {
            if((int)input_image[width*i+j]==0) {
                output_image[width*i+j] = (output_image[width*i+j]-1)*50;
            }
            std::cout << (int)output_image[width*i+j] << " ";
        }
        std::cout << std::endl;
    }
}