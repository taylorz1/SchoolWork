"""
Test 2, problem 2.

Authors: David Mutchler, Dave Fisher, Valerie Galluzzi, Amanda Stouder,
         their colleagues and Zachary Taylor.  Summer 2016.
"""  # DONE: 1. PUT YOUR NAME IN THE ABOVE LINE.

import time
import sys


def main():
    """ Calls the   TEST   functions in this module. """
    test_init()
    test_print_me()
    test_censor()
    test_censor_all()
    test_get_number_of_characters_censored()
    test_make_combined()


########################################################################
# The   StringCensor  class is here.  TESTS for it are further down.
########################################################################


class StringCensor(object):
    """
    Represents a StringCensor -- an object that has a string
    and can "censor" (remove occurrences of) requested characters
    from its string.
    """

    def __init__(self, s):
        """
        What comes in:
          -- self
          -- A string s
        What goes out: Nothing (i.e., None).
        Side effects:
          -- Stores the given string in an instance variable named
               self.string
          -- [Eventually] Sets additional instance variables
                          as needed for other methods.
        Type hints:
            :type s str
        """
        # --------------------------------------------------------------
        # DONE: 2. Implement and test this function.
        #     See the testing code (below) for examples.
        # --------------------------------------------------------------
        self.string = s
        self.removed = 0

    def print_me(self):
        """
        What comes in:
          -- self
        What goes out: Nothing (i.e., None).
        Side effects:
          Prints (on the Console) this StringCensor's string.
        """
        # --------------------------------------------------------------
        # DONE: 3. Implement and test this function.
        #     See the testing code (below) for examples.
        # --------------------------------------------------------------
        print(self.string)

    def censor(self, character):
        """
        What comes in:
          -- self
          -- A string that is a single character
        What goes out: Nothing (i.e., None).
        Side effects:
          Replaces its existing string with a new
          string that is the same as the old one, except with
          all occurrences of the given character excluded.
        Example:
          sc = StringCensor('Hello there!')
          sc.print_me()  # Should print:  Hello there!
          sc.censor('e')
          sc.print_me()  # Should print:  Hllo thr!
        Type hints:
          :type character: str
        """
        # --------------------------------------------------------------
        # DONE: 4. Implement and test this function.
        #     See the testing code (below) for examples.
        # --------------------------------------------------------------
        a = ''
        for k in range(len(self.string)):
            if self.string[k] != character:
                a = a + self.string[k]
            if self.string[k] == character:
                self.removed = self.removed + 1
        self.string = a

    def censor_all(self, string_censors, character):
        """
        What comes in:
          -- self
          -- A sequence of StringCensor objects
        What goes out: Nothing (i.e., None).
        Side effects:
          For each StringCensor in the given list of StringCensors,
          and ALSO for this StringCensor:
            -- Replaces its existing string with a new
               string that is the same as the old one, except with
               all occurrences of the given character excluded.
               (It should call the   censor   method to do so.)
        Example:
        
        Suppose that we construct some StringCensors, like this:
          sc = StringCensor('Hello to each of you!')
          sc1 = StringCensor('Hello there!')
          sc2 = StringCensor('eeee!eeeeeee')
          sc3 = StringCensor('oooooo')
          sc4 = StringCensor('eoeoeoeoeo')
          sc5 = StringCensor('eoeoeoeoeoe')
          
        Then suppose that we call censor_all as follows:
          sc.censor_all([sc1, sc2, sc3, sc4, sc5], 'e')
        
        Doing so should have the effect of calling the  censor  method
        on each, sending it the character 'e' in each case.
        So after the above:
          print(sc.string)   # Should print: Hllo to ach of you!
          print(sc1.string(  # Should print: Hllo thr!
          print(sc2.string(  # Should print: !
          print(sc3.string(  # Should print: oooooo
          print(sc4.string(  # Should print: ooooo
          print(sc5.string(  # Should print: ooooo
    
        Type hints:
            :type string_censors: [StringCensor]
        """
        # --------------------------------------------------------------
        # DONE: 5. Implement and test this function.
        #     See the testing code (below) for examples.
        # --------------------------------------------------------------
        self.censor(character)
        for k in range(len(string_censors)):
            string_censors[k].censor(character)


    def get_number_of_characters_removed(self):
        """
        What comes in:
          -- self
        What goes out:
          Returns the number of characters that this StringCensor
          has removed.
        Side effects:  None.
        Example:
          sc = StringCensor('Hello there, how are you?')
          sc.censor('e')
          n = sc.get_number_of_characters_removed()
          print(n)  # Should print 4
          
          sc.censor('h')
          n = sc.get_number_of_characters_removed()
          print(n)  # Should print 6
          
          sc.string = 'More eees! eee! eee!'
          sc.censor('e')
          n = sc.get_number_of_characters_removed()
          print(n)  # Should print 16
        """
        # --------------------------------------------------------------
        # DONE: 6. Implement and test this function.
        #     See the testing code (below) for examples.
        # --------------------------------------------------------------
        return self.removed

    def make_combined(self, string_censor1, stringcensor_2):
        """
        What comes in:
          -- self
          -- Two more StringCensor objects
        What goes out:
          Returns a new StringCensor whose string is:
            -- the string of string_censor1, followed by
            -- the string of this StringCensor, followed by
            -- the string of string_censor2
        Side effects:  None.
        Example:
          sc_a = StringCensor('Hello')
          sc_b = StringCensor('there')
          sc_c = StringCensor('stranger')
          
          sc_new = sc_a.make_combined(sc_b, sc_c)
          sc_new.print_me()  # Should print:  thereHellostranger
        """
        # --------------------------------------------------------------
        # DONE: 7. Implement and test this function.
        #     See the testing code (below) for examples.
        # --------------------------------------------------------------
        return StringCensor(string_censor1.string + self.string + stringcensor_2.string)

########################################################################
# The TEST functions for the  StringCensor  class begin here.
########################################################################


def test_init():
    """ Tests the   init   method. """
    print()
    print('-----------------------------------------------------------')
    print('Testing the   __init__   method of the StringCensor class.')
    print('-----------------------------------------------------------')

    # Test 1:
    print()
    sc = StringCensor('Hello there!')
    expected = 'Hello there!'
    actual = sc.string
    print("Expected:", expected)
    print("Actual:  ", actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()

    # Test 2:
    print()
    sc = StringCensor('Frodo Baggins')
    expected = 'Frodo Baggins'
    actual = sc.string
    print("Expected:", expected)
    print("Actual:  ", actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()


def test_print_me():
    """ Tests the   print_me   method. """
    print()
    print('-----------------------------------------------------------')
    print('Testing the   print_me   method of the StringCensor class.')
    print('-----------------------------------------------------------')

    # Test 1:
    print()
    sc = StringCensor('CHECK TO SEE that this WORKS!')
    print('The following two lines should be idential:')
    print('   CHECK TO SEE that this WORKS!')
    print('   ', end='')
    sc.print_me()

    # Test 2:
    print()
    sc = StringCensor('Sally Ride')
    print('The following two lines should be idential:')
    print('   Sally Ride')
    print('   ', end='')
    sc.print_me()

    # Test 3:
    print()
    sc.string = 'Neil Armstrong'
    print('The following two lines should be idential:')
    print('   Neil Armstrong')
    print('   ', end='')
    sc.print_me()


def test_censor():
    """ Tests the   censor   method. """
    print()
    print('-----------------------------------------------------------')
    print('Testing the   censor   method of the StringCensor class.')
    print('-----------------------------------------------------------')

    # Test 1:
    print()
    sc = StringCensor('Hello there!')
    sc.censor('e')
    expected = 'Hllo thr!'
    actual = sc.string
    print("Expected:", expected)
    print("Actual:  ", actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()

    # Test 2:
    print()
    sc = StringCensor('Hello there, how are you?')
    sc.censor('e')
    expected = 'Hllo thr, how ar you?'
    actual = sc.string
    print("Expected:", expected)
    print("Actual:  ", actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()

    # Test 3:
    print()
    sc.censor('h')
    expected = 'Hllo tr, ow ar you?'
    actual = sc.string
    print("Expected:", expected)
    print("Actual:  ", actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()

    # Test 4:
    print()
    sc.string = 'More eees! eee! eee!'
    sc.censor('e')
    expected = 'Mor s! ! !'
    actual = sc.string
    print("Expected:", expected)
    print("Actual:  ", actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()

    # Test 5:
    print()
    sc = StringCensor('Hello there!')
    sc.censor('x')
    expected = 'Hello there!'
    actual = sc.string
    print("Expected:", expected)
    print("Actual:  ", actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()


def test_censor_all():
    """ Tests the   censor_all   method. """
    print()
    print('-----------------------------------------------------------')
    print('Testing the   censor_all   method of the StringCensor class.')
    print('-----------------------------------------------------------')

    # Test 1:
    print()
    sc = StringCensor('Hello to each of you!')
    sc1 = StringCensor('Hello there!')
    sc2 = StringCensor('eeee!eeeeeee')
    sc3 = StringCensor('oooooo')
    sc4 = StringCensor('eoeoeoeoeo')
    sc5 = StringCensor('eoeoeoeoeoe')

    sc.censor_all([sc1, sc2, sc3, sc4, sc5], 'e')
    expected = ('Hllo to ach of you!',
                'Hllo thr!',
                '!',
                'oooooo',
                'ooooo',
                'ooooo')
    actual = (sc.string, sc1.string, sc2.string,
              sc3.string, sc4.string, sc5.string)
    print("Expected:", expected)
    print("Actual:  ", actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()

    # Test 2:
    print()
    sc = StringCensor('Hello to each of you!')
    sc.censor_all([], 'e')
    expected = 'Hllo to ach of you!'
    actual = sc.string
    print("Expected:", expected)
    print("Actual:  ", actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()

    # Test 3:
    print()
    sc = StringCensor('Hello to each of you!')
    sc1 = StringCensor('Hello there!')
    sc2 = StringCensor('eeee!eeeeeee')
    sc3 = StringCensor('oooooo')
    sc4 = StringCensor('eoeoeoeoeo')
    sc5 = StringCensor('eoeoeoeoeoe')

    sc.censor_all([sc1, sc2, sc3, sc4, sc5], 'o')
    expected = ('Hell t each f yu!',
                'Hell there!',
                'eeee!eeeeeee',
                '',
                'eeeee',
                'eeeeee')
    actual = (sc.string, sc1.string, sc2.string,
              sc3.string, sc4.string, sc5.string)
    print("Expected:", expected)
    print("Actual:  ", actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()


def test_get_number_of_characters_censored():
    """ Tests the   get_number_of_characters_censored()   method. """
    print()
    print('-----------------------------------------------------------')
    print('Testing the   get_number_of_characters_censored   method')
    print('of the StringCensor class.')
    print('-----------------------------------------------------------')

    # Test 1:
    print()
    sc = StringCensor('Hello there, how are you?')
    sc.censor('e')
    expected = 4
    actual = sc.get_number_of_characters_removed()
    print("Expected:", expected)
    print("Actual:  ", actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()

    # Test 2:
    print()
    sc.censor('h')
    expected = 6
    actual = sc.get_number_of_characters_removed()
    print("Expected:", expected)
    print("Actual:  ", actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()

    # Test 3:
    print()
    sc.string = 'More eees! eee! eee!'
    sc.censor('e')
    expected = 16
    actual = sc.get_number_of_characters_removed()
    print("Expected:", expected)
    print("Actual:  ", actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()


def test_make_combined():
    """ Tests the   make_combined   method. """
    print()
    print('-----------------------------------------------------------')
    print('Testing the   make_combined   method of the StringCensor class.')
    print('-----------------------------------------------------------')

    # Test 1:
    print()
    sc_a = StringCensor('Hello')
    sc_b = StringCensor('there')
    sc_c = StringCensor('stranger')
    expected = 'thereHellostranger'
    result = sc_a.make_combined(sc_b, sc_c)
    actual = result.string
    print("Expected:", expected)
    print("Actual:  ", actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()

    # Test 2:
    print()
    expected = 'Hellostrangerthere'
    result = sc_c.make_combined(sc_a, sc_b)
    actual = result.string
    print("Expected:", expected)
    print("Actual:  ", actual)
    if expected == actual:
        print("Test passed SUCCESSFULLY!")
    else:
        print_failure_message()

def print_failure_message(message='  *** FAILED the above test. ***',
                          flush_time=1.0):
    """ Prints a message onto stderr, hence in RED. """
    time.sleep(flush_time)
    print(message,
          file=sys.stderr, flush=True)
    time.sleep(flush_time)

# ----------------------------------------------------------------------
# If this module is running at the top level (as opposed to being
# imported by another module), then call the 'main' function.
# ----------------------------------------------------------------------
if __name__ == '__main__':
    main()
