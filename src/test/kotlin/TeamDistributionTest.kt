import exception.TeamDistributionException
import io.TDResultProvider
import model.Player
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import service.TeamDistributor
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class TeamDistributionTest {

    lateinit var tdResultProvider: TDResultProvider
    lateinit var teamDistributor: TeamDistributor

    @BeforeAll
    fun setup() {
        tdResultProvider = TDResultProvider()
        teamDistributor = TeamDistributor()
    }

    @Test
    fun `throw TeamDistributionException when # of players is not divisible by # of teams`() {
        // given
        val list = listOf(
            Player("Johnny", 8),
            Player("Robbie", 5),
            Player("Juliet", 3),
            Player("Scarlet", 5),
            Player("Jude", 9),
            Player("Deborah", 6)
        )
        val numberOfTeams = 4
        // then
        assertThrows(TeamDistributionException::class.java) {
            teamDistributor.distributeTeam(
                list,
                numberOfTeams
            )
        }

    }

    @Test
    fun `test team distribution`() {
        // given
        val players = listOf(
            Player("Player1", 10),
            Player("Player2", 20),
            Player("Player3", 15),
            Player("Player4", 25),
            Player("Player5", 30),
            Player("Player6", 30)
        )
        val numberOfTeams = 2

        // when
        val result = teamDistributor.distributeTeam(players, numberOfTeams)

        // then
        assertEquals(
            numberOfTeams, result.teams.size
        )

        val teamSize = result.teams[0].size
        assertTrue(result.teams.all { it.size == teamSize })

        assertTrue(result.standardDeviation >= 0)
        assertTrue(result.standardDeviation < 100)
    }

}