package org.themeselves.alberandroid.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import dagger.android.support.DaggerFragment
import org.themeselves.alberandroid.databinding.LoginFragmentBinding
import org.themeselves.alberandroid.util.EventObserver
import javax.inject.Inject

class LoginFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<LoginViewModel> { viewModelFactory }

    private lateinit var viewDataBinding: LoginFragmentBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var callbackManager: CallbackManager


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LoginFragmentBinding.inflate(inflater, container, false).apply {
            viewDataBinding = this
            viewmodel = viewModel
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupLogin()
        setupLoginEventListener()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun setupLogin() {
        auth = FirebaseAuth.getInstance()
        callbackManager = CallbackManager.Factory.create()


    }

    private fun setupLoginEventListener() {
        viewModel.loginType.observe(viewLifecycleOwner, EventObserver { loginType ->
            when (loginType) {
                LoginType.FACEBOOK -> {
                    LoginManager.getInstance().apply {
                        logInWithReadPermissions(
                            this@LoginFragment,
                            mutableListOf("public_profile", "email")
                        )
                        registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                            override fun onSuccess(result: LoginResult) {
                                handleFacebookAccessToken(result.accessToken)
                            }

                            override fun onCancel() {

                            }

                            override fun onError(error: FacebookException?) {
                            }
                        })
                    }
                }
                LoginType.EMAIL ->{

                }
                else -> {
                }
            }

        })
    }


    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d("TAG", "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this.requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithCredential:success")
                    val user = auth.currentUser
                    Log.d("TAG", "signInWithCredential:success"+user.toString())
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithCredential:failure", task.exception)
                }
            }
    }
}