### Startup
I've used Kotlin 1.8 and Java SDK 17.0.3

To check my solution you should add Player object/s with name and rate values to the list in main function. You may also
use test cases that I've written. I've used gradle so to check the test cases open Gradle tab in IntelliJ and go into "Tasks/verification/test" or you may go into tests folder and start some of the tests. \
\
I didn't test the calculateStandardDeviation and calculateAverageRate functions as they are private, and they are
being tested by higher level function but the tests would look like:

#### Average rate
<pre>
    @Test
    fun `test average rate is correct across teams`() {
        // given
        val teamBins = listOf(
            listOf(
                Player("Player1", 10),
                Player("Player2", 20)
            ),
            listOf(
                Player("Player1", 10),
                Player("Player2", 20)
            ),
            listOf(
                Player("Player1", 10),
                Player("Player2", 20)
            )
        )
        // when
        val result = teamDistributor.calculateRateAverage(teamBins)
        assertTrue(result.equals(listOf(15.0,15.0,15.0)))

    }
</pre>
#### Standard deviation
<pre>
    @Test
    fun `test standard deviation is correct across teams`() {
        // given
        val list = listOf(1.0,2.0,3.0,4.0,5.0)
        // when
        val result = teamDistributor.calculateStandardDeviation(list)
        println(result)
        // then
        assertTrue(result.equals(Math.sqrt(2.0)))

    }
</pre>


