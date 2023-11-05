package service

import model.Player
import model.Result
import exception.TeamDistributionException
import kotlin.math.sqrt

class TeamDistributor {

    fun distributeTeam(players: List<Player>, numberOfTeams: Int): Result {
        if (players.size % numberOfTeams != 0) {
            throw TeamDistributionException("The teams can't be balanced, there is no way of creating teams of equal sizes.")
        }

        val sortedPlayers = players.sortedByDescending { it.rate }
        val teamBins = MutableList(numberOfTeams) { mutableListOf<Player>() }
        val teamRates = MutableList(numberOfTeams) { 0.0 }

        for (player in sortedPlayers) {
            val bestTeamIndex = findBestTeam(teamRates, teamBins, sortedPlayers.size, numberOfTeams)
            teamBins[bestTeamIndex].add(player)
            teamRates[bestTeamIndex] = teamRates[bestTeamIndex] + player.rate
        }

        val teamAverages = calculateRateAverage(teamBins)
        val standardDeviation = calculateStandardDeviation(teamAverages)
        return Result(teamBins, standardDeviation)
    }

    private fun findBestTeam(teamRates: List<Double>, teamBins: List<List<Player>>, players: Int, numberOfTeams: Int): Int {
        val teamRatesCopy = teamRates.toMutableList()
        if (teamBins[teamRatesCopy.indexOf(teamRates.minOrNull())].size >= players/ numberOfTeams) {
            teamRatesCopy[teamRatesCopy.indexOf(teamRates.minOrNull())] = Double.MAX_VALUE
            return findBestTeam(teamRatesCopy, teamBins, players, numberOfTeams)
        }
        return teamRates.indexOf(teamRates.minOrNull())
    }

    private fun calculateRateAverage(teamBins: List<List<Player>>): List<Double> {
        return teamBins.map { team ->
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
