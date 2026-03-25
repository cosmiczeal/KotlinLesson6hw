package com.example.kotlinlesson6hw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kotlinlesson6hw.ui.theme.KotlinLesson6hwTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinLesson6hwTheme() {
                PretzelApp()
            }
        }
    }
}

@Composable
fun PretzelApp() {
    val navController1 = rememberNavController()
    var pretzels by remember { mutableStateOf(0) }
    var multiplier by remember { mutableStateOf(1) }
    var showAlert by remember { mutableStateOf(false) }

    NavHost(
        navController = navController1,
        startDestination = "StartView",
    ) {
        composable(route = "StartView") {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(Color(0xFFFFD180), Color(0xFFFFAB40))
                        )
                    )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text("pretzel popper", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                    Text("the best cookie clicker knockoff", fontSize = 20.sp)

                    Spacer(modifier = Modifier.height(20.dp))

                    Text("ur pretzels: $pretzels", fontSize = 22.sp)

                    Spacer(modifier = Modifier.height(20.dp))

                    // pretzel
                    Button(
                        onClick = { pretzels += multiplier },
                        shape = CircleShape,
                        modifier = Modifier.size(140.dp)
                    ) {
                        Text("🥨", fontSize = 50.sp)
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text("multiplier: x$multiplier")

                    Spacer(modifier = Modifier.height(20.dp))

                    // upgrade
                    Button(
                        onClick = {
                            if (pretzels >= 10) {
                                pretzels -= 10
                                multiplier++
                            } else {
                                showAlert = true // Show alert if not enough pretzels
                            }
                        }
                    ) {
                        Text("upgrade (10 pretzels)")
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    Button(
                        onClick = {
                            navController1.navigate("credits page")
                        }
                    )
                    {
                        Text("credits page")
                    }

                    // ALERT DIALOG
                    if (showAlert) {
                        AlertDialog(
                            onDismissRequest = { showAlert = false },
                            title = { Text("Oops!") },
                            text = { Text("You need at least 10 pretzels to buy this upgrade!") },
                            confirmButton = {
                                Button(onClick = { showAlert = false }) {
                                    Text("OK")
                                }
                            }
                        )
                    }
                }
            }
        }
        composable(route = "credits page") {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF212121))
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text("CREDITS!!!!!", fontSize = 50.sp, color = Color.White, fontWeight = FontWeight.Bold)

                    Spacer(modifier = Modifier.height(20.dp))

                    Text("1. ser yen phua", color = Color.White)
                    Text("2. chloelyn", color = Color.White)
                    Text("3. adhya jain", color = Color.White)

                    Spacer(modifier = Modifier.height(30.dp))

                    Button(
                        onClick = {
                            navController1.navigate("StartView")
                        }
                    )
                    {
                        Text("go back")
                    }
                }
            }
        }
    }
}