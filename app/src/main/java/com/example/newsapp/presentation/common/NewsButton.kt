package com.example.newsapp.presentation.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.newsapp.ui.theme.WhiteGray

@Composable
fun NewsButton(
    text: String,        // Текст, который будет отображаться на кнопке
    onClick: () -> Unit, // Функция, которая будет вызываться при нажатии на кнопку
) {

    Button(
        onClick = onClick, // Устанавливаем функцию обратного вызова для обработки нажатия на кнопку
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary, // Цвет фона кнопки, используя цвет из Material Design
            contentColor = Color.White // Цвет текста на кнопке
        ),
        shape = RoundedCornerShape(size = 6.dp) // Задаем закругленные углы для кнопки
    ) {
        Text(
            text = text, // Устанавливаем текст кнопки
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold), // Устанавливаем стиль текста, включая жирность
        )
    }
}


@Composable
fun NewsTextButton(
    text: String,        // Текст, который будет отображаться на кнопке
    onClick: () -> Unit, // Функция, которая будет вызываться при нажатии на кнопку
) {
    TextButton(onClick = onClick) { // Создаем TextButton с функцией обратного вызова для обработки нажатия
        Text(
            text = text, // Устанавливаем текст кнопки
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold), // Устанавливаем стиль текста, включая жирность
            color = WhiteGray // Устанавливаем цвет текста на кнопке (WhiteGray - это, вероятно, пользовательская переменная)
        )
    }
}







