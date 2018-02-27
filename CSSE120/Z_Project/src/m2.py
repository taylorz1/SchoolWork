"""
The Python Capstone Project.

CSSE 120 - Introduction to Software Development.
Team members: PUT-YOUR-NAMES_HERE (all of them).

The primary author of this module is: Annette Ho.
"""
# DONE: Put the names of ALL team members in the above where indicated.
#       Put YOUR NAME in the above where indicated.

import m0
import m1
import m3
import m4

import tkinter
from tkinter import ttk
import rosebot as rb


class DataContainer():
    def __init__(self):
        self.robot = None
        self.speed = None


def main():
    """
    Tests functions in this module.
    Intended to be used internally by the primary author of this module.
    """
    print('-------------------------------')
    print('Testing functions in module m2:')
    print('-------------------------------')

    # Teammates' Working Times

    read_hours_files()

    # Testing

    root = tkinter.Tk()
    dc = DataContainer()
    dc.root = root
    my_frame(root, dc)
    root.mainloop()


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

    a_frame = ttk.Frame(root, padding=100)
    dc.frame = a_frame
    a_frame.grid()

    # Connect To Robot
    robot_num = ttk.Label(a_frame, text='Robot Number: ')
    robot_num.grid(column=0, row=0)
    enter_box1 = ttk.Entry(a_frame)
    enter_box1.grid(column=1, row=0)
    connect_button = ttk.Button(a_frame, text='Connect')
    connect_button.grid(column=2, row=0)
    connect_button['command'] = (lambda: con(enter_box1, dc))

    # Working Hours
    hours_button = ttk.Button(a_frame, text='Member Working Hours')
    hours_button['command'] = (lambda: read_hours_files())
    hours_button.grid(column=0, row=1)

    # Go Straight in Angles
    enter_box2 = ttk.Entry(a_frame)
    enter_box2.grid()
    straight_button = ttk.Button(a_frame, text='Distance')
    # stright_button['command'] = (lambda)
    enter_box3 = ttk.Entry(a_frame)
    enter_box3.grid()
    angle_button = ttk.Button(a_frame, text='Angle')
    # angle_button['command'] = (lambda)

    # Follow a Curvey Black Line
    follow_button = ttk.Button(a_frame, text='Follow Black Line')
    follow_button['command'] = (lambda: follow_black_line(dc))
    follow_button.grid(column=0, row=12)

    # Compose Music
    compose_button = ttk.Button(a_frame, text='Compose Music')
    compose_button['command'] = (lambda: compose_music(dc))
    compose_button.grid(column=0, row=11)


def con(enter_box, dc):
    robot_number = enter_box.get()
    robot = rb.RoseBot()
    robot.connect_wifly(robot_number)
    dc.robot_number = robot_number
    dc.robot = robot


# Need to Finish
# def go_straight():


# Need Fix
def read_hours_files():

    # Harrelson's Working Time
    f = open('../process/hours-1.txt')
    w = f.read()
    f.close()
    lines = w.split('\n')
    hours1_1 = lines[1]
    hours1_2 = lines[2]
    hours1_3 = lines[3]
    print('Total hours Harrelson worked on Sprint1:', hours1_1[1])
    lines = w.split('\n')
    print('Total hours Harrelson worked on Sprint2:', hours1_2[0])
    lines = w.split('\n')
    print('Total hours Harrelson worked on Sprint3:', hours1_3[0])
    print()

    # Ho's Working Time
    f = open('../process/hours-2.txt')
    w = f.read()
    f.close()
    lines = w.split('\n')
    hours2_1 = lines[1]
    hours2_2 = lines[2]
    hours2_3 = lines[3]
    print('Total hours Ho worked on Sprint1:', int(hours2_1[0]) + int(hours2_2[0]) + int(hours2_3[0]))
    print('Total hours Ho worked on Sprint2:', hours2_2[0])
    print('Total hours Ho worked on Sprint3:', hours2_3[0])
    print()

    # Taylor's Working Time
    f = open('../process/hours-3.txt')
    w = f.read()
    f.close()
    lines = w.split('\n')
    hours3_1 = lines[1]
    hours3_2 = lines[2]
    hours3_3 = lines[3]
    print('Total hours Taylor worked on Sprint1:', hours3_1[0])
    lines = w.split('\n')
    print('Total hours Taylor worked on Sprint2:', hours3_2[0])
    lines = w.split('\n')
    print('Total hours Taylor worked on Sprint3:', hours3_3[0])

# Need Fix
def follow_black_line(dc):
    robot = dc.robot

    right_speed = 5
    left_speed = 10
    k = 0.000005
    while True:
        left_value = robot.sensors.left_reflectance.read()
        right_value = robot.sensors.right_reflectance.read()
        center_value = robot.sensors.center_reflectance.read()

        left_adj = center_value - left_value
        right_adj = center_value - right_value
        # print("left", left_value, "center ", center_value, "Right", right_value)
        # print("Left ", (left_speed + k * left_adj), "Right ", (right_speed + k * right_adj))

        left = k * left_adj + left_speed
        right = k * right_adj + right_speed

        if left >= 20:
            left = 20
        if right >= 20:
            right = 20
        if left <= -5:
            left = -5
        if right <= -5:
            right = -5

        if ((left_value <= 100) and (right_value <= 100) and (center_value <= 100)):
            left = 0
            right = 0

        # print(left_value, center_value, right_value)
        dc.robot.differential_drive.drive(left, right)


def compose_music(dc):
    F3s = 185
    G3 = 196
    A3 = 220
    B3 = 247
    C4s = 277
    D4 = 294
    E4 = 330
    F4s = 370
    G4 = 392
    A4 = 440

    music1 = [A4, F4s, G4, A4, F4s, G4]
    music2 = [A4, A3, B3, C4s, D4, E4, F4s, G4]
    music3 = [F4s, D4, E4, F4s, F3s, G3]
    music4 = [A3, B3, A3, G3, A3, D4, C4s, D4]

    full_music = [music1, music2, music3, music4]

    for k in range(len(full_music)):
        musicnum = full_music[k]
        for n in range(len(musicnum)):
            note = musicnum[n]
            # note = musicnum[random.randrange(0, len(musicnum))]
            length = 0.3
            dc.robot.buzzer.play_frequency(note, length)
            dc.robot.connection.sleep(0.2)
        dc.robot.connection.sleep(1.0)


# ----------------------------------------------------------------------
# If this module is running at the top level (as opposed to being
# imported by another module), then call the 'main' function.
# ----------------------------------------------------------------------
if __name__ == '__main__':
    m0.main()
