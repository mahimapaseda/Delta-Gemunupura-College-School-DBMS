package com.deltagemunupuramv.dbms.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(
    val id: String = "",
    // Basic Information
    val nameWithInitials: String = "",
    val fullName: String = "",
    val indexNumber: String = "",
    val nicNumber: String = "",
    val email: String = "",
    val password: String = "", // For sign in
    
    // Personal Details
    val dateOfBirth: String = "",
    val gender: String = "", // Male/Female
    val religion: String = "",
    val address: String = "",
    val telephoneNumber: String = "",
    val whatsappNumber: String = "",
    
    // Academic Details
    val currentGrade: String = "",
    val currentClass: String = "",
    val admissionDate: String = "",
    val previousSchools: String = "",
    val mediumOfStudy: String = "",
    val subjects: List<String> = emptyList(),
    
    // Parent/Guardian Information
    val parentGuardianName: String = "",
    val parentGuardianContact: String = "",
    val parentGuardianNic: String = "",
    val parentGuardianOccupation: String = "",
    
    // Siblings Information
    val siblingsNames: String = "",
    val siblingsGrades: String = "",
    
    // Special Information
    val specialDisabilities: String = "",
    
    // System Fields
    val studentType: StudentType = StudentType.REGULAR,
    val status: StudentStatus = StudentStatus.ACTIVE,
    val photoUrl: String = "",
    val accessLevel: AccessLevel = AccessLevel.PARTIAL,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) : Parcelable

enum class StudentType {
    REGULAR,
    TRANSFER,
    INTERNATIONAL
}

enum class StudentStatus {
    ACTIVE,
    INACTIVE,
    GRADUATED,
    TRANSFERRED
}
