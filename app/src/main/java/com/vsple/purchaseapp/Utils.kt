package com.vsple.purchaseapp

import android.app.Activity
import android.graphics.LinearGradient
import android.graphics.Shader
import android.widget.TextView
import androidx.core.content.ContextCompat

object Utils {

    fun textViewGradient(textView: TextView, activity: Activity)
    {
        val colorStart = ContextCompat.getColor(activity, R.color.color_gradient_one)
        val colorCenter = ContextCompat.getColor(activity, R.color.color_gradient_two)
        val colorEnd = ContextCompat.getColor(activity, R.color.color_gradient_three)

        // Create a LinearGradient for the text color
        val shader = LinearGradient(
            0f, 0f, 0f, textView.textSize,
            intArrayOf(colorStart, colorCenter, colorEnd), // Array of colors
            floatArrayOf(0.0f, 0.5f, 1.0f), // Positions for the colors
            Shader.TileMode.CLAMP
        )

        // Apply the gradient shader to the TextView's text color
        textView.paint.shader = shader
    }
    fun textViewWhite(textView: TextView, activity: Activity)
    {
        val colorStart = ContextCompat.getColor(activity, R.color.white)
        val colorCenter = ContextCompat.getColor(activity, R.color.white)
        val colorEnd = ContextCompat.getColor(activity, R.color.white)

        // Create a LinearGradient for the text color
        val shader = LinearGradient(
            0f, 0f, 0f, textView.textSize,
            intArrayOf(colorStart, colorCenter, colorEnd), // Array of colors
            floatArrayOf(0.0f, 0.5f, 1.0f), // Positions for the colors
            Shader.TileMode.CLAMP
        )

        // Apply the gradient shader to the TextView's text color
        textView.paint.shader = shader
    }

    fun textViewGray(textView: TextView, activity: Activity)
    {
        val colorStart = ContextCompat.getColor(activity, R.color.color_text_dark_gray)
        val colorCenter = ContextCompat.getColor(activity, R.color.color_text_dark_gray)
        val colorEnd = ContextCompat.getColor(activity, R.color.color_text_dark_gray)

        // Create a LinearGradient for the text color
        val shader = LinearGradient(
            0f, 0f, 0f, textView.textSize,
            intArrayOf(colorStart, colorCenter, colorEnd), // Array of colors
            floatArrayOf(0.0f, 0.5f, 1.0f), // Positions for the colors
            Shader.TileMode.CLAMP
        )

        // Apply the gradient shader to the TextView's text color
        textView.paint.shader = shader
    }


}