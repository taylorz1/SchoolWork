/* File: processScheduler.h
 *
 * Author: JP Mellor (5 Feb 2017)
 *
 * This header file defines the structure of a queue and a time queue
 * to be used for process scheduling.  It also contains the prototpyes
 * of functions used to manage the queue implemented in queue.c
 */
#ifndef PROCESSSCHEDULER_PROCESSSCHEDULER_H_
#define PROCESSSCHEDULER_PROCESSSCHEDULER_H_

#include "./queue.h"

typedef enum {
  CREATE,
  READY,
  WAIT,
  EXIT,
  PRIORITY,
  TIMEOUT,
  NONE,
} EventType;

typedef struct {
  EventType type;
  int time;
} Event;
  
int loadProcessData(Queue *queue, char *filename);

void setTime(int newTime);
int getTime(void);
void setTimeout(int newInterval);
void updateTimeout(void);
void setPriorityPreemption(Bool newPriorityPreemption);
Bool preemptRunningPriority(Queue *ready, Node *running);
Event getNextEvent(Queue *create, Queue *ready, Queue *wait, Node *running);
void printEvent(Event *event);

void createProcess(Queue *create, Queue *ready);
void readyProcess(Queue *wait, Queue *ready);
void waitProcess(Node **running, Queue *wait);
void exitProcess(Node **running);
void preemptProcess(Node **running, Queue *ready);

Node *nextProcess(Queue *ready);

void handleEvent(Event *event, Queue *create, Queue *ready, Queue *wait,
		 Node **running);

#endif /* PROCESSSCHEDULER_PROCESSSCHEDULER_H_ */
