package com.example.gramasuvidha.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gramasuvidha.LanguageState
import com.example.gramasuvidha.ui.theme.NavyBlue
import com.example.gramasuvidha.ui.theme.DarkBlue

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    var selectedTab by remember { mutableStateOf(0) }
    var phone by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showOtp by remember { mutableStateOf(false) }
    var otp by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        NavyBlue,
                        Color(0xFF1A56B0),
                        Color(0xFF2196F3)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(60.dp))

            // Logo
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Text("🏘", fontSize = 50.sp)
            }

            Spacer(Modifier.height(16.dp))

            Text(
                "Grama Suvidha",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                if (LanguageState.isKannada) "ಗ್ರಾಮ ಡಿಜಿಟಲ್ ಪೋರ್ಟಲ್" else "Village Digital Portal",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp
            )

            Spacer(Modifier.height(40.dp))

            // Login Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {

                    Text(
                        if (LanguageState.isKannada) "ಲಾಗಿನ್ ಮಾಡಿ" else "Login",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = NavyBlue
                    )

                    Spacer(Modifier.height(20.dp))

                    // Tab Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFF0F4FF))
                            .padding(4.dp)
                    ) {
                        listOf(
                            if (LanguageState.isKannada) "📱 OTP" else "📱 OTP",
                            if (LanguageState.isKannada) "🔑 ಪಾಸ್ವರ್ಡ್" else "🔑 Password",
                            "G Google"
                        ).forEachIndexed { index, label ->
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(
                                        if (selectedTab == index) NavyBlue
                                        else Color.Transparent
                                    )
                                    .clickable { selectedTab = index }
                                    .padding(vertical = 8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    label,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = if (selectedTab == index) Color.White
                                    else Color.Gray
                                )
                            }
                        }
                    }

                    Spacer(Modifier.height(20.dp))

                    when (selectedTab) {
                        // OTP Login
                        0 -> {
                            if (!showOtp) {
                                OutlinedTextField(
                                    value = phone,
                                    onValueChange = { if (it.length <= 10) phone = it },
                                    label = {
                                        Text(if (LanguageState.isKannada)
                                            "ಮೊಬೈಲ್ ನಂಬರ್" else "Mobile Number")
                                    },
                                    leadingIcon = {
                                        Text("+91 ", color = NavyBlue,
                                            fontWeight = FontWeight.Bold)
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(12.dp),
                                    singleLine = true
                                )
                                Spacer(Modifier.height(16.dp))
                                Button(
                                    onClick = { if (phone.length == 10) showOtp = true },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(52.dp),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = NavyBlue)
                                ) {
                                    Text(
                                        if (LanguageState.isKannada)
                                            "OTP ಕಳುಹಿಸಿ" else "Send OTP",
                                        fontSize = 16.sp, fontWeight = FontWeight.Bold
                                    )
                                }
                            } else {
                                Text(
                                    if (LanguageState.isKannada)
                                        "+91 $phone ಗೆ OTP ಕಳುಹಿಸಲಾಗಿದೆ"
                                    else "OTP sent to +91 $phone",
                                    color = Color.Gray, fontSize = 13.sp
                                )
                                Spacer(Modifier.height(12.dp))

                                // OTP boxes
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    OutlinedTextField(
                                        value = otp,
                                        onValueChange = { if (it.length <= 6) otp = it },
                                        label = { Text("Enter OTP") },
                                        modifier = Modifier.fillMaxWidth(),
                                        shape = RoundedCornerShape(12.dp),
                                        singleLine = true
                                    )
                                }
                                Spacer(Modifier.height(16.dp))
                                Button(
                                    onClick = { onLoginSuccess() },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(52.dp),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = NavyBlue)
                                ) {
                                    Text(
                                        if (LanguageState.isKannada)
                                            "ಪರಿಶೀಲಿಸಿ" else "Verify OTP",
                                        fontSize = 16.sp, fontWeight = FontWeight.Bold
                                    )
                                }
                                Spacer(Modifier.height(8.dp))
                                TextButton(
                                    onClick = { showOtp = false; otp = "" },
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        if (LanguageState.isKannada)
                                            "ನಂಬರ್ ಬದಲಾಯಿಸಿ" else "Change Number",
                                        color = NavyBlue
                                    )
                                }
                            }
                        }

                        // Password Login
                        1 -> {
                            OutlinedTextField(
                                value = username,
                                onValueChange = { username = it },
                                label = {
                                    Text(if (LanguageState.isKannada)
                                        "ಬಳಕೆದಾರ ಹೆಸರು" else "Username")
                                },
                                leadingIcon = {
                                    Icon(Icons.Default.Person, null, tint = NavyBlue)
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                singleLine = true
                            )
                            Spacer(Modifier.height(12.dp))
                            OutlinedTextField(
                                value = password,
                                onValueChange = { password = it },
                                label = {
                                    Text(if (LanguageState.isKannada)
                                        "ಪಾಸ್ವರ್ಡ್" else "Password")
                                },
                                leadingIcon = {
                                    Icon(Icons.Default.Lock, null, tint = NavyBlue)
                                },
                                trailingIcon = {
                                    IconButton(onClick = {
                                        passwordVisible = !passwordVisible
                                    }) {
                                        Icon(
                                            if (passwordVisible)
                                                Icons.Default.Visibility
                                            else Icons.Default.VisibilityOff,
                                            null, tint = Color.Gray
                                        )
                                    }
                                },
                                visualTransformation = if (passwordVisible)
                                    androidx.compose.ui.text.input.VisualTransformation.None
                                else
                                    androidx.compose.ui.text.input.PasswordVisualTransformation(),
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                singleLine = true
                            )
                            Spacer(Modifier.height(16.dp))
                            Button(
                                onClick = { onLoginSuccess() },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(52.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = NavyBlue)
                            ) {
                                Text(
                                    if (LanguageState.isKannada)
                                        "ಲಾಗಿನ್" else "Login",
                                    fontSize = 16.sp, fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        // Google Login
                        2 -> {
                            Spacer(Modifier.height(8.dp))
                            OutlinedButton(
                                onClick = { onLoginSuccess() },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(52.dp),
                                shape = RoundedCornerShape(12.dp),
                                border = BorderStroke(1.dp, Color.LightGray)
                            ) {
                                Text("G", fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF4285F4))
                                Spacer(Modifier.width(8.dp))
                                Text(
                                    if (LanguageState.isKannada)
                                        "Google ಮೂಲಕ ಲಾಗಿನ್" else "Continue with Google",
                                    fontSize = 15.sp, color = Color.DarkGray
                                )
                            }
                            Spacer(Modifier.height(12.dp))
                            Text(
                                if (LanguageState.isKannada)
                                    "ನಿಮ್ಮ Google ಖಾತೆ ಉಪಯೋಗಿಸಿ ಸುರಕ್ಷಿತವಾಗಿ ಲಾಗಿನ್ ಮಾಡಿ"
                                else "Sign in securely using your Google account",
                                color = Color.Gray,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            Text(
                if (LanguageState.isKannada)
                    "ಗ್ರಾಮ ಪಂಚಾಯತ್ ಡಿಜಿಟಲ್ ಸೇವೆ"
                else "Gram Panchayat Digital Service",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 12.sp
            )

            Spacer(Modifier.height(40.dp))
        }
    }
}