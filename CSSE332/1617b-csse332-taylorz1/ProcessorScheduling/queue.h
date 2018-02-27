/********************************************************************* 
 * File: queue.h
 *
 * Author: JP Mellor (5 Feb 2017)
 *
 * Modified version of lab3/queue.h
 *
 * This header file defines the structure of a queue and a time queue
 * to be used for process scheduling.  It also contains the prototpyes
 * of functions used to manage the queue implemented in queue.c
 *
 *********************************************************************/

#ifndef QUEUE_QUEUE_H_
#define QUEUE_QUEUE_H_

#include <stdio.h>
#include <stdlib.h>

#define FALSE 0
#define TRUE 1

typedef int Bool;

typedef enum {
  NOPRIORITY, /* this is the same a FIFO for adding to ready queueu */
  TOTAL,
  NEXT,
} PriorityTest;

typedef struct timenode {
  char type; /* R for run time, W for wait time */
  int time;
  int start;
  int remaining;
  struct timenode *next; /* points to the next timenode */
} TimeNode;

typedef struct timequeue {
  TimeNode *head;
  TimeNode *tail;
} TimeQueue;

typedef struct node {
  int processId;
  int arrivalTime;
  int totalRemainingTime;
  TimeQueue times;
  struct node *next; /* points to the next node */
} Node;

typedef struct queue {
  Node *head;
  Node *tail;
} Queue;


void deleteQueue(Queue *queue);

Node *createNode(int processId, int arrivalTime);

Bool addTail(Queue *queue, Node *node);
Bool addArrivalTimeOrder(Queue *queue, Node *node);
void setPriorityTest(PriorityTest newPriorityTest);
Bool priorityCheck(Node *old, Node *new);
Bool addPriorityOrder(Queue *queue, Node *node);
Bool addNextRunWaitOrder(Queue *queue, Node *node);

int printQueue(Queue queue);
int queueSize(Queue queue);

Node *dequeue(Queue *queue);
void deleteNode(Node **node);

Bool addTailTime(Node *node, char type, int time);

TimeNode *dequeueTime(Node *node);
void deleteTimeNode(TimeNode **timenode);


#endif /* QUEUE_QUEUE_H_ */
