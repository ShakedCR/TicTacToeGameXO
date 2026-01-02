package com.example.tictactoeapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvStatus: TextView
    private lateinit var btnPlayAgain: Button
    private lateinit var cells: List<Button>

    private var currentPlayer = 'X'
    private var gameOver = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvStatus = findViewById(R.id.tvStatus)
        btnPlayAgain = findViewById(R.id.btnPlayAgain)

        cells = listOf(
            findViewById(R.id.btn0),
            findViewById(R.id.btn1),
            findViewById(R.id.btn2),
            findViewById(R.id.btn3),
            findViewById(R.id.btn4),
            findViewById(R.id.btn5),
            findViewById(R.id.btn6),
            findViewById(R.id.btn7),
            findViewById(R.id.btn8),
        )

        cells.forEachIndexed { index, button ->
            button.setOnClickListener { onCellClicked(index) }
        }

        btnPlayAgain.setOnClickListener { resetBoardOnly() }

        updateStatus()
    }

    private fun onCellClicked(index: Int) {
        if (gameOver) return

        val btn = cells[index]
        if (btn.text.isNotEmpty()) return

        btn.text = currentPlayer.toString()
        btn.isEnabled = false


        currentPlayer = if (currentPlayer == 'X') 'O' else 'X'
        updateStatus()
    }

    private fun updateStatus() {
        tvStatus.text = "$currentPlayer turn"
    }


    private fun resetBoardOnly() {
        currentPlayer = 'X'
        gameOver = false
        cells.forEach {
            it.text = ""
            it.isEnabled = true
        }
        btnPlayAgain.visibility = android.view.View.GONE
        updateStatus()
    }
}
