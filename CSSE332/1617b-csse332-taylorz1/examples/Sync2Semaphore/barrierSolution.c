#include <stdio.h>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>
#include <stdlib.h>

#define NUMTHREADS 3

sem_t barrier;
sem_t mutex;
int currentWaiting;

void *doProcess(void *arg) {
  int* timeToSleep = (int*) arg;

  printf("Starting stage 1\n");
  sleep(*timeToSleep);
  printf("Finished stage 1\n");


  sem_wait(&mutex);
  currentWaiting++;
  amLast = currentWaiting == NUMTHREADS
  sem_post(&mutex);

  if() {
     sem_post(&barrier);
  }

  sem_wait(&barrier);
  sem_post(&barrier);
  
  printf("Starting stage 2\n");
  sleep(*timeToSleep);
  printf("Finished stage 2\n");
  
  return NULL;
}

int main(int argc, char **argv) {
  pthread_t tid[NUMTHREADS];
  int timeToSleep[] = {1,0,2};

  currentWaiting = 0;
  sem_init(&barrier, 0, 0);
  sem_init(&mutex, 0, 1);

  for(int i = 0; i < NUMTHREADS; i++) {
    pthread_create(&tid[i], NULL, doProcess, &timeToSleep[i]);
  }
  for(int i = 0; i < NUMTHREADS; i++) {
     pthread_join(tid[i], NULL);
  }

  printf("Everybody done\n");
}
