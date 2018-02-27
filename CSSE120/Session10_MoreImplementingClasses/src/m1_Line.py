"""
A simple   Line   class.
NOTE: This is NOT rosegraphics -- it is your OWN Line class.

Authors: David Mutchler, Dave Fisher, Valerie Galluzzi, Amanda Stouder,
         their colleagues and Zachary Taylor.  March 2016.
"""  # DONE: 1. PUT YOUR NAME IN THE ABOVE LINE.

import math
import m1t_test_Line as m1t

########################################################################
# IMPORTANT:
#   Your instructor will help you get started on this exercise.
########################################################################

# ----------------------------------------------------------------------
# DONE: 2. With your instructor, READ THE INSTRUCTIONS
#   in file  m0_INSTRUCTIONS.txt, asking questions as needed.
#   Once you understand the instructions, mark this TODO as DONE.
# ----------------------------------------------------------------------

########################################################################
# NOTE: For ALL of the methods that you implement, the method is allowed
# to have additional side effects as needed by it and/or other methods.
########################################################################


def main():
    """
    Calls the   TEST   functions in this module, but ONLY if the method
    to be tested has at least a partial implementation.  That is,
    a  TEST   function will not be called until you begin work
    on the code that it is testing.
    """
    if m1t.is_implemented('__init__'):
        test_init()
    if m1t.is_implemented('clone'):
        test_clone()
    if m1t.is_implemented('reverse'):
        test_reverse()
    if m1t.is_implemented('slope'):
        test_slope()
    if m1t.is_implemented('length'):
        test_length()
    if m1t.is_implemented('get_number_of_clones'):
        test_get_number_of_clones()
    if m1t.is_implemented('line_plus'):
        test_line_plus()
    if m1t.is_implemented('line_minus'):
        test_line_minus()
    if m1t.is_implemented('midpoint'):
        test_midpoint()
    if m1t.is_implemented('is_parallel'):
        test_is_parallel()
    if m1t.is_implemented('intersection'):
        test_intersection()
    if m1t.is_implemented('reset'):
        test_reset()

########################################################################
# Students:
#   Do NOT touch the following  Point  class - it has no TODO.
#   Do NOT copy code from the methods in this Point class.
#
#   DO  ** READ **  this Point class,
#     asking questions about any of it that you do not understand.
#
#   DO  ** CALL **  methods in this Point class as needed
#     in implementing and testing the methods of the  ** Line **  class.
#
#   IMPORTANT, IMPORTANT, IMPORTANT:
#     *** In your  ** Line **  class methods,
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

        return (round(self.x, 6) == round(p2.x, 6) and
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
# The TEST functions for the  Line  class begin here.
########################################################################


def test_init():
    """ Tests the   __init__   method of the Line class. """
    m1t.test_init()  # This runs OUR tests.
    # ------------------------------------------------------------------
    # DONE: 3. This function tests the   __init__   method.
    # Add one ADDITIONAL test, putting it BELOW this TODO.
    # ------------------------------------------------------------------
    p1 = Point(30, 17)
    p2 = Point(50, 80)
    line = Line(p1, p2)  # Causes __init__ to run

    print(line.start)  # Should print Point(30, 17)
    print(line.end)  # Should print Point(50, 80)
    print(line.start == p1)  # Should print True
    print(line.start is p1)  # Should print False

    p3 = Point(50, 17)
    p4 = Point(50, 81)
    line = Line(p3, p4)
    print(line.start)
    print(line.end)
    print(line.start == p3)  # T
    print(line.start is p3)  # F



def test_clone():
    """ Tests the   clone   method of the Line class. """
    m1t.test_clone()  # This runs OUR tests.
    # ------------------------------------------------------------------
    # DONE: 5. This function tests the   clone   method.
    # Add one ADDITIONAL test, putting it BELOW this TODO.
    # ------------------------------------------------------------------
    p1 = Point(30, 17)
    p2 = Point(50, 80)
    line1 = Line(p1, p2)
    line2 = line1.clone()

    print(line1)  # Should print: Line[(30, 17), (50, 80)]
    print(line2)  # Should print: Line[(30, 17), (50, 80)]
    print(line1 == line2)  # Should print: True
    print(line1 is line2)  # Should print: False
    print(line1.start is line2.start)  # Should print: False
    print(line1.end is line2.end)  # Should print: False

    line1.start = Point(11, 12)
    print(line1)  # Should print: Line[(11, 12), (50, 80)]
    print(line2)  # Should print: Line[(30, 17), (50, 80)]
    print(line1 == line2)  # Should now print: False

    p1 = Point(50, 24)
    p2 = Point(63, 99)
    line1 = Line(p1, p2)
    line2 = line1.clone()

    print(line1)  # Should print: Line[(50, 24), (63, 99)]
    print(line2)  # Should print: Line[(50, 24), (63, 99)]
    print(line1 == line2)  # Should print: True
    print(line1 is line2)  # Should print: False
    print(line1.start is line2.start)  # Should print: False
    print(line1.end is line2.end)  # Should print: False

    line1.start = Point(11, 12)
    print(line1)  # Should print: Line[(11, 12), (50, 80)]
    print(line2)  # Should print: Line[(30, 17), (50, 80)]
    print(line1 == line2)  # Should now print: False


def test_reverse():
    """ Tests the   reverse   method of the Line class. """
    m1t.test_reverse()  # This runs OUR tests.
    # ------------------------------------------------------------------
    # DONE: 7. This function tests the   reverse   method.
    # Add one ADDITIONAL test, putting it BELOW this TODO.
    # ------------------------------------------------------------------
    p1 = Point(30, 17)
    p2 = Point(50, 80)
    line1 = Line(p1, p2)
    line2 = line1.clone()

    print(line1)  # Should print: Line[(30, 17), (50, 80)]

    line1.reverse()
    print(line1)  # Should print: Line[(50, 80), (30, 17)]
    print(line1 == line2)  # Should print: False

    line1.reverse()
    print(line1 == line2)  # Should now print: True

    p3 = Point(50, 80)
    p4 = Point(55, 100)
    line1 = Line(p3, p4)
    line2 = line1.clone()

    print(line1)

    line1.reverse()
    print(line1)
    print(line1 == line2)  # Should print: False

    line1.reverse()
    print(line1 == line2)  # Should now print: True



def test_slope():
    """ Tests the   slope   method of the Line class. """
    m1t.test_slope()  # This runs OUR tests.
    # ------------------------------------------------------------------
    # DONE: 9. This function tests the   slope   method.
    # Add one ADDITIONAL test, putting it BELOW this TODO.
    # ------------------------------------------------------------------
    p1 = Point(30, 3)
    p2 = Point(50, 8)
    line1 = Line(p1, p2)

    # Since the slope is (8 - 3) / (50 - 30) , which is 0.25:
    print(line1.slope())  # Should print [approximately]: 0.25

    line2 = Line(Point(10, 10), Point(10, 5))
    print(line2.slope())  # Should print:  inf

    # math.inf is NOT the STRING 'inf', so:
    print(line2.slope() == 'inf')  # Should print False

    p3 = Point(0, 1)
    p4 = Point(10, 10)
    line1 = Line(p3, p4)

    print(line1.slope())
    print('This should be', (10 - 1) / (10 - 0))



def test_length():
    """ Tests the   length   method of the Line class. """
    m1t.test_length()  # This runs OUR tests.
    # ------------------------------------------------------------------
    # DONE: 11. This function tests the   length   method.
    # Add one ADDITIONAL test, putting it BELOW this TODO.
    # ------------------------------------------------------------------
    p1 = Point(166, 10)
    p2 = Point(100, 10)
    line1 = Line(p1, p2)

    # Since the distance from p1 to p2 is 66:
    print(line1.length())  # Should print: 66.0

    p3 = Point(0, 0)
    p4 = Point(3, 4)
    line2 = Line(p3, p4)
    print(line2.length())  # Should print about 5.0

    p1 = Point(0, 1)
    p2 = Point(1, 1)
    line1 = Line(p1, p2)

    # Since the distance from p1 to p2 is N:
    print(line1.length())  # Should print: N


def test_get_number_of_clones():
    """ Tests the   get_number_of_clones   method of the Line class. """
    m1t.test_get_number_of_clones()  # This runs OUR tests.
    # ------------------------------------------------------------------
    # DONE: 13. This function tests the   get_number_of_clones   method.
    # Add one ADDITIONAL test, putting it BELOW this TODO.
    # ------------------------------------------------------------------
    line1 = Line(Point(500, 20), Point(100, 8))
    line2 = line1.clone()
    line3 = line1.clone()
    line4 = line3.clone()
    line5 = line1.clone()
    print(line1.get_number_of_clones())
    print(line2.get_number_of_clones())
    print(line3.get_number_of_clones())
    print(line4.get_number_of_clones())
    print(line5.get_number_of_clones())

    line6 = line1.clone()
    print(line6.get_number_of_clones())

def test_line_plus():
    """ Tests the   line_plus   method of the Line class. """
    m1t.test_line_plus()  # This runs OUR tests.
    # ------------------------------------------------------------------
    # DONE: 15. This function tests the   line_plus   method.
    # Add one ADDITIONAL test, putting it BELOW this TODO.
    # ------------------------------------------------------------------
    line1 = Line(Point(500, 20), Point(100, 8))
    line2 = Line(Point(100, 13), Point(400, 8))
    line3 = line1.line_plus(line2)
    print(line3)
    line4 = line1.line_plus(line1)
    print(line4)


def test_line_minus():
    """ Tests the   line_minus   method of the Line class. """
    m1t.test_line_minus()  # This runs OUR tests.
    # ------------------------------------------------------------------
    # DONE: 17. This function tests the   line_minus   method.
    # Add one ADDITIONAL test, putting it BELOW this TODO.
    # ------------------------------------------------------------------
    line1 = Line(Point(500, 20), Point(100, 8))
    line2 = Line(Point(100, 13), Point(400, 8))
    line3 = line1.line_minus(line2)
    print(line3)
    line4 = line1.line_minus(line1)
    print(line4)


def test_midpoint():
    """ Tests the   midpoint   method of the Line class. """
    m1t.test_midpoint()  # This runs OUR tests.
    # ------------------------------------------------------------------
    # DONE: 19. This function tests the   midpoint   method.
    # Add one ADDITIONAL test, putting it BELOW this TODO.
    # ------------------------------------------------------------------
    p1 = Point(3, 10)
    p2 = Point(9, 20)
    line1 = Line(p1, p2)

    print(line1.midpoint())  # Should print: Point(6, 15)


def test_is_parallel():
    """ Tests the   is_parallel   method of the Line class. """
    m1t.test_is_parallel()  # This runs OUR tests.
    # ------------------------------------------------------------------
    # DONE: 21. This function tests the   is_parallel   method.
    # Add one ADDITIONAL test, putting it BELOW this TODO.
    # ------------------------------------------------------------------
    line1 = Line(Point(15, 30), Point(17, 50))  # slope is 10.0
    line2 = Line(Point(10, 10), Point(15, 60))  # slope is 10.0
    line3 = Line(Point(10, 10), Point(80, 80))  # slope is  7.0
    line4 = Line(Point(10, 10), Point(10, 20))  # slope is inf

    print(line1.is_parallel(line2))  # Should print: True
    print(line2.is_parallel(line1))  # Should print: True
    print(line1.is_parallel(line3))  # Should print: False
    print(line1.is_parallel(line4))  # Should print: False
    print(line1.is_parallel(line1))  # Should print: True
    print(line4.is_parallel(line4))  # Should print: True


def test_intersection():
    """ Tests the   intersection   method of the Line class. """
    m1t.test_intersection()  # This runs OUR tests.
    # ------------------------------------------------------------------
    # DONE: 23. This function tests the   intersection   method.
    # Add one ADDITIONAL test, putting it BELOW this TODO.
    # ------------------------------------------------------------------
    # Here the line1 is horizontal and line2 is vertical.
    line1 = Line(Point(10, 0),
                 Point(20, 0))
    line2 = Line(Point(19, 2),
                 Point(19, -3))

    # The following should print: Point(19, 0)
    print(line1.intersection(line2))

    # But when we shorten line2, we should get: None
    line3 = Line(Point(19, 2),
                 Point(19, 1))
    print(line1.intersection(line3))  # Should print: None


def test_reset():
    """ Tests the   reset   method of the Line class. """
    m1t.test_reset()  # This runs OUR tests.
    # ------------------------------------------------------------------
    # DONE: 25. This function tests the   reset   method.
    # Add one ADDITIONAL test, putting it BELOW this TODO.
    # ------------------------------------------------------------------
    p1 = Point(-3, -4)
    p2 = Point(3, 4)
    line1 = Line(p1, p2)
    line2 = Line(Point(0, 1), Point(10, 20))

    line1.start = Point(100, 300)
    line2.end = Point(99, 4)
    line1.reverse()

    # Should print: Line[(x1, y1), (x2, y2)] where (x1, y1) and
    # (x2, y2) are the CURRENT coordinates of line1's endpoints.
    print(line1)
    print(line2)  # Similarly for line2

    line1.reset()
    line2.reset()
    print(line1)  # Should print: Line[(-3, -4), (3, 4)]
    print(line2)  # Should print: Line[(0, 1), (10, 20)]


########################################################################
# The   Line   class (and its methods) begins here.
########################################################################
class Line(object):
    """ Represents a line segment in 2-dimensional space. """

    def __init__(self, start, end):
        """
        What comes in:
          -- self
          -- a Point object named  start
          -- a Point object named  end
        where the two Points are to be the initial start and end points,
        respectively, of this Line.

        What goes out: Nothing (i.e., None).

        Side effects:  MUTATEs this Line by setting two instance
        variables named:
          -- start
          -- end
        to CLONES of the two Point arguments, respectively.
        Other methods must maintain those instance variables as needed
        so that they always indicate the CURRENT start and end points
        of this Line.

        Also, initializes other instance variables as needed
        by other Line methods.

        Example:  This   __init__  method runs when one constructs
        a Line.  So the 3rd of the following statements
        invokes the   __init__   method of this Line class:
            p1 = Point(30, 17)
            p2 = Point(50, 80)
            line = Line(p1, p2)        # Causes __init__ to run

            print(line.start)          # Should print Point(30, 17)
            print(line.end)            # Should print Point(50, 80)
            print(line.start == p1)    # Should print True
            print(line.start is p1)    # Should print False

        Type hints:
          :type start: Point
          :type end:   Point
        """
        # --------------------------------------------------------------
        # DONE: 4. Implement and test this method.  You should have
        #   already implemented its TEST function (above).
        # --------------------------------------------------------------
        self.start = start.clone()
        self.end = end.clone()
        self.clones = 0

        self.ostart = start
        self.oend = end

    def __repr__(self):
        """
        What comes in:
          -- self
        What goes out: Returns a string representation of this Line,
        in the form:
           Line[(x1, y1), (x2, y2)]
        Side effects: None.
        Note:  print(BLAH)  causes BLAH's __repr__ to be called.
               BLAH's __repr__ returns a string,
               which the  print  function then prints.

        Example:  Since the  print  function calls __repr__ on the
        object to be printed:
            p1 = Point(30, 17)
            p2 = Point(50, 80)
            line = Line(p1, p2)  # Causes __init__ to run

            # The following statement causes __repr__ to run,
            # hence should print: Line[(30, 17), (50, 80)]
            print(line)

        Type hints:
          :rtype str
        """
        # --------------------------------------------------------------
        # We have already implemented this  __repr__  function for you.
        # Do NOT modify it.
        # --------------------------------------------------------------
        start = repr(self.start).replace('Point', '')
        end = repr(self.end).replace('Point', '')
        return 'Line[{}, {}]'.format(start, end)

    def __eq__(self, line2):
        """
        What comes in:
          -- self
          -- a Line object
        What goes out: Returns  True  if:
             this Line's start point is equal to line2's start point AND
             this Line's end point is equal to line2's end point.
           Returns  False  otherwise.
        Side effects: None.
        Note:  a == b   is equivalent to  a.__eq__(b).

        Examples:
            p1 = Point(30, 17)
            p2 = Point(50, 80)

            line1 = Line(p1, p2)
            line2 = Line(p1, p2)
            line3 = Line(p2, p1)

            print(line1 == line1)   # Should print: True
            print(line1 == line2)   # Should print: True
            print(line1 == line3)   # Should print: False

            line1.start = Point(0, 0)
            print(line1 == line2)   # Should now print: False

        Type hints:
          :type  line2: Line
          :rtype bool
        """
        # --------------------------------------------------------------
        # We have already implemented this  __eq__  function for you.
        # Do NOT modify it.
        # --------------------------------------------------------------
        return (self.start == line2.start) and (self.end == line2.end)

    def clone(self):
        """
        What comes in:
          -- self
        What goes out: Returns a new Line whose START is a clone of
          this Line's START and whose END is a clone of this Line's END.
        Side effects: None.

        Example:
            p1 = Point(30, 17)
            p2 = Point(50, 80)
            line1 = Line(p1, p2)
            line2 = line1.clone()

            print(line1)  # Should print: Line[(30, 17), (50, 80)]
            print(line2)  # Should print: Line[(30, 17), (50, 80)]
            print(line1 == line2)              # Should print: True
            print(line1 is line2)              # Should print: False
            print(line1.start is line2.start)  # Should print: False
            print(line1.end is line2.end)      # Should print: False

            line1.start = Point(11, 12)
            print(line1)  # Should print: Line[(11, 12), (50, 80)]
            print(line2)  # Should print: Line[(30, 17), (50, 80)]
            print(line1 == line2)  # Should now print: False

        Type hints:
          :rtype Line
        """
        # --------------------------------------------------------------
        # DONE: 6. Implement and test this method.  You should have
        #   already implemented its TEST function (above).
        # --------------------------------------------------------------
        self.clones = self.clones + 1
        return Line(self.start, self.end)

    def reverse(self):
        """
        What comes in:
          -- self
        What goes out: Nothing (i.e., None).
        Side effects: MUTATES this Line so that its direction is
        reversed (that is, its start and end points are swapped).

        Examples:
            p1 = Point(30, 17)
            p2 = Point(50, 80)
            line1 = Line(p1, p2)
            line2 = line1.clone()

            print(line1)  # Should print: Line[(30, 17), (50, 80)]

            line1.reverse()
            print(line1)  # Should print: Line[(50, 80), (30, 17)]
            print(line1 == line2)    # Should print: False

            line1.reverse()
            print(line1 == line2)    # Should now print: True
        """
        # --------------------------------------------------------------
        # DONE: 8. Implement and test this method.  You should have
        #   already implemented its TEST function (above).
        # --------------------------------------------------------------
        alpha = self.end
        beta = self.start
        self.start = alpha
        self.end = beta

    def slope(self):
        """
        What comes in:
          -- self
        What goes out: Returns the slope of this Line.
        If the line is vertical (i.e., has "infinite" slope), returns
           math.inf
        Side effects: None.

        Examples:
            p1 = Point(30, 3)
            p2 = Point(50, 8)
            line1 = Line(p1, p2)

            # Since the slope is (8 - 3) / (50 - 30) , which is 0.25:
            print(line1.slope())    # Should print [approximately]: 0.25

            line2 = Line(Point(10, 10), Point(10, 5))
            print(line2.slope())    # Should print:  inf

            # math.inf is NOT the STRING 'inf', so:
            print(line2.slope() == 'inf')   # Should print False

        Type hints:
          :rtype float
        """
        # --------------------------------------------------------------
        # DONE: 10. Implement and test this method.  You should have
        #   already implemented its TEST function (above).
        # --------------------------------------------------------------
        if self.start.x - self.end.x == 0:
            return math.inf
        else:
            return (self.start.y - self.end.y) / (self.start.x - self.end.x)

    def length(self):
        """
        What comes in:
          -- self
        What goes out: Returns the length of this Line.
        Side effects: None.

        Example:
            p1 = Point(166, 10)
            p2 = Point(100, 10)
            line1 = Line(p1, p2)

            # Since the distance from p1 to p2 is 66:
            print(line1.length())  # Should print: 66.0

            p3 = Point(0, 0)
            p4 = Point(3, 4)
            line2 = Line(p3, p4)
            print(line2.length())  # Should print about 5.0

        Type hints:
          :rtype float
        """
        # --------------------------------------------------------------
        # DONE: 12. Implement and test this method.  You should have
        #   already implemented its TEST function (above).
        # --------------------------------------------------------------
        distance = (self.start.distance_from(self.end))
        return abs(distance)

    def get_number_of_clones(self):
        """
        What comes in:
          -- self
        What goes out:
          -- Returns the number of times that this Line has been cloned
               (via the   clone   method).
        Side effects: None.

        Example:
            line1 = Line(Point(500, 20), Point(100, 8))
            line2 = line1.clone()
            line3 = line1.clone()
            line4 = line3.clone()
            line5 = line1.clone()
            print(line1.get_number_of_clones())
            print(line2.get_number_of_clones())
            print(line3.get_number_of_clones())
            print(line4.get_number_of_clones())
            print(line5.get_number_of_clones())
        would print:
            3    [since there are three   line1.clone()  statements]
            0    [since there are no      line2.clone()  statements]
            1    [since there is one      line3.clone()  statement]
            0    [since there are no      line4.clone()  statements]
            0    [since there are no      line5.clone()  statements]

        Type hints:
          :rtype int:
        """
        # --------------------------------------------------------------
        # DONE: 14. Implement and test this method.  You should have
        #   already implemented its TEST function (above).
        # --------------------------------------------------------------
        return self.clones

    def line_plus(self, other_line):
        """
        What comes in:
          -- self
          -- another Line object
        What goes out:
          -- Returns a Line whose:
              -- start is the sum of this Line's start (a Point)
                   and the other_line's start (another Point).
              -- end is the sum of this Line's end (a Point)
                   and the other_line's end (another Point).
        Side effects: None.

        Example:
            line1 = Line(Point(500, 20), Point(100, 8))
            line2 = Line(Point(100, 13), Point(400, 8))
            line3 = line1.line_plus(line2)
            print(line3)
        would print:   Line[(600, 33), (500, 16)]

        Type hints:
          :type  other_line: Line
          :rtype Line:
        """
        # --------------------------------------------------------------
        # DONE: 16. Implement and test this method.  You should have
        #   already implemented its TEST function (above).
        # --------------------------------------------------------------
        return Line(self.start.plus(other_line.start), self.end.plus(other_line.end))

    def line_minus(self, other_line):
        """
        What comes in:
          -- self
          -- another Line object
        What goes out:
          -- Returns a Line whose:
              -- start is this Line's start (a Point)
                   minus the other_line's start (another Point).
              -- end is this Line's end (a Point)
                   minus the other_line's end (another Point).
        Side effects: None.

        Example:
            line1 = Line(Point(500, 20), Point(100, 8))
            line2 = Line(Point(100, 13), Point(400, 8))
            line3 = line1.line_minus(line2)
            print(line3)
        would print:   Line[(400, 7), (-300, 0)]

        Type hints:
          :type  other_line: Line
          :rtype Line:
        """
        # --------------------------------------------------------------
        # DONE: 18. Implement and test this method.  You should have
        #   already implemented its TEST function (above).
        # --------------------------------------------------------------
        return Line(self.start.minus(other_line.start), self.end.minus(other_line.end))

    def midpoint(self):
        """
        What comes in:
          -- self
        What goes out: returns a Point at the midpoint of this Line.
        Side effects: None.

        Example:
            p1 = Point(3, 10)
            p2 = Point(9, 20)
            line1 = Line(p1, p2)

            print(line1.midpoint())  # Should print: Point(6, 15)

        Type hints:
          :rtype Point
        """
        # --------------------------------------------------------------
        # DONE: 20. Implement and test this method.  You should have
        #   already implemented its TEST function (above).
        # --------------------------------------------------------------
        return self.start.halfway_to(self.end)

    def is_parallel(self, line2):
        """
        What comes in:
          -- self
          -- another Line object (line2)
        What goes out: Returns  True  if this Line is parallel to the
          given Line (line2).  Returns  False  otherwise.
        Side effects: None.

        Examples:
            line1 = Line(Point(15, 30), Point(17, 50))  # slope is 10.0
            line2 = Line(Point(10, 10), Point(15, 60))  # slope is 10.0
            line3 = Line(Point(10, 10), Point(80, 80))  # slope is  7.0
            line4 = Line(Point(10, 10), Point(10, 20))  # slope is inf

            print(line1.is_parallel(line2))   # Should print: True
            print(line2.is_parallel(line1))   # Should print: True
            print(line1.is_parallel(line3))   # Should print: False
            print(line1.is_parallel(line4))   # Should print: False
            print(line1.is_parallel(line1))   # Should print: True
            print(line4.is_parallel(line4))   # Should print: True

        Type hints:
          :type  line2: Line
          :rtype bool
        """
        # --------------------------------------------------------------
        # DONE: 22. Implement and test this method.  You should have
        #   already implemented its TEST function (above).
        #
        # IMPORTANT: When you test whether two FLOATING POINT numbers
        #   are "equal", you must ROUND each to (say) 12 decimal places.
        #   Otherwise, you risk the imprecision of floating-point
        #   arithmetic biting you.  For example, in REAL arithmetic,
        #         1 / (24 * math.pi - 20 * math.pi)
        #   and
        #         3 / (72 * math.pi - 60 * math.pi)
        #   are equal.  But in FLOATING point arithmetic, they are:
        #         0.07957747154594767
        #   and
        #         0.07957747154594765
        #   respectively (hence NOT equal).
        #   Try it out if you don't believe me!
        #
        # IMPORTANT BOTTOM-LINE:  When you want to test whether
        # two FLOATING POINT numbers  a  and  b  are the same,
        #   DON'T use:               a == b
        #   INSTEAD use:  round(a, 12) == round(b, 12)
        #
        # The latter compares the numbers rounded to 12 decimal places
        # which (usually) is adequate to ignore floating-point errors
        # and (usually) adequate to distinguish numbers that really
        # are different from each other.
        # --------------------------------------------------------------
        return round(self.slope(), 12) == round(line2.slope(), 12)

    def intersection(self, other_line):
        """
        What comes in:
          -- self
          -- another Line object
        What goes out: Returns a Point that represents the point
          where this Line intersects the given other_line Line object.
          Returns  None  if the Lines (which are really line SEGMENTS)
          do not intersect.
        Side effects: None.
        
        THIS PROBLEM IS ** OPTIONAL. **

        Examples:
            # Here the line1 is horizontal and line2 is vertical.
            line1 = Line(Point(10, 0),
                         Point(20, 0))
            line2 = Line(Point(19, 2),
                         Point(19, -3))

            # The following should print: Point(19, 0)
            print(line1.intersection(line2))

            # But when we shorten line2, we should get: None
            line3 = Line(Point(19, 2),
                         Point(19, 1))
            print(line1.intersection(line3))  # Should print: None

        Type hints:
          :type  line2: Line
          :rtype Point
        """
        # --------------------------------------------------------------
        # ** OPTIONAL ** TODO: 24. Implement and test this method.  You should have
        #   already implemented its TEST function (above).
        #
        # HINT:  Here is one way to compute the intersection point
        #   for two line segments:
        #   1. See if they are parallel.  If so, return None.
        #   2. Pretend that the line segments are really LINES
        #        (not just line SEGMENTS).  Find their intersection
        #        point (as LINES) -- there WILL be one.
        #   3. Check whether the x-coordinate of the intersection point
        #        is within the start.x to end.x range of BOTH
        #        this Line AND the other_line.
        #      If so, return the intersection point (as a Point object).
        #      If not, return None.
        # --------------------------------------------------------------
        if self.is_parallel(other_line) == True:
            return 'None'
        else:
            a = self.start.y
            b = self.start.x
            alpha = other_line.start.y
            beta = other_line.start.x
            m = self.slope()
            mu = other_line.slope()
            for x in range(self.start.x, self.end.x):
                if round(m * (x - a) + b, 12) == round(mu * (x - alpha) + beta, 12):
                    return Point(x, m * (x - a) + b)
                else:
                    return 'None'

    def reset(self):
        """
        What comes in:
          -- self
        What goes out: Nothing (i.e., None).
        Side effects: MUTATES this Line so that its start and end points
          revert to what they were when this Line was constructed.

        Examples:
            p1 = Point(-3, -4)
            p2 = Point(3, 4)
            line1 = Line(p1, p2)
            line2 = Line(Point(0, 1), Point(10, 20))

              ... [various actions, including some like these:]
            line1.start = Point(100, 300)
            line2.end = Point(99, 4)
            line1.reverse()

            # Should print: Line[(x1, y1), (x2, y2)] where (x1, y1) and
            # (x2, y2) are the CURRENT coordinates of line1's endpoints.
            print(line1)
            print(line2)  # Similarly for line2

            line1.reset()
            line2.reset()
            print(line1)  # Should print: Line[(-3, -4), (3, 4)]
            print(line2)  # Should print: Line[(0, 1), (10, 20)]
        """
        # --------------------------------------------------------------
        # DONE: 26. Implement and test this method.  You should have
        #   already implemented its TEST function (above).
        # --------------------------------------------------------------
        self.end = self.oend
        self.start = self.ostart


# ----------------------------------------------------------------------
# If this module is running at the top level (as opposed to being
# imported by another module), then call the 'main' function.
# ----------------------------------------------------------------------
if __name__ == '__main__':
    main()
