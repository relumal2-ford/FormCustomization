/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */

package com.example.formcustomization

import androidx.annotation.CallSuper
import io.mockk.MockK
import io.mockk.MockKDsl
import io.mockk.clearMocks
import org.junit.After
import java.util.*
import kotlin.reflect.KClass

abstract class KotlinBaseTest {

    val mockList = ArrayList<Any>()

    @After
    @CallSuper
    fun resetMocks() {
        mockList.forEach {
            clearMocks(it)
        }
    }

    inline fun <reified T : Any> mockk(
            name: String? = null,
            relaxed: Boolean = true,
            vararg moreInterfaces: KClass<*>,
            relaxUnitFun: Boolean = false,
            instance: Boolean = false,
            block: T.() -> Unit = {}
    ): T {
        val mock = MockK.useImpl {
            MockKDsl.internalMockk(
                    name,
                    relaxed,
                    *moreInterfaces,
                    relaxUnitFun = relaxUnitFun,
                    block = block
            )
        }
        if (!instance) mockList.add(mock)
        return mock
    }
}