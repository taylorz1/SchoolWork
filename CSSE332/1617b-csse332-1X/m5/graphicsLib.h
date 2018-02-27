/* Graphic Functions */
void drawPixel(int x, int y, char color);
void drawLine(int sx, int sy, int ex, int ey, char color);
void drawRect(int sx, int sy, int ex, int ey, char color);
void drawRectFilled(int sx, int sy, int ex, int ey, char color);

/* Simple Math Functions */
int abs(int x);
int sgn(int x);

#define SCREEN_HEIGHT 200
#define SCREEN_WIDTH 320

#define COLOR_BLACK 0
#define COLOR_LTBLUE 3
