"""
PRACTICE Test 1, problem 3.

Authors: David Mutchler, Valerie Galluzzi, Mark Hays, Amanda Stouder,
         their colleagues and Zachary Taylor.  September 2015.
"""  # DONE: 1. PUT YOUR NAME IN THE ABOVE LINE.

import rosegraphics as rg


def main():
    """ Calls the   TEST   functions in this module. """
    test_problem3a()
    test_problem3b()


def test_problem3a():
    """ Tests the   problem3a   function. """
    # ------------------------------------------------------------------
    # DONE: 2. Implement this TEST function.
    #   It TESTS the  problem3a  function defined below.
    #   Include at least **   3   ** tests, of which
    ####################################################################
    #      ***  at least TWO tests are on ONE window and
    #      ***  at least ONE test is on a DIFFERENT window.
    ####################################################################
    #
    ####################################################################
    # CAUTION: Be sure to test the  ** RETURNED VALUE ** from
    #      problem3a, in ADDITION to testing that it DRAWS correctly.
    ####################################################################
    #
    # HINT: Consider using the same test cases as suggested by
    #       the pictures in    problem3a_picture.pdf  in this project.
    #         -- The pictures ALSO give you correct RETURNED VALUES
    #            to use in your tests.
    # ------------------------------------------------------------------
    # TWO tests on ONE window.
    title = 'Tests 1 & 2 of problem3a: '
    title += '9 lines and 3 lines'
    window = rg.RoseWindow(400, 200, title)
    expected = 75
    actual = problem3a(window, rg.Point(80, 10), 9)
    print()
    print('expected:', expected)
    print('actual:', actual)
    window.continue_on_mouse_click()
    expected = 9
    actual = problem3a(window, rg.Point(30, 50), 3)
    print()
    print('expected:', expected)
    print('actual:', actual)
    window.close_on_mouse_click()

    # Test 3
    title = 'Test 3, 6 lines'
    window = rg.RoseWindow(300, 200, title)
    expected = 36
    actual = problem3a(window, rg.Point(30, 30), 6)
    print()
    print('expected:', expected)
    print('actual:', actual)
    window.close_on_mouse_click()


def problem3a(window, point, n):
    """
    See   problem3a_picture.pdf   in this project for pictures
    that may help you better understand the following specification:

    Draws a sequence of  n  rg.Lines on the given rg.RoseWindow,
    as follows:
      -- There are the given number (n) of rg.Lines.
      -- Each rg.Line is vertical and has length 50.
            (All units are pixels.)
      -- The top of the first (leftmost) rg.Line
            is at the given rg.Point.
      -- Each successive rg.Line is 20 pixels to the right
            and 10 pixels down from the previous rg.Line.
      -- The first rg.Line has thickness 1.
      -- Each successive rg.Line has thickness 2 greater than
         the zg.Line to its left, but no greater than 13.
           (So once a rg.Line has thickness 13,
           it and all the rg.Lines to its right have thickness 13.)

    Returns the sum of the thicknesses of the rg.Line's.

    Preconditions:
        :type window: rg.RoseWindow
        :type point: rg.Point
        :type n: int   and n is positive.
    """
    # ------------------------------------------------------------------
    # DONE: 3. Implement and test this function.
    #   Note that you should write its TEST function first (above).
    # ------------------------------------------------------------------
    thickness = 1
    total = 0
    for k in range(n):
        total = total + thickness
        line = rg.Line(rg.Point(point.x + k * 20, point.y + 50 + k * 10), rg.Point(point.x + k * 20, point.y + k * 10))
        line.thickness = thickness
        line.attach_to(window)
        window.render(0.2)
        if thickness == 13:
            thickness = 13
        else:
            thickness = thickness + 2
    return total


def test_problem3b():
    """ Tests the   problem3b   function. """
    # Test 1 is ALREADY DONE (here).
    expected = 158
    answer = problem3b(4, rg.Point(100, 50))
    print('Expected and actual are:', expected, answer)

    # Test 2 is ALREADY DONE (here).
    expected = 539
    answer = problem3b(7, rg.Point(30, 30))
    print('Expected and actual are:', expected, answer)


def problem3b(m, point1):
    """
    See   problem3b_picture.pdf   in this project for pictures
    that may help you better understand the following specification:

    -- Constructs and displays an rg.RoseWindow
         that is 400 wide by 650 tall.

    -- Draws, on the rg.RoseWindow,  m  SETS of lines, where:
        -- Each SET of lines is drawn by a call to ** problem3a **.
        -- The first set has 3 lines that start at point1
             (the given point).
        -- The second set has 5 lines that start 60 pixels
             directly below point1.
        -- The third set has 7 lines that start 120 pixels
             directly below point1.
        -- The fourth set has 9 lines that start 180 pixels
             directly below point1.
        -- etc until  m  SETS of lines are drawn (where m is given)

       Each set of lines should have widths (thicknesses) per problem3a.

    -- Waits for the user to click the mouse (and displays an appropriate
         message prompting the user to do so), then closes the window.

    -- Returns the sum of the thicknesses of ALL of the lines drawn
         (over all  m  sets of lines).

    Preconditions:
        :type m: int   and is positive
        :type point1: rg.Point
    """
    # ------------------------------------------------------------------
    # DONE: 4. Implement and test this function.
    #          Tests have been written for you (above).
    #
    ####################################################################
    # IMPORTANT:
    #    **  For full credit you must appropriately use (call)
    #    **  the   problem3a   function that you implemented above.
    ####################################################################
    # ------------------------------------------------------------------
    total = 0
    window = rg.RoseWindow(400, 650)
    for k in range(m):
        point = rg.Point(point1.x, point1.y + 60 * k)
        n = 3 + 2 * k
        total = total + problem3a(window, point, n)
    window.close_on_mouse_click()
    return total


# ----------------------------------------------------------------------
# Calls  main  to start the ball rolling.
# ----------------------------------------------------------------------
main()
