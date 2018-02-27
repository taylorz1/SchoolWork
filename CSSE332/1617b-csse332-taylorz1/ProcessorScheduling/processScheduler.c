/************************************************************
 * Program: processScheduler.c
 * Author: JP Mellor (5 Feb 2017)
 *
 * This file defines several functions useful in implementing a
 * process scheduler.
 *
 ************************************************************/

#include <stdio.h>
#include "./processScheduler.h"
#include "./queue.h"

int time = 0;
int timeout = -1;
int interval = -1;
Bool priorityPreemption = FALSE;

int loadProcessData(Queue *queue, char *filename) {
  FILE  *inFile = NULL;
  int count = 0;
  int processId, arrivalTime;
  Node *node;
  char type;
  int time;
  char nextChar;
  int rtn;

  if ((inFile = fopen(filename, "r")) == NULL) {
    printf("loadProcessData: Unable to open input file\n");
    return 0;
  }

  while (!feof(inFile)) {
    fscanf(inFile, "%d %d ", &processId, &arrivalTime);
    node = createNode(processId, arrivalTime);

    nextChar = ' ';
    rtn = 3;
    while ((rtn == 3) && (nextChar != '\n')) {
      rtn = fscanf(inFile, "%c %d%c\n", &type, &time, &nextChar);
      addTailTime(node, type, time);
    }

    addArrivalTimeOrder(queue, node);
    count++;
  }

  fclose(inFile);

  return count;
}

void setTime(int newTime) {
  time = newTime;
}

int getTime(void) {
  return time;
}

void setTimeout(int newInterval) {
  interval = newInterval;

  if (newInterval <= 0) {
    timeout = -1;
  } else {
    timeout = time + interval;
  }

  return;
}

void updateTimeout(void) {
  timeout += interval;
}

void setPriorityPreemption(Bool newPriorityPreemption) {
  priorityPreemption = newPriorityPreemption;
}

Bool preemptRunningPriority(Queue *ready, Node *running) {
  Bool rtn = FALSE;

  if (priorityPreemption) {
    rtn = priorityCheck(running, ready->head);
  }

  return rtn;
}

Event getNextEvent(Queue *create, Queue *ready, Queue *wait, Node *running) {
  Event event;
  Node *node;
  TimeNode *timenode;
  int eventTime;

  event.type = NONE;
  event.time = -1;

  if (create == NULL) {
    printf("getNextEvent: No create queue provided\n");
  } else if (create->head &&
	     ((event.type == NONE) ||
	      (create->head->arrivalTime < event.time))) {
    event.type = CREATE;
    event.time = create->head->arrivalTime;
  }

  if (ready == NULL) {
    printf("getNextEvent: No ready queue provided\n");
  } else if (ready->head && running && preemptRunningPriority(ready, running)) {
    event.type = PRIORITY;
    event.time = time;
  }

  if (wait == NULL) {
    printf("getNextEvent: No wait queue provided\n");
  } else if ((node = wait->head) != NULL) {
    timenode = node->times.head;
    eventTime = timenode->start + timenode->remaining;
    if ((event.type == NONE) || (time < event.time)) {
      event.type = READY;
      event.time = eventTime;
    }
  }

  if (running) {
    timenode = running->times.head;
    eventTime = timenode->start + timenode->remaining;
    if ((event.type == NONE) || (eventTime < event.time)) {
      if (timenode->next) {
	event.type = WAIT;
      } else {
	event.type = EXIT;
      }
      event.time = eventTime;
    }
  }

  if ((timeout > 0) && running) {
    if ((event.type == NONE) || (timeout < event.time)) {
      event.type = TIMEOUT;
      event.time = timeout;
      updateTimeout();
    }
  }

  return event;
}

void printEvent(Event *event) {
  switch(event->type) {
  case CREATE:
    printf("Create @ ");
    break;

  case READY:
    printf("Ready @ ");
    break;
      
  case WAIT:
    printf("Wait @ ");
    break;
      
  case EXIT:
    printf("Exit @ ");
    break;

  case PRIORITY:
    printf("Preempt @ ");
    break;

  case TIMEOUT:
    printf("Preempt @ ");
    break;

  case NONE:
    printf("None @ ");
    break;

  default:
    printf("Unknown @ ");
    break;
  }
  printf("%d\n", event->time);

  return;
}

void createProcess(Queue *create, Queue *ready) {
  Node *node;

  node = dequeue(create);
  addPriorityOrder(ready, node);
  printf("Admitted pid %d @ %d\n", node->processId, time);

  return;
}

void readyProcess(Queue *wait, Queue *ready) {
  Node *node;
  TimeNode *timenode;

  node = dequeue(wait);
  timenode = dequeueTime(node);

  deleteTimeNode(&timenode);
  addPriorityOrder(ready, node);
  printf("Wait complete pid %d @ %d\n", node->processId, time);

  return;
}

void waitProcess(Node **running, Queue *wait) {
  Node *node;
  TimeNode *timenode;

  node = *running;
  timenode = dequeueTime(node);

  node->totalRemainingTime -= timenode->remaining;
  deleteTimeNode(&timenode);
  node->times.head->start = time;
  addNextRunWaitOrder(wait, node);
  *running = NULL;
  printf("Blocked pid %d @ %d\n", node->processId, time);

  return;
}

void exitProcess(Node **running) {
  Node *node;
  TimeNode *timenode;

  node = *running;
  timenode = dequeueTime(node);

  node->totalRemainingTime -= timenode->remaining;
  deleteTimeNode(&timenode);
  printf("Completed pid %d @ %d\n", node->processId, time);
  deleteNode(running);

  return;
}

void preemptProcess(Node **running, Queue *ready) {
  Node *node;

  node = *running;

  /*
   * Write code that will properly preempt the running process, for
   * example, when a timer interrupt is received (i.e. TIMEOUT) or a
   * process at the head of the ready queue has a higher priority
   * than that of the running process.  The currently running process
   * should be preempted and placed in the ready queue.  Be sure to
   * update the statistics associated with the process.
   *
   * Note: the process at the head of the ready queue will be
   * dispatched in handleEvent.
   *
   */

  
  printf("Preempted pid %d @ %d\n", node->processId, time);
  
  addTail(ready, node); /* produces a close output but for some reason still loops forever when you
  use addPriorityOrder as other methods do */

  *running = '\0';
  return;
}

Node *nextProcess(Queue *ready) {
  Node *node;
  TimeNode *timenode;

  node = dequeue(ready);
  if (node) {
    timenode = node->times.head;
  
    timenode->start = time;
  }

  return node;
}

void handleEvent(Event *event, Queue *create, Queue *ready, Queue *wait,
		 Node **running) {
  setTime(event->time);

  switch(event->type) {
  case CREATE:
    createProcess(create, ready);
    break;

  case READY:
    readyProcess(wait, ready);
    break;

  case WAIT:
    waitProcess(running, wait);
    break;

  case EXIT:
    exitProcess(running);
    break;

  case PRIORITY:
  case TIMEOUT:
    preemptProcess(running, ready);
    break;

  case NONE:
    break;
  }

  if (*running == NULL) {
    if (ready->head) {
      *running = nextProcess(ready);
      if (*running) {
	printf("Dispatched pid %d @ %d\n", (*running)->processId, time);
      }
    } else {
      printf("No processes to run @ %d\n", time);
    }
  }
}
