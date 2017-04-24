/*
 * edwardse
 * CS 362 SW Eng II
 * Spring 2017
 *
 * Assignment 3
 * unittest1.c
 * int fullDeckCount(int player, int card, struct gameState *state)
 */
 
#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include "rngs.h"
#include <stdlib.h>

#define UNDERTEST "fullDeckCount()"

int test1(){
    string testName = "";
    printf("----- Starting: %s -----", testName);
    printf("----- Finished: %s -----", testName);
    
    #set up environment
    #exercise function under test
    #assert the game state
    #report useful information
}
int test2(){}

int main(){
    printf("---------- START Testing: %s ----------", UNDERTEST);
    #test1()
    #test2()
    printf("---------- COMPLETED Testing: %s ----------", UNDERTEST);
}