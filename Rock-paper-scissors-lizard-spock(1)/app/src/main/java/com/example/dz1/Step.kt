package com.example.dz1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class Step : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step)
        val tvNameComp1 = findViewById<TextView>(R.id.textViewCompName)
        val tvNameComp2 = findViewById<TextView>(R.id.textViewCompName2)
        val tvNamePlayer1 = findViewById<TextView>(R.id.textViewPlayerName)
        val tvNamePlayer2 = findViewById<TextView>(R.id.textViewPlayerName2)
        val tvCountWinPlayer = findViewById<TextView>(R.id.textViewPlayerCountWin)
        val tvCountWinComp = findViewById<TextView>(R.id.textViewCompCountWin)
        val bStone = findViewById<Button>(R.id.buttonStoune)
        val bShears = findViewById<Button>(R.id.buttonShears)
        val bPaper = findViewById<Button>(R.id.buttonPaper)
        val bLizard = findViewById<Button>(R.id.buttonLizard)
        val bSpoke = findViewById<Button>(R.id.buttonSpouke)
        val stepPeople = findViewById<ImageView>(R.id.imageViewPlayer)
        val stepComp = findViewById<ImageView>(R.id.imageViewComp)

        val playerName = intent.getStringExtra("Username").toString()
        var people: Player = Player(playerName)
        tvNamePlayer1.setText(people.name)
        tvNamePlayer2.setText(people.name)

        var computer: Player = Player()
        computer.createName()
        tvNameComp1.setText(computer.name)
        tvNameComp2.setText(computer.name)

        val game: Game = Game(people,computer)

        fun OneStepInGame(nameButton: String){
            PutImage(nameButton, stepPeople)
            game.people.step = nameButton
            game.computerStep()
            PutImage(game.computer.step,stepComp)
            var resultRound:String= game.whoWin()
            Toast.makeText(this, resultRound, Toast.LENGTH_SHORT).show()
            tvCountWinPlayer.setText(game.people.countWin.toString())
            tvCountWinComp.setText(game.computer.countWin.toString())
        }

        bStone.setOnClickListener {
            OneStepInGame("Камень")
        }
        bShears.setOnClickListener {
           OneStepInGame("Ножницы")
        }
        bPaper.setOnClickListener {
            OneStepInGame("Бумага")
        }
        bLizard.setOnClickListener {
            OneStepInGame("Ящерица")
        }
        bSpoke.setOnClickListener {
            OneStepInGame("Спок")
        }


    }
    fun PutImage(nameImage:String, imageView: ImageView){
        if(nameImage =="Камень")
            imageView.setBackgroundResource(R.drawable.stone);
        if(nameImage =="Ножницы")
            imageView.setBackgroundResource(R.drawable.shears);
        if(nameImage =="Бумага")
            imageView.setBackgroundResource(R.drawable.paper);
        if(nameImage =="Ящерица")
            imageView.setBackgroundResource(R.drawable.lizard);
        if(nameImage =="Спок")
            imageView.setBackgroundResource(R.drawable.spoke);
    }

}