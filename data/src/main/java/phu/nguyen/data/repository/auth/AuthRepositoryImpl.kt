package phu.nguyen.data.repository.auth

import com.example.mymallupgrade.domain.repository.auth.AuthRepository

class AuthRepositoryImpl(
    private val firebase: AuthDataSource
) : AuthRepository {
    override fun register(email: String, password: String) =  firebase.register(email,password)
    override fun login(email: String,password: String) = firebase.login(email,password)
    override fun sendEmailResetPassword(email: String) = firebase.sendEmailResetPassword(email)
}