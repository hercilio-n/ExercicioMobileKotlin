package com.senac.celsiusconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TemperatureConverterApp()
        }
    }
}

@Composable
fun TemperatureConverterApp() {
    var temperatureInCelsius by remember { mutableStateOf("") }
    var temperatureInFahrenheit by remember { mutableStateOf("") }
    var showResult by remember { mutableStateOf(false) }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = temperatureInCelsius,
                onValueChange = { value ->
                    temperatureInCelsius = value
                },
                label = { Text("Celsius") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = temperatureInFahrenheit,
                onValueChange = { value ->
                    temperatureInFahrenheit = value
                },
                label = { Text("Fahrenheit") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (temperatureInCelsius.isNotEmpty()) {
                        temperatureInFahrenheit = ((temperatureInCelsius.toDoubleOrNull() ?: 0.0) * 1.8 + 32).toString()
                    } else if (temperatureInFahrenheit.isNotEmpty()) {
                        temperatureInCelsius = (((temperatureInFahrenheit.toDoubleOrNull() ?: 0.0) - 32) / 1.8).toString()
                    }
                    showResult = true
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Converter")
            }

            if (showResult) {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Celsius Convertido: $temperatureInCelsius")
                Text("Fahrenheit Convertido: $temperatureInFahrenheit")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TemperatureConverterApp()
}