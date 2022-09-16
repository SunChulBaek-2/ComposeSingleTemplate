package com.example.composetemplate.domain

import com.example.composetemplate.data.FakeRepository
import com.example.composetemplate.util.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPhotosParam

class GetPhotosResult

class GetPhotosUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val repository: FakeRepository
) : FlowUseCase<GetPhotosParam, GetPhotosResult>(dispatcher) {

    override fun execute(parameters: GetPhotosParam): Flow<Result<GetPhotosResult>> = flow {
        try {

        } catch (e: Exception) {

        }
    }
}