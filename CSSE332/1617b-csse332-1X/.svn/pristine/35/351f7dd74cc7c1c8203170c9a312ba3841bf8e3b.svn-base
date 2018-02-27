#include "./graphicsLib.h"

int main() {
  int x, y;

  interrupt(0x21,-2,0,0,0);

  for(y=0;y<240;y++)
    for(x=0;x<320;x++)
      drawPixel(x,y,COLOR_LTBLUE);

  drawLine(0,0,320,10,0);
  drawLine(0,0,320,20,0);
  drawLine(0,0,320,30,0);
  drawLine(0,0,320,40,0);
  drawLine(0,0,320,50,0);
  drawLine(0,0,320,60,0);
  drawLine(0,0,320,70,0);
  drawLine(0,0,320,80,0);

  for(y=0;y<SCREEN_HEIGHT;y=y+10) {
    drawLine(0,0,SCREEN_WIDTH,y,0);
  }

  drawLine(0,0,SCREEN_WIDTH,SCREEN_HEIGHT,COLOR_BLACK);

  for(x=0;x<SCREEN_WIDTH;x=x+10) {
    drawLine(0,0,x,SCREEN_HEIGHT,COLOR_BLACK);
  }

  drawRect(0,0,90,90,1);
  drawRectFilled(90,90,190,190,2);

  interrupt(0x16,0,0,0,0);
  interrupt(0x21,-3,0,0,0);
  interrupt(0x21,5,0,0,0);
}
