"""
Demonstrates using OBJECTS via Turtle Graphics.
It is the same   m3_turtles.py   except that it organizes
the code into FUNCTIONS and then defines ADDITIONAL functions.

Concepts include:
  -- CONSTRUCT an INSTANCE of a CLASS (we call such instances OBJECTS).
  -- Make an object   ** DO **   something by using a METHOD.
  -- Reference an object's   ** DATA **   by using an INSTANCE VARIABLE.

Also:
  -- ASSIGNING a VALUE to a NAME (VARIABLE).
  -- ORGANIZING code into FUNCTIONS.
  -- MAIN as the place where execution starts.
  -- CALLING functions.

Authors: David Mutchler, Dave Fisher, Valerie Galluzzi, Amanda Stouder,
         and their colleagues.  March 2016.
"""
########################################################################
#
# STUDENTS:  This is an EXAMPLE file.
#
# RUN it, and then note that:
#  -- The   example_from_m3   function below contains the SAME code
#       that you saw in the  m3_turtles   module.
#  -- There are several OTHER examples that you can use
#       for inspiration when you do the NEXT module.
#
# SKIM the examples, just so that you know what is in here.
# Then proceed directly to m6 where you will use code like that
# in this example to do cool, creative things with SimpleTurtles!
#
########################################################################

import rosegraphics as rg


def main():
    """ Calls the other functions in this module to demo them. """
    example_from_m3()
    draw_you_guess_it()
    draw_pink_square()
    draw_squares_in_squares()
    cool_turtle()


def example_from_m3():
    """
    Constructs several SimpleTurtles and demonstrates their use.
    This code in this example is EXACTLY the same as that from the
        m3_turtles.py
    module that you saw previously except that the code is now
    INSIDE this FUNCTION (also this version runs the turtles faster).
    """
    # ------------------------------------------------------------------
    # Next two lines after this comment set up a   TurtleWindow   object
    # for animation.  The definition of a TurtleWindow is in the
    #   rg  (shorthand for rosegraphics) module.
    # ------------------------------------------------------------------
    window = rg.TurtleWindow()
    window.delay(1)  # Bigger numbers mean slower animation.

    # ------------------------------------------------------------------
    # Next two lines make (construct) two   SimpleTurtle   objects.
    # ------------------------------------------------------------------
    nadia = rg.SimpleTurtle()
    akil = rg.SimpleTurtle('turtle')

    # ------------------------------------------------------------------
    # Next lines ask the SimpleTurtle objects to do things:
    # ------------------------------------------------------------------
    nadia.forward(100)
    nadia.left(90)
    nadia.forward(200)

    akil.right(45)
    akil.backward(50)
    akil.right(60)

    nadia.forward(50)
    nadia.left(135)

    # ------------------------------------------------------------------
    # Next lines set the   pen   and  speed   characteristics of the
    # SimpleTurtle objects.  The   pen   characteristic is itself
    # an object that is constructed, of type Pen.
    # ------------------------------------------------------------------
    nadia.pen = rg.Pen('blue', 10)  # The  10   is the Pen's thickness
    nadia.speed = 10  # 1 is slowest, big is faster, maxes out about 100

    akil.pen = rg.Pen('red', 30)
    akil.speed = 1

    akil.backward(100)
    nadia.forward(100)

    nadia.left(60)
    nadia.forward(500)
    nadia.speed = 1  # was 10, so much slower now
    nadia.right(120)
    nadia.forward(200)

    window.close_on_mouse_click()


def draw_you_guess_it():
    """
    Constructs a window and a medium-speed, blue Turtle
    that draws a certain letter of the alphabet.
    """
    window = rg.TurtleWindow()

    tx = rg.SimpleTurtle('turtle')
    tx.pen = rg.Pen('blue', 20)
    tx.speed = 5  # Medium

    tx.left(60)
    tx.forward(200)

    tx.pen_up()
    tx.left(120)
    tx.forward(100)
    tx.left(120)

    tx.pen_down()
    tx.forward(200)

    window.close_on_mouse_click()


def draw_pink_square():
    """
    Constructs a window and a slow, pink SimpleTurtle
    that draws a square.
    """
    window = rg.TurtleWindow()

    pink_turtle = rg.SimpleTurtle('turtle')
    pink_turtle.pen = rg.Pen('DeepPink', 5)
    pink_turtle.speed = 1  # Slowest

    pink_turtle.draw_square(80)

    window.close_on_mouse_click()


def draw_squares_in_squares():
    """
    Constructs a window and a SimpleTurtle
    that draws squares within squares.
    """
    window = rg.TurtleWindow()

    square_turtle = rg.SimpleTurtle('turtle')
    square_turtle.pen = rg.Pen('midnight blue', 3)
    square_turtle.speed = 10  # Fast

    size = 300
    delta = 20

    # Do the indented code 13 times.  Each time draws a square.
    for _ in range(13):
        square_turtle.draw_square(size)

        # Move "inside" the previous square a bit.
        square_turtle.pen_up()
        point_inside = rg.Point(square_turtle.x_cor() + (delta // 2),
                                square_turtle.y_cor() + (delta // 2))
        square_turtle.go_to(point_inside)
        square_turtle.pen_down()

        # Next square will be a bit smaller.
        size = size - 20

    window.close_on_mouse_click()


def cool_turtle():
    """
    Constructs a window and a SimpleTurtle and makes her
    draw a pretty shape on the window.
    Uses the variables (see below):
        size   angle   iterations
    to control the nature of the shape that it draws.

    Both of these settings make pretty pictures, as do other settings:
      size = 100     angle = 1     iterations = 360
      size = 150     angle = 20    iterations = 90
    """
    # Make the TurtleWindow.
    window = rg.TurtleWindow()

    # Make the SimpleTurtle.
    cool_turtle = rg.SimpleTurtle('turtle')
    cool_turtle.pen = rg.Pen('forest green', 1)  # Try thickness 5 too
    cool_turtle.speed = 1  # Slow

    # Move the SimpleTurtle to her starting position.
    start_at = rg.Point(100, -50)
    cool_turtle.pen_up()
    cool_turtle.go_to(start_at)
    cool_turtle.pen_down()

    # Set up some parameters that control the nature of the shape drawn.
    size = 100  # Try 150 too
    angle = 1  # Try 20 too
    iterations = 360  # Try 90 too

    # Store the animation speed (to reset it later).
    tracer_n, tracer_d = window.tracer(), window.delay()

    # Make the animation go much faster.
    #   First number:  bigger means faster.
    #   Second number: bigger means slower.
    window.tracer(5, 5)

    for _ in range(iterations):
        cool_turtle.right(angle)
        cool_turtle.draw_square(size)

    # Reset the animation to its original speed.
    window.tracer(tracer_n, tracer_d)

    window.close_on_mouse_click()

# ----------------------------------------------------------------------
# Calls  main  to start the ball rolling.
# ----------------------------------------------------------------------
main()
