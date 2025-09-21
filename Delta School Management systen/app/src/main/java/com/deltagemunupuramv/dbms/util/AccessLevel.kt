package com.deltagemunupuramv.dbms.util

object AccessLevel {
    
    fun canAccessStudents(role: String): Boolean {
        return role in listOf("Principal", "Data Officer", "Technical Officer")
    }
    
    fun canAccessStaff(role: String): Boolean {
        return role in listOf("Principal", "Data Officer", "Technical Officer")
    }
    
    fun canViewStaff(role: String): Boolean {
        return role in listOf("Principal", "Data Officer", "Technical Officer", "Academic Staff")
    }
    
    fun canAccessAssets(role: String): Boolean {
        return role in listOf("Principal", "Data Officer", "Technical Officer")
    }
    
    fun canAccessExams(role: String): Boolean {
        return role in listOf("Principal", "Data Officer", "Technical Officer", "Academic Staff")
    }
    
    fun canModifyTimetables(role: String): Boolean {
        return role in listOf("Principal", "Data Officer", "Technical Officer")
    }
    
    fun hasFullAccess(role: String): Boolean {
        return role in listOf("Principal", "Data Officer", "Technical Officer")
    }
    
    fun hasPartialAccess(role: String): Boolean {
        return role in listOf("Academic Staff", "Non-Academic Staff")
    }
    
    fun canManageUsers(role: String): Boolean {
        return role in listOf("Principal", "Data Officer")
    }
    
    fun canViewReports(role: String): Boolean {
        return role in listOf("Principal", "Data Officer", "Technical Officer", "Academic Staff")
    }
    
    fun canExportData(role: String): Boolean {
        return role in listOf("Principal", "Data Officer", "Technical Officer")
    }
}
