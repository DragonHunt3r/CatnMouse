# CatnMouse
Cat and Mouse assignment solution

Made on request by retrooper#8346.

## Assignment Description
## Setup
Consider the following simulation: A cat is chasing a mouse. When the cat catches the mouse, the simulation ends. The cat and mouse are running around a circular track consisting of `n` squares. The cat moves `c` squares each turn and the mouse moves `m` squares each turn. They both start at the beginning of the track. If they end up on the same square at the end of a turn, then the cat catches the mouse and the simulation ends. If not, they continue to go around and around the track. The cat and mouse may be in the same square during a turn - that's okay. It's only at the end of the turn that the cat may catch the mouse.

## Rocks
To make things a little more complicated, there are rocks on some of the squares of the track. Each time the cat or the mouse goes to a square with a rock on it, the number of squares that the cat or mouse can move that turn is decremented by `1` in addition to the move itself. That is, if the cat or mouse had 2 squares left in a move and it moves to a square with a rock on it, then its count goes to 0 and it stops on that square. If the cat or mouse had one move left and it moves to a square with a rock on it, it also stops on that square. If it had more then 2 squares left in a move, moving to a square with a rock on it decrements the counter by 2, instead of the usual 1 for a move.

### Input
The input to the program with contain a line with three integers on it: `n`, the number of squares of the track, `c`, the number of squares the cat moves in a turn, and `m`, the number of squares that the mouse moves in a turn. These numbers are separated by whitespace. The second line contains first the number of rocks (an integer), following by the list of squares containing the rocks. The squares are numbered on the track, starting at 1, up to n.

### Output
The output of the program should be a diagram of the track at the beginning of the simulation and after each turn. Print out an `x` if a square contains only a rock, `c` is a square contains the cat but not the mouse (and may or may not have a rock), an `m` if a square contains the mouse but not the cat (and may or may not contain a rock.) Print out an `*` if the space contains both the cat and the mouse (whether or not it has a rock) and a ` ` (space) if the square is empty. At the end of the simulation, print out the number of turns it took the cat to catch the mouse.
