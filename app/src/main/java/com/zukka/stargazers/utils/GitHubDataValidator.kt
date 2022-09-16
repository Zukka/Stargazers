package com.zukka.stargazers.utils

import java.util.regex.Matcher
import java.util.regex.Pattern

class GitHubDataValidator {

    private lateinit var _username: String
    private lateinit var _gitHubRepositoryName: String
    private val usernameMaxLength = 39
    private val regexForRepositoryName = "[^a-z0-9-_.]"

    fun isValidUserName(username: String): Boolean {
        _username = username
        if (isUsernameEmpty() ||
            isUsernameInvalidLength() ||
            isUsernameEndWithHyphenChar() ||
            isUsernameStartWithHyphenChar() ||
            isUsernameContainsToConsecutiveHyphenChar())
            return false

        return true
    }

    private fun isUsernameEmpty() = _username.isEmpty()
    private fun isUsernameInvalidLength() = _username.length > usernameMaxLength
    private fun isUsernameEndWithHyphenChar() = _username.endsWith('-')
    private fun isUsernameStartWithHyphenChar() = _username.startsWith('-')
    private fun isUsernameContainsToConsecutiveHyphenChar() = _username.contains("--")

    fun isValidRepositoryName(gitHubRepositoryName: String): Boolean {
        _gitHubRepositoryName = gitHubRepositoryName
        if(isRepositoryNameEmpty() ||
            isRepositoryNameContainsSpecialChars())
            return false

        return true
    }

    private fun isRepositoryNameEmpty() = _gitHubRepositoryName.isEmpty()

    private fun isRepositoryNameContainsSpecialChars(): Boolean {
        val validChars: Pattern = Pattern.compile(regexForRepositoryName, Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = validChars.matcher(_gitHubRepositoryName)
        return matcher.find()
    }
}