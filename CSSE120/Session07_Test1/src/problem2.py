"""
Test 1, problem 2.

Authors: David Mutchler, Dave Fisher, Valerie Galluzzi, Amanda Stouder,
         their colleagues and Zachary Taylor.  June 2016.
"""  # DONE: 1. PUT YOUR NAME IN THE ABOVE LINE.

import rosegraphics as rg


def main():
    """ Calls the   TEST   functions in this module. """
    test_problem2()


def test_problem2():
    """ Tests the   problem2   function. """
    print()
    print('--------------------------------------------------')
    print('Testing the  problem2  function:')
    print('  See the graphics windows that pop up.')
    print('--------------------------------------------------')

    # TWO tests on ONE window.
    title = 'Tests 1 and 2 of problem2'
    window = rg.RoseWindow(600, 250, title)

    circle = rg.Circle(rg.Point(100, 50), 30)
    circle.outline_thickness = 5
    rectangle = rg.Rectangle(rg.Point(100, 120),
                             rg.Point(200, 170))
    rectangle.outline_color = 'blue'
    rectangle.outline_thickness = 5
    problem2(circle, rectangle, window)

    window.continue_on_mouse_click()

    circle = rg.Circle(rg.Point(500, 100), 80)
    circle.fill_color = 'green'
    rectangle = rg.Rectangle(rg.Point(300, 60),
                             rg.Point(250, 200))
    rectangle.fill_color = 'yellow'
    problem2(circle, rectangle, window)

    window.close_on_mouse_click()

    # A third test on ANOTHER window.
    title = 'Test 3 of problem2'
    window = rg.RoseWindow(400, 300, title)

    circle = rg.Circle(rg.Point(70, 175), 50)
    rectangle = rg.Rectangle(rg.Point(200, 170), rg.Point(300, 120))
    rectangle.outline_color = 'green'
    rectangle.outline_thickness = 10

    problem2(circle, rectangle, window)
    window.close_on_mouse_click()


def problem2(circle, rectangle, window):
    """
    See   problem2_pictures.pdf   for pictures that may help you
    better understand the following specification:

    What comes in:
      -- An rg.Circle.
      -- An rg.Rectangle.
      -- An rg.RoseWindow.
    What goes out:  Nothing (i.e., None).
    Side effects:
      -- Draws the given rg.Circle and rg.Rectangle
           on the given rg.RoseWindow.
      -- Draws an rg.Line:
           -- from the center of the rg.Circle
           -- to the center of the rg.Rectangle,
           -- with the thickness of that rg.Line being 10
           -- and its color being 'red'.
      -- Draws an rg.Line:
           -- that is VERTICAL and divides the given rg.Rectangle
                into two equally-sized halves,
           -- with the thickness of that rg.Line being the outline
                thickness of the given rg.Rectangle.
      -- Sets the fill color of the given rg.Circle to the
           outline color of the given rg.Rectangle.
      Must render but   ** NOT close **   the window.

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
    window.render()
    start = circle.center
    end = rectangle.get_center()
    line = rg.Line(start, end)
    line.thickness = 10
    line.color = 'red'
    line.attach_to(window)
    window.render(0.05)
    centert = rectangle.get_center()
    start2 = rg.Point(centert.x, centert.y - rectangle.get_height() / 2)
    end2 = rg.Point(centert.x, centert.y + rectangle.get_height() / 2)
    line2 = rg.Line(start2, end2)
    line2.thickness = rectangle.outline_thickness
    circle.fill_color = rectangle.outline_color
    line2.attach_to(window)
    window.render()


# ----------------------------------------------------------------------
# Calls  main  to start the ball rolling.
# ----------------------------------------------------------------------
main()
