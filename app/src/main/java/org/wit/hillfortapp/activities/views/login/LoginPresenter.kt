package org.wit.hillfortapp.activities.views.login

import org.wit.hillfortapp.activities.views.BasePresenter
import org.wit.hillfortapp.activities.views.BaseView
import org.wit.hillfortapp.activities.views.VIEW

class LoginPresenter(view: BaseView) : BasePresenter(view) {

    fun doLogin(email: String, password: String) {
        view?.navigateTo(VIEW.HILLFORT)
    }

    fun doSignUp(email: String, password: String) {
        view?.navigateTo(VIEW.LIST)
    }
}