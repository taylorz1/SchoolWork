"""
The Python Capstone Project.

CSSE 120 - Introduction to Software Development.
Team members: Hoy, Taylorz1, harrelsn,  (all of them).

The primary author of this module is: Zachary Taylor.
"""
# DONE: Put the names of ALL team members in the above where indicated.
#       Put YOUR NAME in the above where indicated.

import m0
import m1
import m2
import m4

import tkinter
from tkinter import ttk
import rosebot as rb
import time


def my_frame(root, dc):
    """
    Constructs and returns a   ttk.Frame   on the given root window.
    The frame contains all of this module's widgets.
    Does NOT   grid   the Frame, since the caller will do that.
    Also sets up callbacks for this module's widgets.

    The first argument is the  root  window (a tkinter.Tk object)
    onto which the   ttk.Frame  returned from this function
    will be placed.  The second argument is the shared DataContainer
    object that is CONSTRUCTED in m0 but USED in m1, m2, m3 and m4.

    Preconditions:
      :type root: tkinter.Tk
      :type dc:   m0.DataContainer
    """
    frame = ttk.Frame(root, padding=10, relief='ridge')

    name_label = ttk.Label(frame, text='Zachary Taylor')
    name_label.grid()

    button_connect_wired = ttk.Button(frame, text='Connect Wired Robot')
    button_connect_wired.grid()
    button_connect_wired['command'] = lambda : connect(dc)

    degree_turn = ttk.Entry(frame)
    degree_turn.grid()


    # This is the button that initiates it's turn cycle
    button_turn = ttk.Button(frame, text='Turns the above number of degrees (counterclockwise is +)')
    button_turn['command'] = lambda : turning_function(degree_turn, dc)
    button_turn.grid()

    # This is the button that stops the robot
    button_stop = ttk.Button(frame, text='Stops the motors')
    button_stop['command'] = lambda : stop_function(dc)
    button_stop.grid()


    # This is the button that will cause the robot to trigger it's keyboard
    # mode. That is to say it can be keyboard operated.
    button_keyboard_toggle = ttk.Button(frame, text='keyboard drive mode, press q to exit')
    button_keyboard_toggle['command'] = lambda : keyboard_toggle_function(dc)
    button_keyboard_toggle.grid()

    # These buttons dedicate themselves to the pursuit of manhatten movement.
    # The input is pairs of coordinates in the following format (x,y).
    # While this is actually a few buttons it's largely self-intuitive.
    entry_manhatten = ttk.Entry(frame)
    entry_manhatten.grid()

    button_add = ttk.Button(frame, text='Adds the above coordinate pair, INPUT FORMAT: XX , YY or (XX, YY)')
    button_add.grid()
    button_add['command'] = lambda : add_coordinates(entry_manhatten, dc)

    button_clear = ttk.Button(frame, text='Clears coordinate pair list')
    button_clear.grid()
    button_clear['command'] = lambda : clear_coordinates(dc)

    entry_scale = ttk.Entry(frame)
    entry_scale.grid()

    button_scale = ttk.Button(frame, text='Set scale! Avail: inches, centimeters')
    button_scale.grid()
    button_scale['command'] = lambda : set_scale(entry_scale, dc)

    button_manhatten = ttk.Button(frame, text='Run Manhatten Movement, every input is assumed scale = inches')
    button_manhatten.grid()
    button_manhatten['command'] = lambda : manhatten_project(dc)


    button_forward = ttk.Button(frame, text='Testing function for scale reasons: Forward')
    button_forward.grid()
    button_forward['command'] = lambda : dc.robot.differential_drive.drive_pwm(200, 200)  # At this power setting it travels 3ft/s.

    button_turning_debug = ttk.Button(frame, text='Testing function for scale reasons: Turn')
    button_turning_debug.grid()
    button_turning_debug['command'] = lambda : dc.robot.differential_drive.drive_pwm(-100, 100)



    return frame

def connect(dc):
    robot = rb.RoseBot()
    robot.connect_wired(None)
    dc.robot = robot


# The below function ideally turns the robot the indicated number of degrees
# In the desired direction.

def turning_function(degree_entry, dc):
    if isinstance(degree_entry, int) or isinstance(degree_entry, float):
        degrees = degree_entry
    else:
        degrees = float(degree_entry.get())  # This should acquire the degree number
    # print(degrees)

    # Utilizing the DifferentialDrive class we find that to turn we just
    # Need to move the wheels in opposite directions using the
    # drive_pwm(entry, -entry) method. I'll arbitrarily choose to move at 100.

    # Below follows some assumptions due to not knowing how far something moves.
    magic_number = 1 / 180  # This represents the conversion between degrees and time based on examination.
    time_to_run = degrees * magic_number

    if degrees > 0:
        while time.time() < time_to_run:
            dc.robot.differential_drive.drive_pwm(-100, 100)  # This turns counterclockwise based on the input being >0
    else:
        while time.time() < time_to_run:
            dc.robot.differential_drive.drive_pwm(100, -100)  # This turns clockwise based on the input being <0




# Below is a function to stop the motors.

def stop_function(dc):
    dc.robot.differential_drive.stop()
    print('Robot Stops')


# Function below will cause keyboard driving
def keyboard_toggle_function(dc):
    root = tkinter.Toplevel()
    # key = root.bind_all('<Key>')
    # Designating arrow key press to call movement functions and arrow key
    # release to stop.
    root.bind_all('<KeyPress-Up>', lambda x: move_forward(dc))
    root.bind_all('<KeyPress-Left>', lambda x: turn_counterclockwise(dc))
    root.bind_all('<KeyPress-Right>', lambda x: turn_clockwise(dc))
    root.bind_all('<KeyPress-Down>', lambda x: move_backward(dc))
    root.bind_all('<KeyRelease-Up>', lambda x: stop_function(dc))
    root.bind_all('<KeyRelease-Left>', lambda x: stop_function(dc))
    root.bind_all('<KeyRelease-Right>', lambda x: stop_function(dc))
    root.bind_all('<KeyRelease-Down>', lambda x: stop_function(dc))
    root.bind_all('<KeyPress-q>', lambda x: root.destroy())


# Below are functions related to arrow keys
# The functions below take the general form of the direction we would
# like the robot to go and then simply tells the robot to
def move_forward(dc):
    dc.robot.differential_drive.drive_pwm(200, 200)
    print('moving forward')

def turn_counterclockwise(dc):
    dc.robot.differential_drive.drive_pwm(-100, 100)
    print('Turning Counterclockwise')

def turn_clockwise(dc):
    dc.robot.differential_drive.drive_pwm(100, -100)
    print('Turning Clockwise')

def move_backward(dc):
    dc.robot.differential_drive.drive_pwm(-200, -200)
    print('moving backward')


# Functions related to Manhatten Movement

def set_scale(scale_entry, dc):
    scale_entry_string = scale_entry.get()
    # Gotta check if the string entry is valid, then we write it to dc.scale if it is.
    if scale_entry_string not in ['inches', 'Inches', 'in', 'centimeters', 'Centimeters', 'cm']:
        dc.scale = 'inches'
        print('Invalid scale input, set to Inches')
    else:
        if scale_entry_string in ['inches', 'Inches', 'in']:
            dc.scale = 'inches'
        else:
            dc.scale = 'centimeters'
    print('scale set to ' + dc.scale)


def add_coordinates(entry_manhatten, dc):
    # Gotta format the data into a usable array, remove spaces, remove parens, split into an array
    contents_coordinates = entry_manhatten.get().replace(' ', '').replace('(', '').replace(')', '').split(',')

    # Add the coordinates to any existing coordinates.
    dc.manhatten_coordinate_list.append(contents_coordinates[0])
    dc.manhatten_coordinate_list.append(contents_coordinates[1])
    print(dc.manhatten_coordinate_list)


def clear_coordinates(dc):
    dc.manhatten_coordinate_list = []


# Now we'll actually move in accordance to manhatten movement, which is moving x then y in coordinate pairs.
def manhatten_project(dc):
    coordinate_list_x = [float(x) for x in dc.manhatten_coordinate_list[::2]]  # Split into x and y lists
    coordinate_list_y = [float(x) for x in dc.manhatten_coordinate_list[1::2]]
    # print(coordinate_list_x)
    # print(coordinate_list_y)



    orientation = 0  # We'll use this so our robot doesn't get lost.
    for n in range(len(coordinate_list_x)):
        # After each pair we'll reset to 0
        if orientation is not 0:
            if n is not 0:
                if orientation == 90:
                    if coordinate_list_x[n] < coordinate_list_x[n - 1]:
                        turning_function(90, dc)
                    else:
                        turning_function(-90, dc)
                else:
                    if coordinate_list_x[n] < coordinate_list_x[n - 1]:
                        turning_function(-90, dc)
                    else:
                        turning_function(90, dc)

        # Then we move as far as described
        # m2.go_straight(abs(dc.manhatten_coordinate_list[n])) #Doesn't exist yet, commented for usability reasons.


        # Below are y instructions.

        # Determining orientation to turn
        if n == 0:  # I hate maybe special exceptions but I can't think of a better way.
            if coordinate_list_y[0] < 0:
                turning_function(-90, dc)
                orientation = orientation - 90
            else:
                turning_function(90, dc)
                orientation = orientation + 90
        else:
            if coordinate_list_y[n] < coordinate_list_y[n - 1]:
                turning_function(-90, dc)
                orientation = orientation - 90
            else:
                turning_function(90, dc)
                orientation = orientation + 90
        # m2.go_straight(coordinate_list_y[n])



# ----------------------------------------------------------------------
# If this module is running at the top level (as opposed to being
# imported by another module), then call the 'main' function.
# ----------------------------------------------------------------------
if __name__ == '__main__':
    m0.main()
