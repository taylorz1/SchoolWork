#include <stdio.h>
#include <pthread.h>
#include <semaphore.h>

int count = 0;
sem_t semaphore;

void *inc(void *arg) {
  int i;

  for (i=0; i <= *(int *)arg; i++) {
    sem_wait(&semaphore);
    count++;
    sem_post(&semaphore);
  }

  return (void *)0;
}

void *dec(void *arg) {
  int i;

  for (i=0; i <= *(int *)arg; i++) {
    sem_wait(&semaphore);
    count--;
    sem_post(&semaphore);
  }

  return (void *)0;
}

int main(int argc, char **argv) {
  pthread_t tid[2];
  pthread_attr_t attr;
  int value = 99999;

  sem_init(&semaphore, 0, 1);
  
  pthread_attr_init(&attr);

  pthread_create(&tid[0], &attr, inc, (void *)&value);
  pthread_create(&tid[1], &attr, dec, (void *)&value);

  pthread_join(tid[0], NULL);
  pthread_join(tid[1], NULL);

  printf("count = %d\n", count);
}
