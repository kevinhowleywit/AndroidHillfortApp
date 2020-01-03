package org.wit.hillfortapp.activities.views.login

import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.toast
import org.wit.hillfortapp.activities.views.BasePresenter
import org.wit.hillfortapp.activities.views.BaseView
import org.wit.hillfortapp.activities.views.VIEW

class LoginPresenter(view: BaseView) : BasePresenter(view) {
    var auth: FirebaseAuth =FirebaseAuth.getInstance()


    fun doLogin(email: String, password: String) {
        view?.showProgress()
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(view!!){task ->
            if(task.isSuccessful){
                view?.navigateTo(VIEW.HILLFORTACTIVITY)
            }else{
                view?.toast("Sign in Failed: ${task.exception?.message}")
            }
        }
        view?.hideProgress()
    }

    fun doSignUp(email: String, password: String) {
        view?.showProgress()
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(view!!) { task ->
            if (task.isSuccessful) {
                view?.navigateTo(VIEW.HILLFORTACTIVITY)
            } else {
                view?.toast("Sign Up Failed: ${task.exception?.message}")
            }
            view?.hideProgress()
        }
    }
}