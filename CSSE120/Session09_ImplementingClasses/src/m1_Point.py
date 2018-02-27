"""
A simple   Point   class.
NOTE: This is NOT rosegraphics -- it is your OWN Point class.

Authors: David Mutchler, Dave Fisher, Valerie Galluzzi, Amanda Stouder,
         their colleagues and Zachary Taylor.  March 2016.
"""  # DONE: 1. PUT YOUR NAME IN THE ABOVE LINE.


def main():
    """ Calls the   TEST   functions in this module. """
    test_init()
    test_repr()
    test_clone()
    test_move_to()
    test_move_by()
    test_get_number_of_moves_made()
    test_get_distance_from()
    test_get_distance_from_start()
    test_get_distance_traveled()
    test_closer_to()
    test_halfway_to()

########################################################################
# IMPORTANT:
#   Your instructor will help you get started on this exercise.
########################################################################

# ----------------------------------------------------------------------
# DONE: 2. With your instructor, READ THE INSTRUCTIONS
#   in file  m0_INSTRUCTIONS.txt, asking questions as needed.
#
#   Then implement a class called   Point   that has NO METHODS yet,
#   just the lines that start the definition of any class:
#
#      class NAME_OF_CLASS(object):
#          """ Brief description of what objects of the class 'are'."""
#
#   Run the program and correct any syntax (notational) errors.
# ----------------------------------------------------------------------

########################################################################
# NOTE: For ALL of the methods that you implement, the method is allowed
# to have additional side effects as needed by it and/or other methods.
########################################################################
class Point(object):
    """ Represents a point in two-dimensional space. """
    def __init__(self, x, y):
        """ Initializes the Point's coordinates to the given x and y."""
        self.x = x
        self.y = y
        self.moves = 0
        self.clone1 = x
        self.clone2 = y
        self.distance = 0


    def __repr__(self):
        return "Point({},{})".format(self.x, self.y)

    def clone(self):
        return Point(self.x, self.y)

    def get_distance_from(self, p):
        distance = ((self.x - p.x) ** 2 + (self.y - p.y) ** 2) ** (1 / 2)
        return distance

    def move_to(self, x, y):
        distancex = self.x
        distancey = self.y
        self.x = x
        self.y = y
        self.moves = self.moves + 1
        self.distance = self.distance + self.get_distance_from(Point(distancex, distancey))

    def move_by(self, dx, dy):
        self.x = self.x + dx
        self.y = self.y + dy
        self.moves = self.moves + 1
        self.distance = self.distance + self.get_distance_from(Point(self.x - dx, self.y - dy))

    def get_number_of_moves_made(self):
        return self.moves


    def get_distance_from_start(self):
        distance = self.get_distance_from(Point(self.clone1, self.clone2))
        return distance

    def get_distance_traveled(self):
        return self.distance

    def closer_to(self, p2, p3):
        if p2.get_distance_from(self) > p3.get_distance_from(self):
            return p3
        else:
            return p2

    def halfway_to(self, p2):
        return Point((self.x + p2.x) / 2, (self.y + p2.y) / 2)

def test_init():
    """
    Tests the   __INIT__   method of the Point class.
      -- IMPORTANT:  There are   TWO  underscores on each side.
      -- Note:  the   __init__  method runs when one constructs
         a Point.  See examples below.

    Here is the specification for the   __init__   method:
    What comes in:
       -- self
       -- an integer x
       -- an integer y
       where (x, y) is to be the initial position of this Point.
    What goes out:  Nothing (i.e., None).
    Side effects:  Sets two instance variables named:
           x
           y
       to the given coordinate (i.e., to the given x and y).
       Other methods should modify the instance variables
           x
           y
       as needed so that they always indicate the CURRENT position
       of the Point.

    EXAMPLE: The following shows   __INIT__   in action.
    You may also use this example to test this method.

        p1 = Point(30, 18)
        print()
        print('Expected for p1: 30 18')
        print('Actual for p1:  ', p1.x, p1.y)

        p2 = Point(100, -40)
        print()
        print('Expected for p2: 100 -40')
        print('Actual for p2:  ', p2.x, p2.y)
        print('Expected for p1: 30 18')
        print('Actual for p1:  ', p1.x, p1.y)

        p1.y = 999
        print()
        print('Expected for p1: 30 999')
        print('Actual for p1:  ', p1.x, p1.y)
        print('Expected for p2: 100 -40')
        print('Actual for p2:  ', p2.x, p2.y)
    """
    # ------------------------------------------------------------------
    # DONE: 3.
    #   a. Read the above description of the   __INIT__   method.
    #        Do NOT proceed until you understand WHAT it should do
    #        (but not necessary HOW it will do it).
    #        NO CODE YET.  Ask questions as needed.
    #   b. Figure out what TEST(S) would TEST the   __init__   method.
    #        Implement (i.e., write the code for) those tests.
    #        You will see red marks and cannot RUN those tests because
    #        you have not (yet) implemented the   __init__   method.
    #        COMMIT YOUR WORK and also ask questions as needed.
    #   c. Implement and test the  __init__   method.
    #        Get someone knowledgeable to confirm that your
    #        implementation AND tests are correct.  COMMIT YOUR WORK.
    # ------------------------------------------------------------------
    print()
    print('-----------------------------------------------------------')
    print('Testing the   __INIT__   method of the Point class.')
    print('-----------------------------------------------------------')
    p1 = Point(30, 18)
    print()
    print('Expected for p1: 30 18')
    print('Actual for p1:  ', p1.x, p1.y)
    p2 = Point(100, -40)
    print()
    print('Expected for p2: 100 -40')
    print('Actual for p2:  ', p2.x, p2.y)
    print('Expected for p1: 30 18')
    print('Actual for p1:  ', p1.x, p1.y)
    p1.y = 999
    print()
    print('Expected for p1: 30 999')
    print('Actual for p1:  ', p1.x, p1.y)
    print('Expected for p2: 100 -40')
    print('Actual for p2:  ', p2.x, p2.y)


def test_repr():
    """
    Tests the   __REPR__   method of the Point class.
      -- IMPORTANT:  There are   TWO  underscores on each side.
      -- Note:  the   __repr__  method is called by the PRINT
           function and other functions that DISPLAY a Point object.
           See examples below.

    Here is the specification for the   __repr__   method:
    What comes in:
       -- self
    What goes out:
      Returns a string that represents a Point like this:
         'Point(x, y)'
      where   x   and   y   are replaced by this Point's
      x and y coordinates.
    Side effects: None.

    EXAMPLE: The following shows   __REPR__   in action.
    You may also use this example to test this method.

        p1 = Point(30, 18)
        print()
        print('Expected for p1: Point(30, 18)')
        print('Actual for p1:  ', p1)

        p2 = Point(100, -40)
        print()
        print('Expected for p2: Point(100, -40)')
        print('Actual for p2:  ', p2)
        print('Expected for p1: Point(30, 18)')
        print('Actual for p1:  ', p1)

        p1.y = 999
        print()
        print('Expected for p1: Point(30, 999)')
        print('Actual for p1:  ', p1)
        print('Expected for p2: Point(100, -40)')
        print('Actual for p2:  ', p2)
    """
    # ------------------------------------------------------------------
    # DONE: 4.
    #   a. Read the above description of the   __REPR__   method.
    #        Do NOT proceed until you understand WHAT it should do
    #        (but not necessary HOW it will do it).
    #        NO CODE YET.  Ask questions as needed.
    #   b. Figure out what TEST(S) would TEST the   __repr__   method.
    #        Implement (i.e., write the code for) those tests.
    #        You will see red marks and cannot RUN those tests because
    #        you have not (yet) implemented the   __repr__   method.
    #        COMMIT YOUR WORK and also ask questions as needed.
    #   c. Implement and test the   __repr__   method.
    #        Get someone knowledgeable to confirm that your
    #        implementation AND tests are correct.  COMMIT YOUR WORK.
    # ------------------------------------------------------------------
    print()
    print('-----------------------------------------------------------')
    print('Testing the   __REPR__   method of the Point class.')
    print('-----------------------------------------------------------')
    p1 = Point(30, 18)
    print()
    print('Expected for p1: Point(30, 18)')
    print('Actual for p1:  ', p1)
    p2 = Point(100, -40)
    print()
    print('Expected for p2: Point(100, -40)')
    print('Actual for p2:  ', p2)
    print('Expected for p1: Point(30, 18)')
    print('Actual for p1:  ', p1)
    p1.y = 999
    print()
    print('Expected for p1: Point(30, 999)')
    print('Actual for p1:  ', p1)
    print('Expected for p2: Point(100, -40)')
    print('Actual for p2:  ', p2)

def test_clone():
    """
    Tests the   CLONE   method of the Point class.

    Here is the specification for the   clone   method:
    What comes in:
       -- self
    What goes out:
      Returns a new Point whose x and y coordinates are the same
      as the x and y coordinates of this Point.
    Side effects: None.

    EXAMPLE: The following shows   CLONE   in action.
    You may also use this example to test this method.

        p1 = Point(10, 8)
        print()
        print('Expected for p1: Point(10, 8)')
        print('Actual for p1:  ', p1)

        p2 = p1.clone()
        p3 = p2.clone()
        print()
        print('Expected for p1: Point(10, 8)')
        print('Actual for p1:  ', p1)
        print('Expected for p2: Point(10, 8)')
        print('Actual for p2:  ', p2)
        print('Expected for p3: Point(10, 8)')
        print('Actual for p3:  ', p3)

        p1.x = 999
        print()
        print('Expected for p1: Point(999, 8)')
        print('Actual for p1:  ', p1)
        print('Expected for p2: Point(10, 8)')
        print('Actual for p2:  ', p2)
        print('Expected for p3: Point(10, 8)')
        print('Actual for p3:  ', p3)

        p1.y = 333
        p2 = Point(11, 22)
        p3.x = 777
        p3.y = 555
        print()
        print('Expected for p1: Point(999. 333)')
        print('Actual for p1:  ', p1)
        print('Expected for p2: Point(11, 22)')
        print('Actual for p2:  ', p2)
        print('Expected for p3: Point(777, 555)')
        print('Actual for p3:  ', p3)
   """
    # ------------------------------------------------------------------
    # DONE: 5.
    #   a. Read the above description of the   CLONE   method.
    #        Do NOT proceed until you understand WHAT it should do
    #        (but not necessary HOW it will do it).
    #        NO CODE YET.  Ask questions as needed.
    #   b. Figure out what TEST(S) would TEST the   clone   method.
    #        Implement (i.e., write the code for) those tests.
    #        You will see red marks and cannot RUN those tests because
    #        you have not (yet) implemented the   clone   method.
    #        COMMIT YOUR WORK and also ask questions as needed.
    #   c. Implement and test the  clone   method.
    #        Get someone knowledgeable to confirm that your
    #        implementation AND tests are correct.  COMMIT YOUR WORK.
    # ------------------------------------------------------------------
    print()
    print('-----------------------------------------------------------')
    print('Testing the   CLONE   method of the Point class.')
    print('-----------------------------------------------------------')
    p1 = Point(10, 8)
    print()
    print('Expected for p1: Point(10, 8)')
    print('Actual for p1:  ', p1)

    p2 = p1.clone()
    p3 = p2.clone()
    print()
    print('Expected for p1: Point(10, 8)')
    print('Actual for p1:  ', p1)
    print('Expected for p2: Point(10, 8)')
    print('Actual for p2:  ', p2)
    print('Expected for p3: Point(10, 8)')
    print('Actual for p3:  ', p3)

    p1.x = 999
    print()
    print('Expected for p1: Point(999, 8)')
    print('Actual for p1:  ', p1)
    print('Expected for p2: Point(10, 8)')
    print('Actual for p2:  ', p2)
    print('Expected for p3: Point(10, 8)')
    print('Actual for p3:  ', p3)

    p1.y = 333
    p2 = Point(11, 22)
    p3.x = 777
    p3.y = 555
    print()
    print('Expected for p1: Point(999. 333)')
    print('Actual for p1:  ', p1)
    print('Expected for p2: Point(11, 22)')
    print('Actual for p2:  ', p2)
    print('Expected for p3: Point(777, 555)')
    print('Actual for p3:  ', p3)


def test_move_to():
    """
    Tests the   MOVE_TO   method of the Point class.

    Here is the specification for the   move_to   method:
    What comes in:
       -- self
       -- an integer x
       -- an integer y
    What goes out:  Nothing (i.e., None).
    Side effects:  Changes the instance variables
          x
          y
       that store the position of this Point to the given x and y.
       This has the effect of "moving" this Point TO the given (x, y).

    EXAMPLE: The following shows   MOVE_TO   in action.
    You may also use this example to test this method.

        p1 = Point(10, 8)
        p2 = Point(50, 20)
        print()
        print('Expected for p1: Point(10, 8)')
        print('Actual for p1:  ', p1)
        print('Expected for p2: Point(50, 20)')
        print('Actual for p2:  ', p2)

        p1.move_to(5, -1)
        p2.move_to(0, 0)
        print()
        print('Expected for p1: Point(5, -1)')
        print('Actual for p1:  ', p1)
        print('Expected for p2: Point(0, 0)')
        print('Actual for p2:  ', p2)

        p2.y = 99
        print()
        print('Expected for p1: Point(5, -1)')
        print('Actual for p1:  ', p1)
        print('Expected for p2: Point(0, 99)')
        print('Actual for p2:  ', p2)

        p2.move_to(0, 222)
        print()
        print('Expected for p1: Point(5, -1)')
        print('Actual for p1:  ', p1)
        print('Expected for p2: Point(0, 222)')
        print('Actual for p2:  ', p2)
    """
    # ------------------------------------------------------------------
    # DONE: 6.
    #   a. Read the above description of the   MOVE_TO   method.
    #        Do NOT proceed until you understand WHAT it should do
    #        (but not necessary HOW it will do it).
    #        NO CODE YET.  Ask questions as needed.
    #   b. Figure out what TEST(S) would TEST the   move_to   method.
    #        Implement (i.e., write the code for) those tests.
    #        You will see red marks and cannot RUN those tests because
    #        you have not (yet) implemented the   move_to   method.
    #        COMMIT YOUR WORK and also ask questions as needed.
    #   c. Implement and test the  move_to   method.
    #        Get someone knowledgeable to confirm that your
    #        implementation AND tests are correct.  COMMIT YOUR WORK.
    # ------------------------------------------------------------------
    print()
    print('-----------------------------------------------------------')
    print('Testing the   MOVE_TO   method of the Point class.')
    print('-----------------------------------------------------------')
    p1 = Point(10, 8)
    p2 = Point(50, 20)
    print()
    print('Expected for p1: Point(10, 8)')
    print('Actual for p1:  ', p1)
    print('Expected for p2: Point(50, 20)')
    print('Actual for p2:  ', p2)

    p1.move_to(5, -1)
    p2.move_to(0, 0)
    print()
    print('Expected for p1: Point(5, -1)')
    print('Actual for p1:  ', p1)
    print('Expected for p2: Point(0, 0)')
    print('Actual for p2:  ', p2)

    p2.y = 99
    print()
    print('Expected for p1: Point(5, -1)')
    print('Actual for p1:  ', p1)
    print('Expected for p2: Point(0, 99)')
    print('Actual for p2:  ', p2)

    p2.move_to(0, 222)
    print()
    print('Expected for p1: Point(5, -1)')
    print('Actual for p1:  ', p1)
    print('Expected for p2: Point(0, 222)')
    print('Actual for p2:  ', p2)


def test_move_by():
    """
    Tests the   MOVE_BY   method of the Point class.

    Here is the specification for the   move_by   method:
    What comes in:
       -- self
       -- an integer dx
       -- an integer dy
    What goes out:  Nothing (i.e., None).
    Side effects:  Adds the given   dx   and   dy
    to the instance variables
          x
          y
       that store the position of this Point.
       This has the effect of "moving" this Point BY the given (dx, dy).

    EXAMPLE: The following shows   MOVE_BY   in action.
    You may also use this example to test this method.

        p1 = Point(10, 8)
        p2 = Point(50, 20)
        print()
        print('Expected for p1: Point(10, 8)')
        print('Actual for p1:  ', p1)
        print('Expected for p2: Point(50, 20)')
        print('Actual for p2:  ', p2)

        p1.move_by(5, -1)
        p2.move_by(0, 0)
        print()
        print('Expected for p1: Point(15, 7)')
        print('Actual for p1:  ', p1)
        print('Expected for p2: Point(50, 20)')
        print('Actual for p2:  ', p2)

        p2.move_by(200, 0)
        print()
        print('Expected for p1: Point(15, 7)')
        print('Actual for p1:  ', p1)
        print('Expected for p2: Point(250, 20)')
        print('Actual for p2:  ', p2)

        p2.move_by(-100, 300)
        print()
        print('Expected for p1: Point(15, 7)')
        print('Actual for p1:  ', p1)
        print('Expected for p2: Point(150, 320)')
        print('Actual for p2:  ', p2)
    """
    # ------------------------------------------------------------------
    # DONE: 7.
    #   a. Read the above description of the   MOVE_BY   method.
    #        Do NOT proceed until you understand WHAT it should do
    #        (but not necessary HOW it will do it).
    #        NO CODE YET.  Ask questions as needed.
    #   b. Figure out what TEST(S) would TEST the   move_by   method.
    #        Implement (i.e., write the code for) those tests.
    #        You will see red marks and cannot RUN those tests because
    #        you have not (yet) implemented the   move_by   method.
    #        COMMIT YOUR WORK and also ask questions as needed.
    #   c. Implement and test the  move_by   method.
    #        Get someone knowledgeable to confirm that your
    #        implementation AND tests are correct.  COMMIT YOUR WORK.
    # ------------------------------------------------------------------
    print()
    print('-----------------------------------------------------------')
    print('Testing the   MOVE_BY   method of the Point class.')
    print('-----------------------------------------------------------')
    p1 = Point(10, 8)
    p2 = Point(50, 20)
    print()
    print('Expected for p1: Point(10, 8)')
    print('Actual for p1:  ', p1)
    print('Expected for p2: Point(50, 20)')
    print('Actual for p2:  ', p2)

    p1.move_by(5, -1)
    p2.move_by(0, 0)
    print()
    print('Expected for p1: Point(15, 7)')
    print('Actual for p1:  ', p1)
    print('Expected for p2: Point(50, 20)')
    print('Actual for p2:  ', p2)

    p2.move_by(200, 0)
    print()
    print('Expected for p1: Point(15, 7)')
    print('Actual for p1:  ', p1)
    print('Expected for p2: Point(250, 20)')
    print('Actual for p2:  ', p2)

    p2.move_by(-100, 300)
    print()
    print('Expected for p1: Point(15, 7)')
    print('Actual for p1:  ', p1)
    print('Expected for p2: Point(150, 320)')
    print('Actual for p2:  ', p2)


def test_get_number_of_moves_made():
    """
    Tests the   GET_NUMBER_OF_MOVES_MADE   method of the Point class.

    Here is the specification for the  get_number_of_moves_made  method:
    What comes in:
       -- self
    What goes out:  Returns an integer that is the number of times that
       this Point has "moved" via calls to   move_to  and/or   move_by.
    Side effects:
       ** You figure out what side effect(s) MUST happen! **

    EXAMPLE: The following shows   GET_NUMBER_OF_MOVES_MADE   in action.
    You may also use this example to test this method.

        p1 = Point(10, 8)
        p2 = Point(50, 20)
        print()
        print('Expected for p1 moves made: 0')
        print('Actual for p1 moves made:  ', p1.get_number_of_moves_made())
        print('Expected for p2 moves made: 0')
        print('Actual for p2 moves made:  ', p2.get_number_of_moves_made())

        p1.move_by(5, -1)
        p2.move_by(0, 0)
        print()
        print('Expected for p1 moves made: 1')
        print('Actual for p1 moves made:  ', p1.get_number_of_moves_made())
        print('Expected for p2 moves made: 1')
        print('Actual for p2 moves made:  ', p2.get_number_of_moves_made())

        p2.move_by(200, 0)
        p2.move_by(-100, 300)
        p2.move_to(-100, 300)
        p1.move_to(3, 3)
        print()
        print('Expected for p1 moves made: 2')
        print('Actual for p1 moves made:  ', p1.get_number_of_moves_made())
        print('Expected for p2 moves made: 4')
        print('Actual for p2 moves made:  ', p2.get_number_of_moves_made())

        p1.move_by(200, 0)
        p1.move_by(-100, 300)
        p1.move_to(-100, 300)
        p1.move_to(3, 3)
        print()
        print('Expected for p1 moves made: 6')
        print('Actual for p1 moves made:  ', p1.get_number_of_moves_made())
        print('Expected for p2 moves made: 4')
        print('Actual for p2 moves made:  ', p2.get_number_of_moves_made())

        p1.x = 400
        print()
        print('Expected for p1 moves made: 6')
        print('Actual for p1 moves made:  ', p1.get_number_of_moves_made())
        print('Expected for p2 moves made: 4')
        print('Actual for p2 moves made:  ', p2.get_number_of_moves_made())

        p1.move_to(3, 3)
        p2.move_by(0, 0)
        print()
        print('Expected for p1 moves made: 7')
        print('Actual for p1 moves made:  ', p1.get_number_of_moves_made())
        print('Expected for p2 moves made: 5')
        print('Actual for p2 moves made:  ', p2.get_number_of_moves_made())
    """
    # ------------------------------------------------------------------
    # DONE: 8.
    #   a. Read the above description of the
    #          GET_NUMBER_OF_MOVES_MADE
    #        method.  Do NOT proceed until you understand WHAT it
    #        should do (but not necessary HOW it will do it).
    #        NO CODE YET.  Ask questions as needed.
    #   b. Figure out what TEST(S) would TEST the
    #        get_number_of_moves_made  method.  Implement
    #        (i.e., write the code for) those tests.
    #        You will see red marks and cannot RUN those tests because
    #        you have not (yet) implemented the
    #        get_number_of_moves_made  method.
    #        COMMIT YOUR WORK and also ask questions as needed.
    #   c. Implement and test the  get_number_of_moves_made   method.
    #        Get someone knowledgeable to confirm that your
    #        implementation AND tests are correct.  COMMIT YOUR WORK.
    # ------------------------------------------------------------------
    print()
    print('-----------------------------------------------------------')
    print('Testing the   GET_NUMBER_OF_MOVES_MADE   method')
    print('of the Point class.')
    print('-----------------------------------------------------------')
    p1 = Point(10, 8)
    p2 = Point(50, 20)
    print()
    print('Expected for p1 moves made: 0')
    print('Actual for p1 moves made:  ', p1.get_number_of_moves_made())
    print('Expected for p2 moves made: 0')
    print('Actual for p2 moves made:  ', p2.get_number_of_moves_made())

    p1.move_by(5, -1)
    p2.move_by(0, 0)
    print()
    print('Expected for p1 moves made: 1')
    print('Actual for p1 moves made:  ', p1.get_number_of_moves_made())
    print('Expected for p2 moves made: 1')
    print('Actual for p2 moves made:  ', p2.get_number_of_moves_made())

    p2.move_by(200, 0)
    p2.move_by(-100, 300)
    p2.move_to(-100, 300)
    p1.move_to(3, 3)
    print()
    print('Expected for p1 moves made: 2')
    print('Actual for p1 moves made:  ', p1.get_number_of_moves_made())
    print('Expected for p2 moves made: 4')
    print('Actual for p2 moves made:  ', p2.get_number_of_moves_made())

    p1.move_by(200, 0)
    p1.move_by(-100, 300)
    p1.move_to(-100, 300)
    p1.move_to(3, 3)
    print()
    print('Expected for p1 moves made: 6')
    print('Actual for p1 moves made:  ', p1.get_number_of_moves_made())
    print('Expected for p2 moves made: 4')
    print('Actual for p2 moves made:  ', p2.get_number_of_moves_made())

    p1.x = 400
    print()
    print('Expected for p1 moves made: 6')
    print('Actual for p1 moves made:  ', p1.get_number_of_moves_made())
    print('Expected for p2 moves made: 4')
    print('Actual for p2 moves made:  ', p2.get_number_of_moves_made())

    p1.move_to(3, 3)
    p2.move_by(0, 0)
    print()
    print('Expected for p1 moves made: 7')
    print('Actual for p1 moves made:  ', p1.get_number_of_moves_made())
    print('Expected for p2 moves made: 5')
    print('Actual for p2 moves made:  ', p2.get_number_of_moves_made())


def test_get_distance_from():
    """
    Tests the   GET_DISTANCE_FROM   method of the Point class.

    Here is the specification for the   get_distance_from   method:
    What comes in:
       -- self
       -- another Point object
    What goes out:
       Returns the distance from this Point to the given Point.
    Side effects:
       ** You figure out WHETHER OR NOT side effect(s) MUST happen! **

    EXAMPLE: The following shows   GET_DISTANCE_FROM   in action.
    You may also use this example to test this method.

        p1 = Point(1, 5)
        p2 = Point(10, 5)
        p3 = Point(13, 9)

        print()
        print('Expected p1 to p2: 9.0')
        print('Actual   p1 to p2:', p1.get_distance_from(p2))

        print()
        print('Expected p2 to p3: 5.0')
        print('Actual   p2 to p3:', p2.get_distance_from(p3))
        print('Expected p3 to p2: 5.0')
        print('Actual   p3 to p2:', p3.get_distance_from(p2))

        print()
        print('Expected p1 to p3: about 12.65')
        print('Actual   p1 to p3:', p1.get_distance_from(p3))
        print('Expected p3 to p1: about 12.65')
        print('Actual   p3 to p1:', p3.get_distance_from(p1))

        print()
        print('Expected p1 to p1: 0.0')
        print('Actual   p1 to p1:', p1.get_distance_from(p1))
        print('Expected p2 to p2: 0.0')
        print('Actual   p2 to p2:', p2.get_distance_from(p2))
        print('Expected p3 to p3: 0.0')
        print('Actual   p3 to p3:', p3.get_distance_from(p3))

        p4 = p1.clone()
        print()
        print('Expected p1 to p4: 0.0')
        print('Actual   p1 to p4:', p1.get_distance_from(p4))
        print('Expected p4 to p1: 0.0')
        print('Actual   p4 to p1:', p4.get_distance_from(p1))
        print('Expected p4 to p2: 9.0')
        print('Actual   p4 to p2:', p4.get_distance_from(p2))
        print('Expected p2 to p4: 9.0')
        print('Actual   p2 to p4:', p2.get_distance_from(p4))
    """
    # ------------------------------------------------------------------
    # DONE: 9.
    #   a. Read the above description of the
    #          GET_DISTANCE_FROM
    #        method.  Do NOT proceed until you understand WHAT it
    #        should do (but not necessary HOW it will do it).
    #        NO CODE YET.  Ask questions as needed.
    #   b. Figure out what TEST(S) would TEST the   get_distance_from
    #        method.  Implement (i.e., write the code for) those tests.
    #        You will see red marks and cannot RUN those tests because
    #        you have not (yet) implemented the
    #        get_distance_from  method.
    #        COMMIT YOUR WORK and also ask questions as needed.
    #   c. Implement and test the  get_distance_from   method.
    #        Get someone knowledgeable to confirm that your
    #        implementation AND tests are correct.  COMMIT YOUR WORK.
    # ------------------------------------------------------------------
    print()
    print('-----------------------------------------------------------')
    print('Testing the   GET_DISTANCE_FROM  method of the Point class.')
    print('-----------------------------------------------------------')
    p1 = Point(1, 5)
    p2 = Point(10, 5)
    p3 = Point(13, 9)

    print()
    print('Expected p1 to p2: 9.0')
    print('Actual   p1 to p2:', p1.get_distance_from(p2))

    print()
    print('Expected p2 to p3: 5.0')
    print('Actual   p2 to p3:', p2.get_distance_from(p3))
    print('Expected p3 to p2: 5.0')
    print('Actual   p3 to p2:', p3.get_distance_from(p2))

    print()
    print('Expected p1 to p3: about 12.65')
    print('Actual   p1 to p3:', p1.get_distance_from(p3))
    print('Expected p3 to p1: about 12.65')
    print('Actual   p3 to p1:', p3.get_distance_from(p1))

    print()
    print('Expected p1 to p1: 0.0')
    print('Actual   p1 to p1:', p1.get_distance_from(p1))
    print('Expected p2 to p2: 0.0')
    print('Actual   p2 to p2:', p2.get_distance_from(p2))
    print('Expected p3 to p3: 0.0')
    print('Actual   p3 to p3:', p3.get_distance_from(p3))

    p4 = p1.clone()
    print()
    print('Expected p1 to p4: 0.0')
    print('Actual   p1 to p4:', p1.get_distance_from(p4))
    print('Expected p4 to p1: 0.0')
    print('Actual   p4 to p1:', p4.get_distance_from(p1))
    print('Expected p4 to p2: 9.0')
    print('Actual   p4 to p2:', p4.get_distance_from(p2))
    print('Expected p2 to p4: 9.0')
    print('Actual   p2 to p4:', p2.get_distance_from(p4))


def test_get_distance_from_start():
    """
    Tests the   GET_DISTANCE_FROM_START   method of the Point class.

    Here is the specification for the   get_distance_from_start  method:
    What comes in:
       -- self
    What goes out:
       Returns the distance from this Point's current position
       to the position that the Point was at when it was constructed.
    Side effects:
       ** You figure out WHETHER OR NOT side effect(s) MUST happen! **

    EXAMPLE: The following shows   GET_DISTANCE_FROM_START   in action.
    You may also use this example to test this method.

        p1 = Point(20, 30)
        p1.move_to(111, 222)
        p1.move_by(10, 20)
        p1.move_to(0, 0)
        p1.move_to(21, 31)
        print()
        print('p1 from start to (21, 31), should be about 1.414')
        print('Actually is:', p1.get_distance_from_start())

        p1.move_by(29, 39)
        print()
        print('p1 from start to (50, 70), should be about 50.0')
        print('Actually is:', p1.get_distance_from_start())

        p2 = Point(1, 1)
        print()
        print('p2 from start to (1, 1), should be about 0.0')
        print('Actually is:', p2.get_distance_from_start())

        p2.move_to(11, 1)
        print()
        print('p2 from start to (11, 1), should be about 10.0')
        print('Actually is:', p2.get_distance_from_start())

        p2.move_to(999, 999)
        p2.move_to(1, 1)
        print()
        print('p2 from start to (1, 1), should be about 0.0')
        print('Actually is:', p2.get_distance_from_start())
    """
    # ------------------------------------------------------------------
    # DONE: 10.
    #   a. Read the above description of the   GET_DISTANCE_FROM_START
    #        method.  Do NOT proceed until you understand WHAT it
    #        should do (but not necessary HOW it will do it).
    #        NO CODE YET.  Ask questions as needed.
    #   b. Figure out what TEST(S) would TEST the
    #        get_distance_from_start
    #        method.  Implement (i.e., write the code for) those tests.
    #        You will see red marks and cannot RUN those tests because
    #        you have not (yet) implemented the  get_distance_from_start
    #        method.  COMMIT YOUR WORK and also ask questions as needed.
    #   c. Implement and test the  get_distance_from_start   method.
    #        Get someone knowledgeable to confirm that your
    #        implementation AND tests are correct.  COMMIT YOUR WORK.
    # ------------------------------------------------------------------
    print()
    print('-----------------------------------------------------------')
    print('Testing the   GET_DISTANCE_FROM_START   method')
    print('of the Point class.')
    print('-----------------------------------------------------------')
    p1 = Point(20, 30)
    p1.move_to(111, 222)
    p1.move_by(10, 20)
    p1.move_to(0, 0)
    p1.move_to(21, 31)
    print()
    print('p1 from start to (21, 31), should be about 1.414')
    print('Actually is:', p1.get_distance_from_start())

    p1.move_by(29, 39)
    print()
    print('p1 from start to (50, 70), should be about 50.0')
    print('Actually is:', p1.get_distance_from_start())

    p2 = Point(1, 1)
    print()
    print('p2 from start to (1, 1), should be about 0.0')
    print('Actually is:', p2.get_distance_from_start())

    p2.move_to(11, 1)
    print()
    print('p2 from start to (11, 1), should be about 10.0')
    print('Actually is:', p2.get_distance_from_start())

    p2.move_to(999, 999)
    p2.move_to(1, 1)
    print()
    print('p2 from start to (1, 1), should be about 0.0')
    print('Actually is:', p2.get_distance_from_start())


def test_get_distance_traveled():
    """
    Tests the   GET_DISTANCE_TRAVELED   method of the Point class.

    Here is the specification for the   get_distance_traveled   method:
    What comes in:
       -- self
    What goes out:  Returns the sum of all the distances that
       this Point has "moved" via calls to   move_to  and/or   move_by.
    Side effects:
       ** You figure out WHETHER OR NOT side effect(s) MUST happen! **

    EXAMPLE: The following shows   GET_DISTANCE_TRAVELED   in action.
    You may also use this example to test this method.

        p1 = Point(20, 30)
        p1.move_to(21, 30)
        p1.move_to(21, 38)
        print()
        print('Expected p1 has traveled 9.0')
        print('Actual:', p1.get_distance_traveled())

        p1.move_by(1, 1)
        print()
        print('Expected p1 has now traveled about 10.414')
        print('Actual:', p1.get_distance_traveled())

        p2 = Point(0, 0)
        p3 = Point(100, 22)
        p4 = Point(0, 555)
        for k in range(100):
            p2.move_by(0, k + 1)
            p3.move_by(k + 1, 0)
            p4.move_to(k + 1, 555)

        print()
        print('Expected p2 has now traveled', 101 * 50.0)
        print('Actual:', p2.get_distance_traveled())
        print('Expected p3 has now traveled', 101 * 50.0)
        print('Actual:', p3.get_distance_traveled())
        print('Expected p4 has now traveled 100.0')
        print('Actual:', p4.get_distance_traveled())
    """
    # ------------------------------------------------------------------
    # DONE: 11.
    #   a. Read the above description of the   GET_DISTANCE_TRAVELED
    #        method.  Do NOT proceed until you understand WHAT it
    #        should do (but not necessary HOW it will do it).
    #        NO CODE YET.  Ask questions as needed.
    #   b. Figure out what TEST(S) would TEST the  get_distance_traveled
    #        method.  Implement (i.e., write the code for) those tests.
    #        You will see red marks and cannot RUN those tests because
    #        you have not (yet) implemented the   get_distance_traveled
    #        method.  COMMIT YOUR WORK and also ask questions as needed.
    #   c. Implement and test the  get_distance_traveled   method.
    #        Get someone knowledgeable to confirm that your
    #        implementation AND tests are correct.  COMMIT YOUR WORK.
    # ------------------------------------------------------------------
    print()
    print('-----------------------------------------------------------')
    print('Testing the   GET_DISTANCE_TRAVELED   method')
    print('of the Point class.')
    print('-----------------------------------------------------------')
    p1 = Point(20, 30)
    p1.move_to(21, 30)
    p1.move_to(21, 38)
    print()
    print('Expected p1 has traveled 9.0')
    print('Actual:', p1.get_distance_traveled())

    p1.move_by(1, 1)
    print()
    print('Expected p1 has now traveled about 10.414')
    print('Actual:', p1.get_distance_traveled())

    p2 = Point(0, 0)
    p3 = Point(100, 22)
    p4 = Point(0, 555)
    for k in range(100):
        p2.move_by(0, k + 1)
        p3.move_by(k + 1, 0)
        p4.move_to(k + 1, 555)

    print()
    print('Expected p2 has now traveled', 101 * 50.0)
    print('Actual:', p2.get_distance_traveled())
    print('Expected p3 has now traveled', 101 * 50.0)
    print('Actual:', p3.get_distance_traveled())
    print('Expected p4 has now traveled 100.0')
    print('Actual:', p4.get_distance_traveled())


def test_closer_to():
    """
    Tests the   CLOSER_TO   method of the Point class.

    Here is the specification for the   closer_to   method:
    What comes in:
       -- self
       -- a Point object p2
       -- a Point object p3
    What goes out:
        Returns whichever of p2 and p3 this Point is closer to.
        (Just to be specific, it should return p2 in the case of a tie.)
    Side effects:
       ** You figure out WHETHER OR NOT side effect(s) MUST happen! **

    EXAMPLE: The following shows   CLOSER_TO   in action.
    You may also use this example to test this method.

        p1 = Point(10, 20)
        p2 = Point(15, 20)
        p3 = Point(14, 24)

        print()
        print('Expected:', p2)
        print('Actual:  ', p1.closer_to(p2, p3))
        print('Expected:', p2)
        print('Actual:  ', p1.closer_to(p3, p2))

        print()
        print('Expected:', p1)
        print('Actual:  ', p1.closer_to(p1, p3))
        print('Expected:', p2)
        print('Actual:  ', p2.closer_to(p3, p2))
        print('Expected:', p3)
        print('Actual:  ', p3.closer_to(p3, p3))

        print()
        p4 = p1.clone()
        p5 = p1.clone()
        print('Expected:', p4)
        print('Actual:  ', p1.closer_to(p4, p5))
        print('Expected: True')
        print('Actual:  ', p1.closer_to(p4, p5) is p4)
        print('Expected: False')
        print('Actual:  ', p1.closer_to(p4, p5) is p5)
    """
    # ------------------------------------------------------------------
    # DONE: 12.
    #   a. Read the above description of the   CLOSER_TO   method.
    #        Do NOT proceed until you understand WHAT it should do
    #        (but not necessary HOW it will do it).
    #        NO CODE YET.  Ask questions as needed.
    #   b. Figure out what TEST(S) would TEST the   closer_to   method.
    #        Implement (i.e., write the code for) those tests.
    #        You will see red marks and cannot RUN those tests because
    #        you have not (yet) implemented the   closer_to   method.
    #        COMMIT YOUR WORK and also ask questions as needed.
    #   c. Implement and test the  closer_to   method.
    #        Get someone knowledgeable to confirm that your
    #        implementation AND tests are correct.  COMMIT YOUR WORK.
    # ------------------------------------------------------------------
    print()
    print('-----------------------------------------------------------')
    print('Testing the   CLOSER_TO   method of the Point class.')
    print('-----------------------------------------------------------')
    p1 = Point(10, 20)
    p2 = Point(15, 20)
    p3 = Point(14, 24)

    print()
    print('Expected:', p2)
    print('Actual:  ', p1.closer_to(p2, p3))
    print('Expected:', p2)
    print('Actual:  ', p1.closer_to(p3, p2))

    print()
    print('Expected:', p1)
    print('Actual:  ', p1.closer_to(p1, p3))
    print('Expected:', p2)
    print('Actual:  ', p2.closer_to(p3, p2))
    print('Expected:', p3)
    print('Actual:  ', p3.closer_to(p3, p3))

    print()
    p4 = p1.clone()
    p5 = p1.clone()
    print('Expected:', p4)
    print('Actual:  ', p1.closer_to(p4, p5))
    print('Expected: True')
    print('Actual:  ', p1.closer_to(p4, p5) is p4)
    print('Expected: False')
    print('Actual:  ', p1.closer_to(p4, p5) is p5)


def test_halfway_to():
    """
    Tests the   HALFWAY_TO   method of the Point class.

    Here is the specification for the   halfway_to   method:
    What comes in:
       -- self
       -- a Point object p2
    What goes out:
        Returns a new Point that is halfway between this Point and p2.
        That is, the x coordinate of the new Point is the average
        of the x coordinate of this Point and the x coordinate of p2,
        and likewise for the new Point's y coordinate.
    Side effects:
       ** You figure out WHETHER OR NOT side effect(s) MUST happen! **

    EXAMPLE: The following shows   HALFWAY_TO   in action.
    You may also use this example to test this method.

        p1 = Point(10, 20)
        p2 = Point(30, 100)

        print()
        print('Should be: Point(20.0, 60.0)')
        print('Actual is:', p1.halfway_to(p2))
        print('Should be: Point(20.0, 60.0)')
        print('Actual is:', p2.halfway_to(p1))

        print()
        print('Should be: Point(10.0, 20.0)')
        print('Actual is:', p1.halfway_to(p1))

        p3 = Point(-10, 20)
        p4 = Point(30, -100)

        print()
        print('Should be: Point(10.0, -40.0)')
        print('Actual is:', p3.halfway_to(p4))
        print('Should be: Point(10.0, -40.0)')
        print('Actual is:', p3.halfway_to(p4))

        print()
        print('Should be: Point(-10.0, 20.0)')
        print('Actual is:', p3.halfway_to(p3))

    """
    # ------------------------------------------------------------------
    # TODO: 13.
    #   a. Read the above description of the   HALFWAY_TO   method.
    #        Do NOT proceed until you understand WHAT it should do
    #        (but not necessary HOW it will do it).
    #        NO CODE YET.  Ask questions as needed.
    #   b. Figure out what TEST(S) would TEST the   halfway_to   method.
    #        Implement (i.e., write the code for) those tests.
    #        You will see red marks and cannot RUN those tests because
    #        you have not (yet) implemented the   halfway_to   method.
    #        COMMIT YOUR WORK and also ask questions as needed.
    #   c. Implement and test the  halfway_to   method.
    #        Get someone knowledgeable to confirm that your
    #        implementation AND tests are correct.  COMMIT YOUR WORK.
    # ------------------------------------------------------------------
    print()
    print('-----------------------------------------------------------')
    print('Testing the   HALFWAY_TO   method of the Point class.')
    print('-----------------------------------------------------------')
    p1 = Point(10, 20)
    p2 = Point(30, 100)

    print()
    print('Should be: Point(20.0, 60.0)')
    print('Actual is:', p1.halfway_to(p2))
    print('Should be: Point(20.0, 60.0)')
    print('Actual is:', p2.halfway_to(p1))

    print()
    print('Should be: Point(10.0, 20.0)')
    print('Actual is:', p1.halfway_to(p1))

    p3 = Point(-10, 20)
    p4 = Point(30, -100)

    print()
    print('Should be: Point(10.0, -40.0)')
    print('Actual is:', p3.halfway_to(p4))
    print('Should be: Point(10.0, -40.0)')
    print('Actual is:', p3.halfway_to(p4))

    print()
    print('Should be: Point(-10.0, 20.0)')
    print('Actual is:', p3.halfway_to(p3))


# ----------------------------------------------------------------------
# Calls  main  to start the ball rolling.
# ----------------------------------------------------------------------
main()
