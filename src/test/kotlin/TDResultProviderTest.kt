import io.TDResultProvider
import model.Player
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import service.TeamDistributor
import kotlin.test.assertFalse


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TDResultProviderTest {

    lateinit var tdResultProvider: TDResultProvider
    lateinit var teamDistributor: TeamDistributor

    @BeforeAll
    fun setup() {
        tdResultProvider = TDResultProvider()
        teamDistributor = TeamDistributor()
    }

    @Test
    fun `test output correctness`() {
        // given
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
        val expectedResult = "Team number 1 has 2 players (Jude, Juliet). Average rate: 6.0\n" +
                "Team number 2 has 2 players (Johnny, Scarlet). Average rate: 6.5\n" +
                "Team number 3 has 2 players (Deborah, Robbie). Average rate: 5.5\n" +
                "Teams rate standard deviation: 0.41"
        val actualResult = tdResultProvider.resultToString(result)

        // then
        assert(
            actualResult.equals(
                expectedResult
            )
        )
    }

    @Test
    fun `test incorrect output`() {
        // given
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
        val badResult = "EMPTY"
        val actualResult = tdResultProvider.resultToString(result)

        // then
        assertFalse(
            actualResult.equals(
                badResult
            )
        )
    }
}