"""
Lets you practice DEFINING and CALLING
     FUNCTIONS

Authors: David Mutchler, Dave Fisher, Valerie Galluzzi, Amanda Stouder,
         their colleagues and Zachary Taylor.  March 2016.
"""  # DONE: 1. PUT YOUR NAME IN THE ABOVE LINE.
#               Also look for other  TODO's  LATER in this file!

import rosegraphics as rg
import random


def main():
    """
    Makes a TurtleWindow,
    calls the other functions in this module to demo them, and
    waits for the user to click anywhere in the window to close it.
    """
    window = rg.TurtleWindow()

    turtle1()
    turtle4()
    turtle5()
    turtle3()
    turtle2()
    turtle2()

    window.close_on_mouse_click()


def turtle1():
    """
    Constructs a square SimpleTurtle that draws a yellow-filled circle.
    """
    ada = rg.SimpleTurtle('square')

    ada.pen = rg.Pen('aqua', 30)
    ada.paint_bucket = rg.PaintBucket('yellow')

    ada.begin_fill()
    ada.draw_circle(150)
    ada.end_fill()


def turtle2():
    """
    Constructs a triangle SimpleTurtle that goes to a RANDOM point,
    draws a cool shape, and returns to where it started from.
    """
    grace = rg.SimpleTurtle('triangle')

    grace.pen = rg.Pen('blue', 15)
    grace.paint_bucket = rg.PaintBucket('magenta')

    # Keep track of where I am, to go back to it at the end.
    # Then choose a RANDOM starting point for the motion in here.
    i_began_here = rg.Point(grace.x_cor(), grace.y_cor())
    i_am_going_here = rg.Point(random.randrange(-500, 500),
                               random.randrange(-300, 0))
    grace.pen_up()
    grace.go_to(i_am_going_here)
    grace.pen_down()

    # Do the motion.
    grace.left(90)
    grace.forward(200)
    grace.begin_fill()
    grace.draw_circle(25)
    grace.end_fill()

    # Go back to where I was when this function began its run.
    grace.go_to(i_began_here)


def turtle3():
    """
    Constructs a default SimpleTurtle that goes forward 300 units
    and then draws a black-filled circle.
    """
    maja = rg.SimpleTurtle()
    maja.pen = rg.Pen('black', 10)

    maja.forward(300)

    maja.begin_fill()
    maja.draw_circle(50)
    maja.end_fill()


def turtle4():
    tom = rg.SimpleTurtle()
    tom.pen = rg.Pen('Orange', 3)

    tom.left(45)
    tom.forward(100)


def turtle5():
    jenn = rg.SimpleTurtle()
    steven2 = rg.SimpleTurtle()

    jenn.pen = rg.Pen('Blue', 4)
    steven2.pen = rg.Pen('Orange', 7)

    jenn.left(40)
    jenn.forward(10)
    jenn.right(40)
    jenn.begin_fill()
    jenn.draw_regular_polygon(9, 100)
    jenn.end_fill()

    steven2.forward(100)
########################################################################
#
# DONE: 2.
#   (Yes, that means for YOU to DO things per these instructions:)
#
#   READ the code above.  Be sure you understand:
#     -- How many functions are defined above?
#           (Answer: 4)
#     -- For each function definition:
#          -- Where does that function definition begin?
#             Where does it end?
#     -- How many times does   main   call the   turtle1   function?
#            (Answer: 1)
#     -- How many times does   main   call the   turtle2   function?
#            (The answer is NOT 1.)
#     -- What line of code calls the   main   function?
#            (Answer: look at the LAST line of this module, far below.)
#
#     ** ASK QUESTIONS if you are uncertain. **
#
#   When you believe you understand the answers
#   to all of the above questions, change the above TODO to DONE.
#
########################################################################


########################################################################
# DONE: 3.
#   RUN this module.
#
#   RELATE what is DRAWN to the CODE above.  Be sure you understand:
#       -- WHEN does the code in   main   run?
#       -- WHEN does the code in   turtle1   run?
#                    the code in   turtle2   run?
#                    the code in   turtle3   run?
#       -- For each of the above, WHY does that code run when it does?
#
#     ** ASK QUESTIONS if you are uncertain. **
#
#   When you believe you understand the answers
#   to all of the above questions, change the above TODO to DONE.
#
########################################################################

########################################################################
#
# DONE: 4.
#   Define another function,
#   immediately below the end of the definition of   turtle3   above.
#   Name your new function   turtle4.
#
#   The Python "pep8" coding standard says to leave exactly 2 blank
#   lines between function definitions, so be sure to do so.
#
#   Your new function should:
#
#    1. Define a SimpleTurtle (as   turtle3   and other functions did).
#
#    2. Set your SimpleTurtle's
#               pen
#       to a new rg.Pen with a color and thickness of your own choosing.
#       The  COLORS.pdf  file included lists all legal color-names.
#
#    3. Make your SimpleTurtle move around a bit.
#           ** Nothing fancy is required. **
#
#   BTW, if you see a RED mark at one of your lines, that means that
#   there is a SYNTAX (notation) error at that line or elsewhere.
#   Get help as needed to fix any such errors.
#
########################################################################

########################################################################
#
# DONE: 5.
#   Add a line to   main   that CALLS your new function immediately
#   AFTER  main  calls turtle1.  So:
#     -- the SimpleTurtle from turtle1 should move,
#     -- then YOUR SimpleTurtle should move,
#     -- then the other 3 SimpleTurtles should move.
#
#   Run this module.  Check that there is another SimpleTurtle (yours)
#   that uses the pen you chose and moves around a bit.
#   If your code has errors (shows RED in the Console window)
#   or does not do what it should, get help as needed to fix it.
#
########################################################################

########################################################################
#
# DONE: 6.
#   The previous two TODOs IMPLEMENTED a function (TODO 4)
#   and TESTED that function (TODO 5).
#
#   Now implement AND test one more function, defining it immediately
#   below the definition of your   turtle4   function.
#   Name your new function   turtle5.
#
#   The Python "pep8" coding standard says to leave exactly 2 blank
#   lines between function definitions, so be sure to do so.
#
#   Your new function should define TWO new SimpleTurtles,
#   set their characteristics (i.e., instance variables) as you choose,
#   and make each move a bit.
#
#           ** Nothing fancy is required. **
#              KISS.
#
#  Get help as needed on this (and every!) exercise!
#
#  As always COMMIT your work (which turns it in) as often as you want,
#  but for sure after you have tested it and believe that it is correct,
#  by selecting this module
#       and doing     SVN ~ Commit.
#
########################################################################

# ----------------------------------------------------------------------
# Calls  main  to start the ball rolling.
# ----------------------------------------------------------------------
main()
