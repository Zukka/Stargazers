package com.zukka.stargazers.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zukka.stargazers.enums.GitHubValidatorResult
import com.zukka.stargazers.network.GitHubApi
import com.zukka.stargazers.network.GitHubApiService
import com.zukka.stargazers.network.StargazerProperty
import com.zukka.stargazers.utils.ConnectionChecker
import com.zukka.stargazers.utils.GitHubDataValidator
import kotlinx.coroutines.launch
import java.lang.Exception

class FirstFragmentViewModel: ViewModel() {

    private var _connectionError = MutableLiveData<Boolean>()
    val connectionChecker: LiveData<Boolean>
        get() = _connectionError

    private var _stargazers = MutableLiveData<List<StargazerProperty>?>()
    val stargazers: LiveData<List<StargazerProperty>?>
        get() = _stargazers

    fun checkConnectivity(context: Context): Boolean {
        val connectionChecker = ConnectionChecker()
        val isConnected = connectionChecker.checkForInternet(context)
        _connectionError.postValue(isConnected)
        return isConnected
    }

    fun resetLiveData() {
        _connectionError.value = true
        _stargazers.value = null
    }

    fun validateData(userName: String, repositoryName: String): GitHubValidatorResult {
        val gitHubDataValidator = GitHubDataValidator()
        val isValidUsername = gitHubDataValidator.isValidUserName(userName)
        val isValidRepositoryName = gitHubDataValidator.isValidRepositoryName(repositoryName)
        return when {
            !isValidUsername && !isValidRepositoryName -> GitHubValidatorResult.AllDataInvalid
            !isValidUsername -> GitHubValidatorResult.UsernameInvalid
            !isValidRepositoryName -> GitHubValidatorResult.RepositoryNameInvalid
            else -> GitHubValidatorResult.Success
        }
    }

    fun getStargazers(userName: String, repositoryName: String) {
        viewModelScope.launch {
            try {
                val retrievedStargazers : List<StargazerProperty> = GitHubApi.retrofitService.getStargazers(userName, repositoryName)
                when {
                    retrievedStargazers.isEmpty() -> _stargazers.postValue(ArrayList())
                    else -> _stargazers.postValue(retrievedStargazers)
                }
            } catch (ex: Exception) {
                _stargazers.postValue(ArrayList())
            }
        }
    }
}