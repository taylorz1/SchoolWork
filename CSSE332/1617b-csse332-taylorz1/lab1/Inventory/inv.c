/* This is where you implement the core solution.
   by Zachary Taylor, 11/30/2016
   CopyRight 2016 Zachary Taylor
*/
#include "./inv.h"
int main(int argc, char** argv) {
    if (argc < 3) {
        printf("Usage input.txt output.txt\n");
        return 0;
    }
    FILE* input = fopen(argv[1], "r");
    FILE* output = fopen(argv[2], "w+");
    inventory* invs = malloc(100 * sizeof(inventory));
    int i;
    printf("Item Number\tQuantity\tPrice\tExpiration Date\n");
    while (!feof(input)) {
        fscanf(input, "%d %d %f %d/%d", &invs[i].item_num, &invs[i].quantity,
               &invs[i].price,
               &invs[i].expiration.month, &invs[i].expiration.year);
        printf("%d\t%d\t%f\t%d/%d\n", invs[i].item_num, invs[i].quantity,
               invs[i].price,
               invs[i].expiration.month,
               invs[i].expiration.year);
        if (invs[i].expiration.month > 9 && invs[i].expiration.year > 2007) {
            fprintf(output, "%d\t%d\t%f\t%d/%d\n", invs[i].item_num,
                    invs[i].quantity,
                    invs[i].price, invs[i].expiration.month,
                    invs[i].expiration.year);
        }
    }
}
