package com.example.tes

data class Trivia(
    val id:Int,
    val teks:String,
    val image: Int
    )

val trivia1 = Trivia (
    0,
    "Kamu tau gak, sih? " +
        "Dalam sehari manusia tuh harus minum 2 - 3 liter sehari? " +
        "Karena salah satu manfaatnya adalah meningkatkan kinerja otak, lho!",
        R.drawable.drink)

val trivia2 = Trivia (
    1,
    "Stretching lah sebelum stretching itu dilarang. Karena dengan stretching" +
            " membuat badan kita yang kaku jadi lebih rileks, jadi gak gampang " +
            "stress deh.",
    R.drawable.sport
        )
val trivia3 = Trivia (
    2,
    "Gak selamanya nyemil itu negatif. Sebab nyemil itu ternyata bisa nambah " +
            "energi ke tubuh yang dapat memberikan tambahan energi untuk produktivitas",
    R.drawable.lunch
        )

val trivia4 = Trivia (
    3,
    "Meditasi atau relaksasi dapat membantu kita konsentrasi dan mengendalikan pikiran" +
            " loh! Maka hal tersebut dapat membantu efektifitas belajar kita!",
    R.drawable.meditate
        )

val trivia5 = Trivia (
    4,
    "Jika merasa penat atau bosan, mungkin dalam waktu 5 menit ini kamu boleh mengisinya" +
            " dengan mendengarkan lagu kesukaan kamu",
    R.drawable.head
        )
