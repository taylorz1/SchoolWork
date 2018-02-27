/* Copyright 2016 Rose-Hulman

   But based on idea from http://cnds.eecs.jacobs-university.de/courses/caoslab-2007/
   Copyright 2016 Zachary Taylor
 */
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>


int main() {
    char command[82];

    //   infinite loop but ^C quits
    while (1) {
        printf("%% ");
        fgets(command, 82, stdin);
        // note that for the zombie step you'll want to use strcmp but
        // remember that fgets will return the \n as part of the result
        // so compare with "zombie\n"
        int j = 0;
        int k = 0;
        j = ('B' == command[0]);
        k = ('G' == command[1]);
        if (j & k) {
            int nchild = fork();
            if (nchild == -1) {
                printf("Fork failed!");
                continue;
            }
            if (nchild == 0) {
                int nchild2 = fork();
                if (nchild2 == -1) {
                    printf("The child's fork failed!");
                    exit(0);
                    continue;
                }
                if (nchild2 == 0) {
                    system(command + 2);
                    exit(0);
                }
                if (nchild2 != 0) {
                    wait(&nchild2);
                    printf("Background command finished\n");
                    exit(0);
                }
            } else {
            }
        } else {
            waitpid(-1, NULL, WNOHANG);
            system(command);
        }
    }
}
