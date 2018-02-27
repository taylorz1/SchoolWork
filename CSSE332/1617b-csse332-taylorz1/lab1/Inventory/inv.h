/*  This is where you define the structs needed to 
    solve this problem.  You can define constants, global
    variables, and function signatures here as well. 
    By <Name>, <Date>
*/
#include <stdio.h>
#include <stdlib.h>
typedef struct
{
     int item_num;
     int quantity;
     float price;
     struct Expirationdate {
           int month;
           int year;
     } expiration;
} inventory;


