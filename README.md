# Conways 3D Game Of Life

This is a model ("game") based on the mathematical model constructed by John Horton Conway. In this model exists a 3 dimensional space filled with voxels containing two states, "dead" or "alive", denoted as a "cell". The model then runs (t) amount of time-steps. For every time step, all voxel's will determine their state at time (t+1) as a function of the states of the voxels around them in radus (Rx) at time (t), denoted as "rules". In this 3D Implementation, The user is able to set the rules of the game.


### The rules of the game are bound such that:

At time step (t) a cell will not change state unless:
- If the number of neighbors is >= R1 and <= R2 (ALIVE).
- If the number of neighbors is >= R3 or  <=R4 (DEAD).

### Game Options:
- There are interesting presets to enjoy.
- The Camera is initialized dynamically, placing the initial distance from the camera to the center of the grid relative to the size of the grid.

### Controls:

Zoom In/Out:
```UP arrow key and DOWN arrow key```
