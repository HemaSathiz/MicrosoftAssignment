package com.persistent.microsoftassignment.api

import com.persistent.microsoftassignment.helper.ConstantHelper

class AppUtils{
    companion object {
        fun getSOService(): RestInterface {
            return RetrofitClient.getClient(ConstantHelper.URL)!!.create(RestInterface::class.java)
        }
    }
}