/*
Miya 
Last edit: 25/3/2024
*/
package Entity.Utility;

import Entity.Game.*;
import Entity.Data.*; 
import java.util.*;
import java.io.*;


/*
To do:
Make methods to instantiate hands that fulfil the Jester role and King role
If got time, do for Other hands just to verify that the hand Combination class is working properly. 

*/

public class TestCases {

  private Role testRole; 
  private Hand testHand; 
  private River testRiver;
  private Flop testFlop;
  private String testDetails;
  


  public JesterSpeed(boolean Win) {
    

    this.testHand = new Hand(testHand, testRiver);
  /* incomplete*/
  
  String winningHand = Win ? "a winning " : "a losing ";
  this.testDetail = "Testing Jester role with " + winningHand;
  System.out.println("Running Test 1");
  System.out.priintln("" + testDetail);
  if (Win) { 
    System.out.println("Expected: true");
    
  } else {
    System.out.println("Expected: false");
  }

  if () {
    
  

  }




















}
