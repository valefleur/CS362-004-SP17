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
#define DEBUG 1 //set to 0 to remove debug print statements

int test1(struct gameState *gs){
    string testName = "";
    printf("----- Starting: %s -----", testName);
    
    //set up environment
#if (DEBUG == 1)
    printf("Setting up environment.");
#endif

    //exercise function under test
#if (DEBUG == 1)
    printf("Exercising function under test.");
#endif

    //assert the game state
#if (DEBUG == 1)
    printf("Asserting game state.");
#endif

    //report useful information
#if (DEBUG == 1)
    printf("Reporting information.");
#endif

    printf("----- Finished: %s -----", testName);
    return 0;
}


int main(){
    printf("---------- START Testing: %s ----------", UNDERTEST);
    //test1()
    printf("---------- COMPLETED Testing: %s ----------", UNDERTEST);
    
    return 0;
}