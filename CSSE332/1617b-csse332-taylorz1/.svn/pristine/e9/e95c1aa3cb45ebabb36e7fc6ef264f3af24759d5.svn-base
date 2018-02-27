/* CopyRight 2017 Zachary Taylor */
#include "./realRoots.h"
#include <math.h>

void findRealRoots(int a, int b, int c) {
    double d, root1, root2;
    d = b * b - 4 * a * c;
    printf("==================================================\n");
    printf("Quadratic equation = %dx^2%+dx%+d = 0\n", a, b, c);
    if (d >  0) {
        printf("Has real number roots.\n");
        root2 = (-b + sqrt(d)) / (2 * a);
        root1 = (-b - sqrt(d)) / (2 * a);
        printf("Roots of quadratic equation are  %.3f and %.3f\n",
               root1, root2);
    } else {
        printf("Sorry, but the roots are not real.\n");
    }
    printf("==================================================\n");
}
