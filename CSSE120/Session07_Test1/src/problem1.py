"""
Test 1, problem 1.

Authors: David Mutchler, Dave Fisher, Valerie Galluzzi, Amanda Stouder,
         their colleagues and Zachary Taylor.  June 2016.
"""  # DONE: 1. PUT YOUR NAME IN THE ABOVE LINE.


def main():
    """ Calls the   TEST   functions in this module. """
    test_problem1a()
    test_problem1b()
    test_problem1c()


def is_palindrome(n):
    """
    What comes in:  An non-negative integer n.
    What goes out:  Returns True if the given integer is a palindrome,
      that is, if it reads the same backwards and forwards.
      Returns False if the given integer is not a palindrome.
    Side effects:   None.
    Examples:
      -- if n is 12344321   this function returns True
      -- if n is  121121    this function returns True
      -- if n is  372273    this function returns True
      -- if n is    88      this function returns True
      -- if n is   808      this function returns True
      -- if n is    1       this function returns True
      -- if n is   6556     this function returns True

      -- if n is   6557     this function returns False
      -- if n is   228      this function returns False
      -- if n is    81      this function returns False
    """
    ####################################################################
    # Ask your instructor for help if you do not understand
    # the green doc-string above.
    ####################################################################
    forwards = str(n)
    backwards = str(n)[::-1]
    return forwards == backwards

    # ------------------------------------------------------------------
    # Students:
    #   Do NOT touch the above   is_palindrome   function
    #      - it has no TODO.
    #   Do NOT copy code from this function.
    #
    # Instead, ** CALL ** this function as needed in the problems below.
    # ------------------------------------------------------------------


def is_prime(n):
    """
    What comes in:  An integer n >= 2.
    What goes out:  True if the given integer is prime, else False.
    Side effects:   None.
    Examples:
      -- is_prime(11) returns  True
      -- is_prime(12) returns  False
      -- is_prime(2)  returns  True
    Note: The algorithm used here is simple and clear but slow.
    """
    for k in range(2, (n // 2) + 1):
        if n % k == 0:
            return False

    return True
    # ------------------------------------------------------------------
    # Students:
    #   Do NOT touch the above   is_prime   function - it has no TODO.
    #   Do NOT copy code from this function.
    #
    # Instead, ** CALL ** this function as needed in the problems below.
    # ------------------------------------------------------------------


def test_problem1a():
    """ Tests the   problem1a   function. """
    # ------------------------------------------------------------------
    # DONE: 2. Implement this TEST function.
    #   It TESTS the   problem1a   function defined below.
    #   Include at least **   5   ** tests.
    #
    # Use the same 4-step process as for previous TEST functions.
    # In particular, include both EXPECTED and ACTUAL results.
    # ------------------------------------------------------------------
    print()
    print('--------------------------------------------------')
    print('Testing the   problem1a   function:')
    print('--------------------------------------------------')
    print('Test 1')
    expected = 83
    actual = problem1a(11, 2)
    print()
    print('expected:', expected)
    print('actual:', actual)
    print('Test 2')
    expected = 1025
    actual = problem1a(70, 3)
    print()
    print('expected:', expected)
    print('actual:', actual)
    print('Test 3')
    expected = 10
    actual = problem1a(2, 1)
    print()
    print('expected:', expected)
    print('actual:', actual)
    print('Test 4')
    expected = 13245677
    actual = problem1a(1000, 6)
    print()
    print('expected:', expected)
    print('actual:', actual)
    print('Test 5')
    expected = 5
    actual = problem1a(5, 1)
    print()
    print('expected:', expected)
    print('actual:', actual)


def problem1a(m, p):
    """
    What comes in:  Positive integers m and p,
      with m >= 2   and   (5 raised to the pth power) >= m.
    What goes out:  Returns the sum of all the integers
      between m and (5 raised to the pth power), inclusive,
      that are prime.
    Side effects:   None.
    Examples:
      -- If m is 11 and p = 2, this function returns 83,
           because the sum of the primes
           between 11 and (5 to the 2nd power, i.e. 25) is:
             11 + 13 + 17 + 19 + 23, which is 83.
      -- If m is 70 and p = 3, this function returns 1025,
           because the sum of the primes between 70 and
           (5 to the 3rd power, i.e. 125) is:
             71 + 73 + 79 + 83 + 89 + 97 + 101 + 103 + 107 + 109 + 113,
             which is 1025.
      -- If m is 2 and p = 1, this function returns 10,
           because the sum of the primes between 2 and
           (5 to the 1st power, i.e. 5) is:
             2 + 3 + 5, which is 10.
      -- If m is 1000 and p = 6,
            this function returns 13245677  (trust me!)
    """
    # ------------------------------------------------------------------
    # DONE: 3. Implement and test this function.
    #   Note that you should write its TEST function first (above).
    #
    ####################################################################
    # IMPORTANT:
    #    **  For full credit you must appropriately use (call)
    #    **  the   is_prime   function that is defined above.
    ####################################################################
    # ------------------------------------------------------------------
    alpha = 5 ** p
    summation = 0
    for i in range(m, alpha + 1):
        if is_prime(i) == True:
            summation = summation + i
    return summation


def test_problem1b():
    """ Tests the   problem1b   function. """
    print()
    print('--------------------------------------------------')
    print('Testing the   problem1b   function:')
    print('--------------------------------------------------')

    ####################################################################
    # THESE TESTS ARE ALREADY DONE.  DO NOT CHANGE THEM.
    # You may add more tests if you want,
    # but you are not required to do so.
    ####################################################################

    # Test 1:
    expected = True
    answer = problem1b(17, 2)
    print()
    print('Test 1 expected:', expected)
    print('       actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')

    # Test 2:
    expected = False
    answer = problem1b(18, 2)
    print()
    print('Test 2 expected:', expected)
    print('       actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')

    # Test 3:
    expected = True
    answer = problem1b(85, 3)
    print()
    print('Test 3 expected:', expected)
    print('       actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')

    # Test 4:
    expected = True
    answer = problem1b(89, 3)
    print()
    print('Test 4 expected:', expected)
    print('       actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')

    # Test 5:
    expected = False
    answer = problem1b(90, 3)
    print()
    print('Test 5 expected:', expected)
    print('       actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')

    # Test 6:
    expected = False
    answer = problem1b(449, 4)
    print()
    print('Test 6 expected:', expected)
    print('       actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')

    # Test 7:
    expected = True
    answer = problem1b(450, 4)
    print()
    print('Test 7 expected:', expected)
    print('       actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')

    # Test 8:
    expected = True
    answer = problem1b(457, 4)
    print()
    print('Test 8 expected:', expected)
    print('       actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')

    # Test 9:
    expected = False
    answer = problem1b(458, 4)
    print()
    print('Test 9 expected:', expected)
    print('       actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')

    # Test 10:
    expected = False
    answer = problem1b(569, 5)
    print()
    print('Test 10 expected:', expected)
    print('        actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')

    # Test 11:
    expected = True
    answer = problem1b(570, 5)
    print()
    print('Test 11 expected:', expected)
    print('        actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')

    # Test 12:
    expected = True
    answer = problem1b(571, 5)
    print()
    print('Test 12 expected:', expected)
    print('        actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')

    # Test 13:
    expected = False
    answer = problem1b(572, 5)
    print()
    print('Test 13 expected:', expected)
    print('        actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')

    # Test 14:
    expected = True
    answer = problem1b(15610, 6)
    print()
    print('Test 14 expected:', expected)
    print('        actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')

    # Test 15:
    expected = False
    answer = problem1b(15600, 6)
    print()
    print('Test 15 expected:', expected)
    print('        actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')

    # Test 16:
    expected = False
    answer = problem1b(10000, 6)
    print()
    print('Test 16 expected:', expected)
    print('        actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')

    # Test 17:
    expected = True
    answer = problem1b(5861, 6)
    print()
    print('Test 17 expected:', expected)
    print('        actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')

    # Test 18:
    expected = False
    answer = problem1b(5862, 6)
    print()
    print('Test 18 expected:', expected)
    print('        actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')


def problem1b(m, p):
    """
    What comes in:  Positive integers m and p,
      with m >= 2   and   (5 raised to the pth power) >= m.
    What goes out:  Let X = the sum of all the integers
      between m and (5 raised to the pth power), inclusive,
      that are prime.
      This function returns  True   if X is prime.
      This function returns  False  if X is NOT a prime.
    Side effects:   None.
    Examples:
      -- If m is 17 and p = 2, this function returns True,
           because the sum of the primes
           between 17 and (5 to the 2nd power, i.e. 25) is:
             17 + 19 + 23, which is 59,
             and  59 IS prime.
      -- If m is 18 and p = 2, this function returns False,
           because the sum of the primes
           between 18 and (5 to the 2nd power, i.e. 25) is:
             19 + 23, which is 42,
             and  42 is NOT prime.
      -- If m is 85 and p = 3, this function returns True,
           because the sum of the primes
           between 85 and (5 to the 3rd power, i.e. 125) is:
             89 + 91 + 97 + 101 + 103 + 107 + 109 + 113, which is 719,
             and  719 IS prime.
    """
    # ------------------------------------------------------------------
    # DONE: 4. Implement and test this function.
    #          Tests have been written for you (above).
    ####################################################################
    # IMPORTANT:
    #    **  For full credit you must appropriately use (call)
    #    **  the appropriate functions that are defined above
    #    **  possibly including ones you have written.
    ####################################################################
    # ------------------------------------------------------------------
    summation = 0
    for i in range(m, 5 ** p + 1):
        if is_prime(i) == True:
            summation = summation + i
    if is_prime(summation) == True:
        return True
    else:
        return False


def test_problem1c():
    """ Tests the   problem1c   function. """
    print()
    print('--------------------------------------------------')
    print('Testing the   problem1c   function:')
    print('--------------------------------------------------')

    ####################################################################
    # THESE TESTS ARE ALREADY DONE.  DO NOT CHANGE THEM.
    # You may add more tests if you want,
    # but you are not required to do so.
    ####################################################################

    # Test 1:
    expected = 5 * 10
    answer = problem1c(50, 100)
    print()
    print('Test 1 expected:', expected)
    print('       actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')

    # Test 2:
    expected = 2 * 8
    answer = problem1c(23, 53)
    print()
    print('Test 2 expected:', expected)
    print('       actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')

    # Test 3:
    expected = 2 * 5
    answer = problem1c(33, 53)
    print()
    print('Test 3 expected:', expected)
    print('       actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')

    # Test 4:
    expected = 1 * 0
    answer = problem1c(20, 22)
    print()
    print('Test 4 expected:', expected)
    print('       actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')

    # Test 5:
    expected = 4 * 7
    answer = problem1c(101, 131)
    print()
    print('Test 5 expected:', expected)
    print('       actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')

    # Test 6:
    expected = 2 * 5
    answer = problem1c(102, 130)
    print()
    print('Test 6 expected:', expected)
    print('       actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')

    # Test 7:
    expected = 107 * 168
    answer = problem1c(2, 1000)
    print()
    print('Test 7 expected:', expected)
    print('       actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')

    # Test 8:
    expected = 90 * 1061
    answer = problem1c(1000, 10000)
    print()
    print('Test 8 expected:', expected)
    print('       actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')

    # Test 9:
    expected = 83 * 133
    answer = problem1c(101, 929)
    print()
    print('Test 9 expected:', expected)
    print('       actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')

    # Test 10:
    expected = 83 * 133
    answer = problem1c(100, 930)
    print()
    print('Test 10 expected:', expected)
    print('        actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')

    # Test 11:
    expected = 81 * 131
    answer = problem1c(102, 928)
    print()
    print('Test 11 expected:', expected)
    print('        actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')

    # Test 12:
    expected = 82 * 132
    answer = problem1c(101, 928)
    print()
    print('Test 12 expected:', expected)
    print('        actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')

    # Test 13:
    expected = 82 * 132
    answer = problem1c(102, 929)
    print()
    print('Test 13 expected:', expected)
    print('        actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')

    # Test 14:
    expected = 280 * 2237
    answer = problem1c(100, 20000)
    print()
    print('Test 14 expected:', expected)
    print('        actual:  ', answer)
    if expected != answer:
        print(' **** THIS TEST FAILED. ****')


def problem1c(m, n):
    """
    What comes in:  Positive integers m and n, with m <= n.
    What goes out:  Returns the product XY where:
      -- X is the number of integers from m to n, inclusive,
           that are PALINDROMES.
      -- Y is the number of integers from m to n, inclusive,
           that are PRIME.
    Side effects:   None.
    Examples:
      -- If m is 50 and n is 100:
           this function returns 5 * 10, which is 50,
           because the palindromes between 50 and 100 are:
              55  66  77  88  99   [so there are 5 of them]
           and the primes between 50 and 100 are:
              53  59  61  67  71  73  79  83  89  97
              [so there are 10 of them]
      -- If m is 23 and n is 53:
           this function returns 2 * 8, which is 16,
           because the palindromes between 23 and 53 are
              33  44     [so there are 2 of them]
           and the primes between 23 and 53 are
              23  29  31  37  41  43  47  53
              [so there are 8 of them]
    """
    # ------------------------------------------------------------------
    # DONE: 4. Implement and test this function.
    #          Tests have been written for you (above).
    ####################################################################
    # IMPORTANT:
    #    **  For full credit you must appropriately use (call)
    #    **  the appropriate functions that are defined above.
    ####################################################################
    # ------------------------------------------------------------------
    x = 0
    y = 0
    for i in range(m, n + 1):
        if is_palindrome(i) == True:
            x = x + 1
            if is_prime(i) == True:
                y = y + 1
        elif is_prime(i) == True:
            y = y + 1
        # print(i)
        # print(x, y)
    return (x * y)


# ----------------------------------------------------------------------
# Calls  main  to start the ball rolling.
# ----------------------------------------------------------------------
main()
