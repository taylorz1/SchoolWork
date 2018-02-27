"""
Test 2, problem 1.

Authors: David Mutchler, Dave Fisher, Valerie Galluzzi, Amanda Stouder,
         their colleagues and Zachary Taylor.  Summer 2016.
"""  # DONE: 1. PUT YOUR NAME IN THE ABOVE LINE.

import simple_testing as st


def main():
    """ Calls the   TEST   functions in this module. """
    test_problem1()


########################################################################
# Students:
#   Do NOT touch the following  sum_of_digits function - it has no TODO.
#   Do NOT copy code from this function.
#
# Instead, ** CALL ** this function as needed in the problems below.
########################################################################
def sum_of_digits(number):
    """
    What comes in:  An integer.
    What goes out:  The sum of the digits in the given integer.
    Side effects:   None.
    Example:
      If the integer is 83135,
      this function returns (8 + 3 + 1 + 3 + 5), which is 20.
    """
    if number < 0:
        number = -number

    digit_sum = 0
    while True:
        if number == 0:
            break
        digit_sum = digit_sum + (number % 10)
        number = number // 10

    return digit_sum


########################################################################
# Students: Some of the testing code below uses SimpleTestCase objects,
#           from the imported   simple_testing (st)   module.
#           See details in the  test  code below.
########################################################################

def test_problem1():
    """ Tests the    problem1    function. """
    # ------------------------------------------------------------------
    # Several tests.
    # They use the imported   simple_testing (st)   module.
    # Each test is a SimpleTestCase with 3 arguments:
    #   -- the function to test,
    #   -- a list containing the argument(s) to send to the function,
    #   -- the correct returned value.
    # For example, the first test below will call
    #   problem1((104, 310011, 9, 5000))
    # and compare the returned value against 25 (the correct answer).
    # ------------------------------------------------------------------
    tests = [st.SimpleTestCase(problem1,
                               [(104,
                                 310011,
                                 9,
                                 5000)],
                               25),
             st.SimpleTestCase(problem1,
                               [[9090111]],
                               21),
             st.SimpleTestCase(problem1,
                               [[]],
                               0),
             st.SimpleTestCase(problem1,
                               [(2 ** 50,
                                 3 ** 230,
                                 5 ** 32,
                                 7 ** 777)],
                               3702),

             ]

    st.SimpleTestCase.run_tests('problem1', tests)

def problem1(sequence):
    """
    What comes in: A sequence of nonnegative integers.
    What goes out:
      Returns the sum of all the digits in the given sequence.
      -- Use the   sum_of_digits   function above
         for computing the sum of the digits of a SINGLE integer.
    Side effects: None.
    Example:
      If the given sequence is:
         (104, 310011, 9, 5000)
      then this function should return:
          1 + 0 + 4 + 3 + 1 + 0 + 0 + 1 + 1 + 9 + 5 + 0 + 0 + 0
      which is 25.
    Type hints:
      :type: sequence: (int)
    """
    ####################################################################
    # DONE: 2. Implement and test this function.
    #     The testing code is already written for you (above).
    ####################################################################
    ans = 0
    for k in range(len(sequence)):
        ans = ans + sum_of_digits(sequence[k])
    return ans


# ----------------------------------------------------------------------
# Calls  main  to start the ball rolling.
# ----------------------------------------------------------------------
main()
