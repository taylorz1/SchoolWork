"""
Test 2, problem 3.

Authors: David Mutchler, Dave Fisher, Valerie Galluzzi, Amanda Stouder,
         their colleagues and Zachary Taylor.  Summer 2016.
"""  # DONE: 1. PUT YOUR NAME IN THE ABOVE LINE.

import simple_testing as st
import math


def main():
    """ Calls the   TEST   functions in this module. """
    test_problem3a()
    test_problem3b()


########################################################################
# Students:
#   Do NOT touch the following  Point  class - it has no TODO.
#   Do NOT copy code from the methods in this Point class.
#
#   DO  ** READ **  this Point class,
#     asking questions about any of it that you do not understand.
#
#   DO  ** CALL **  methods in this Point class as needed
#     in implementing and testing the functions you implement below.
#
#   IMPORTANT, IMPORTANT, IMPORTANT:
#     *** In the problems below,
#     *** you should NEVER have code
#     *** that a  ** Point **  class method could do for you.
########################################################################
class Point(object):
    """ Represents a point in 2-dimensional space. """

    def __init__(self, x, y):
        """
        Sets instance variables  x  and  y  to the given coordinates.
        """
        self.x = x
        self.y = y

    def __repr__(self):
        """
        Returns a string representation of this Point.
        For each coordinate (x and y), the representation
          - uses no decimal points if the number is close to an integer,
          - else it uses D places after the decimal point, where D = 2.
        Examples:
           Point(10, 3.14)
           Point(3.01, 2.99)
        """
        D = 2  # Use 2 places after the decimal point

        formats = []
        numbers = []
        for coordinate in (self.x, self.y):
            if abs(coordinate - round(coordinate)) < (10 ** -D):
                # Treat it as an integer:
                formats.append('{}')
                numbers.append(round(coordinate))
            else:
                # Treat it as a float to D decimal places:
                formats.append('{:.' + str(D) + 'f}')
                numbers.append(round(coordinate, D))

        format_string = 'Point(' + formats[0] + ', ' + formats[1] + ')'
        return format_string.format(numbers[0], numbers[1])

    def __eq__(self, p2):
        """
        Defines == for Points:  a == b   is equivalent to  a.__eq__(b).
        Treats two numbers as "equal" if they are within 6 decimal
        places of each other.
        """

        return (isinstance(p2, Point) and
                round(self.x, 6) == round(p2.x, 6) and
                round(self.y, 6) == round(p2.y, 6))

    def clone(self):
        """  Returns a new Point at the same (x, y) as this Point. """
        return Point(self.x, self.y)

    def distance_from(self, p2):
        """ Returns the distance this Point is from the given Point. """
        dx_squared = (self.x - p2.x) ** 2
        dy_squared = (self.y - p2.y) ** 2

        return math.sqrt(dx_squared + dy_squared)

    def halfway_to(self, p2):
        """
        Given another Point object p2, returns a new Point
        that is half-way between this Point and the given Point (p2).
        """
        return Point((self.x + p2.x) / 2,
                     (self.y + p2.y) / 2)

    def plus(self, p2):
        """
        Returns a Point whose coordinates are those of this Point
        plus the given Point.  For example:
            p1 = Point(500, 20)
            p2 = Point(100, 13)
            p3 = p1.plus(p2)
            print(p3)
        would print:   Point(600, 33)
        """
        return Point(self.x + p2.x, self.y + p2.y)

    def minus(self, p2):
        """
        Returns a Point whose coordinates are those of this Point
        minus the given Point.  For example:
            p1 = Point(500, 20)
            p2 = Point(100, 13)
            p3 = p1.minus(p2)
            print(p3)
        would print:   Point(400, 7)        
        """
        return Point(self.x - p2.x, self.y - p2.y)


########################################################################
# Students: Some of the testing code below uses SimpleTestCase objects,
#           from the imported   simple_testing (st)   module.
#           See details in the  test  code below.
########################################################################
def test_problem3a():
    """ Tests the    problem3a    function. """
    # ------------------------------------------------------------------
    # Several tests.
    # They use the imported   simple_testing (st)   module.
    # Each test is a SimpleTestCase with 3 arguments:
    #   -- the function to test,
    #   -- a list containing the argument(s) to send to the function,
    #   -- the correct returned value.
    # For example, the first test below will call
    #   problem3a(Point(2, 4),
    #            [Point(2, 10), Point(2, 13), Point(2, 7), Point(2, 11)])
    # and compare the returned value against Point(2, 13) (the correct answer).
    # ------------------------------------------------------------------
    tests = [st.SimpleTestCase(problem3a,
                               [(Point(30, 25),
                                 Point(90, 89),
                                 Point(49, 56),
                                 Point(33, 100))],
                               Point(49, 56)),
             st.SimpleTestCase(problem3a,
                               [(Point(49, 56),
                                 Point(30, 25),
                                 Point(90, 89),
                                 Point(33, 100))],
                               Point(49, 56)),
             st.SimpleTestCase(problem3a,
                               [(Point(30, 25),
                                 Point(90, 89),
                                 Point(49, 56))],
                               Point(49, 56)),
             st.SimpleTestCase(problem3a,
                               [[Point(30, 35)]],
                               Point(30, 35)),
             st.SimpleTestCase(problem3a,
                               [[Point(30, 25)]],
                               'No such point'),
             st.SimpleTestCase(problem3a,
                               [(Point(50, 4),
                                 Point(40, 13),
                                 Point(7, 7),
                                 Point(80, 11),
                                 )],
                                'No such point'),
             st.SimpleTestCase(problem3a,
                               [(Point(3, 4),
                                 Point(3000, 4000),
                                 )],
                                Point(3, 4)),
             st.SimpleTestCase(problem3a,
                               [(Point(50, 40),
                                 Point(99, 83),
                                 Point(33, 41),
                                 Point(70, 7),
                                 Point(88, 11),
                                 Point(44, 88),
                                 Point(55, 78),
                                 Point(95, 90),
                                 Point(90, 97),
                                 Point(50, 50),
                                 )],
                                Point(33, 41)),
             st.SimpleTestCase(problem3a,
                               [(Point(100, 43),
                                 Point(444, 88),
                                 Point(50, 40),
                                 Point(99, 83),
                                 Point(330, 41),
                                 Point(700, 7),
                                 Point(880, 11),
                                 Point(550, 78),
                                 Point(950, 90),
                                 Point(900, 97),
                                 Point(500, 50),
                                 )],
                                'No such point'),
             st.SimpleTestCase(problem3a,
                               [(Point(90, 43),
                                 Point(50, 40),
                                 Point(99, 83),
                                 Point(55, 41),
                                 Point(70, 7),
                                 Point(88, 11),
                                 Point(99, 78),
                                 Point(95, 90),
                                 Point(99, 97),
                                 Point(50, 50),
                                 Point(44, 88),
                                 )],
                                Point(44, 88)),
             ]

    st.SimpleTestCase.run_tests('problem3a', tests)


def problem3a(points):
    """
    What comes in:
      -- A sequence of Points
    What goes out:
      Returns the first (lefmost) Point in the given sequence of Points
      whose x-coordinate is less than its y-coordinate.
      Returns 'No such point' if there is no such point in the sequence.
    Side effects: None.
    Examples:
      If the given sequence is:
        (Point(30, 25), Point(90, 89), Point(49, 56), Point(33, 100))
      then this function returns:
        Point(49, 56)
      because its x-coordinate (49) is less than its y-coordinate (56)
      and the same is NOT true of the Points prior to it in the list
      (i.e., 30 is NOT less than 25 and 90 is NOT less than 89).
      
      If the given sequence is:
        (Point(30, 25), Point(90, 89), Point(49, 40), Point(33, 33))
      then this function returns the string 'No such point'
      since none of the Points in the sequence have its x-coordinate
      less than its y-coordinate.
    Type hints:
      :type points: [Point]
    """
    ####################################################################
    # DONE: 2. Implement and test this function.
    #     The testing code is already written for you (above).
    ####################################################################
    for k in range(len(points)):
        if points[k].x < points[k].y:
            return points[k]
    return 'No such point'

def test_problem3b():
    """ Tests the    problem3b    function. """
    # ------------------------------------------------------------------
    # Several tests.
    # They use the imported   simple_testing (st)   module.
    # Each test is a SimpleTestCase with 3 arguments:
    #   -- the function to test,
    #   -- a list containing the argument(s) to send to the function,
    #   -- the correct returned value.
    # For example, the first test below will call
    #   problem3b(Point(2, 4),
    #            [Point(2, 10), Point(2, 13), Point(2, 7), Point(2, 11)])
    # and compare the returned value against Point(2, 13) (the correct answer).
    # ------------------------------------------------------------------

    tests = [st.SimpleTestCase(problem3b,
                               [Point(2, 4),
                                (Point(2, 10),
                                 Point(2, 13),
                                 Point(2, 7),
                                 Point(2, 11),
                                 )],
                                Point(2, 13)),
             st.SimpleTestCase(problem3b,
                               [Point(3, 4),
                                (Point(50, 4),
                                 Point(4, 13),
                                 Point(7, 7),
                                 Point(8, 11),
                                 )],
                                Point(50, 4)),
             st.SimpleTestCase(problem3b,
                               [Point(3, 4),
                                (Point(5000, 4000),
                                 )],
                                Point(5000, 4000)),
             st.SimpleTestCase(problem3b,
                               [Point(100, 43),
                                (Point(50, 40),
                                 Point(99, 83),
                                 Point(33, 41),
                                 Point(70, 7),
                                 Point(88, 11),
                                 Point(44, 88),
                                 Point(55, 78),
                                 Point(95, 90),
                                 Point(90, 97),
                                 Point(50, 50),
                                 )],
                                Point(44, 88)),
             st.SimpleTestCase(problem3b,
                               [Point(100, 43),
                                (Point(44, 88),
                                 Point(50, 40),
                                 Point(99, 83),
                                 Point(33, 41),
                                 Point(70, 7),
                                 Point(88, 11),
                                 Point(55, 78),
                                 Point(95, 90),
                                 Point(90, 97),
                                 Point(50, 50),
                                 )],
                                Point(44, 88)),
             st.SimpleTestCase(problem3b,
                               [Point(100, 43),
                                (Point(50, 40),
                                 Point(99, 83),
                                 Point(33, 41),
                                 Point(70, 7),
                                 Point(88, 11),
                                 Point(55, 78),
                                 Point(95, 90),
                                 Point(90, 97),
                                 Point(50, 50),
                                 Point(44, 88),
                                 )],
                                Point(44, 88)),
             st.SimpleTestCase(problem3b,
                               [Point(100, 43),
                                (Point(50, 40),
                                 Point(99, 83),
                                 Point(33, 41),
                                 Point(70, 7),
                                 Point(88, 11),
                                 Point(44, 88),
                                 Point(55, 78),
                                 Point(95, 90),
                                 Point(90, 97),
                                 Point(50, 50),
                                 )],
                                Point(44, 88)),
             st.SimpleTestCase(problem3b,
                               [Point(100, 43),
                                (Point(50, 40),
                                 Point(99, 83),
                                 Point(33, 41),
                                 Point(70, 7),
                                 Point(88, 11),
                                 Point(44, 88),
                                 Point(55, 78),
                                 Point(95, 90),
                                 Point(90, 97),
                                 Point(50, 50),
                                 )],
                                Point(44, 88)),
             ]

    st.SimpleTestCase.run_tests('problem3b', tests)


def problem3b(p1, points):
    """
    What comes in:
      -- A Point p1
      -- A non-empty sequence of Points
    What goes out:
      Returns the Point in the given sequence of Points
      that is farthest from the given Point p1.
      (You can break ties however you wish.)
    Side effects: None.
    Example:
      If the given Point p1 is Point(2, 4)
      and the given sequence of Points is:
        (Point(2, 10), Point(2, 13), Point(2, 7), Point(2, 11))
      then this function returns:  Point(2, 13)
      because:
        the distance from (2, 10) to (2, 4) is 6
        the distance from (2, 13) to (2, 4) is 9
        the distance from (2, 7) to (2, 4) is 3
        the distance from (2, 11) to (2, 4) is 7
      and the largest of those distances is 9,
      which is the distance associated with Point(2, 13).
    
    NOTE: Although this example involves only x-coordinates,
    the problem (and other tests) expects real distances,
    per the usual distance formula between two points,
    for which you should the relevant method in the POINT class.
    
    Type hints:
      :type p1: Point
      :type points: [Point]
    """
    ####################################################################
    # DONE: 2. Implement and test this function.
    #     The testing code is already written for you (above).
    ####################################################################
    a = 0
    for k in range(len(points)):
        if a < points[k].distance_from(p1):
            a = points[k].distance_from(p1)
            b = points[k]
    return b

# ----------------------------------------------------------------------
# Calls  main  to start the ball rolling.
# ----------------------------------------------------------------------
main()
