import io.TDResultProvider
import model.Player
import service.TeamDistributor

fun main(args: Array<String>) {
    val teamDistributor = TeamDistributor()
    val tdResultProvider = TDResultProvider()
    val result = teamDistributor.distributeTeam(
        listOf(
            Player("Johnny", 8),
            Player("Robbie", 5),
            Player("Juliet", 3),
            Player("Scarlet", 5),
            Player("Jude", 9),
            Player("Deborah", 6)

        ), 3
    )
    println(tdResultProvider.resultToString(result))

}