/* This is the shell you must fill in or replace in order to complete
   lab 2.  Do not forget to include your name in the initial
   comments of this file.
   CopyRight 2016 Zachary Taylor
*/

#include <string.h>
#include <stdlib.h>
#include <stdio.h>

void main(int argc, char* argv) {
    char* buf = malloc(128 * sizeof(char));
    char* out = malloc(256 * sizeof(char));
    char* str = malloc(128 * sizeof(char));


    printf("Enter the input you would like to convert\n");
    fgets(buf, 127, stdin);
    buf[strlen(buf) - 1] = '\0';
    str  = strtok(buf, " ");
    while (str != NULL) {
        out = strcat(out, str + 1);
        out = strncat(out, str, sizeof(char));
        out = strcat(out, "ay ");
        str = strtok(NULL, " ");
    }
    out[strlen(out) - 1] = '\0';
    printf("This is the translated string\n");
    printf("%s\n", out);

    free(buf);
    free(out);
    free(str);
}
