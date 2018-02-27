/*
 * CSSE 132
 * Rose-Hulman Institute of Technology
 * Computer Science and Software Engineering
 *
 * problem1.c: this file is where you implement your solutions
 *             for the homework assignment.
 *
 * This file contains code for homework in the CSSE 132 class.
 * When you edit this file for class, be sure to put your name here!
 *
 * Edited by
 * NAME:
 *
 */
#include <stdlib.h>
#include <stdio.h>


/**
 * This function counts the number of characters in the given string.
 *
 * For example: length("Thing") returns 5.
 * For this function, You MUST use the array brackets: [ and ].
 *
 * @param str - a string to measure
 * @returns the number of characters, excluding the null terminator.
 */
int length(char* string)
{
  // TODO: your code goes here.  You may also want to change the return
  // statement.
  return -1;
}


/**
 * This function is the same as length() but doesn't use array operators.
 *
 * For this function, You MUST NOT use the square brackets: [ or ].
 *
 * @param str - a string to measure
 * @returns the number of characters, excluding the null terminator.
 */
int lengthNoArrays(char* string)
{
  // TODO: your code goes here.  You may also want to change the return
  // statement.
  return -1;
}


/**
 * This function takes an integer and zeroes out every second bit.
 * For example: Since 0xF = 1111 and 0xA = 1010,
 *              bitDropper(0xFFFFFFFF) returns 0xAAAAAAAA
 * @param input - the integer to modify
 * @return the same integer except with every other bit zeroed.
 */
int bitDropper(int input)
{
  // TODO: your code goes here.  You may also want to change the return
  // statement.
  return -1;
}


/**
 * This function iterates through a string and changes it to "L33TSP34K".
 * That means the upper-case characters 
 * A, E, L, O, T are replaced with the characters for
 * 4, 3, 1, 0, 7 respectively.
 * Lower case characters are not modified.
 *
 * For example, GREAT KNOWLEDGE becomes GR347 KN0W73DG3.
 *
 * You can assume the string is properly null-terminated.
 * Write this function yourself using a loop.
 *
 * @param tochange - the string to modify
 * @return the number of characters changed.
 */
int leetSpeak(char* tochange)
{
  // TODO: your code goes here.  You may also want to change the return
  // statement.
  return -1;
}


/**
 * This function allocates a new string that contains every other character
 * from the input string.  
 * For example, skipper("AbAbAb") returns "AAA"
 *
 * BE SURE to allocate the right number of bytes, no more and no less.
 * DO NOT de-allocate or free the string after you make it.
 *
 * IMPORTANT: any code using this function MUST de-allocate the string.
 *
 * @param input - the string to copy some characters from
 * @return the address of a newly allocated string
 */
char* skipper(char* input)
{
  // TODO: your code goes here.  You may also want to change the return
  // statement.
  return NULL;
}

/** DO NOT create a main function in this file or the test executable won't work. **/
