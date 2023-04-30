# Style used in this program

## Variables, attributes and parameters
The names of variables, attributes, or method parameters must be lowercase, except
for the first letters of the words that compose it (except the first letter of the
first word which must be lowercase)

In addition, the name of a variable, an attribute or a parameter must be significant, that is to say, it must represent the content of this variable, attribute or parameter.

## Constants

Constant names must be in uppercase, with each word separated by an underscore `_`.
In addition, the name of a constant must be meaningful, that is, it must
represent the content the constant.

## Methods
Method names must be lowercase, except for the first letters of words
which compose it (except the first letter of the first word which must be in lower case).
Also, the name of a method must be meaningful, i.e. that it must represent what the
method done


In the case of accessors (getters / setters) of instance attributes of a class, the
method name should start with the word get/set followed by the name of the attribute to which
it provides access.

## Classes
Class names must be lowercase, except for the first letter of each
word that composes it. Unlike the names of variables or methods, the name of
classes always starts with a capital letter.
Also, the name of a class must be meaningful, i.e. that it must represent what the
model class, or its usefulness

### Declaration of variables

Variables should preferably be declared line by line (one variable per
line).

Variables must be declared at the very beginning of the method in which they are
exist (immediately after the opening brace), not anywhere in the code
of this method.

### Blocks and indentation

The beginning of a block is specified by an opening brace and the end of the block is specified by
a closing brace: `{ … }`.
The opening brace must be at the very end of the first line of the block, preceded
a space, immediately after the statement, method or class creating the block.
The closing brace must be placed at the beginning of a blank line, just after the last
block statement.
When you enter a “child” block (block inside another block), you must add a
indentation level. Two blocks of the same level (“sibling” blocks) must
start and end at the same level of indentation.

### Line size

Lines of code should not be too long. We recommend a maximum of 80
characters per line (including indentation). It may then be necessary to cut
certain lines to continue on the next one.
You can break a line:

* after a comma

* before an operator

   In a control structure, when we cut the expression, we must increase
   the indentation of at least two levels (and not only one) on the following lines.
