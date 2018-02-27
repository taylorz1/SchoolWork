/* User Graphics Library
   This is a quick and dirty graphics library for simple shapes.
   Written by Tucker Osman
*/

#define VGA_SEG 0xA000

void drawPixel(int x, int y, int color) {
  putInMemory(VGA_SEG,y*320+x,color);
  return;
}

/* Taken from http://www.brackeen.com/vga/shapes.html */
void drawLine(int sx, int sy, int ex, int ey, char color) {
  int i,dx,dy,sdx,sdy,dxabs,dyabs,x,y,px,py;
  dx=ex-sx;
  dy=ey-sy;
  dxabs=abs(dx);
  dyabs=abs(dy);
  sdx=sgn(dx);
  sdy=sgn(dy);
  x=dyabs>>1;
  y=dxabs>>1;
  px=sx;
  py=sy;

  if (dxabs>=dyabs) {
    for(i=0;i<dxabs;i++) {
      y+=dyabs;
      if (y>=dxabs) {
        y-=dxabs;
        py+=sdy;
      }
      px+=sdx;
      drawPixel(px,py,color);
    }
  } else {
    for(i=0;i<dyabs;i++) {
      x+=dxabs;
      if (x>=dyabs) {
        x-=dyabs;
        px+=sdx;
      }
      py+=sdy;
      drawPixel(px,py,color);
    }
  }
}

void drawRect(int sx, int sy, int ex, int ey, char color) {
  drawLine(sx, sy, ex, sy, color);
  drawLine(ex, sy, ex, ey, color);
  drawLine(ex, ey, sx, ey, color);
  drawLine(sx, ey, sx, sy, color);
  return;
}

void drawRectFilled(int sx, int sy, int ex, int ey, char color) {
  int x;
  drawRect(sx, sy, ex, ey, color);
  for(x=sx;x<=ex;x++) {
    drawLine(x, sy, x, ey, color);
  }
  return;
}

/* Fast abs by Tucker */
int abs(int x) {
  return x>0?x:-x;
}

/* From http://stackoverflow.com/a/1903975/1461223*/
int sgn(int x) {
  return (x > 0) - (x < 0);
}
