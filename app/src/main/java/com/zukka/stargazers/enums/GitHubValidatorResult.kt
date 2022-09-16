package com.zukka.stargazers.enums

enum class GitHubValidatorResult(val value: String) {
    Success(""),
    UsernameInvalid("Invalid username."),
    RepositoryNameInvalid("Invalid repository name."),
    AllDataInvalid("Invalid username and repository name.")
}