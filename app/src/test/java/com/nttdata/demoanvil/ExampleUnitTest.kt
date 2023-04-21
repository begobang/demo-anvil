package com.nttdata.demoanvil

import com.nttdata.demoanvil.di.AppComponent
import dagger.Component
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(AnvilTestRunner::class)
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test fun `component merges modules and interfaces`() {
        val annotation = AppComponent::class.java.getAnnotation(Component::class.java)!!
        assertEquals(annotation.modules.withoutAnvilModule()
        )
    }
}