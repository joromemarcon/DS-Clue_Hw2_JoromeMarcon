package edu.miracosta.cs113;

/**
 * RandomClue.java : Your job is to ask your AssistantJack and get the correct
 * answer in <= 20 tries.  RandomClue is ONE solution to the problem,
 * where a set of random numbers is generated every attempt until all three
 * random numbers match the solution from the AssistantJack object.
 *
 * This is a sample solution, a driver using random number implementation.
 * You can use this file as a guide to create your own SEPARATE driver for
 * your implementation that can solve it in <= 20 times consistently.
 *
 * @author Nery Chapeton-Lamas (material from Kevin Lewis)
 * @version 1.0
 *
 */

import java.util.Random;
import java.util.Scanner;
import model.Theory;
import model.AssistantJack;

public class RandomClue {

    /*
     * ALGORITHM:
     *
     * PROMPT "Which theory to test? (1, 2, 3[random]): "
     * READ answerSet
     * INSTANTIATE jack = new AssistantJack(answerSet)
     * DO
     *      weapon = random int between 1 and 6
     *      location = random int between 1 and 10
     *      murder = random int between 1 and 6
     *      solution = jack.checkAnswer(weapon, location, murder)
     * WHILE solution != 0
     *
     * OUTPUT "Total checks = " + jack.getTimesAsked()
     * IF jack.getTimesAsked() is greater than 20 THEN
     *      OUTPUT "FAILED"
     * ELSE
     *      OUTPUT "PASSED"
     * END IF
     */

    /**
     * Driver method for random guessing approach
     *
     * @param args not used for driver
     */
    public static void main(String[] args) {
        // DECLARATION + INITIALIZATION
        int answerSet, solution, murder, weapon, location;
        int countTimesAsked = 1;
        Theory answer;
        AssistantJack jack;
        Scanner keyboard = new Scanner(System.in);
        Random random = new Random();

        // INPUT
        System.out.print("Which theory would like you like to test? (1, 2, 3[random]): ");
        answerSet = keyboard.nextInt(); //ex enter 1 so AnswerSet(1,1,1)
        keyboard.close();

        // PROCESSING
        jack = new AssistantJack(answerSet); //AnswerSet is (1,1,1)

        do {
            weapon = random.nextInt(6) + 1;
            location = random.nextInt(10) + 1;
            murder = random.nextInt(6) + 1;

            int i = 1;
            int j = 10;
            int k = 1;

            /* @return 0 if all three are correct, 1 if the weapon is incorrect, 2 if
             * the location is incorrect and 3 if the person is incorrect. If
             * multiple are incorrect it will randomly select one of the
             * incorrect parts and return that.
             */

            solution = jack.checkAnswer(weapon, location, murder);
            //Added Code
            System.out.println(countTimesAsked + " " + solution + " " + weapon + " " + location + " " + murder);

            while(solution!=0 || countTimesAsked != 21) {
                if(solution == 1 && weapon == 1) i = 2;
                if(solution == 2 && location == 10) j = 9;
                if(solution == 3 && murder == 1) k = 2;

                if (solution == 1) {
                        solution = jack.checkAnswer(i, location, murder);
                        weapon = i;
                        countTimesAsked++;
                        i++;
                        if(weapon == i) i++;
                        System.out.println(countTimesAsked + " " + solution + " " + weapon + " " + location + " " + murder);

                }
                else if (solution == 2) {
                        solution = jack.checkAnswer(weapon, j, murder);
                        location = j;
                        countTimesAsked++;
                        j--;
                        if(location == j) j--;
                        System.out.println(countTimesAsked + " " + solution + " " + weapon + " " + location + " " + murder);

                }
                else if (solution == 3) {
                        solution = jack.checkAnswer(weapon, location, k);
                        murder = k;
                        countTimesAsked++;
                        k++;
                        if(murder == k) k++;
                        System.out.println(countTimesAsked + " " + solution + " " + weapon + " " + location + " " + murder);

                }
                else break;
            }

        }while (solution != 0);

            answer = new Theory(weapon, location, murder);

            // OUTPUT
            System.out.println("Total Checks = " + jack.getTimesAsked() + ", Solution " + answer);

            if (jack.getTimesAsked() > 20) {
                System.out.println("FAILED!! You're a horrible Detective...");
            } else {
                System.out.println("WOW! You might as well be called Batman!");
            }
        }
    }

