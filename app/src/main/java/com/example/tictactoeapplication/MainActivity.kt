package com.example.tictactoeapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var buttons: Array<Button>
    private lateinit var textViewStatus: TextView
    private lateinit var buttonReset: Button

    private var playerXTurn = true
    private var board = Array(9) { "" }
    private var gameEnded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttons = Array(9) { i ->
            findViewById(resources.getIdentifier("button$i", "id", packageName))
        }

        textViewStatus = findViewById(R.id.textViewStatus)
        buttonReset = findViewById(R.id.buttonReset)

        for (i in buttons.indices) {
            buttons[i].setOnClickListener { onButtonClick(i) }
        }

        buttonReset.setOnClickListener {
            resetGame()
        }
    }

    private fun onButtonClick(index: Int) {
        if (board[index].isNotEmpty() || gameEnded) return

        board[index] = if (playerXTurn) "X" else "O"
        buttons[index].text = board[index]

        if (checkWin()) {
            textViewStatus.text = "Player ${board[index]} wins!"
            gameEnded = true
            buttonReset.visibility = View.VISIBLE
        } else if (board.all { it.isNotEmpty() }) {
            textViewStatus.text = "It's a tie!"
            gameEnded = true
            buttonReset.visibility = View.VISIBLE
        } else {
            playerXTurn = !playerXTurn
            textViewStatus.text = "Player ${if (playerXTurn) "X" else "O"}'s turn"
        }
    }

    private fun checkWin(): Boolean {
        val lines = arrayOf(
            intArrayOf(0,1,2), intArrayOf(3,4,5), intArrayOf(6,7,8), // שורות
            intArrayOf(0,3,6), intArrayOf(1,4,7), intArrayOf(2,5,8), // עמודות
            intArrayOf(0,4,8), intArrayOf(2,4,6)                      // אלכסונים
        )
        for (line in lines) {
            val (a, b, c) = line
            if (board[a] == board[b] && board[b] == board[c] && board[a].isNotEmpty()) {
                return true
            }
        }
        return false
    }

    private fun resetGame() {
        for (i in board.indices) {
            board[i] = ""
            buttons[i].text = ""
        }
        playerXTurn = true
        gameEnded = false
        textViewStatus.text = "Player X's turn"
        buttonReset.visibility = View.GONE
    }
}
