package com.coppermobile.android.egowall.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import com.coppermobile.android.egowall.R
import com.coppermobile.android.egowall.fragments.BaseFragment
import com.coppermobile.android.egowall.interfaces.AlertDialogCallback
import com.coppermobile.android.egowall.interfaces.RequestPermissionsListener
import com.coppermobile.android.egowall.utils.Helpers
import com.coppermobile.android.egowall.utils.PermissionUtils
import androidx.core.content.ContextCompat
import android.view.WindowManager


/**
 * Created by Noopur on 30, January, 2019
 * Base activity class to extend by every activity
 */
open class BaseActivity : BaseConnectivityActivity() {
    private val AUDIO_RECORD = 100
    private val EXTERNAL_STORAGE = 102
    private val SETTINGS_CODE = 101
    private val EXTERNAL_STORAGE_SETTINGS_CODE = 103
    private val CAMERA = 104
    private val CAMERA_SETTING_CODE = 105
    var requestPermissionListener: RequestPermissionsListener? = null

    /**
     * This method is used to switch fragments associated to any activity.
     *
     * @param targetFragment The fragment which we want top on activitiy.
     * @param addToBackStack true, if we want to add fragment into stack and false, if we don't want to add fragment into stack.
     * @param fragmentTag    Tag of the fragment to be open on activity.
     */
    fun switchFragment(targetFragment: BaseFragment, addToBackStack: Boolean, fragmentTag: String?, rootContainer : Int) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.replace(rootContainer, targetFragment, fragmentTag)
        fragmentTransaction.commit()
    }


    fun changeStatusBarColor(color: Int) {

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, color)
        }
    }


    fun setFullScreenActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

    fun hasAudioPermission(): Boolean {
        return PermissionUtils.hasPermission(this, Manifest.permission.RECORD_AUDIO)
    }

    fun hasStorageWritePermission(): Boolean {
        return PermissionUtils.hasPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    /**
     * Method to check if permission granted and request for permission if not granted.
     */
    fun activityRequestingPermission() {

        if (PermissionUtils.hasPermission(
                this, Manifest.permission.RECORD_AUDIO
            )
        ) {
            requestPermissionListener?.onSuccess()
        } else {
            PermissionUtils.requestPermissions(
                this,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                AUDIO_RECORD
            )
        }
    }


    /**
     * Method to check Read and Write permission granted and request for permission if not granted.
     */
    fun activityRequestingReadWritePermission() {

        if (PermissionUtils.hasPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        ) {
            requestPermissionListener?.onSuccess()
        } else {
            PermissionUtils.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                EXTERNAL_STORAGE
            )
        }
    }


    /**
     * Method to check if permission granted and request for permission if not granted.
     */
    fun activityRequestCameraPermission() {

        if (PermissionUtils.hasPermission(
                this, Manifest.permission.CAMERA
            )
        ) {
            requestPermissionListener?.onSuccess()
        } else {
            PermissionUtils.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA
            )
        }
    }


    /**
     * Action to perform when user denied to grant permission.
     */
    private fun doWhenAudioDenied(grantResults: IntArray) {

        Helpers.showAlertDialog(
            this,
            getString(R.string.permission_title),
            getString(R.string.permission_logic_audio),
            getString(R.string.grant),
            getString(R.string.cancel),
            object : AlertDialogCallback {
                override fun posCallback() {
                    PermissionUtils.requestPermissions(
                        this@BaseActivity,
                        arrayOf<String>(Manifest.permission.RECORD_AUDIO),
                        AUDIO_RECORD
                    )
                }

                override fun negCallback() {
                    //Do nothing
                }
            })
    }


    /**
     * Action to perform when user denied to grant external storage permission.
     */
    private fun doWhenStoragePermissionDenied(grantResults: IntArray) {

        Helpers.showAlertDialog(
            this,
            getString(R.string.permission_title),
            getString(R.string.permission_logic_storage),
            getString(R.string.grant),
            getString(R.string.cancel),
            object : AlertDialogCallback {
                override fun posCallback() {
                    PermissionUtils.requestPermissions(
                        this@BaseActivity,
                        arrayOf<String>(
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ),
                        EXTERNAL_STORAGE
                    )
                }

                override fun negCallback() {
                    //Do something
                }
            })
    }


    /**
     * Action to perform when user denied to grant permission.
     */
    private fun doWhenCameraDenied(grantResults: IntArray) {

        Helpers.showAlertDialog(
            this,
            getString(R.string.permission_title),
            getString(R.string.permission_logic_camera),
            getString(R.string.grant),
            getString(R.string.cancel),
            object : AlertDialogCallback {
                override fun posCallback() {
                    PermissionUtils.requestPermissions(
                        this@BaseActivity,
                        arrayOf<String>(Manifest.permission.CAMERA),
                        CAMERA
                    )
                }

                override fun negCallback() {
                    //Do nothing
                }
            })
    }


    /**
     * Action to perform if user denied permission with never ask again.
     */
    private fun doWhenAudioDeniedWithNeverAskAgain(grantResults: IntArray) {
        if (grantResults[0] == -1) {
            Helpers.showAlertDialog(
                this,
                getString(R.string.permission_title),
                getString(R.string.permission_logic_audio),
                getString(R.string.grant),
                getString(R.string.cancel),
                object : AlertDialogCallback {
                    override fun posCallback() {
                        val intent = Intent(
                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.fromParts("package", packageName, null)
                        )
                        intent.addCategory(Intent.CATEGORY_DEFAULT)
                        startActivityForResult(intent, SETTINGS_CODE)
                    }

                    override fun negCallback() {
                        //Do nothing
                    }
                })
        }
    }


    /**
     * Action to perform if user denied external storage permission with never ask again.
     */
    private fun doWhenExternalStorageDeniedWithNeverAskAgain(grantResults: IntArray) {
        if (grantResults[0] == -1) {
            Helpers.showAlertDialog(
                this,
                getString(R.string.permission_title),
                getString(R.string.permission_logic_storage),
                getString(R.string.grant),
                getString(R.string.cancel),
                object : AlertDialogCallback {
                    override fun posCallback() {
                        val intent = Intent(
                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.fromParts("package", packageName, null)
                        )
                        intent.addCategory(Intent.CATEGORY_DEFAULT)
                        startActivityForResult(intent, EXTERNAL_STORAGE_SETTINGS_CODE)
                    }

                    override fun negCallback() {
                        //Do nothing
                    }
                })
        }
    }


    /**
     * Action to perform if user denied permission with never ask again.
     */
    private fun doWhenCameraDeniedWithNeverAskAgain(grantResults: IntArray) {
        if (grantResults[0] == -1) {
            Helpers.showAlertDialog(
                this,
                getString(R.string.permission_title),
                getString(R.string.permission_logic_camera),
                getString(R.string.grant),
                getString(R.string.cancel),
                object : AlertDialogCallback {
                    override fun posCallback() {
                        val intent = Intent(
                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.fromParts("package", packageName, null)
                        )
                        intent.addCategory(Intent.CATEGORY_DEFAULT)
                        startActivityForResult(intent, CAMERA_SETTING_CODE)
                    }

                    override fun negCallback() {
                        //Do nothing
                    }
                })
        }
    }


    /**
     * To check if permission granted and handle result for permission request.
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (audioPermissionGiven(requestCode, grantResults) && externalPermissionGiven(
                requestCode,
                grantResults
            ) && cameraPermissionGiven(requestCode, grantResults)
        ) {
            requestPermissionListener?.onSuccess()
        } else {
            if (requestCode == AUDIO_RECORD) {
                if (alreadyAskedForPerm()) {
                    if (weNeedToShowRational()) {
                        doWhenAudioDenied(grantResults)
                    } else {
                        doWhenAudioDeniedWithNeverAskAgain(grantResults)
                    }
                } else {
                    PermissionUtils.markedPermissionAsAsked(this, Manifest.permission.RECORD_AUDIO)

                    if (weNeedToShowRational()) {
                        doWhenAudioDenied(grantResults)
                    }
                }
            } else if (requestCode == CAMERA) {

                if (alreadyAskedForCameraPerm()) {
                    if (weNeedToShowCameraRational()) {
                        doWhenCameraDenied(grantResults)
                    } else {
                        doWhenCameraDeniedWithNeverAskAgain(grantResults)
                    }
                } else {
                    PermissionUtils.markedPermissionAsAsked(this, Manifest.permission.CAMERA)

                    if (weNeedToShowCameraRational()) {
                        doWhenCameraDenied(grantResults)
                    }
                }


            } else {
                if (alreadyAskedForExternalStoragePerm()) {

                    if (weNeedToShowExternalStorageRational()) {
                        doWhenStoragePermissionDenied(grantResults)
                    } else {
                        doWhenExternalStorageDeniedWithNeverAskAgain(grantResults)
                    }
                } else {
//                    if (grantResults[0] == -1) {
                    PermissionUtils.markedPermissionAsAsked(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                    }

                    if (weNeedToShowExternalStorageRational()) {
                        doWhenStoragePermissionDenied(grantResults)
                    }
                }
            }
        }
    }


    /**
     * Returns boolean if user is already asked for permission.
     */
    private fun alreadyAskedForPerm(): Boolean {
        return PermissionUtils.hasAskedForPermission(this, Manifest.permission.RECORD_AUDIO)
    }


    /**
     * Returns boolean if user is already asked for permission.
     */
    private fun alreadyAskedForExternalStoragePerm(): Boolean {
        return PermissionUtils.hasAskedForPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
                PermissionUtils.hasAskedForPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
    }


    /**
     * Returns boolean if user is already asked for permission.
     */
    private fun alreadyAskedForCameraPerm(): Boolean {
        return PermissionUtils.hasAskedForPermission(this, Manifest.permission.CAMERA)
    }


    /**
     * Returns boolean if user denied for granting permission.
     */
    private fun weNeedToShowRational(): Boolean {
        return PermissionUtils.shouldShowRational(this, Manifest.permission.RECORD_AUDIO)
    }

    /**
     * Returns boolean if user denied for granting external storage permission.
     */
    private fun weNeedToShowExternalStorageRational(): Boolean {
        return PermissionUtils.shouldShowRational(this, Manifest.permission.READ_EXTERNAL_STORAGE) ||
                PermissionUtils.shouldShowRational(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }


    /**
     * Returns boolean if user denied for granting permission.
     */
    private fun weNeedToShowCameraRational(): Boolean {
        return PermissionUtils.shouldShowRational(this, Manifest.permission.CAMERA)
    }


    /**
     * Returns boolean if audio permission is already granted.
     * @param requestCode request code from result of permission.
     * @param grantResults grant
     */
    private fun audioPermissionGiven(requestCode: Int, grantResults: IntArray): Boolean {
        return requestCode == AUDIO_RECORD && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
    }

    /**
     * Returns boolean if external permission is already granted.
     * @param requestCode request code from result of permission.
     * @param grantResults grant
     */
    private fun externalPermissionGiven(requestCode: Int, grantResults: IntArray): Boolean {
        return requestCode == EXTERNAL_STORAGE && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
    }


    /**
     * Returns boolean if camera permission is already granted.
     * @param requestCode request code from result of permission.
     * @param grantResults grant
     */
    private fun cameraPermissionGiven(requestCode: Int, grantResults: IntArray): Boolean {
        return requestCode == CAMERA && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
    }


    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SETTINGS_CODE) {
            activityRequestingPermission()
        } else if (requestCode == EXTERNAL_STORAGE_SETTINGS_CODE) {
            activityRequestingReadWritePermission()
        } else if (requestCode == CAMERA_SETTING_CODE) {
            activityRequestCameraPermission()
        }
    }
}