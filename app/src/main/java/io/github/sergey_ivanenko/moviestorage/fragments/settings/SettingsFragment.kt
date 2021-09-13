package io.github.sergey_ivanenko.moviestorage.fragments.settings

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import io.github.sergey_ivanenko.moviestorage.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.settings)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        return
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val sortBy = prefs.getString("sortBy", "")
        val isUseCursor = prefs.getBoolean("cursor", false)

        /*val args = Bundle()
        args.putString("sortBy", sortBy)
        args.putBoolean("cursor", isUseCursor)
        findNavController().*/

        Log.d("PREF", sortBy.orEmpty())
        Log.d("PREF", isUseCursor.toString())

        /*Log.d("PREF", "${button?.title}")
        Log.d("PREF", "${R.layout.fragment_settings}")*/

    }
}