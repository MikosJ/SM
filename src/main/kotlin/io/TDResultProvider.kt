package io

import model.Result

class TDResultProvider {
    fun resultToString(result: Result): String {
        val output = StringBuilder()
        result.teams.withIndex().forEach {
            output.append(
                "Team number ${it.index + 1} has ${it.value.size} players ${
                    it.value.map { str -> str.name }.toString().replace('[', '(').replace(']', ')')
                }. Average rate: ${it.value.sumOf { player -> player.rate }.toDouble() / it.value.size}\n"
            )
        }
        output.append("Teams rate standard deviation: ${String.format("%.2f", result.standardDeviation)}")
        return output.toString()
    }
}