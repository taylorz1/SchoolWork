/* Copyright 2016 Zachary Taylor

Here is some code that factors in a super dumb way.  We won't be
attempting to improve the algorithm in this case (though that would be
the correct thing to do).

Modify code so that it starts the specified number of threads and
splits the computation between the threads.  You can be sure the max
allowed number of threads is 50.  Be sure your threads actually run in
paralell.

Your threads should each just print the factors they find, they don't
need to communicate the factors to the original thread.

ALSO - be sure to compile this code with -lpthead

 */
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <pthread.h>
#include <stdlib.h>

typedef struct factorin {
    int number;
    int numthread;
    int threadnumber;
} factorin;


void* runner(void* ptr) {
    factorin* ptr2 = (factorin*) ptr;
    int  num = ptr2->number;
    int  numthread = ptr2->numthread;
    int i;
    int theadnum = ptr2->threadnumber;
    for (i = 1; (theadnum + i * numthread) * (theadnum + i * numthread) <= num;
            i++) {
        if (num % (theadnum + i * numthread) == 0) {
            printf("%d, is a factor, %d\n", theadnum + i * numthread, theadnum);
        }
    }
    return NULL;
}

int main(void) {
    /* you can ignore the linter warning about this */
    unsigned long long int target, i, start = 0;
    int numThreads;
    printf("Give a number to factor.\n");
    scanf("%llu", &target);

    printf("How man threads should I create?\n");
    scanf("%d", &numThreads);
    if (numThreads > 50 || numThreads < 1) {
        printf("Bad number of threads!\n");
        return 0;
    }
    factorin* threadData = malloc(numThreads * sizeof(factorin));
    int k;
    for (k = 0; k < numThreads; k++) {
        threadData[k].numthread = numThreads;
        threadData[k].number = target;
        threadData[k].threadnumber = k;
    }
    pthread_t* tid = malloc(numThreads * sizeof(pthread_t));
    int j;
    for (j = 0; j < numThreads; j++) {
        pthread_create(&tid[j], NULL, runner, &threadData[j]);
    }
    for (j = 0; j < numThreads; j++) {
        pthread_join(tid[j], NULL);
    }

    /*for (i = 2; i <= target/2; i = i + 1) {
      /* You'll want to keep this testing line in.  Otherwise it goes so
         fast it can be hard to detect your code is running in
         paralell. Also test with a large number (i.e. > 3000) */
    /* printf("testing %llu\n", i);
     if (target % i == 0) {
       printf("%llu is a factor\n", i);
     }
    } */
    return 0;
}

