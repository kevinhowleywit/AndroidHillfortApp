package org.wit.hillfortapp.activities.views.login

import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.toast
import org.wit.hillfortapp.activities.views.BasePresenter
import org.wit.hillfortapp.activities.views.BaseView
import org.wit.hillfortapp.activities.views.VIEW
import org.wit.hillfortapp.models.firebase.HillfortFireStore

class LoginPresenter(view: BaseView) : BasePresenter(view) {
    var auth: FirebaseAuth =FirebaseAuth.getInstance()
    var fireStore: HillfortFireStore? = null

    init {
        if (app.hillforts is HillfortFireStore) {
            fireStore = app.hillforts as HillfortFireStore
        }
    }

    fun doLogin(email: String, password: String) {
        view?.showProgress()
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(view!!) { task ->
            if (task.isSuccessful) {
                if (fireStore != null) {
                    fireStore!!.fetchHillforts {
                        view?.hideProgress()
                        view?.navigateTo(VIEW.HILLFORTACTIVITY)
                    }
                } else {
                    view?.hideProgress()
                    view?.navigateTo(VIEW.HILLFORTACTIVITY)
                }
            } else {
                view?.hideProgress()
                view?.toast("Bad Sign In:  ${task.exception?.message}")
            }
        }
    }


    fun doSignUp(email: String, password: String) {
        view?.showProgress()
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(view!!) { task ->
            if (task.isSuccessful) {
                view?.navigateTo(VIEW.HILLFORTACTIVITY)
            } else {
                view?.toast("Register error: ${task.exception?.message}")
            }
            view?.hideProgress()
        }
    }
}