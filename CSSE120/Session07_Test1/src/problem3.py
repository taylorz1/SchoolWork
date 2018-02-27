"""
Test 1, problem 3.

Authors: David Mutchler, Dave Fisher, Valerie Galluzzi, Amanda Stouder,
         their colleagues and Zachary Taylor.  June 2016.
"""  # DONE: 1. PUT YOUR NAME IN THE ABOVE LINE.

import rosegraphics as rg


def main():
    """ Calls the   TEST   functions in this module. """
    test_problem3()


def test_problem3():
    """ Tests the  problem3   function. """
    print()
    print('--------------------------------------------------')
    print('Testing the   problem3   function:')
    print('  See the graphics windows that pop up.')
    print('--------------------------------------------------')

    # TWO tests on ONE window.
    title = 'Tests 1 and 2 of problem3:    '
    title += '6 green lines, then 3 blue lines'
    window = rg.RoseWindow(500, 300, title)

    problem3(150, 230, 6, 'green', window)
    window.continue_on_mouse_click()

    problem3(450, 120, 3, 'blue', window)
    window.close_on_mouse_click()

    # A third test on another window.
    title = 'Test 3 of problem3:    15 red lines'
    window = rg.RoseWindow(400, 500, title)

    problem3(300, 450, 15, 'red', window)
    window.close_on_mouse_click()


def problem3(x1, y1, n, color, window):
    """
    See    problem3_pictures.pdf     for pictures that may help you
    better understand the following specification:

    What comes in:
      -- Two positive integers x1, y1.
      -- A positive integer n.
      -- A string that is a color (e.g. 'red' or 'green' or ...)
      -- An rg.RoseWindow.
    What goes out:  Nothing (i.e., None).
    Side effects:
      1. Draws a rg.Point at (x1, y1).
      2. Draws a rg.Point at (x1, 30).
      3. Draws  n  rg.Lines on the given rg.RoseWindow such that:
           -- All  n  rg.Lines have the given color.
           -- All  n  rg.Lines have (0, 0)
                [the upper-left corner of the window]
                as their leftmost endpoint.
           -- The topmost    rg.Line goes from (0, 0) to (x1, 30),
                and has thickness n.
           -- The bottommost rg.Line goes from (0, 0) to (x1, y1),
                and has thickness 1.
           -- The  n  rg.Lines are equally spaced between the topmost
                and the bottommost, and decrease evenly in thickness
                from top to bottom.
      Must render but   ** NOT close **   the window.

    Type hints:
      :type x1:      int
      :type y1:      int
      :type n:       int
      :type color:   str
      :type window:  rg.RoseWindow
    """
    # ------------------------------------------------------------------
    # DONE: 2. Implement and test this function.
    #          Tests have been written for you (above).
    # ------------------------------------------------------------------
    start = rg.Point(0, 0)
    counter = n
    rg.Point(x1, 30).attach_to(window)
    point = rg.Point(x1, y1)
    point.attach_to(window)
    for i in range (n):
        line = rg.Line(start, rg.Point(x1, 30 + i * (y1 - 30) / (n - 1)))
        line.attach_to(window)
        line.color = color
        line.thickness = counter
        counter = counter - 1
        window.render()


# ----------------------------------------------------------------------
# Calls  main  to start the ball rolling.
# ----------------------------------------------------------------------
main()
