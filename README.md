Logic's Game of Life - Demitri Maestas

This is a game based on the mathematical model constructed by John Horton Logic.

In this 3D Implementation, The user is able to control the Rules of the game.
The rules per game itteration are as follows:

-A cell will first continue the state it is in, Unless:
-A cell will become ALIVE if the number of neighbors is equal or more than R1 and equal or less that R2.
-A cell will DIE if the number of neighbors is more than R3 or less than R4.

Users can zoom in and out with the UP arrow key and DOWN arrow key, or with the
buttons provided in the interface.

There are interesting presets to enjoy.

The Camera is initialized dynamically, placing the initial distance from
the camera to the center of the grid relative to the size of the grid.

Bugs:
Rouge cells will sometimes not die.
