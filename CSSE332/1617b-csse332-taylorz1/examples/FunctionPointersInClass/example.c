#include <stdio.h>
#include <stdlib.h>

/* A function pointer tutorial adopted from 
 * http://www.cprogramming.com/tutorial/function-pointers.html 

 * A function pointer is a variable that stores the address of a function 
 * that can later be called through that function pointer.
 
 * Function pointers allow you to pass functions as arguments to other 
 * functions. 
 
 * Another use for function pointers is setting up "listener" or "callback" 
 * functions that are invoked when a particular event happens. */

/* Another function pointer declaration: See main() for the
 * first. Here, func is a pointer to a function that takes a void
 * pointer as a variable and returns an int pointer. You can
 * experiement with this one on your own. */
int *(*func)(void *);

/* This function computes the first n fibonacci numbers and displays
 * them on a line. */
void fibonacci(int n);

/* This function compares two int values pointer to by ints pointer
 * parammeters and returns an int that signifies how they compare. */
int int_sorter(const void *first_arg, const void *second_arg);

int main(int argc, char *argv[]) {
  int array[10];  /* An array of integers to sort. */
  int i;  /* To index into the array. */

  /* Function pointer declaration: func_ptr is a pointer to a function
   * that takes an int as its only parameter and returns a void. This
   * is equivalent to declaring a a function called *func_ptr. */
  void (*func_ptr)(int);

  /* Another function pointer declaration. */
  void (*foo)(int);

  /* the address operator is actually optional */
  func_ptr = &fibonacci;

  /* Shorthand with no address operator. */
  foo = fibonacci;

  /* One way to use a function pointer. This is technically correct,
   * but looks ugly and intimidating. */
  (*func_ptr)(5);

  /* Another way to use function pointers is to use this shorthand
   * approach.  It is much easier to use and understand. */
  foo(10);

  /* Note that function pointers provide a lot of flexibility.  You
   * can use the * and & operators, or you can elude that part of the
   * syntax. This is similar to how arrays are used.  You can use the
   * pointer syntax or avoid using it all together. */

  return 0;
}

/* Computes Fibonacce numbers. */
void fibonacci(int n) {
  int i, f0, f1, f;
  f0 = 0;
  f1 = 1;
  f = 1;
  for (i = 0; i < n; i++) {
    if (i < 2) {
      printf("%d\t", i);
    } else {
      f = f0 + f1;
      printf("%d\t", f);
      f0 = f1;
      f1 = f;
    }
  }
  printf("\n\n");
}
