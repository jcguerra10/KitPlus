package kitplus.project.app.model

import com.google.gson.Gson
import kitplus.project.app.units.Constants
import kitplus.project.app.units.WebUtil
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.lifecycleScope
import java.util.*
import kotlin.collections.ArrayList

class ControllerUser {
    private val profiles : ArrayList<Profile> = ArrayList()

    fun addNewProfile(newProfile: Profile) {
        profiles.add(newProfile)
    }

    fun getProfile(idLocationProfile: Int): Profile {
        return profiles[idLocationProfile]
    }

    fun authentication(username:String, password:String): Boolean {
        var ret = false

        for (i in profiles.indices) {
            if (profiles[i].username == username && profiles[i].password == password) {
                ret = true
                break
            }
        }

        return ret
    }
}