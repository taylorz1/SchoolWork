"""
PRACTICE Test 1, problem 2.

Authors: David Mutchler, Valerie Galluzzi, Mark Hays, Amanda Stouder,
         their colleagues and Zachary Taylor.  September 2015.
"""  # DONE: 1. PUT YOUR NAME IN THE ABOVE LINE.

import rosegraphics as rg


def main():
    """ Calls the   TEST   functions in this module. """
    test_problem2a()
    test_problem2b()


def test_problem2a():
    """ Tests the   problem2a  function. """
    print()
    print('--------------------------------------------------')
    print('Testing the  problem2a  function:')
    print('  See the graphics windows that pop up.')
    print('--------------------------------------------------')

    # TWO tests on ONE window.
    title = 'Tests 1 & 2 of problem2a: '
    title += 'red to blue, then blank to green'
    window = rg.RoseWindow(450, 250, title)

    circle = rg.Circle(rg.Point(100, 50), 30)
    rectangle = rg.Rectangle(rg.Point(100, 120), rg.Point(200, 170))
    rectangle.outline_color = 'blue'
    circle.fill_color = 'red'
    problem2a(circle, rectangle, window)
    window.continue_on_mouse_click()

    circle = rg.Circle(rg.Point(300, 100), 50)
    rectangle = rg.Rectangle(rg.Point(300, 170), rg.Point(400, 120))
    rectangle.outline_color = 'green'
    problem2a(circle, rectangle, window)
    window.close_on_mouse_click()

    # A third test on ANOTHER window.
    title = 'Test 3 of problem2a: yellow to black'
    window = rg.RoseWindow(400, 300, title)

    circle = rg.Circle(rg.Point(100, 50), 30)
    rectangle = rg.Rectangle(rg.Point(100, 100), rg.Point(50, 250))
    rectangle.outline_color = 'black'
    circle.fill_color = 'yellow'
    problem2a(circle, rectangle, window)
    window.close_on_mouse_click()


def problem2a(circle, rectangle, window):
    """
    What comes in:
      -- An rg.Circle.
      -- An rg.Rectangle.
      -- An rg.RoseWindow.
    What goes out:  Nothing (i.e., None).
    Side effects:
      -- Draws the given rg.Circle and rg.Rectangle
           on the given rg.RoseWindow,
           then waits for the user to click the window.
      -- Then draws an rg.Line from the upper-right corner
           of the rg.Rectangle to its lower-left corner,
           with the line drawn as an arrow,
           then waits for the user to click the window.
      -- Changes the fill color of the given rg.Circle to the
           outline color of the given rg.Rectangle,
           then renders the window again
           (with no waiting for a click from the user this time).
      Must  ** NOT close **   the window.

    Type hints:
      :type circle:    rg.Circle
      :type rectangle: rg.Rectangle
      :type window:    rg.RoseWindow
    """
    # ------------------------------------------------------------------
    # DONE: 2. Implement and test this function.
    #          Tests have been written for you (above).
    # ------------------------------------------------------------------
    circle.attach_to(window)
    rectangle.attach_to(window)
    window.render(0.01)
    window.continue_on_mouse_click('To continue, click anywhere in this window', None, None, False, True)
    end = rg.Point(rectangle.get_center().x - rectangle.get_width() / 2, rectangle.get_center().y + rectangle.get_height() / 2)
    start = rg.Point(rectangle.get_center().x + rectangle.get_width() / 2, rectangle.get_center().y - rectangle.get_height() / 2)
    line = rg.Line(start, end)
    line.arrow = 'last'
    line.attach_to(window)
    window.render(0.01)
    window.continue_on_mouse_click('To continue, click anywhere in this window', None, None, False, True)
    circle.fill_color = rectangle.outline_color
    circle.attach_to(window)
    window.render(0.01)


def test_problem2b():
    """ Tests the  problem2b   function. """
    print()
    print('--------------------------------------------------')
    print('Testing the   problem2b   function:')
    print('  See the graphics windows that pop up.')
    print('--------------------------------------------------')

    # TWO tests on ONE window.
    title = 'Tests 1 & 2 of problem2b: '
    title += '6 on blue with delta=15, 3 on green with delta=50'
    window = rg.RoseWindow(550, 450, title)

    rectangle = rg.Rectangle(rg.Point(100, 100), rg.Point(140, 120))
    rectangle.fill_color = 'blue'
    problem2b(rectangle, 6, 15, window)
    window.continue_on_mouse_click()

    rectangle = rg.Rectangle(rg.Point(400, 300), rg.Point(350, 200))
    rectangle.fill_color = 'green'
    problem2b(rectangle, 3, 50, window)
    window.close_on_mouse_click()

    title = 'Test 3 of problem2b: 10 on red with delta=12'
    window = rg.RoseWindow(400, 350, title)

    rectangle = rg.Rectangle(rg.Point(250, 150), rg.Point(200, 200))
    rectangle.fill_color = 'red'
    problem2b(rectangle, 10, 12, window)
    window.close_on_mouse_click()


def problem2b(rect, n, delta, win):
    """
    What comes in:
      -- An rg.Rectangle.
      -- A positive integer n.
      -- A positive integer delta.
      -- An rg.RoseWindow.
    What goes out:  Nothing (i.e., None).
    Side effects:
      Draws n rg.Rectangles on the given rg.RoseWindow such that:
        -- The first rg.Rectangle is the given one.
        -- Subsequent rg.Rectangles have the same center
            as the given rg.Rectangle, but their width
            and height are each   delta   greater than
            the width and height of the previous rg.Rectangle.
      Must render but   ** NOT close **   the window.

    Type hints:
      :type circle:    rg.Circle
      :type rectangle: rg.Rectangle
      :type window:    rg.RoseWindow
    """
    # ------------------------------------------------------------------
    # DONE: 3. Implement and test this function.
    #          Tests have been written for you (above).
    # ------------------------------------------------------------------
    for k in range(n):
        center = rect.get_center()
        point1x = center.x + rect.get_width() / 2 + k * delta / 2
        point1y = center.y + rect.get_height() / 2 + k * delta / 2
        point2x = center.x - rect.get_width() / 2 - k * delta / 2
        point2y = center.y - rect.get_height() / 2 - k * delta / 2
        corner_1 = rg.Point(point1x, point1y)
        corner_2 = rg.Point(point2x, point2y)
        recta = rg.Rectangle(corner_1, corner_2)
        recta.fill_color = rect.fill_color
        recta.attach_to(win)
        win.render(0.5)

# ----------------------------------------------------------------------
# Calls  main  to start the ball rolling.
# ----------------------------------------------------------------------
main()
