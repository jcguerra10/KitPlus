package kitplus.project.app.model

class User {
    var userid : String
    var name: String
    var weightUser: String
    var heightUser: String
    var birthday: String

    constructor(
        userid: String,
        name: String,
        weightUser: String,
        heightUser: String,
        birthday: String
    ) {
        this.userid = userid
        this.name = name
        this.weightUser = weightUser
        this.heightUser = heightUser
        this.birthday = birthday
    }
}