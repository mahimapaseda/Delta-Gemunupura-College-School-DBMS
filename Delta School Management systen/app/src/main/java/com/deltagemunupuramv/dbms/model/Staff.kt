package com.deltagemunupuramv.dbms.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Staff(
    val id: String = "",
    // Basic Information
    val nameWithInitials: String = "",
    val fullName: String = "",
    val nicNumber: String = "",
    val registrationNumber: String = "",
    val email: String = "",
    val password: String = "", // For sign in
    val phoneNumber: String = "",
    val whatsappNumber: String = "",
    val personalAddress: String = "",
    
    // Personal Details
    val dateOfBirth: String = "",
    val gender: String = "", // Male/Female
    val maritalStatus: String = "", // Single/Married
    val spouseName: String = "",
    val spouseAddress: String = "",
    val spouseTelephone: String = "",
    
    // Employment Details
    val dateOfFirstAppointment: String = "",
    val dateOfAppointmentToSchool: String = "",
    val previouslyServedSchools: List<String> = emptyList(),
    val classAndGrade: String = "",
    
    // Qualifications
    val educationalQualifications: List<String> = emptyList(),
    val professionalQualifications: List<String> = emptyList(),
    
    // Teaching Details
    val appointedSubject: String = "",
    val subjectsTaught: List<String> = emptyList(),
	val gradesTaught: List<String> = emptyList(),
	val teacherNumber: String = "",
    
    // Emergency Contact
    val emergencyContactName: String = "",
    val emergencyContactPhone: String = "",
    
    // System Fields
    val staffType: StaffType = StaffType.ACADEMIC,
    val status: StaffStatus = StaffStatus.ACTIVE,
    val photoUrl: String = "",
    val accessLevel: AccessLevel = AccessLevel.PARTIAL,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) : Parcelable

enum class StaffType {
    ACADEMIC,
    NON_ACADEMIC
}

enum class StaffStatus {
    ACTIVE,
    INACTIVE,
    ON_LEAVE
}

enum class AccessLevel {
    FULL,
    PARTIAL
}

enum class Gender {
    MALE,
    FEMALE
}

enum class MaritalStatus {
    SINGLE,
    MARRIED
}
