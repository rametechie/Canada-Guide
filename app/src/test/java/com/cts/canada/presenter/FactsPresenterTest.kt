package com.cts.canada.presenter



import android.content.Context
import android.view.Display
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Test


import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FactsPresenterTest {
    private lateinit var factsPresenter: FactsPresenter

    @Mock
    private  lateinit var mockcontext: Context

    @Before
    fun setUp() {


        factsPresenter   = FactsPresenter(mockcontext)

    }

    @After
    fun tearDown() {
    }

    @Test
    fun onResume() {
    }

    @Test
    fun onPause() {
    }

    @Test
    fun onStop() {
    }

    @Test
    fun getFactsData() {
    }

    @Test
    fun onGetPayIdsSuccess() {
    }

    @Test
    fun onGetPayIdsFailure() {
    }

    @Test
    fun getContext() {
    }

    @Test
    fun getDisplayContext()
    {


        val result =  factsPresenter.getDisplayContext(mockcontext)

//        assertEquals(result, `is`(mockcontext is FactsPresenter.Display))
         //assertEquals(result, mockcontext)
    }
}