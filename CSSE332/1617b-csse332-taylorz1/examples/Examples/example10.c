#include <stdio.h>

int main(int argc, char *argv[]) {
  int *ptr, j;  // j is not a pointer.

 /* initialize ptr before using it 
   *ptr = 4 does NOT initialize ptr */
  ptr = &j;

  *ptr = 4;   /* j <- 4 */

  j = *ptr+1;  /* j <- ??? */

  printf("j is stored in memory at address  %p\n", &j);
  printf("ptr is initialized to the address %p\n", ptr);
  printf("This confirms that ptr == &j\n");
  printf("The value pointed to by ptr is %d\n", *ptr);
  printf("j = %d, *ptr = %d, thus j = *ptr\n", j, *ptr);
  return 0;
}
