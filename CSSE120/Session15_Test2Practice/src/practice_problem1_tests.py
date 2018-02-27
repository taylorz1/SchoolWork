"""
TESTS the   Animated_Circle   class in module   m1_Animated_Circle.

Authors: David Mutchler, Dave Fisher, Valerie Galluzzi, Mark Hays,
         Amanda Stouder and their colleagues.  April 2016.
"""

import sys
import time
import inspect
import random
import rosegraphics as rg
import practice_problem1 as p1

########################################################################
# Change this to False if you want to see only failures (if any).
PRINT_SUCCESSES = False
########################################################################

# CONSIDER: I should have NOT used these global variables,
# but I ran out of time.  I will fix it for next term.
global WINDOW
WINDOW = None
rg._ShapeWithText.defaults['font_size'] = 11  # So messages fit
WIDTH = 600
HEIGHT = 500


def main():
    """ Calls the   TEST   functions in this module. """
    test_init()
    test_swell_or_shrink_once()
    test_swell_or_shrink_repeatedly()
    test_swallow()
    test_change_color()
    test_change_to_original_color()
    test_swell_to_prime()
    test_change_to_previous_color()


def evaluate_test(expected, actual, test_title=None, flush_time=0.05):
    """
    Prints the (optional) test_title,
    then prints the expected and actual results for the test.
    If the test FAILED, also prints a failure message in red.
    """
    # If expected is a NUMBER, then actual must also be a number,
    # and their values, rounded to 12 decimal places, must be the same.
    # both in type and in value.  If expected is NOT a number,
    # then actual must match in in both type and value.
    if is_a_number(expected):
        passes_test = (is_a_number(actual) and
                       round(actual, 12) == round(expected, 12))
    else:
        passes_test = (type(actual) is type(expected) and
                       actual == expected)

    if PRINT_SUCCESSES or not passes_test:
        print()
        if test_title:
            print(test_title)
        print('Expected:', expected)
        print('Actual:  ', actual, flush=True)
        time.sleep(flush_time)

    if not passes_test:
        print_failure(flush_time=flush_time)


def is_a_number(x):
    """ Returns True if x is an int or a float. """
    return (type(x) is int) or (type(x) is float)


def print_failure(message='  *** FAILED the above test. ***',
                  flush_time=0.05):
    """ Prints a message onto stderr, hence in RED. """
    print(message,
          file=sys.stderr, flush=True)
    time.sleep(flush_time)


def is_implemented(animated_circle_method, expected_lines=2):
    """ True if the given Animated_Circle method is not yet implemenented. """
    # There is probably a better way to do this...
    method = getattr(p1.Animated_Circle, animated_circle_method)
    source = inspect.getsource(method)
    doc_string = method.__doc__
    expected = source.replace(doc_string, '')
    lines_left = expected.splitlines()

    return len(lines_left) > expected_lines


def start_drawing(title=None):
    global WINDOW
    if WINDOW:
        WINDOW.close()
    WINDOW = rg.RoseWindow(WIDTH, HEIGHT, title)


def draw(animated_circle, message=None):
    """
    Type hints:
      :type animated_circle: Animated_Circle
      :type message: str
    """
    global WINDOW
    if not WINDOW:
        WINDOW = rg.RoseWindow(WIDTH, HEIGHT)

    animated_circle.circle.attach_to(WINDOW)
    WINDOW.render()

    if message is None:
        WINDOW.render(animated_circle.seconds_to_sleep *
                      animated_circle.animation_factor)
    elif type(message) is float or type(message) is int:
        if message > 0:
            WINDOW.render(message * animated_circle.animation_factor)
    elif message:
        if WINDOW.title:
            prefix = WINDOW.title + '.  You should see:\n'
        else:
            prefix = 'You should see:\n'
        suffix = '\nTo continue, click anywhere in this window.'
        screen_message = prefix + message.strip() + suffix
        from_bottom = HEIGHT - (12 * (screen_message.count('\n') + 1))
        WINDOW.continue_on_mouse_click(screen_message,
                                       y_position=from_bottom)


def evaluate_animated_circle(animated_circle,
                             x, y, radius, color, thickness, colors):
    expected_circle = rg.Circle(rg.Point(x, y), radius)
    expected_circle.fill_color = color
    expected_circle.outline_thickness = thickness
    instance_variables = ('center', 'radius', 'fill_color',
                          'outline_thickness')
    for instance_variable in instance_variables:
        expected = getattr(expected_circle, instance_variable)
        actual = getattr(animated_circle.circle, instance_variable)
        title = 'Testing the   ' + instance_variable + '   attribute:'
        evaluate_test(expected, actual, title)

    evaluate_test(colors, animated_circle.colors,
                  'Testing the   colors   attribute:')

########################################################################
# The TEST functions for the  Animated_Circle  class begin here.
########################################################################


def test_init():
    """ Tests the   __init__   method of the Animated_Circle class. """
    print()
    print('-----------------------------------------------------------')
    print('Testing the   __init__   method:')
    print('-----------------------------------------------------------')
    if not is_implemented('__init__'):
        return

    print('The following are OUR tests (from practice_problem1_tests):')

    colors = ('red', 'green', 'blue')
    animated_circles = []
    try:
        for k in range(1, 3):
            ac = p1.Animated_Circle(100 * k,
                                    20 * k,
                                    50 + k,
                                    colors[k % len(colors)],
                                    k * colors)

            animated_circles.append(ac)
    except:
        print_failure()
        print_failure('Something broke in your   __init__   function.')
        print_failure('The following error messages may be helpful.')
        raise

    for k in range(1, 3):
        for attribute in ('circle', 'colors'):
            try:
                getattr(animated_circles[k - 1], attribute)
            except AttributeError:
                print_failure()
                print_failure('Your Animate_Circle class does not seem')
                print_failure('to have a   ' + attribute +
                              '   instance variable.')
                print_failure('Stopping tests of __init__ here.')
                return

        if type(animated_circles[k - 1].circle) is not rg.Circle:
            print_failure()
            print_failure('Your  circle  instance variable')
            print_failure('does not seem to be an rg.Circle.')
            print_failure('Stopping tests of __init__ here.')

        if type(animated_circles[k - 1].colors) is not tuple:
            print_failure()
            print_failure('Your  colors  instance variable')
            print_failure('does not seem to be a tuple.')
            print_failure('Tuples are like lists, but with parentheses')
            print_failure("like this:  ('red', 'blue', 'pink')")
            print_failure('Stopping tests of __init__ here.')

        evaluate_test(100 * k,
                      animated_circles[k - 1].circle.center.x,
                      'Testing x-coordinate of the  circle  attribute')
        evaluate_test(20 * k,
                      animated_circles[k - 1].circle.center.y,
                      'Testing y-coordinate of the  circle  attribute')
        evaluate_test(50 + k,
                      animated_circles[k - 1].circle.radius,
                      'Testing radius of the  circle  attribute')
        evaluate_test(colors[k % len(colors)],
                      animated_circles[k - 1].circle.fill_color,
                      'Testing fill_color of the  circle  attribute')
        evaluate_test(k * colors,
                      animated_circles[k - 1].colors,
                      'Testing the  colors  attribute')

    print()
    print('-----------------------------------------------------------')
    print('Here is the test in practice_problem1 itself:')
    print('-----------------------------------------------------------')


def test_swell_or_shrink_once():
    """ Tests the   swell_or_shrink_once   method. """
    print()
    print('-----------------------------------------------------------')
    print('Testing the   swell_or_shrink_once   method')
    print('-----------------------------------------------------------')
    if not is_implemented('swell_or_shrink_once'):
        return

    print('The following are OUR tests (from practice_problem1_tests):')
    random.seed(42)  # Lets us predict the results of tests.

    animated_circles = []
    colors1 = ('dark red', 'green', 'tomato', 'blue', 'plum',
               'seashell', 'red', 'peach puff', 'coral', 'chocolate',
               'brown')
    ac1 = p1.Animated_Circle(100, 110, 20, 'violet', colors1)
    animated_circles.append(ac1)
    colors2 = ('yellow', 'magenta', 'blue', 'lavender', 'cyan', 'aqua',
               'black', 'salmon', 'hot pink', 'forest green',
               'spring green')
    ac2 = p1.Animated_Circle(200, 220, 50, 'orange', colors2)
    animated_circles.append(ac2)

    colors = (('green', 'plum', 'blue'),
              ('magenta', 'hot pink', 'forest green'))
    thicknesses = ((13, 3, 6),
                   (5, 13, 4))
    xs, ys, rs = (100, 200), (110, 220), (20, 50)
    colors_tuples = (colors1, colors2)

    for k in range(1, 3):
        total = 0
        for j in range(1, 4):
            amount = 10 * k * (2 * j - 3)
            total = total + amount
            animated_circles[k - 1].swell_or_shrink_once(amount)
#             print(animated_circles[k - 1].circle.fill_color,
#                   animated_circles[k - 1].circle.outline_thickness)
            evaluate_animated_circle(animated_circles[k - 1],
                                     xs[k - 1], ys[k - 1],
                                     rs[k - 1] + total,
                                     colors[k - 1][j - 1],
                                     thicknesses[k - 1][j - 1],
                                     colors_tuples[k - 1])

    print()
    print('-----------------------------------------------------------')
    print('Here is the test in practice_problem1 itself:')
    print('-----------------------------------------------------------')


def test_swell_or_shrink_repeatedly():
    """ Tests the   swell_or_shrink_repeatedly   method. """
    print()
    print('-----------------------------------------------------------')
    print('Testing the  swell_or_shrink_repeatedly   method:')
    print('-----------------------------------------------------------')
    if not is_implemented('swell_or_shrink_repeatedly'):
        return

    print('The following are OUR tests (from practice_problem1_tests):')
    print('No tests, since this requires VISUAL tests.')

    print()
    print('-----------------------------------------------------------')
    print('Here is the test in practice_problem1 itself:')
    print('-----------------------------------------------------------')


def test_swallow():
    """ Tests the   swallow   method. """
    print()
    print('-----------------------------------------------------------')
    print('Testing the   swallow   method:')
    print('-----------------------------------------------------------')
    if not is_implemented('swallow'):
        return

    print('The following are OUR tests (from practice_problem1_tests):')

    animated_circles = []
    colors1 = ('dark red', 'green', 'tomato', 'blue', 'plum',
               'seashell', 'red', 'peach puff', 'coral', 'chocolate',
               'brown')
    ac1 = p1.Animated_Circle(100, 110, 20, 'violet', colors1)
    animated_circles.append(ac1)
    colors2 = ('yellow', 'magenta', 'blue', 'lavender', 'cyan', 'aqua',
               'black', 'salmon', 'hot pink', 'forest green',
               'spring green')
    ac2 = p1.Animated_Circle(200, 220, 50, 'orange', colors2)
    animated_circles.append(ac2)

    x, y, r = 150, 165, 70
    color = 'red'
    thickness = 1
    colors_tuples = (colors1, colors2)
    for k in range(1, 3):
        # Following does ac 0 vs ac 1, then ac 1 vs ac 0.
        colors_tuple = colors_tuples[k - 2] + colors_tuples[k - 1]
        ac = animated_circles[k - 2].swallow(animated_circles[k - 1])

        evaluate_animated_circle(ac, x, y, r, color, thickness,
                                 colors_tuple)

    print()
    print('-----------------------------------------------------------')
    print('Here is the test in practice_problem1 itself:')
    print('-----------------------------------------------------------')


def test_change_color():
    """ Tests the   change_color   method. """
    print()
    print('-----------------------------------------------------------')
    print('Testing the   change_color   method:')
    print('-----------------------------------------------------------')
    if not is_implemented('change_color'):
        return

    print('The following are OUR tests (from practice_problem1_tests):')
    random.seed(42)  # Makes the tests deterministic, so repeatable.

    animated_circles = []
    colors1 = ('dark red', 'green', 'tomato', 'blue', 'plum',
               'seashell', 'red', 'peach puff', 'coral', 'chocolate',
               'brown')
    ac1 = p1.Animated_Circle(100, 110, 20, 'violet', colors1)
    animated_circles.append(ac1)
    colors2 = ('yellow', 'magenta', 'blue', 'lavender', 'cyan', 'aqua',
               'black', 'salmon', 'hot pink', 'forest green',
               'spring green')
    ac2 = p1.Animated_Circle(200, 220, 50, 'orange', colors2)
    animated_circles.append(ac2)

    xs, ys, rs = (100, 200), (110, 220), (20, 50)
    colors_tuples = (colors1, colors2)
    thickness = 1

    for k in range(2):
        js = list(range(len(animated_circles[k].colors)))
        random.shuffle(js)
        for j in js:
            animated_circles[k].change_color(j)
            evaluate_animated_circle(animated_circles[k],
                                     xs[k], ys[k], rs[k],
                                     animated_circles[k].colors[j],
                                     thickness,
                                     colors_tuples[k])

    print()
    print('-----------------------------------------------------------')
    print('Here is the test in practice_problem1 itself:')
    print('-----------------------------------------------------------')


def test_change_to_original_color():
    """ Tests the   change_to_original_color   method. """
    print()
    print('-----------------------------------------------------------')
    print('Testing the   change_to_original_color   method')
    print('-----------------------------------------------------------')
    if not is_implemented('change_to_original_color'):
        return

    print('The following are OUR tests (from practice_problem1_tests):')

    animated_circles = []
    colors1 = ('dark red', 'green', 'tomato', 'blue', 'plum',
               'seashell', 'red', 'peach puff', 'coral', 'chocolate',
               'brown')
    ac1 = p1.Animated_Circle(100, 110, 20, 'violet', colors1)
    animated_circles.append(ac1)
    colors2 = ('yellow', 'magenta', 'blue', 'lavender', 'cyan', 'aqua',
               'black', 'salmon', 'hot pink', 'forest green',
               'spring green')
    ac2 = p1.Animated_Circle(200, 220, 50, 'orange', colors2)
    animated_circles.append(ac2)

    xs, ys, rs = (100, 200), (110, 220), (20, 50)
    colors = ('violet', 'orange')
    colors_tuples = (colors1, colors2)
    thickness = 1

    for k in range(2):
        animated_circles[k].fill_color = 'ghost white'
        animated_circles[k].change_to_original_color()
        evaluate_animated_circle(animated_circles[k],
                                 xs[k], ys[k], rs[k],
                                 colors[k],
                                 thickness,
                                 colors_tuples[k])

    print()
    print('-----------------------------------------------------------')
    print('Here is the test in practice_problem1 itself:')
    print('-----------------------------------------------------------')


def test_swell_to_prime():
    """ Tests the   swell_to_prime   method. """
    print()
    print('-----------------------------------------------------------')
    print('Testing the   swell_to_prime   method:')
    print('-----------------------------------------------------------')
    if not is_implemented('swell_to_prime'):
        return

    print('The following are OUR tests (from practice_problem1_tests):')

    animated_circles = []
    colors1 = ('dark red', 'green', 'tomato', 'blue', 'plum',
               'seashell', 'red', 'peach puff', 'coral', 'chocolate',
               'brown')
    ac1 = p1.Animated_Circle(100, 110, 1, 'violet', colors1)
    animated_circles.append(ac1)
    colors2 = ('yellow', 'magenta', 'blue', 'lavender', 'cyan', 'aqua',
               'black', 'salmon', 'hot pink', 'forest green',
               'spring green')
    ac2 = p1.Animated_Circle(200, 220, 15680, 'orange', colors2)
    animated_circles.append(ac2)

    xs, ys = (100, 200), (110, 220)
    colors = ('violet', 'orange')
    colors_tuples = (colors1, colors2)
    thickness = 1

    primes = ((2, 3, 5, 7, 11, 13, 17, 19,
               23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73,
               79, 83, 89, 97, 101),
              (15683, 15727, 15731, 15733, 15737, 15739, 15749,
               15761, 15767, 15773, 15787, 15791, 15797, 15803))
    for k in range(2):
        for j in range(len(primes[k])):
            animated_circles[k].swell_to_prime()
            evaluate_animated_circle(animated_circles[k],
                                     xs[k], ys[k],
                                     primes[k][j],
                                     colors[k],
                                     thickness,
                                     colors_tuples[k])

    print()
    print('-----------------------------------------------------------')
    print('Here is the test in practice_problem1 itself:')
    print('-----------------------------------------------------------')


def test_change_to_previous_color():
    """ Tests the   change_to_previous_color   method. """
    print()
    print('-----------------------------------------------------------')
    print('Testing the   change_to_previous_color   method:')
    print('-----------------------------------------------------------')
    if not is_implemented('change_to_previous_color'):
        return

    print('The following are OUR tests (from practice_problem1_tests):')
    random.seed(42)  # Makes the tests deterministic, so repeatable.

    animated_circles = []
    colors1 = ('dark red', 'green', 'tomato', 'blue', 'plum',
               'seashell', 'red', 'peach puff', 'coral', 'chocolate',
               'brown')
    ac1 = p1.Animated_Circle(100, 110, 20, 'violet', colors1)
    animated_circles.append(ac1)
    colors2 = ('yellow', 'magenta', 'blue', 'lavender', 'cyan', 'aqua',
               'black', 'salmon', 'hot pink', 'forest green',
               'spring green')
    ac2 = p1.Animated_Circle(200, 220, 50, 'orange', colors2)
    animated_circles.append(ac2)

    xs, ys, rs = (100, 200), (110, 220), (20, 50)
    colors = ('violet', 'orange')
    colors_tuples = (colors1, colors2)
    thickness = 1

    for _ in range(3):
        for k in range(2):
            colors = animated_circles[k].colors
            js = list(range(len(colors)))
            random.shuffle(js)
            j2s = list(range(len(colors)))
            random.shuffle(j2s)
#             print(js)
#             print(j2s)

            for j in range(len(colors)):
                animated_circles[k].circle.fill_color = colors[js[j]]
                animated_circles[k].change_color(j2s[j])
            color = colors[js[-1]]
            animated_circles[k].change_to_previous_color()
            evaluate_animated_circle(animated_circles[k],
                                     xs[k], ys[k], rs[k],
                                     color,
                                     thickness,
                                     colors_tuples[k])

    print()
    print('-----------------------------------------------------------')
    print('Here is the test in practice_problem1 itself:')
    print('-----------------------------------------------------------')

# ----------------------------------------------------------------------
# If this module is running at the top level (as opposed to being
# imported by another module), then call the 'main' function.
# ----------------------------------------------------------------------
if __name__ == '__main__':
    main()
