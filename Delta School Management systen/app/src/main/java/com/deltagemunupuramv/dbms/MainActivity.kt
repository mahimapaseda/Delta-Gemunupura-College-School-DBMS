package com.deltagemunupuramv.dbms

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.deltagemunupuramv.dbms.adapter.FeatureAdapter
import com.deltagemunupuramv.dbms.databinding.ActivityMainBinding
import com.deltagemunupuramv.dbms.databinding.NavHeaderBinding
import com.deltagemunupuramv.dbms.model.Feature
import com.deltagemunupuramv.dbms.util.AccessLevel
import com.deltagemunupuramv.dbms.util.BiometricAuthManager
import com.deltagemunupuramv.dbms.util.BiometricAvailability
import com.deltagemunupuramv.dbms.util.UserSession
import com.google.android.material.navigation.NavigationView
import android.widget.Toast

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var featureAdapter: FeatureAdapter
    private lateinit var biometricAuthManager: BiometricAuthManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Check if user is logged in
        if (UserSession.getUser() == null) {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
            return
        }

        // Initialize biometric manager
        biometricAuthManager = BiometricAuthManager(this)
        
        setupToolbar()
        setupNavigationDrawer()
        setupFeaturesList()
        updateUserInfo()
    }

    private fun setupToolbar() {
        val toolbar = binding.toolbar as androidx.appcompat.widget.Toolbar
        setSupportActionBar(toolbar)
        toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            toolbar,
            R.string.nav_drawer_open,
            R.string.nav_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        
        // Ensure navigation icon is white
        toggle.drawerArrowDrawable.color = getColor(R.color.white)
    }

    private fun setupNavigationDrawer() {
        binding.navigationView.setNavigationItemSelectedListener(this)
        
        // Hide menu items based on user role
        val menu = binding.navigationView.menu
        val userRole = UserSession.getUser()?.role ?: ""
        
        // Hide students management if user doesn't have access
        menu.findItem(R.id.nav_students)?.isVisible = AccessLevel.canAccessStudents(userRole)
        
        // Hide staff management if user doesn't have access
        menu.findItem(R.id.nav_staff)?.isVisible = AccessLevel.canAccessStaff(userRole)
        
        // Hide assets management if user doesn't have access
        menu.findItem(R.id.nav_assets)?.isVisible = AccessLevel.canAccessAssets(userRole)
        
        // Setup biometric menu item
        setupBiometricMenuItem()
    }
    
    private fun setupBiometricMenuItem() {
        val biometricMenuItem = binding.navigationView.menu.findItem(R.id.nav_biometric)
        val biometricAvailability = biometricAuthManager.isBiometricAvailable()
        
        // Show/hide biometric option based on device capability
        biometricMenuItem?.isVisible = biometricAvailability == BiometricAvailability.AVAILABLE
        
        // Set initial checked state
        biometricMenuItem?.isChecked = biometricAuthManager.isBiometricEnabled()
    }

    private fun setupFeaturesList() {
        val features = mutableListOf<Feature>()
        val userRole = UserSession.getUser()?.role ?: ""
        
        // Add students management if user has access
        if (AccessLevel.canAccessStudents(userRole)) {
            features.add(Feature(
                R.drawable.ic_person,
                getString(R.string.manage_students),
                getString(R.string.students_desc),
                true
            ))
        }

        // Add staff management if user has access
        if (AccessLevel.canAccessStaff(userRole)) {
            features.add(Feature(
                R.drawable.ic_staff,
                getString(R.string.manage_staff),
                getString(R.string.staff_desc),
                true
            ))
        }

        // Add assets management if user has access
        if (AccessLevel.canAccessAssets(userRole)) {
            features.add(Feature(
                R.drawable.ic_assets,
                getString(R.string.manage_assets),
                getString(R.string.assets_desc),
                true
            ))
        }

        features.add(Feature(
            R.drawable.ic_exams,
            getString(R.string.manage_exams),
            getString(R.string.exams_desc),
            true
        ))

        features.add(Feature(
            R.drawable.ic_time,
            getString(R.string.manage_timetables),
            getString(R.string.timetables_desc),
            true,
            UserSession.canModifyTimetables()
        ))

        // Add System Settings for full access users only
        if (AccessLevel.hasFullAccess(userRole)) {
            features.add(Feature(
                R.drawable.ic_settings,
                getString(R.string.system_settings),
                getString(R.string.system_settings_desc),
                true
            ))
        }

        featureAdapter = FeatureAdapter(features) { feature ->
            // Handle feature click
            when (feature.title) {
                getString(R.string.manage_students) -> {
                    startActivity(Intent(this, ManageStudentsActivity::class.java))
                }
                getString(R.string.manage_staff) -> {
                    startActivity(Intent(this, ManageStaffActivity::class.java))
                }
                getString(R.string.manage_assets) -> {
                    startActivity(Intent(this, ManageAssetsActivity::class.java))
                }
                getString(R.string.manage_exams) -> {
                    startActivity(Intent(this, ManageExamsActivity::class.java))
                }
                getString(R.string.manage_timetables) -> {
                    startActivity(Intent(this, ManageTimetableActivity::class.java))
                }
                getString(R.string.system_settings) -> {
                    startActivity(Intent(this, SystemSettingsActivity::class.java))
                }
            }
        }

        binding.featuresRecyclerView.apply {
            val span = resources.getInteger(R.integer.grid_span_features)
            layoutManager = GridLayoutManager(this@MainActivity, span)
            adapter = featureAdapter
        }
    }

    private fun updateUserInfo() {
        UserSession.getUser()?.let { user ->
            // Update navigation header
            val headerView = binding.navigationView.getHeaderView(0)
            val headerBinding = NavHeaderBinding.bind(headerView)
            headerBinding.userNameText.text = user.nameWithInitials
            headerBinding.userRoleText.text = user.role

            // Load profile image if available
            if (user.photoUrl.isNotBlank()) {
                com.bumptech.glide.Glide.with(this)
                    .load(user.photoUrl)
                    .placeholder(R.drawable.ic_person_placeholder)
                    .circleCrop()
                    .into(headerBinding.profileImage)
            } else {
                headerBinding.profileImage.setImageResource(R.drawable.ic_person_placeholder)
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_students -> {
                startActivity(Intent(this, ManageStudentsActivity::class.java))
            }
            R.id.nav_staff -> {
                startActivity(Intent(this, ManageStaffActivity::class.java))
            }
            R.id.nav_assets -> {
                startActivity(Intent(this, ManageAssetsActivity::class.java))
            }
            R.id.nav_exams -> {
                startActivity(Intent(this, ManageExamsActivity::class.java))
            }
            R.id.nav_timetables -> {
                startActivity(Intent(this, ManageTimetableActivity::class.java))
            }
            R.id.nav_profile -> {
                startActivity(Intent(this, ProfileActivity::class.java))
            }
            R.id.nav_biometric -> {
                handleBiometricToggle(item)
            }
            R.id.nav_logout -> {
                // Clear both user session and authentication state
                UserSession.clearSession()
                startActivity(Intent(this, SignInActivity::class.java))
                finish()
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    
    private fun handleBiometricToggle(item: MenuItem) {
        val isCurrentlyEnabled = biometricAuthManager.isBiometricEnabled()
        
        if (!isCurrentlyEnabled) {
            // User wants to enable biometric
            biometricAuthManager.showBiometricPrompt(
                activity = this,
                title = getString(R.string.biometric_title),
                subtitle = getString(R.string.biometric_description),
                description = getString(R.string.biometric_touch_sensor),
                onSuccess = {
                    // Enable biometric authentication
                    biometricAuthManager.setBiometricEnabled(true)
                    item.isChecked = true
                    Toast.makeText(this, getString(R.string.biometric_enabled), Toast.LENGTH_SHORT).show()
                },
                onError = { errorMessage ->
                    // Keep current state (disabled)
                    item.isChecked = false
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                },
                onFailed = {
                    // Keep current state (disabled)
                    item.isChecked = false
                    Toast.makeText(this, getString(R.string.biometric_failed), Toast.LENGTH_SHORT).show()
                }
            )
        } else {
            // User wants to disable biometric
            biometricAuthManager.setBiometricEnabled(false)
            item.isChecked = false
            Toast.makeText(this, getString(R.string.biometric_disabled), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    
    override fun onPause() {
        super.onPause()
        // Clear authentication state when app goes to background
        UserSession.setAppAuthenticated(false)
    }
}
