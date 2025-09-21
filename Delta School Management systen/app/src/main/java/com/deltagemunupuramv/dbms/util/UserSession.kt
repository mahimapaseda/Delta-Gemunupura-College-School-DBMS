package com.deltagemunupuramv.dbms.util

import android.content.Context
import android.content.SharedPreferences
import com.deltagemunupuramv.dbms.model.User
import com.google.gson.Gson

object UserSession {
    private const val PREFS_NAME = "user_session"
    private const val KEY_USER = "user_data"
    private const val KEY_IS_LOGGED_IN = "is_logged_in"
    private const val KEY_APP_AUTHENTICATED = "app_authenticated"
    private const val KEY_BIOMETRIC_ENABLED = "biometric_enabled"
    
    private lateinit var prefs: SharedPreferences
    private val gson = Gson()
    
    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
    
    fun saveUser(user: User) {
        val userJson = gson.toJson(user)
        prefs.edit()
            .putString(KEY_USER, userJson)
            .putBoolean(KEY_IS_LOGGED_IN, true)
            .apply()
    }
    
    fun getUser(): User? {
        val userJson = prefs.getString(KEY_USER, null)
        return if (userJson != null) {
            gson.fromJson(userJson, User::class.java)
        } else {
            null
        }
    }
    
    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false) && getUser() != null
    }
    
    fun clearSession() {
        prefs.edit()
            .remove(KEY_USER)
            .putBoolean(KEY_IS_LOGGED_IN, false)
            .putBoolean(KEY_APP_AUTHENTICATED, false)
            .apply()
    }
    
    fun setAppAuthenticated(authenticated: Boolean) {
        prefs.edit()
            .putBoolean(KEY_APP_AUTHENTICATED, authenticated)
            .apply()
    }
    
    fun isAppAuthenticated(): Boolean {
        return prefs.getBoolean(KEY_APP_AUTHENTICATED, false)
    }
    
    fun setBiometricEnabled(enabled: Boolean) {
        prefs.edit()
            .putBoolean(KEY_BIOMETRIC_ENABLED, enabled)
            .apply()
    }
    
    fun isBiometricEnabled(): Boolean {
        return prefs.getBoolean(KEY_BIOMETRIC_ENABLED, false)
    }
    
    fun canModifyTimetables(): Boolean {
        val user = getUser()
        return user?.role in listOf("Principal", "Data Officer", "Technical Officer")
    }
    
    fun hasFullAccess(): Boolean {
        val user = getUser()
        return user?.accessLevel == "Full access"
    }
}
