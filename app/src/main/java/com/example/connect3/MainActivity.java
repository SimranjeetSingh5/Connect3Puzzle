package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int[] gameState = {-1, -1, -1, -1, -1, -1, -1, -1, -1};

    //3 rows and 3 colomns and 2 diagonals are winning positions

    int[][] winingPositions = {{0, 1, 2}, {3, 4, 5}, {6 ,7, 8},{0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    //0 for yellow //1 for red
    int activePlayer = 0;

    boolean gameActive = true;

    public void dropIn(View view){

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == -1 && gameActive){

            //setting array element as 0 for yellow and 1 for red in the their respective indexes
            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            //alternatively placing yellow and red in grid
            if(activePlayer == 0) {
                //placing yellow in grid
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            }
            else{
                //placing red in grid
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
        }

        counter.animate().translationYBy(1500).rotation(300).setDuration(300);

        for(int[] iteratingWiningPosition : winingPositions){

            if(gameState[iteratingWiningPosition[0]] == gameState[iteratingWiningPosition[1]] && gameState[iteratingWiningPosition[1]] == gameState[iteratingWiningPosition[2]] && gameState[iteratingWiningPosition[0]] != -1){

                //A Player Won
                //Switching game state to false : no further taps allowed
                gameActive = false;

                String winner ="";
                if(activePlayer == 1){
                    winner = "Yellow";
                }
                else{
                    winner = "Red";
                }

//                Toast.makeText(this, winner + " has won!", Toast.LENGTH_SHORT).show();

                Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                TextView winnerTextView = (TextView) findViewById(R.id.textView);

                winnerTextView.setText(winner + " has won");

                playAgainButton.setVisibility(View.VISIBLE);

                winnerTextView.setVisibility(View.VISIBLE);
            }
        }
        }

    }

    public void playAgain(View view){

        for(int i = 0; i < gameState.length; i++) {
            gameState[i] = -1;
        }
        activePlayer = 0;

        gameActive = true;

        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.textView);

        playAgainButton.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout =(GridLayout) findViewById(R.id.gridLayout);

        for(int i = 0; i < gridLayout.getChildCount(); i++){

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}