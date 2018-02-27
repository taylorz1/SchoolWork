"""
This module uses ROSEGRAPHICS to demonstrate:
  -- CONSTRUCTING objects,
  -- applying METHODS to them, and
  -- accessing their DATA via INSTANCE VARIABLES.

Authors: David Mutchler, Dave Fisher, Valerie Galluzzi, Amanda Stouder,
         their colleagues.  March 2016.
"""
# ----------------------------------------------------------------------
# Students: Read and run this program.
#           Make sure that you understand all 3 examples.
#
#           Also, WITH YOUR INSTRUCTOR, learn to use the DOT trick:
#               rg.           and rg.Circle().
#           to POP UP help from which you can learn how to use
#           objects of a class that you have never seen before.
#           (And the HOVER over a class or method to get its code.)
# ----------------------------------------------------------------------

import rosegraphics as rg


def main():
    """
    Uses ROSEGRAPHICS to demonstrate:
      -- CONSTRUCTING objects,
      -- applying METHODS to them, and
      -- accessing their DATA via INSTANCE VARIABLES
    """
    example1()
    example2()
    example3()


def example1():
    """ Displays an empty window. """
    window = rg.RoseWindow(500, 300, 'Example 1: An empty window')
    window.close_on_mouse_click()


def example2():
    """ Displays two Point objects. """
    # ------------------------------------------------------------------
    # Construct the window in which objects will be drawn.
    # ------------------------------------------------------------------
    window = rg.RoseWindow()

    # ------------------------------------------------------------------
    # Construct some rg.Point objects.
    # Note: the y-axis goes DOWN from the TOP.
    # ------------------------------------------------------------------
    point1 = rg.Point(100, 150)
    point2 = rg.Point(200, 50)

    # ------------------------------------------------------------------
    # A RoseGraphics object is not associated with a window,
    # and hence are not drawn, until you ATTACH it to a window.
    # ------------------------------------------------------------------
    point1.attach_to(window)
    point2.attach_to(window)

    # ------------------------------------------------------------------
    # And they still are not DRAWN until you RENDER the window.
    # That will draw ALL the objects on the window.
    # This two-step approach is important for animation.
    # ------------------------------------------------------------------
    window.render()

    window.close_on_mouse_click()


def example3():
    """ Displays a Circle and a Rectangle. """
    # ------------------------------------------------------------------
    # RoseWindow: optionally takes its width and height.
    # ------------------------------------------------------------------
    width = 700
    height = 400
    window = rg.RoseWindow(width, height)

    # ------------------------------------------------------------------
    # Circle: needs its center and radius.
    # Has  fill_color  instance variable.
    # ------------------------------------------------------------------
    center_point = rg.Point(300, 100)
    radius = 50
    circle = rg.Circle(center_point, radius)
    circle.fill_color = 'green'
    circle.attach_to(window)

    # ------------------------------------------------------------------
    # Rectangle: needs two opposite corners.
    # ------------------------------------------------------------------
    point1 = rg.Point(100, 150)
    point2 = rg.Point(200, 50)
    rectangle = rg.Rectangle(point1, point2)
    rectangle.attach_to(window)

    # ------------------------------------------------------------------
    # render: Draw ALL the objects attached to this window.
    # ------------------------------------------------------------------
    window.render()

    # ------------------------------------------------------------------
    # A Rectangle has instance variables  corner_1  and  corner2.
    # ------------------------------------------------------------------
    corner1 = rectangle.corner_1
    corner2 = rectangle.corner_2
    print(corner1, corner2)  # You can also PRINT RoseGraphics objects.
    print(rectangle)  # See the Console for the output.

    # ------------------------------------------------------------------
    # close_on_mouse_click: Keeps the window open until user clicks.
    # ------------------------------------------------------------------
    window.close_on_mouse_click()

# ----------------------------------------------------------------------
# Calls  main  to start the ball rolling.
# ----------------------------------------------------------------------
main()
