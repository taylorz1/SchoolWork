"""
This module demonstrates the WAIT-FOR-EVENT pattern:
   while True:
       ...
       if <event has occurred>:
           break
       ...

Authors: David Mutchler, Amanda Stouder, Chandan Rupakheti, Katie Dion,
         Claude Anderson, Delvin Defoe, Curt Clifton, Matt Boutell,
         and their colleagues. December 2013.
"""
# ----------------------------------------------------------------------
# Students: Read and run this program.  There is nothing else
#           for you to do in here.  Just use it as an example.
#
#           Before you leave this example,
#   *** MAKE SURE YOU UNDERSTAND THE   WAIT-FOR-EVENT   PATTERN,
#   *** with its use of   while True:   and   break.
# ----------------------------------------------------------------------

import math
import random
import rosegraphics as rg


def main():
    """ Demonstrates applications of the wait-for-event pattern. """
    demonstrate_wait_for_circle_to_reach_edge()
    demonstrate_wait_for_sentinel()
    demonstrate_wait_for_small_enough_number()


# ----------------------------------------------------------------------
# Demonstrates waiting for a sequence of "growing" circles
# to reach the edge of the window in which they are drawn.
# ----------------------------------------------------------------------
def demonstrate_wait_for_circle_to_reach_edge():
    """
    Demonstrates the   wait_for_event   pattern, where the event
    is that a growing graphical object has grown beyond the window size.

    This particular example draws, moves and grows purple circles
    until a circle extends beyond the border of the window.
    """
    print()
    print('---------------------------------------------------------')
    print('Demonstrating the WAIT FOR EVENT pattern in graphics:')
    print('See the graphics window that pops up.')
    print('---------------------------------------------------------')

    window = rg.RoseWindow(700, 450, 'Animation until a TOO-BIG circle')

    x = 20
    y = 50
    radius = 5
    k = 0
    window.continue_on_mouse_click()

    while True:
        # Construct and draw a purple circle.
        circle = rg.Circle(rg.Point(x, y), radius)
        circle.fill_color = 'purple'
        circle.attach_to(window)

        # If the circle has reached a right/bottom border of the window,
        # break out of the loop
        right_edge = x + radius
        bottom_edge = y + radius

        if right_edge >= window.width or bottom_edge >= window.height:
            break

        # Make the next circle be down, to the right, and bigger.
        k = k + 1
        x = x + 2
        y = y + 1
        radius = radius + (k / 100)

        # Render.  Allow a little time to elapse,
        #          else the animation flashes by.
        window.render(0.01)

    window.close_on_mouse_click()


# ----------------------------------------------------------------------
# Demonstrates waiting for a "sentinel" value to be input.
# ----------------------------------------------------------------------
def demonstrate_wait_for_sentinel():
    """
    Demonstrates the   wait_for_event   pattern, where the event
    is inputting a SENTINEL value to signal the end of user input.

    This particular example inputs integers and processes them by
    printing their square roots, and when input is finished,
    printing the sum of those square roots.  User input stops when
    the user inputs the agreed-upon SENTINEL value of -1.
    """
    print()
    print('----------------------------------------------')
    print('Demonstrating the WAIT FOR SENTINEL pattern:')
    print('----------------------------------------------')

    total = 0
    while True:
        number = int(input('Enter a positive integer, or -1 to quit: '))
        if number == -1:
            break
        print(math.sqrt(number))
        total = total + math.sqrt(number)

    print('The total is', total)


# ----------------------------------------------------------------------
# Demonstrates waiting for a "small enough" random number
# to be generated.  This demonstration runs the function:
#    wait_for_small_enough_number_generated
# several times, each time printing how long the function had to "wait"
# until a small enough random number was generated.
# ----------------------------------------------------------------------
def demonstrate_wait_for_small_enough_number():
    print()
    print('----------------------------------------------------------')
    print('Demonstrating the WAIT FOR SMALL ENOUGH NUMBER function:')
    print('----------------------------------------------------------')

    small = 3
    smallest = 1
    biggest = 100
    trials = 5

    # The following uses the FORMAT method that we will study later.
    s1 = 'I will now repeatedly generate numbers between {} and {},'
    s2 = 'stopping when I generate a number less than or equal to {}.'
    s3 = 'I will do this process {} times.  EACH time demonstrates'
    s4 = 'one application of the wait-for-event pattern.\n'
    print(s1.format(smallest, biggest))
    print(s2.format(small))
    print(s3.format(trials))
    print(s4)

    for k in range(trials):
        message1 = 'Generated random numbers from {} to {}, trial {:2}.'
        message2 = '   Waiting for a generated number to be'
        message3 = '   less than or equal to {}.'
        print(message1.format(smallest, biggest, k + 1))
        print(message2)
        print(message3.format(small))

        result = wait_for_small_enough_number(small, biggest)

        message2 = '   Trial {:2}: waited for {:3} random numbers.'
        print(message2.format(k + 1, result))


def wait_for_small_enough_number(small_number, max_number):
    """
    Demonstrates the   wait_for_event   pattern, where random numbers
    between 1 and max_number, inclusive, are repeatedly generated.
    The event is that a number less than or equal to small_number is
    generated, where small_number and max_number are as given.

    The function returns the number of random numbers generated,
    including the one that causes the waiting-loop to terminate.
    """
    count = 0
    while True:
        count = count + 1
        number = random.randrange(1, max_number + 1)
        if number <= small_number:
            break

    return count

# ----------------------------------------------------------------------
# If this module is running at the top level (as opposed to being
# imported by another module), then call the 'main' function.
# ----------------------------------------------------------------------
if __name__ == '__main__':
    main()
