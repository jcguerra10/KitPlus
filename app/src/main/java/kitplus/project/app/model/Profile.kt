package kitplus.project.app.model

class Profile {
    var username: String
    var password:String

    var userid: Int

    constructor(username: String, password: String, userid: Int) {
        this.username = username
        this.password = password
        this.userid = userid
    }
}