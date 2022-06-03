package kitplus.project.app.model

data class User(
    var userid : String = "" ,
    var name: String = "" ,
    var weightUser: String = "" ,
    var heightUser: String = "" ,
    var birthday: String = "",
    var water: List<WaterRegistry> = listOf(),
    var exercise: List<ExerciseRegistry> = listOf(),
    var steps: Float = 0f
)