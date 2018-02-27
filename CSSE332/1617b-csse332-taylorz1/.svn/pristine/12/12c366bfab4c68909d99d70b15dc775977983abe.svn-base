#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
  int *ptr, i, size;

  printf("Enter the size of the array");
  scanf("%d", &size);

  /* allocate space to hold an array of ints */
  ptr = (int*)malloc(4 * sizeof(int));

  /* do stuff with the space */
  *ptr = 4;  // ptr[0] = 4;

  /* free up the allocated space */
  free(ptr);
  ptr = NULL;

  return 0;
}
