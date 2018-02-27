"""
This project lets you try out Tkinter/Ttk and practice it!

Authors: David Mutchler, Valerie Galluzzi, Mark Hays, Amanda Stouder,
         their colleagues and Zachary Taylor.  Summer 2016.
"""  # DONE: 1. PUT YOUR NAME IN THE ABOVE LINE.

import tkinter
from tkinter import ttk


def main():
    """ Constructs a GUI with stuff on it. """
    # ------------------------------------------------------------------
    # DONE: 2. After reading and understanding the m1e module,
    #   ** make a window that shows up. **
    # ------------------------------------------------------------------
    window = tkinter.Tk()


    # ------------------------------------------------------------------
    # DONE: 3. After reading and understanding the m2e module,
    #   ** put a Frame on the window. **
    # ------------------------------------------------------------------
    frame = ttk.Frame(window, padding=15)
    frame.grid()


    # ------------------------------------------------------------------
    # DONE: 4. After reading and understanding the m2e module,
    #   ** put a Button on the Frame. **
    # ------------------------------------------------------------------
    button1 = ttk.Button(frame, text='This is a button')
    button1.grid()

    # ------------------------------------------------------------------
    # DONE: 5. After reading and understanding the m3e module,
    #   ** make your Button respond to a button-press **
    #   ** by printing   "Hello"  on the Console.     **
    # ------------------------------------------------------------------
    button1['command'] = lambda : print('Hello')

    # ------------------------------------------------------------------
    # DONE: 6. After reading and understanding the m4e module,
    #   -- Put an Entry box on the Frame.
    #   -- Put a second Button on the Frame.
    #   -- Make this new Button, when pressed, print "Hello"
    #        on the Console if the current string in the Entry box
    #        is the string 'ok', but print "Goodbye" otherwise.
    # ------------------------------------------------------------------
    entry = ttk.Entry(frame)
    button2 = ttk.Button(frame, text='Special Discerning Button')
    entry.grid()
    button2.grid()
    button2['command'] = (lambda:  discern(entry))





    # ------------------------------------------------------------------
    # DONE: 7.
    #    -- Put a second Entry on the Frame.
    #    -- Put a third Button on the frame.
    #    -- Make this new Button respond to a button-press as follows:
    #
    #    Pressing this new Button causes the STRING that the user typed
    #    in the FIRST Entry box to be printed N times on the Console,
    #      where N is the INTEGER that the user typed
    #      in the SECOND Entry box.
    #
    #    If the user fails to enter an integer,
    #    that is a "user error" -- do NOT deal with that.
    #
    # ------------------------------------------------------------------
    ####################################################################
    # HINT:
    #   You will need to obtain the INTEGER from the STRING
    #   that the GET method returns.
    #   Use the   int   function to do so, as in this example:
    #      s = entry_box.get()
    #      n = int(s)
    ####################################################################
    entry2 = ttk.Entry(frame)
    button3 = ttk.Button(frame, text='Print N times')
    entry2.grid()
    button3.grid()
    button3['command'] = lambda: N_print(entry, entry2)


    # ------------------------------------------------------------------
    # DONE: 8. As time permits, do other interesting GUI things!
    # ------------------------------------------------------------------
    button4 = ttk.Button(frame, text='A new Button')
    button4.grid()
    button4['command'] = lambda: needed_why(entry2)


    window.mainloop()

def discern(entry):
    alpha = entry.get()
    beta = 'ok'
    if alpha == beta:
        print('Goodbye')
    else:
        print('Hello')

def N_print(entry, entry2):
    alpha = entry.get()
    beta = entry2.get()
    for _ in range(int(beta)):
        print(alpha)

def needed_why(entry2):
    import math
    for n in range(1, int(entry2.get()) + 1):
        print(math.factorial(n))


# ----------------------------------------------------------------------
# Calls  main  to start the ball rolling.
# ----------------------------------------------------------------------
main()
