/*
 * edwardse
 * CS 362 SW Eng II
 * Spring 2017
 *
 * Quiz 2 Random Testing
 * randomstring.c
 * testme.c
 */
 
/*
Description: Implement a random tester for the function testme() 
in testme.c that is capable of printing the error message.  You 
should implement inputChar() and inputString() to produce random 
values. Write up the development of your random tester and how it 
finds the error message as randomstring.c. Submit testme.c and 
randomstring.c under your onid directory in class github. (under 
your onid directory and not under dominion directory). The 
randomstring.c is a text file that outlines how you developed your 
solution and how it works!
*/

inputChar() was written to return a single random char.  Only ASCII
chars between x20 (d32) and x7E (d126) are allowed, which gives it
a possible ASCII range of ' ' through ~, which includes all alphanumeric
characters and all the chars that are looked for in the function.

inputString() was somewhat different.  Because inputString() has to
return multiple chars in a very specific order, the chances of passing
it six random chars and each being exactly what is needed to reset 
testme() is significantly lower than the chances of passing a single 
matching char.  For this reason, the function was written to return the
"reset\0" string *or* a different series of chars based on "the flip of a 
coin" or a random one out of two chance.

Since iterating through states 1 through 9 depends only upon the return
value from inputChar(), we don't care about the value of inputString() 
until state 9 is reached.  Calls to inputChar will keep being made until
the appropriate value for the state is returned.  Only then will testme.c
move onto the next state.  Once state 9 is reached, testme.c only cares 
about the return value from inputString().  If inputString() returns 
"reset\0", the error condition has been reached and testme.c will exit.

It would truely be a more random tester if both functions returned 
completely random chars, but that wouldn't necessarily make it a more 
valuable test.  (If we were stress testing hardware or gateware, it might
be useful, although there are probably better random testing approaches
than testme.c for those scenarios.)  It could also be improved by widening
the range of possible ASCII characters to include some of the non-visible
ones to see if they broke the code, however, our goal for this quiz was to
write something "capable of printing the error message", not find areas 
where the code could be improved.  