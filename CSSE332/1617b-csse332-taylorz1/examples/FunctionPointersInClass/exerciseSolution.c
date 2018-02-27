#include <stdio.h>
#include <stdlib.h>

/* Defines a way to compare integers. */
int int_sorter(const void *first_arg, const void *second_arg) {
  int first = *(int*)first_arg;
  int second = *(int*)second_arg;
  if (first < second) {
    return -1;
  } else if (first == second) {
    return 0;
  } else {
    return 1;
  }
}

int normalSort(int val1, int val2) {
  return val1 < val2;
}

int reversedSort(int val1, int val2) {
  return val1 > val2;
}


void BubbleSort(int inputList[], int inputLength, int (*comp) (int,int)) {
  char sorted = 0;
  int i, temp;
  while (!sorted) {
    sorted = 1;
    for (i = 1; i < inputLength; i++) {
      if (comp(inputList[i],inputList[i-1])) {
        sorted = 0;
        temp = inputList[i-1];
        inputList[i-1] = inputList[i];
        inputList[i] = temp;
      }
    }
  }
}


int main(int argc, char *argv[]) {
  int array[] = {4, 18, 2, 3, 5, -6, 3, 22, 100, 7};
  int i;  /* To index into the array. */


  /* STEP 1: Modify the BubbleSort function above to take a comparison
   * function as a parameter.  Then create 2 comparison functions
   * (i.e. a function that takes two ints and determins if one is
   * larger than the other for some definition of "larger").  Then
   * call Bubble sort with your new comparison functions below. */
  
  BubbleSort(array, 10, normalSort);
  printf("Should print out ordered:\n");
  for (i = 0; i < 10; ++i) {
    printf("%d\n", array[i]);
  }

  BubbleSort(array, 10, reversedSort);
  printf("\nShould print out reversed:\n");
  for (i = 0; i < 10; ++i) {
    printf("%d\n", array[i]);
  }

  /* STEP 2: use the qsort manpage to see the signature of the
   * function.  Notice that one of its argument is a function pointer
   * to a function that defines how to compare two elements. The code
   * below uses the function pointers to pass the int_sorter function
   * to qsort. */

  
  qsort(array, 10, sizeof(int), int_sorter);

  printf("\nShould print out ordered:\n");
  for (i = 0; i < 10; ++i) {
    printf("%d\n", array[i]);
  }
  
  return 0;
}
