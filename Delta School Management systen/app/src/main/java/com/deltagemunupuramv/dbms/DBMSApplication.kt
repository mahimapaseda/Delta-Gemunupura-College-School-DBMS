package com.deltagemunupuramv.dbms

import android.app.Application
import android.util.Log
import com.deltagemunupuramv.dbms.util.FirebaseManager
import com.deltagemunupuramv.dbms.util.UserSession
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

class DBMSApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        // Initialize UserSession
        UserSession.init(this)
        
        initializeFirebase()
        
        // Initialize Firebase Manager for sync operations
        FirebaseManager.initializeRealtimeSync()
    }
    
    private fun initializeFirebase() {
        try {
            // Initialize Firebase Realtime Database
            FirebaseDatabase.getInstance().apply {
                // Enable offline persistence
                setPersistenceEnabled(true)
                
                // Keep important data synced for offline access
                reference.child("staff").keepSynced(true)
                reference.child("students").keepSynced(true)
                reference.child("users").keepSynced(true)
                reference.child("assets").keepSynced(true)
                reference.child("exams").keepSynced(true)
                reference.child("ol_exams").keepSynced(true)
                reference.child("al_exams").keepSynced(true)
                reference.child("grade6_9Termtest").keepSynced(true)
                reference.child("grade10_11_term_test").keepSynced(true)
                reference.child("BioScienceTermTest").keepSynced(true)
                reference.child("timetables").keepSynced(true)
                
                Log.d("DBMSApplication", "Firebase Realtime Database initialized successfully")
            }

            // Initialize Firestore with optimized settings
            val firestore = FirebaseFirestore.getInstance()
            val settings = FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .setCacheSizeBytes(50L * 1024 * 1024) // 50MB to avoid high memory usage
                .build()
            firestore.firestoreSettings = settings

            Log.d("DBMSApplication", "Firestore initialized successfully")
            
        } catch (e: Exception) {
            Log.e("DBMSApplication", "Error initializing Firebase", e)
        }
    }
}
