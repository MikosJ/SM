fun main() {
    val teamDistributor = TeamDistributor()
    teamDistributor.distributeTeam(
        listOf(
            Player("Johnny", 8),
            Player("Robbie", 5),
            Player("Juliet", 3),
            Player("Scarlet", 5),
            Player("Jude", 9),
            Player("Deborah", 6)
        ), 2
    )

}