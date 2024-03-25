package com.senac.restaurante

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorApp()
        }
    }
}

@Composable
fun TipCalculatorApp() {
    var totalBill by remember { mutableStateOf("") }
    var customTipPercentage by remember { mutableStateOf(18f) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Calculadora de gorjetas", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = totalBill,
                onValueChange = {
                    totalBill = it
                },
                label = { Text("Valor Ganho") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text("Custom %: ${customTipPercentage.toInt()}%")
            Slider(
                value = customTipPercentage,
                onValueChange = { customTipPercentage = it },
                valueRange = 0f..30f
            )
            Spacer(modifier = Modifier.height(16.dp))

            val billAmount = totalBill.toDoubleOrNull() ?: 0.0
            val standardTip = billAmount * 0.15
            val customTip = billAmount * (customTipPercentage / 100)
            Text("Gorjeta 15%: $${"%.2f".format(standardTip)}")
            Text("Gorjeta Personalizada: $${"%.2f".format(customTip)}")
            Text("Total 15%: $${"%.2f".format(billAmount + standardTip)}")
            Text("Total Personalizado: $${"%.2f".format(billAmount + customTip)}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TipCalculatorApp()
}