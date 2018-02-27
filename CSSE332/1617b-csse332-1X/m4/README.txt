WHAT WE DID:

We added onto the shell with a few new commands. We can write to files, copy files, delete files, and create brand new files. We also added the ability to list the contents of the directory. A few new interrupts were added to handle the new things that were implemented such as delete and write sector. 

HOW TO VERIFY:

Boot the system using `make test-bochs` (or however you want to start the VM). Then after it is open try out deleting a file by calling delete <FILENAME> of a file that you know to be in there. You can also try copying a file by typing copy <FIRST FILE> <SECOND FILE> and this will copy file 1 into file 2. You can easily test out dir by typing it in and seeing the contents of the directory. Try this before and after the dir command to see that the file diappears. Lastly, type create <FILENAME> to create a file. Input a few lines and then enter an empty line to finish off the file. Use type <FILENAME> in order to see the contents you just entered into the file. 