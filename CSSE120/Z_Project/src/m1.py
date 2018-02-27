"""
The Python Capstone Project.

CSSE 120 - Introduction to Software Development.
Team members: Nick Harrelson, Annette Ho, Zachary Taylor, Joey Wilkins

The primary author of this module is: Nick Harrelson.
"""
# DONE: Put the names of ALL team members in the above where indicated.
#       Put YOUR NAME in the above where indicated.

import m0
import m2
import m3
import m4


import tkinter
from tkinter import ttk
import rosebot as rb
import tweepy


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

    name_label = ttk.Label(frame, text='Nick Harrelson')
    name_label.grid()

    # Travel in Square
    square_button = ttk.Button(frame, text='Travel in a square')
    square_button['command'] = lambda: square_travel(dc)
    square_button.grid()


    # Tweet and LED toggle for status
    # Tweets can be found @RobotTeam5

    tweet_label = ttk.Label(frame, text='Tweet LED status to @RobotTeam5')
    tweet_label.grid()

    led_button = ttk.Button(frame, text='LED toggle')
    led_button['command'] = lambda: led_toggle(dc)
    led_button.grid()

    tweet_button = ttk.Button(frame, text='Tweet LED status')
    tweet_button['command'] = lambda: tweet_led(dc)
    tweet_button.grid()

    # Follow the largest object the robot senses

    follow_label = ttk.Label(frame, text='Follow the largest object the robot senses')
    follow_label.grid()

    follow_button = ttk.Button(frame, text='Follow')
    follow_button['command'] = lambda: follow_this(dc)
    follow_button.grid()

    return frame


def follow_this(dc):
    blocks = dc.robot.get_blocks()
    # if it detects an object, do this
    if(blocks):
        # recognize the biggest object
        max_index = 0
        max_size = 0
        for k in range(len(blocks)):
            size = blocks[k].width() * blocks[k].height()
            if size > max_size:
                max_index = k
                max_size = size

        # simplified negative feedback loop
        # doesn't scale movement based on size of error
        while True:
            # distance_check allows us to fix the angle of orientation
            # before fixing the distance from object
            distance_check = False
            # check where the object is relative to field of view
            # if its outside the desired range, rotate to it
            if blocks[max_index].x() > 170:
                distance_check = False
                m3.turning_function(-5, dc)
            elif blocks[max_index].x() < 150:
                distance_check = False
                m3.turning_function(5, dc)
            else:
                distance_check = True
            # once angle is fixed, fix distance
            if distance_check:
                distance = dc.robot.distance_to_object_seen()
                # arbitrary distance check
                if distance < 3500:
                    # drive forward a small distance
                    dc.robot.differential_drive.drive_pwm(120, 120)
                    dc.robot.connection.sleep(0.5)
                    dc.robot.differential_drive.stop()


def get_api(cfg):
    # allows the robot to interact with twitter
    auth = tweepy.OAuthHandler(cfg['consumer_key'], cfg['consumer_secret'])
    auth.set_access_token(cfg['access_token'], cfg['access_token_secret'])
    return tweepy.API(auth)


def tweet_led(dc):
    # checks the LED status at a time
    # writes a tweet about said status
    from datetime import datetime
    timedate = str(datetime.now())
    led_status = ''
    if dc.robot.led.check():
        led_status = 'on'
    else:
        led_status = 'off'
    msg = 'At ' + timedate[11:16] + ' on ' + timedate[0:10]
    msg = msg + ' my LED was turned ' + led_status
    # sends message to twitter
    tweet(dc, msg)


def tweet(dc, msg):
    # grants access to out specific twitter account
    cfg = {
    "consumer_key"        : "4jTJDx0Z7WfVJNai33vnU5KBC",
    "consumer_secret"     : "QbXyvkych9wtnhqxhsYonhZNaSWFNrQhaj7TAdAY13jJHt2Yrg",
    "access_token"        : "765886478283145216-lr6oDjtRs3E3jMHU2Y1tHaNboCUpOAg",
    "access_token_secret" : "MUXZ2IZCRECNmjQkWgiZIRSoVLLflx5RS9sG9cC8117hQ"
    }
    api = get_api(cfg)
    # tweets the passed message via the API
    status = api.update_status(status=msg)


def led_toggle(dc):
    # simple toggle of the onboard LED
    if dc.robot.led.check():
        dc.robot.led.off()
    else:
        dc.robot.led.on()


def square_travel(dc):
    # simple square movement pattern
    for _ in range(4):
        dc.robot.differential_drive.drive_pwm(120, 120)
        dc.robot.connection.sleep(2.0)
        dc.robot.differential_drive.stop()
        m3.turning_function(90, dc)
        dc.robot.differential_drive.sleep(2.0)


# ----------------------------------------------------------------------
# If this module is running at the top level (as opposed to being
# imported by another module), then call the 'main' function.
# ----------------------------------------------------------------------
if __name__ == '__main__':
    m0.main()
