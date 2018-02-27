/************************************************************
 * Program: queue.c
 *
 * Author: JP Mellor (5 Feb 2017)
 *
 * Modified version of lab3/queue.c
 *
 * This file defines several functions useful in manipulating
 * queues.
 *
 ************************************************************/

#include <stdio.h>
#include <stdlib.h>
#include "./queue.h"

int priorityTest = NOPRIORITY;

void deleteQueue(Queue *queue) {
  Node *node;
  
  if (queue == NULL) {
    printf("deleteQueue: No queue provided\n");
    return;
  }

  if (queue->head == NULL) {
    printf("deleteQueue: Queue is already empty.\n");
    return;
  }

  while (queue->head != NULL) {
    node = queue->head;
    queue->head = queue->head->next;
    free(node);
  }

  queue->head = NULL;
  queue->tail = NULL;

  return;
}

Node *createNode(int processId, int arrivalTime) {
  Node *node;

  if ((node = (Node *)malloc(sizeof(Node))) == NULL) {
    printf("createNode: Can't malloc node\n");
    return node;
  }

  node->processId = processId;
  node->arrivalTime = arrivalTime;
  node->totalRemainingTime = 0;
  node->times.head = NULL;
  node->times.tail = NULL;
  node->next = NULL;

  return node;
}

Bool addTail(Queue *queue, Node *node) {
  if (queue == NULL) {
    printf("addTail: No queue provided\n");
    return FALSE;
  }

  if (node == NULL) {
    printf("addTail: No node provided\n");
    return FALSE;
  }

  if (queue->tail) {
    queue->tail->next = node;
    queue->tail = node;
  } else {
    queue->head = node;
    queue->tail = node;
    node->next = NULL;
  }

  return TRUE;
}

/* You can define a multi-line parameratized macro as follows */
#define ADDNODELOGIC(test)   \
  if (test) {                \
    do_option_one();         \
  } else {                   \
    do_option_two();         \
  }

Bool addArrivalTimeOrder(Queue *queue, Node *node) {

  if (queue == NULL) {
    printf("addArrivalTimeOrder: No queue provided\n");
    return FALSE;
  }

  if (node == NULL) {
    printf("addArrivalTimeOrder: No node provided\n");
    return FALSE;
  }

  /* This does not work correctly.
   *
   * You should add code that adds the node in the proper spot.
   * 
   * If next is a node in the queue then node should be added before
   * next if next->arrivalTime <= node->arrivalTime is false.
   *
   * Note the code requred in addArrivalTimeOrder, addPriorityOrder
   * and addNextRunWaitOrder is the same except for the test
   * condition.  You may simply copy the code in all three places or
   * you may use a helper function.
   *
   */
  if (queueSize(*queue) == 0) {
	addTail(queue, node);
   } else {
   Node* temp = queue->head;
   Node* tempparent = queue->head;
   	while(temp != queue->tail) {
	   if (!(temp->arrivalTime <= node->arrivalTime)) {
		if (temp == queue->head) {
			queue->head = node;
			node->next = temp;
		} else {
			tempparent->next = node;
			node->next = temp;
		}
	   }
	   tempparent = temp;
	   temp = temp->next;
   	}
        if(!(temp->arrivalTime <= node->arrivalTime)) {
		queue->head = node;
		node->next = temp;
	} else {
		addTail(queue,node);
	}
   }

  return TRUE;
}

void setPriorityTest(PriorityTest newPriorityTest) {
  priorityTest = newPriorityTest;
}

Bool priorityCheck(Node *old, Node *new) {
  Bool rtn = FALSE;

  switch(priorityTest) {
  case NOPRIORITY:
    rtn = FALSE;
    break;

  case TOTAL:
    rtn = (old->totalRemainingTime > new->totalRemainingTime);
    break;

  case NEXT:
    rtn = (old->times.head->remaining > new->times.head->remaining);
    break;
  }

  return rtn;
}

Bool addPriorityOrder(Queue *queue, Node *node) {

  if (queue == NULL) {
    printf("addPriorityOrder: No queue provided\n");
    return FALSE;
  }

  if (node == NULL) {
    printf("addPriorityOrder: No node provided\n");
    return FALSE;
  }

  /* This does not work correctly.
   *
   * You should add code that adds the node in the proper spot.
   * 
   * If next is a node in the queue then node should be added before
   * next if priorityCheck(next, node) is false.
   *
   * Note the code requred in addArrivalTimeOrder, addPriorityOrder
   * and addNextRunWaitOrder is the same except for the test
   * condition.  You may simply copy the code in all three places or
   * you may use a helper function.
   *
   */
  if (queueSize(*queue) == 0) {
	addTail(queue, node);
   } else {
   Node* temp = queue->head;
   Node* tempparent = queue->head;
   	while(temp != queue->tail) {
	   if (!(priorityCheck(temp, node))) {
		if (temp == queue->head) {
			queue->head = node;
			node->next = temp;
		} else {
			tempparent->next = node;
			node->next = temp;
		}
	   }
	   tempparent = temp;
	   temp = temp->next;
   	}
        if(!(priorityCheck(temp, node))) {
		queue->head = node;
		node->next = temp;
	} else {
		addTail(queue,node);
	}
   }

  return TRUE;
}

Bool addNextRunWaitOrder(Queue *queue, Node *node) {

  if (queue == NULL) {
    printf("addNextRunWaitOrder: No queue provided\n");
    return FALSE;
  }

  if (node == NULL) {
    printf("addNextRunWaitOrder: No node provided\n");
    return FALSE;
  }

  /* This does not work correctly.
   *
   * You should add code that adds the node in the proper spot.
   * 
   * If next is a node in the queue then node should be added before
   * next if next->times.head->remaining <= node->times.head->remaining
   * is false. This is the test condition.
   *
   * Note the code requred in addArrivalTimeOrder, addPriorityOrder
   * and addNextRunWaitOrder is the same except for the test
   * condition.  You may simply copy the code in all three places or 
   * you may use a helper function.
   *
   */
   if (queueSize(*queue) == 0) {
	addTail(queue, node);
   } else {
   Node* temp = queue->head;
   Node* tempparent = queue->head;
   	while(temp != queue->tail) {
	   if (!(temp->times.head->remaining <= node->times.head->remaining)) {
		if (temp == queue->head) {
			queue->head = node;
			node->next = temp;
		} else {
			tempparent->next = node;
			node->next = temp;
		}
	   }
	   tempparent = temp;
	   temp = temp->next;
   	}
        if(!(temp->times.head->remaining <= node->times.head->remaining)) {
		queue->head = node;
		node->next = temp;
	} else {
		addTail(queue,node);
	}
   }

  return TRUE;
}

int printQueue(Queue queue) {
  int count;
  Node *node;
  TimeNode *timenode;

  count = 0;
  node = queue.head;

  printf("Process id | Arrival Time | Total Remaining | Type "
	 "| Time | Start | Remaining\n");
  while (node != NULL) {
    count++;
    printf("% 10d | % 12d | % 15d\n", node->processId, node->arrivalTime,
	   node->totalRemainingTime);
    timenode = node->times.head;
    while (timenode != NULL) {
      printf("%43.d | %3.d%c | % 4d | % 5d | % 9d\n", 0, 0, timenode->type,
	     timenode->time, timenode->start, timenode->remaining);
      timenode = timenode->next;
    }
    node = node->next;
  }
  return count;
}

int queueSize(Queue queue) {
  int count;
  Node *node;

  count = 0;
  node = queue.head;

  while (node != NULL) {
    count++;
    node = node->next;
  }

  return count;
}

Node *dequeue(Queue *queue) {
  Node *node;

  if (queue == NULL) {
    printf("dequeue: No queue provided\n");
    return NULL;
  }

  if (queue->head == NULL) {
    printf("dequeue: Queue is empty\n");
    return NULL;
  }

  node = queue->head;
  queue->head = node->next;
  node->next = NULL;

  if (queue->head == NULL) {
    queue->tail = NULL;
  }

  return node;
}

void deleteNode(Node **node) {
  free(*node);
  *node = NULL;

  return;
}

Bool addTailTime(Node *node, char type, int time) {
  TimeQueue *times;
  TimeNode *timeNode;

  times = &(node->times);

  if ((timeNode = (TimeNode *)malloc(sizeof(TimeNode))) == NULL) {
    printf("addTailTime: Can't malloc TimeNode\n");
    return FALSE;
  }
  
  timeNode->type = type;
  timeNode->time = time;
  timeNode->start = -1;
  timeNode->remaining = time;
  timeNode->next = NULL;

  if (times->tail) {
    times->tail->next = timeNode;
    times->tail = timeNode;
  } else {
    times->head = timeNode;
    times->tail = timeNode;
  }

  if (type == 'R') {
    node->totalRemainingTime += time;
  }

  return(TRUE);
}

TimeNode *dequeueTime(Node *node) {
  TimeNode *timenode;

  if (node == NULL) {
    printf("dequeueTime: No Node provided\n");
    return NULL;
  }

  if (node->times.head == NULL) {
    printf("dequeueTime: Queue is empty\n");
    return NULL;
  }

  timenode = node->times.head;
  node->times.head = timenode->next;
  timenode->next = NULL;

  if (node->times.head == NULL) {
    node->times.tail = NULL;
  }

  return timenode;
}

void deleteTimeNode(TimeNode **timenode) {
  free(*timenode);
  *timenode = NULL;

  return;
}
