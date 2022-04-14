package kitplus.project.app.model

class ControllerUser {
    private val profiles : ArrayList<Profile> = ArrayList()

    init {
        profiles.add(Profile("juanguerra@gmail.com", "1234", 0))
    }

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