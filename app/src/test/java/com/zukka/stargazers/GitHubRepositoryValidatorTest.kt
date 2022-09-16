package com.zukka.stargazers

import com.zukka.stargazers.utils.GitHubDataValidator
import org.junit.Assert
import org.junit.Test

class GitHubRepositoryValidatorTest {

    @Test
    fun repositoryNameIsEmptyTest() {
        val gitHubRepositoryName = ""
        val expected = false
        val gitHubDataValidator = GitHubDataValidator()
        val actual = gitHubDataValidator.isValidRepositoryName(gitHubRepositoryName)
        Assert.assertTrue(actual == expected)
    }

    @Test
    fun repositoryNameValidTest() {
        val gitHubRepositoryName = "repository-valid.test_valid"
        val expected = true
        val gitHubDataValidator = GitHubDataValidator()
        val actual = gitHubDataValidator.isValidRepositoryName(gitHubRepositoryName)
        Assert.assertTrue(actual == expected)
    }

    @Test
    fun repositoryNameInvalidSpecialCharTest() {
        val gitHubRepositoryName = "repository&invalid"
        val expected = false
        val gitHubDataValidator = GitHubDataValidator()
        val actual = gitHubDataValidator.isValidRepositoryName(gitHubRepositoryName)
        Assert.assertTrue(actual == expected)
    }

    @Test
    fun repositoryNameInvalidSpaceTest() {
        val gitHubRepositoryName = "repository invalid"
        val expected = false
        val gitHubDataValidator = GitHubDataValidator()
        val actual = gitHubDataValidator.isValidRepositoryName(gitHubRepositoryName)
        Assert.assertTrue(actual == expected)
    }
}