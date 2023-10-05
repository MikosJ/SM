import kotlin.math.sqrt

class TeamDistributor {
    class Result(val teams: List<List<Player>>, val standardDeviation: Double)

    fun distributeTeam(players: List<Player>, numberOfTeams: Int): Result {
        if (players.size % numberOfTeams != 0) {
            throw TeamDistributionError("The teams can't be balanced, there is no way of creating teams of equal sizes.")
        }
        val sortedPlayers = players.sortedByDescending { it.rate }
        val teams = MutableList(numberOfTeams) { mutableListOf<Player>() }
        for ((index, player) in sortedPlayers.withIndex()) {
            val teamIndex = index % numberOfTeams
            teams[teamIndex].add(player)
        }
        val teamAverages = calculateRateAverage(teams)
        val standardDeviation = calculateStandardDeviation(teamAverages)
        return Result(teams, standardDeviation)
    }

    private fun calculateRateAverage(teams: List<List<Player>>): List<Double> {
        return teams.map { team ->
            val sum = team.sumOf { it.rate }
            sum.toDouble() / team.size
        }
    }

    private fun calculateStandardDeviation(values: List<Double>): Double {
        val mean = values.average()
        val squaredDifferences = values.map { (it - mean) * (it - mean) }
        val variance = squaredDifferences.sum() / values.size
        return sqrt(variance)
    }


}
