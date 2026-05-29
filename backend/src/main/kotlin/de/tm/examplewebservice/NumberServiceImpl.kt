package de.tm.examplewebservice

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random

@RestController
@CrossOrigin(origins = ["*"])
class NumberServiceImpl {

    private val random = Random(System.currentTimeMillis())

    @GetMapping("/random")
    fun getRandomNumber(): Int {
        return random.nextInt(100)
    }
}