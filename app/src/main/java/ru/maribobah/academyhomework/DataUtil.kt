package ru.maribobah.academyhomework

import ru.maribobah.academyhomework.data.models.Actor
import ru.maribobah.academyhomework.data.models.Movie

class DataUtil {
    fun generateMovies() : List<Movie> {
        return listOf(
            Movie("Avengers: End Game", "13+", false, "Action, Adventure, Drama", 4, 125, 137,
                    R.drawable.avengers_card, R.drawable.avengers_details,
                    "After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.",
                    listOf(
                            Actor("Robert Downey Jr.", R.drawable.robert_downey),
                            Actor("Chris Evans", R.drawable.chris_evans),
                            Actor("Mark Ruffalo", R.drawable.mark_ruffalo),
                            Actor("Chris Hemsworth", R.drawable.chris_hemsworth),
                            Actor("Scarlett Johansson", R.drawable.scarlett_johansson),
                            Actor("Jeremy Renner", R.drawable.jeremy_renner)
                    )),
            Movie("Tenet", "16+", true, "Action, Sci-Fi, Thriller", 5, 98, 97,
                    R.drawable.tenet_card, R.drawable.avengers_details,
                    "Armed with only one word, Tenet, and fighting for the survival of the entire world, a Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time.",
                    listOf(
                            Actor("John David Washington", R.drawable.john_david_washington),
                            Actor("Robert Pattinson", R.drawable.robert_pattinson),
                            Actor("Elizabeth Debicki", R.drawable.elizabeth_debicki),
                            Actor("Kenneth Branagh", R.drawable.kenneth_branagh),
                            Actor("Dimple Kapadia", R.drawable.dimple_kapadia),
                            Actor("Himesh Patel", R.drawable.himesh_patel)
                    )),
            Movie("Black Widow", "13+", false, "Action, Adventure, Sci-Fi", 4, 38, 102,
                    R.drawable.black_widow_card, R.drawable.avengers_details,
                    "A film about Natasha Romanoff in her quests between the films Civil War and Infinity War.",
                    listOf(
                            Actor("Scarlett Johansson", R.drawable.scarlett_johansson),
                            Actor("Florence Pugh", R.drawable.florence_pugh),
                            Actor("David Harbour", R.drawable.david_harbour),
                            Actor("O.T. Fagbenle", R.drawable.fagbenle),
                            Actor("Rachel Weisz", R.drawable.rachel_weisz),
                            Actor("William Hurt", R.drawable.william_hurt)
                    )),
            Movie("Wonder Woman 1984", "13+", false, "Action, Adventure, Fantasy", 5, 74, 120,
                    R.drawable.wonder_woman_card, R.drawable.avengers_details,
                    "Fast forward to the 1980s as Wonder Woman's next big screen adventure finds her facing two all-new foes: Max Lord and The Cheetah.",
                    listOf(
                            Actor("Gal Gadot", R.drawable.gal_gadot),
                            Actor("Chris Pine", R.drawable.chris_pine),
                            Actor("Kristen Wiig", R.drawable.kristen_wiig),
                            Actor("Pedro Pascal", R.drawable.pedro_pascal),
                            Actor("Connie Nielsen", R.drawable.connie_nielsen),
                            Actor("Robin Wright", R.drawable.robin_wright)
                    )),
        )
    }
}