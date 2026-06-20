package de.tm.examplewebservice.service.rest

import de.tm.examplewebservice.service.RandomApiService
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import kotlin.random.Random

@Component
class NumberServiceImpl: RandomApiService {

    private val random = Random(System.currentTimeMillis())

    override fun getRandomNumber(max: Long?): Long {
        return random.nextLong(max ?: 100L)
    }
}