package kitplus.project.app.model

class Profile {
    var username: String
    var password:String

    var userid: String

    constructor(username: String, password: String, userid: String) {
        this.username = username
        this.password = password
        this.userid = userid
    }
}