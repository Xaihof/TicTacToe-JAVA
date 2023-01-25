package com.example.xicxacxoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bttn0, bttn1, bttn2, bttn3, bttn4, bttn5, bttn6, bttn7, bttn8, bttnretry;
    TextView display_data, x_name, o_name;
    String p_o_name = "O", p_x_name = "X";


    int player_o = 0, player_x = 1, activeplayer = player_o;

    int[] touched_position = {-1, -1, -1, -1, -1, -1, -1, -1, -1};

    boolean isGameactive = true;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Alert DialogBox.
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Confirmation")
                .setMessage("Do you Want to Enter your name?")
                .setIcon(R.drawable.icon_question_answer)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //Dialog box.
                        Dialog dialog = new Dialog(MainActivity.this);
                        dialog.setContentView(R.layout.dialog_items);

                        EditText player_o_name = dialog.findViewById(R.id.player_o_name);
                        EditText player_x_name = dialog.findViewById(R.id.player_x_name);
                        Button button_play = dialog.findViewById(R.id.button_play);

                        button_play.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                YoYo.with(Techniques.Bounce)
                                        .duration(70)
                                        .repeat(3)
                                        .playOn(button_play);

                                p_o_name = player_o_name.getText().toString();
                                x_name.setText(p_o_name);
                                p_x_name = player_x_name.getText().toString();
                                o_name.setText(p_x_name);
                            dialog.dismiss();
                            }
                        });
                        dialog.show();
                    }
                }).setNegativeButton("No.", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        alert.show();
        //yoyo animations.
        YoYo.with(Techniques.FadeInLeft)
                .duration(70)
                .repeat(1)
                .playOn(findViewById(R.id.display_data));

        //Display turn.
        display_data = findViewById(R.id.display_data);

        x_name = findViewById(R.id.xname);
        o_name = findViewById(R.id.oname);

        //Hooks.
        bttn0 = findViewById(R.id.btn0);
        bttn1 = findViewById(R.id.btn1);
        bttn2 = findViewById(R.id.btn2);
        bttn3 = findViewById(R.id.btn3);
        bttn4 = findViewById(R.id.btn4);
        bttn5 = findViewById(R.id.btn5);
        bttn6 = findViewById(R.id.btn6);
        bttn7 = findViewById(R.id.btn7);
        bttn8 = findViewById(R.id.btn8);

        bttnretry = findViewById(R.id.btnretry);

        //OnClickListners.
        bttn0.setOnClickListener(this);
        bttn1.setOnClickListener(this);
        bttn2.setOnClickListener(this);
        bttn3.setOnClickListener(this);
        bttn4.setOnClickListener(this);
        bttn5.setOnClickListener(this);
        bttn6.setOnClickListener(this);
        bttn7.setOnClickListener(this);
        bttn8.setOnClickListener(this);
    }

    //OnClickMethod.
    @Override
    public void onClick(View view) {

        if (!isGameactive)
            return;

        //Logic for buttons
        Button clickedbtn = findViewById(view.getId());
        int clickedTag = Integer.parseInt(view.getTag().toString());

        //logic for solving the changing of button text for the 2nd time.
        if (touched_position[clickedTag] != -1) {
            return;
        }
        touched_position[clickedTag] = activeplayer;

        if (activeplayer == player_o) {
            clickedbtn.setText("o");
            YoYo.with(Techniques.Bounce)
                    .duration(70)
                    .repeat(3)
                    .playOn(clickedbtn);
            activeplayer = player_x;
            display_data.setText("Turn for " + p_x_name);

        } else {
            clickedbtn.setText("x");
            YoYo.with(Techniques.Bounce)
                    .duration(70)
                    .repeat(3)
                    .playOn(clickedbtn);
            activeplayer = player_o;
            display_data.setText("Turn for " + p_o_name);
        }

        checkforwin();
    }

    //Function Check For Winner.
    private void checkforwin() {
        //To check and show the winner.
        int[][] winning_position = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

        for (int i = 0; i < 8; i++) {
            int value0 = winning_position[i][0];
            int value1 = winning_position[i][1];
            int value2 = winning_position[i][2];

            int xs = 1, os = 1;

            if (touched_position[value0] == touched_position[value1] && touched_position[value1] == touched_position[value2]) {
                if (touched_position[value0] != -1) {
                    //winner declared
                    isGameactive = false;
                    if (touched_position[value0] == player_o) {
                        YoYo.with(Techniques.Wave)
                                .duration(70)
                                .repeat(3)
                                .playOn(display_data);
                        display_data.setText("Congratulations!");
                        showDialogue(p_o_name + " won the Game!");
                    } else {
                        YoYo.with(Techniques.Wave)
                                .duration(70)
                                .repeat(3)
                                .playOn(display_data);
                        display_data.setText("Congratulations!");
                        showDialogue(p_x_name + " won the Game!");
                    }
                }
            }
        }
        bttnretry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YoYo.with(Techniques.Bounce)
                        .duration(70)
                        .repeat(3)
                        .playOn(bttnretry);
                restartGame();
            }
        });
    }

    //Function dialoguebox.
    private void showDialogue(String winnerText) {
        new AlertDialog.Builder(this)
                .setTitle(winnerText)
                .setIcon(R.drawable.icon_replay)
                .setPositiveButton("Replay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        restartGame();
                    }
                }).show();
    }

    private void restartGame() {
        activeplayer = player_o;
        display_data.setText("Turn for " + p_o_name);
        touched_position = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};
        bttn0.setText("");
        bttn1.setText("");
        bttn2.setText("");
        bttn3.setText("");
        bttn4.setText("");
        bttn5.setText("");
        bttn6.setText("");
        bttn7.setText("");
        bttn8.setText("");
        isGameactive = true;

    }
}