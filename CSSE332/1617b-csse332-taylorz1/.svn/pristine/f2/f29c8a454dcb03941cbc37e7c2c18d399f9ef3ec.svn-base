/************************************************************
 * Program: main.c
 * Author: JP Mellor (5 Feb 2017)
 *
 * This program runs the process scheduler.
 *
 ************************************************************/

#include <stdio.h>
#include <stdlib.h>
#include "./processScheduler.h"
#include "./queue.h"

int main(int argc, char *argv[]) {
  Queue create, ready, wait;
  Node *running = 0;
  Event event;
  int count = 0;

  if (argc < 2) {
    printf("Usage: ./main <process data>\n");
  }

  create.head = NULL;
  create.tail = NULL;

  ready.head = NULL;
  ready.tail = NULL;

  wait.head = NULL;
  wait.tail = NULL;

  /* Load in process data to the create queue */
  count = loadProcessData(&create, argv[1]);
  printf("%d processes loaded\n", count);

  /* Print out the create queue */
  if (printQueue(create) != count) {
    printf("main: counts don't match\n");
  }
  printf("\n");

  setTime(0);
  /* Set FIFO scheduling - add entries to tail of queue, no timeouts
     and no preemption */
  setPriorityTest(TOTAL);
  setTimeout(0);
  setPriorityPreemption(TRUE);
  /* handle events until all processes complete */
  event = getNextEvent(&create, &ready, &wait, running);
  while (event.type != NONE) {
    handleEvent(&event, &create, &ready, &wait, &running);
    event = getNextEvent(&create, &ready, &wait, running);
  }
  printf("\nNo more events\n");

  return 0;
}
