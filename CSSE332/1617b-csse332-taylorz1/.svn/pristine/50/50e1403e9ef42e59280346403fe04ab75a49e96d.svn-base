/* Copyright 2016 Rose-Hulman Institute of Technology

 */
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <pthread.h>

void *runner(void* ptr) {
  int* num = (int*) ptr;
  printf("Hello from thread %d\n", *num);
}

int main(void) {
  int i, threadData[] = {1, 2, 3};
  pthread_t tid[3];

  for (i = 0; i < 3; i++) {
    pthread_create(&tid[i], NULL, runner, &threadData[i]);
  }
  for (i = 0; i < 3; i++) {
    pthread_join(tid[i], NULL);
  }
  printf("All threads complete\n");

  return 0;
}

