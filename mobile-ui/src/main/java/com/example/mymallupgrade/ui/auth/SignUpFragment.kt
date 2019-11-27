package com.example.mymallupgrade.ui.auth

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mymallupgrade.R
import com.example.mymallupgrade.di.SignUpViewModelFactory
import com.example.mymallupgrade.presentation.auth.MVIBase.MviView
import com.example.mymallupgrade.presentation.auth.auth.UserSignUpIntent
import com.example.mymallupgrade.presentation.auth.auth.UserSignUpViewModes
import com.example.mymallupgrade.presentation.auth.auth.UserSignUpViewState
import com.example.mymallupgrade.utils.startHomeActivity
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_sign_up.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import timber.log.Timber

class SignUpFragment : Fragment(), MviView<UserSignUpIntent, UserSignUpViewState>,
    KodeinAware {
    override fun intents(): Observable<UserSignUpIntent> {
        Timber.d("intents")
        return Observable.merge(
            emailIntent(),
            passwordIntent()
        )
            .mergeWith(signUpIntent())
    }

    override fun render(state: UserSignUpViewState) {
        if (state.isLoading) {
            Timber.d("isLoading")
            prg_sign_up.visibility = View.VISIBLE
        }

        if (state.isSuccess) {
            Timber.d("isSuccess")
            prg_sign_up.visibility = View.GONE
            //context?.startHomeActivity()
        }

        if (state.isError != null) {
            Timber.d("isError")
            prg_sign_up.visibility = View.GONE
            Snackbar.make(
                activity!!.findViewById(android.R.id.content),
                "onFailure ${state.isError!!.message}",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    override val kodein by kodein()

    private val emailPublisher = PublishSubject.create<UserSignUpIntent.EmailIntent>()
    private val passwordPublisher = PublishSubject.create<UserSignUpIntent.PasswordIntent>()
    private val signUpPublisher = PublishSubject.create<UserSignUpIntent.SignUpIntent>()

    private val disposables = CompositeDisposable()
    private fun emailIntent(): Observable<UserSignUpIntent.EmailIntent> = emailPublisher
    private fun passwordIntent(): Observable<UserSignUpIntent.PasswordIntent> = passwordPublisher
    private fun signUpIntent(): Observable<UserSignUpIntent.SignUpIntent> = signUpPublisher

    private val viewModel: UserSignUpViewModes by lazy {
        val factory: SignUpViewModelFactory by instance()
        ViewModelProvider(this, factory).get(UserSignUpViewModes::class.java)
    }

    private var listener: OnSignUpFragmentInteractionListener? = null

    override fun onAttach(context: Context) {
        Timber.d("onAttach")
        super.onAttach(context)
        if (context is OnSignUpFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnSignInFragmentInteractionListener")
        }
    }


    override fun onStart() {
        super.onStart()
        bind()
    }

    private fun bind() {
        Timber.d("bind")
        disposables.add(viewModel.states().subscribe(this::render))
        viewModel.processIntents(intents())
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_sign_up, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        edt_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                emailPublisher.onNext(UserSignUpIntent.EmailIntent(p0.toString()))
            }
        })

        edt_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                passwordPublisher.onNext(UserSignUpIntent.PasswordIntent(p0.toString()))
            }
        })

        btn_sign_up.setOnClickListener {
            signUpPublisher.onNext(
                UserSignUpIntent.SignUpIntent(
                    edt_email.text.toString(),
                    edt_password.text.toString()
                )
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnSignUpFragmentInteractionListener {
        fun onJumpToSignInFragment()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment SignUpFragment.
         */
        @JvmStatic
        fun newInstance() = SignUpFragment()
    }

}
