package com.app.myrickshawparent.base

import androidx.lifecycle.ViewModel
import com.app.myapistructure.network.utility.CheckValidation
import javax.inject.Inject

/*import com.app.myrickshawparent.network.utility.CheckValidation
import com.app.myrickshawparent.prefrence.MyDataStore
import javax.inject.Inject*/

open class BaseViewModel : ViewModel() {
    /*
        @Inject
        lateinit var checkValidation: CheckValidation

        @Inject
        lateinit var dataStore: MyDataStore*/
    @Inject
    lateinit var checkValidation: CheckValidation


}