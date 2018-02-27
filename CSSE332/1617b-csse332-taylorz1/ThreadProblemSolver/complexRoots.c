/* CopyRight 2017 Zachary Taylor */

#include "./complexRoots.h"
#include <math.h>

void findComplexRoots(int a, int b, int c) {
    double d, denom, numer;

    d = b * b - 4 * a * c;
    printf("==================================================\n");
    printf("Quadratic equation = %dx^2%+dx%+d = 0\n", a, b, c);
    if (d < 0) {
        printf("Has Complex roots.\n");
        denom = 2 * a;
        numer = -b;
        d = sqrt(-d) / denom;
        numer = numer / denom;
        printf("Roots of quadratic equation are: \n");
	printf("%.3f + %.3f i\n", numer, d);
	printf("%.3f - %.3f i\n", numer, d);
    } else {
        printf("Sorry, but the roots are not equal.\n");
    }
    printf("==================================================\n");
}

