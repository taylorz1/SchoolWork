#include <stdio.h>
#include "CuTest.h"

/*
Copyright 2016 Rose-Hulman Institute of Technology
*/


/*
  Determines the position of the last 4 in the given array range.
  Note that end is not inclusive.

  Does not return a value.  Instead modifies the output parameter to
  point to the position of the 4.

  Use the given values as pointers, NOT arrays.  Remember - this is
  for practice!
  
  If the list does not contain a 4 output should be set to NULL.

  See the test case for examples.
 */

void last4(int* start, int* end, int** outputPosition) {
  
}

void test_last4(CuTest *tc) {

  int input[] = {4,0,0,4,4,0,4};
  int* outputPos;

  //input is {4,0,0}
  last4(&input[0],&input[2],&outputPos);
  CuAssertPtrEquals(tc, &input[0], outputPos);

  //input is {4,0}
  last4(&input[0],&input[1],&outputPos);
  CuAssertPtrEquals(tc, &input[0], outputPos);

  //input is {4,0,0,4,4,0,4}
  last4(&input[0],&input[7],&outputPos);
  CuAssertPtrEquals(tc, &input[6], outputPos);

  //input is {4,0,0,4,4,0}
  last4(&input[0],&input[6],&outputPos);
  CuAssertPtrEquals(tc, &input[4], outputPos);

  //input is {}
  last4(&input[0],&input[0],&outputPos);
  CuAssertPtrEquals(tc, NULL, outputPos);

  //input is {0,0}
  last4(&input[1],&input[3],&outputPos);
  CuAssertPtrEquals(tc, NULL, outputPos);
  
}



struct LinkedListNode {
  int data;
  struct LinkedListNode* next;
};

/*
  Returns 1 of 3 values

  0 if the linked lists are not equal to each other
  1 if the linked lists are equal and distinct in memory
  2 if the linked lists are equal but NOT distinct in memory

  Note that it is possible for two linked lists to be partly distinct.
  That is they start out with equal and distint nodes, but then
  eventually the lists merge into the same (non-distint) list.  In
  this case, return 2.

  If both lists are empty (null), they are equal and distinct.

  See the test cases for some examples.
 */
int linkedListEqualAndDistinct(struct LinkedListNode* list1,
                               struct LinkedListNode* list2) {
  return 0;
}

void test_linkedListEqualAndDistinct(CuTest *tc) {
  //make a list 5->6->7 
  struct LinkedListNode listA3 = {7, NULL};
  struct LinkedListNode listA2 = {6, &listA3};
  struct LinkedListNode listA1 = {5, &listA2};

  //make another list 5->6->7 
  struct LinkedListNode listB3 = {7, NULL};
  struct LinkedListNode listB2 = {6, &listB3};
  struct LinkedListNode listB1 = {5, &listB2};

  //make a list 5->100->7
  struct LinkedListNode listC3 = {7, NULL};
  struct LinkedListNode listC2 = {100, &listC3};
  struct LinkedListNode listC1 = {5, &listC2};

  //make a list that is partly distinct and partly B list
  struct LinkedListNode listD1 = {5, &listB2};

  // be careful not to segfault
  struct LinkedListNode shortList = {5, NULL};
  
  CuAssertIntEquals(tc, 0, linkedListEqualAndDistinct(&listA1, &listC1));
  CuAssertIntEquals(tc, 1, linkedListEqualAndDistinct(&listA1, &listB1));
  CuAssertIntEquals(tc, 2, linkedListEqualAndDistinct(&listA1, &listA1));
  CuAssertIntEquals(tc, 2, linkedListEqualAndDistinct(&listB1, &listD1));

  //these tests can easily segfault
  CuAssertIntEquals(tc, 0, linkedListEqualAndDistinct(&listA1, &shortList));
  CuAssertIntEquals(tc, 0, linkedListEqualAndDistinct(&shortList, &listC1));

}


void RunAllTests() {
  CuString *output = CuStringNew();
  CuSuite* suite = CuSuiteNew();
  SUITE_ADD_TEST(suite, test_linkedListEqualAndDistinct);
  SUITE_ADD_TEST(suite, test_last4);
  CuSuiteRun(suite);
  CuSuiteSummary(suite, output);
  CuSuiteDetails(suite, output);
  printf("%s\n", output->buffer);
}

int main() {
  // if you want to test by hand, feel free to comment out this line
  // and use your own code
  RunAllTests();
}
