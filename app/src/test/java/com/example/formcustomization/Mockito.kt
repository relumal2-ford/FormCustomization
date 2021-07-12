/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 *  This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2021. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 *  Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 *  Copyright 2021, Ford Motor Company.
 */

package com.example.formcustomization

import org.mockito.Mockito

inline fun <reified T> mock(): T = Mockito.mock(T::class.java)

infix fun <T> T.returns(response: T) = Mockito.`when`(this).thenReturn(response)