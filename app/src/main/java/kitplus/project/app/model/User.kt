package kitplus.project.app.model

class User {
    var userid : String
    var name: String
    var weightUser: Double
    var heightUser: Double
    var birthday: String

    constructor(
        userid: String,
        name: String,
        weightUser: Double,
        heightUser: Double,
        birthday: String
    ) {
        this.userid = userid
        this.name = name
        this.weightUser = weightUser
        this.heightUser = heightUser
        this.birthday = birthday
    }
}