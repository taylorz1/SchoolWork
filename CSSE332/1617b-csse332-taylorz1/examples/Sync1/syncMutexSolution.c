#include <stdio.h>
#include <pthread.h>

int count = 0;
pthread_mutex_t mymutex = PTHREAD_MUTEX_INITIALIZER;

void *inc(void *arg) {
  int i;

  for (i=0; i <= *(int *)arg; i++) {
    pthread_mutex_lock(&mymutex);
    count++;
    pthread_mutex_unlock(&mymutex);
  }

  return (void *)0;
}

void *dec(void *arg) {
  int i;

  for (i=0; i <= *(int *)arg; i++) {
    pthread_mutex_lock(&mymutex);
    count--;
    pthread_mutex_unlock(&mymutex);
  }

  return (void *)0;
}

int main(int argc, char **argv) {
  pthread_t tid[2];
  pthread_attr_t attr;
  int value = 99999;

  pthread_attr_init(&attr);

  pthread_create(&tid[0], &attr, inc, (void *)&value);
  pthread_create(&tid[1], &attr, dec, (void *)&value);

  pthread_join(tid[0], NULL);
  pthread_join(tid[1], NULL);

  printf("count = %d\n", count);
}
