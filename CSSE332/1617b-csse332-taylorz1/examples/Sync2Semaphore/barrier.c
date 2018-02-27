#include <stdio.h>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>
#include <stdlib.h>

#define NUMTHREADS 3


/*
  The goal of this code is to implement a "barrier".  A barrier is a
  structure that prevents threads from moving past a certian point
  until all threads are ready to be at that point.
x
  In this case, what we'd like is for all threads to work in paralell
  for Stage 1, then all threads to wait for others to finish Stage 1,
  then all threads to be in paralell as they do stage 2.

  Here's one thing that WON'T work.  You can't initialize a semaphore
  to a negative value.
  
  This can be accomplished with 2 semaphores, plus a little other
  data.  If you need a hint, I've put one below the main function.

 */   
void *doProcess(void *arg) {
  int* timeToSleep = (int*) arg;

  printf("Starting stage 1\n");
  sleep(*timeToSleep);
  printf("Finished stage 1\n");

  printf("Starting stage 2\n");
  sleep(*timeToSleep);
  printf("Finished stage 2\n");
  
  return NULL;
}

int main(int argc, char **argv) {
  pthread_t tid[NUMTHREADS];
  int timeToSleep[] = {1,0,2};
  int i;

  for(i = 0; i < NUMTHREADS; i++) {
     pthread_create(&tid[i], NULL, doProcess, &timeToSleep[i]);
  }

  for(i = 0; i < NUMTHREADS; i++) {
     pthread_join(tid[i], NULL);
  }

  
  printf("Everybody done\n");
}


/*

  Further hint: here's the names of the variables in my solution

  sem_t barrier;
  sem_t mutex;
  int currentWaiting;


*/
