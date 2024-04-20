package com.android.aye_ideacanvasv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.credentials.GetCredentialResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class SignIn : ComponentActivity() {
    private lateinit var googleSignIn: GoogleSignIn
    private lateinit var auth: FirebaseAuth
    private lateinit var credsResponse: GetCredentialResponse


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            println("Someone's signed in!")
            Firebase.auth.signOut()
            googleSignIn = GoogleSignIn(this, CoroutineScope(Dispatchers.Main), auth)
            setContent {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    googleSignIn.GoogleLoginButton()
                }
            }
        } else {
            googleSignIn = GoogleSignIn(this, CoroutineScope(Dispatchers.Main), auth)
            setContent {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    googleSignIn.GoogleLoginButton()
                }
            }
        }
    }
}