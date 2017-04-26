/*
 * edwardse
 * CS 362 SW Eng II
 * Spring 2017
 *
 * Assignment 3
 * unittest1.c
 * int playSmithy()
 */
 
#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include "rngs.h"
#include <stdlib.h>

#define UNDERTEST "playSmithy()"
#define DEBUGGER 1 //set to 0 to remove debug print statements

int main(){
    printf("\n\n---------- START Testing: %s ----------\n", UNDERTEST);
    int numPlayers = 2;
    int kingdom[10] = {adventurer, embargo, village, minion, mine, cutpurse,
			sea_hag, tribute, smithy, council_room};
    struct gameState gs;
    int seed = rand() % 100 + 1;
    
    initializeGame(numPlayers, kingdom, seed, &gs);
    
    int handPos = 1;
    int currentPlayer = 1;

    /*Gherkin:
    Given: The game has started
           And it's the current player's turn
    When: The Smithy card is played
    Then: The current player will remove three cards from her deck
          And place them in her hand
*/
    char testName[43] = "Player Puts Three Cards from Deck into Hand";
    printf("----- Starting: %s -----\n", testName);
    
    //set up environment
#if (DEBUGGER == 1)
    printf("Setting up environment.\n");
#endif
    struct gameState testGS;
    int discarded = 1; /*Discard Smithy once played*/
    int drawnCards = 3; /*Specific to Smithy*/
    int shuffledCards = 0;
    
    memcpy(&testGS, &gs, sizeof(struct gameState));
    
    //exercise function under test
#if (DEBUGGER == 1)
    printf("Exercising function under test.\n");
#endif
    playSmithy(&testGS, handPos);
    //assert the game state
#if (DEBUGGER == 1)
    printf("Asserting game state.\n");
#endif
    printf("hand count = %d, expected = %d\n", testGS.handCount[currentPlayer], gs.handCount[currentPlayer] + drawnCards - discarded);
    printf("deck count = %d, expected = %d\n", testGS.deckCount[currentPlayer], gs.deckCount[currentPlayer] - drawnCards + shuffledCards);
    assert(testGS.handCount[currentPlayer] == gs.handCount[currentPlayer] + drawnCards - discarded);
    assert(testGS.deckCount[currentPlayer] == gs.deckCount[currentPlayer] - drawnCards + shuffledCards);
    //TODO utilize modified assert function
    
    //report useful information
#if (DEBUGGER == 1)
    printf("Reporting information.\n");
#endif

    printf("----- Finished: %s -----\n\n", testName);
    
    printf("---------- COMPLETED Testing: %s ----------\n\n\n", UNDERTEST);
    
    return 0;
}
///////struct gameState *gs,
int test_1( int currentPlayer, int handPos){

    return 0;
}
