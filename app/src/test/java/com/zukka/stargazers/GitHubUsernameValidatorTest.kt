package com.zukka.stargazers

import com.zukka.stargazers.utils.GitHubDataValidator
import org.junit.Test
import org.junit.Assert.*


class GitHubUsernameValidatorTest {

    @Test
    fun usernameIsEmptyTest() {
        val gitHubUsername = ""
        val expected = false
        val gitHubDataValidator = GitHubDataValidator()
        val actual = gitHubDataValidator.isValidUserName(gitHubUsername)
        assertTrue(actual == expected)
    }

    @Test
    fun usernameIsNotEmptyTest() {
        val gitHubUsername = "UserTest"
        val expected = true
        val gitHubDataValidator = GitHubDataValidator()
        val actual = gitHubDataValidator.isValidUserName(gitHubUsername)
        assertTrue(actual == expected)
    }

    @Test
    fun usernameIsTooLongTest() {
        val gitHubUsername = "ThisUserNameIsTooLongBecauseIsMoreOfThirdNineChars"
        val expected = false
        val gitHubDataValidator = GitHubDataValidator()
        val actual = gitHubDataValidator.isValidUserName(gitHubUsername)
        assertTrue(actual == expected)
    }

    @Test
    fun usernameIsValidLengthTest() {
        val gitHubUsername = "ThisUserNameIsValid"
        val expected = true
        val gitHubDataValidator = GitHubDataValidator()
        val actual = gitHubDataValidator.isValidUserName(gitHubUsername)
        assertTrue(actual == expected)
    }

    @Test
    fun usernameEndWithHyphenTest() {
        val gitHubUsername = "userTest-"
        val expected = false
        val gitHubDataValidator = GitHubDataValidator()
        val actual = gitHubDataValidator.isValidUserName(gitHubUsername)
        assertTrue(actual == expected)
    }

    @Test
    fun usernameStartWithHyphenTest() {
        val gitHubUsername = "-userTest"
        val expected = false
        val gitHubDataValidator = GitHubDataValidator()
        val actual = gitHubDataValidator.isValidUserName(gitHubUsername)
        assertTrue(actual == expected)
    }

    @Test
    fun usernameStartEndEndWithHyphenTest() {
        val gitHubUsername = "-userTest-"
        val expected = false
        val gitHubDataValidator = GitHubDataValidator()
        val actual = gitHubDataValidator.isValidUserName(gitHubUsername)
        assertTrue(actual == expected)
    }

    @Test
    fun usernameContainsTwoConsecutiveHyphenTest() {
        val gitHubUsername = "user--Test"
        val expected = false
        val gitHubDataValidator = GitHubDataValidator()
        val actual = gitHubDataValidator.isValidUserName(gitHubUsername)
        assertTrue(actual == expected)
    }

    @Test
    fun usernameContainsTwoNotConsecutiveHyphenTest() {
        val gitHubUsername = "user-T-est"
        val expected = true
        val gitHubDataValidator = GitHubDataValidator()
        val actual = gitHubDataValidator.isValidUserName(gitHubUsername)
        assertTrue(actual == expected)
    }
}