Logic's Game of Life - Demitri Maestas

This is a game based on the mathematical model constructed by John Horton Logic.

In this 3D Implementation, The user is able to control the Rules of the game.
The rules per game itteration are as follows:

-A cell will first continue the state it is in, Unless:
-A cell will become ALIVE if the number of neighbors is equal or more than R1 and equal or less that R2.
-A cell will DIE if the number of neighbors is more than R3 or less than R4.

Users can zoom in and out with the UP arrow key and DOWN arrow key.

There are 5 interesting presets to enjoy.

The Game uses multi threading to assist in the smooth life and death animations
of the cells. The cell groping class is designed to chunk the cell updates down
into smaller groups every time the frame rate dips below 60, without ever using
more than 9 threads.