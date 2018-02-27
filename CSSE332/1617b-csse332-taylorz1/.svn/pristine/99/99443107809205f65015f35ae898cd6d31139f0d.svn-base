#include <stdio.h>
#include <pthread.h>
#include <stdbool.h>
#include <unistd.h>

int count = 0;

bool flag[2];
int turn;

//call this process 0
void *inc(void *arg) {
  int i;
  int myNumber = 0;
  int otherNumber = 1;

  for (i=0; i <= *(int *)arg; i++) {

    flag[myNumber] = true;
    turn = otherNumber;
    while(flag[otherNumber] && turn == otherNumber)
    {
      //busy wait
    }
    count++;
    flag[myNumber] = false;
  }

  return (void *)0;
}

//call this process 1
void *dec(void *arg) {
  int i;

  int myNumber = 1;
  int otherNumber = 0;

  for (i=0; i <= *(int *)arg; i++) {

    flag[myNumber] = true;
    turn = otherNumber;
    while(flag[otherNumber] && turn == otherNumber)
    {
      //busy wait
    }

    count--;

    flag[myNumber] = false;
    
  }

  return (void *)0;
}

int main(int argc, char **argv) {
  pthread_t tid[2];
  pthread_attr_t attr;
  int value = 99999;

  flag[0] = false;
  flag[1] = false;
  
  
  pthread_attr_init(&attr);

  pthread_create(&tid[0], &attr, inc, (void *)&value);
  pthread_create(&tid[1], &attr, dec, (void *)&value);

  pthread_join(tid[0], NULL);
  pthread_join(tid[1], NULL);

  printf("count = %d\n", count);
}
