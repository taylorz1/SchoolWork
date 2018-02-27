/* This is the shell you must fill in or replace in order to complete
   this project.  Do not forget to include your name in the initial
   comments of this file.

  Copyright 2016 Zachary Taylor
*/
#include <stdio.h>
#include <math.h>
int main() {
float x;
float pw;
int y;

printf("Input x\n");

scanf("%f", &x);

printf("Input y\n");
scanf("%d", &y);

pw = 1;

int i = 0;
if (y == 0) {
printf("x = 1\n");
pw = pow(x, y);
printf("pow returned %.2f\n", pw);
return 0;
}
if (y < 0) {
       int yn;
       yn = y*(-1);
       for (i = 0; i < yn; i++) {
               pw = pw / x;
        }
        printf("x = %.2f\n", pw);
        pw = pow(x, y);
        printf("pow returned %.2f\n", pw);
} else {
        for (i = 0; i < y; i++) {
               pw = pw*x;
        }
        printf("x = %.2f\n", pw);
        pw = pow(x, y);
        printf("pow returned %.2f\n", pw);
}
return 0;
}
