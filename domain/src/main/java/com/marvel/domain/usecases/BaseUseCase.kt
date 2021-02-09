package com.marvel.domain.usecases

import arrow.core.Either
import com.marvel.domain.entities.Failure

abstract class BaseUseCase<out Type, in Params> where Type : Any? {
    abstract fun run(params: Params? = null): Either<Failure, Type>

    @JvmOverloads
    operator fun invoke(
        params: Params? = null
    ): Either<Failure, Type> {
        return run(params)
    }
}