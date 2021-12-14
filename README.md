## Authors
Danish, Nicholas, John, Bennett

# Picasso

An application that allows the user to create expressions that
evaluate to colors and then eventually to images.

The given code base is a good start, but it is sparsely documented
(document each method/part after you understand what it's doing) and,
as your application grows, you may need to refactor.

See the specification for Picasso on the course web site

## Project Organization

`src` - the source code for the project

`conf` - the configuration files for the project

`doc` - the Javadocs for the project

The `images` directory contains some sample images generated from Picasso.  Some of the expressions for these images can be found in the `expressions` directory.

## How to use the extensions

##### allow users to use the up and down arrows to move through the history of expressions
Start by adding inputs by typing in the field and hitting the "user input" button. Next, when you want to go up the history of the inputs, press the up key. When you want to go down the history, press the down key. If you go out of bounds (such as hitting up when you are already at the top), it will throw an exception.

##### allow users to refer to history of expressions in this session by entering their history number prefixed by "$" (i.e., "$3")
Start by adding inputs by typing in the field and hitting the "user input" button. Next, when you want to refer to a specific index in the history, type "$<integer>" into the text field and hit the "user input" button. This will set the text field to the appropriate string and also evaluate it. The indexes are not computer indexes, but human indexes. For example, in a history of ["cos(x)", "sin(x)", "x + y"], entering "$1" would return "cos(x)" and entering "$3" would return "x + y."
