/*****************************************************************************
 Excerpt from "Linux Programmer's Guide - Chapter 6"
 (C)opyright 1994-1995, Scott Burkett

 Modified by Buffalo, Rose-Hulman Institute of Technology
 *****************************************************************************/
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>


int main(void)
{
        int     fd[2];
        FILE* pipestream;
        pid_t   childpid;



        pipe(fd);
        
        if((childpid = fork()) == -1)
        {
                perror("fork");
                return 1;
        }

        if(childpid == 0)
        {
                /* Child process closes up input side of pipe */
                close(fd[0]);
                pipestream = fdopen(fd[1], "w");

                /* Send "string" through the output side of pipe */
                fprintf(pipestream,"sending %d %d", 23, 37);
                return 0;
        }
        else
        {
                /* Parent process closes up output side of pipe */
                close(fd[1]);
                int i, j;
                pipestream = fdopen(fd[0], "r");
                /* Read in a string from the pipe */
                fscanf(pipestream,"sending %d %d", &i, &j);

                printf("Received ints: %d %d\n",i,j);
        }
        
        return 0;
}
