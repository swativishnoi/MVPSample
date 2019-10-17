package com.codebase.base

import com.codebase.api.NetModule
import com.codebase.dagger.PresenterModule
import com.codebase.dagger.UtilsModule
import com.codebase.modules.mainModule.MainActivity
import com.codebase.modules.mainModule.homeScreen.BlogFragment
import com.codebase.modules.mainModule.homeScreen.HomeActivity
import com.codebase.modules.mainModule.homeScreen.HomePresenter
import com.codebase.modules.userAccountModule.forgotPasswordScreen.ForgotPasswordActivity
import com.codebase.modules.userAccountModule.forgotPasswordScreen.ForgotPasswordPresenter
import com.codebase.modules.userAccountModule.loginScreen.LoginActivity
import com.codebase.modules.userAccountModule.loginScreen.LoginPresenter
import com.codebase.modules.userAccountModule.mangeAccount.ManageAccountActivity
import com.codebase.modules.userAccountModule.mangeAccount.ManageAccountPresenter
import com.codebase.modules.userAccountModule.signUpScreen.SignUpActivity
import com.codebase.modules.userAccountModule.signUpScreen.SignUpPresenter
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(modules = [UtilsModule::class, NetModule::class, PresenterModule::class])

interface AppComponent : AndroidInjector<JoinTrainApplication> {

    fun inject(mainActivity: MainActivity)

    fun inject(mainActivity: BaseActivity)
    fun inject(baseFragment: BaseFragment)

    fun inject(signUpActivity: SignUpActivity)
    fun inject(signUpPresenter: SignUpPresenter)

    fun inject(manageAccountActivity: ManageAccountActivity)
    fun inject(manageAccountPresenter: ManageAccountPresenter)

    fun inject(loginActivity: LoginActivity)
    fun inject (loginPresenter: LoginPresenter)

    fun inject(forgotPasswordActivity: ForgotPasswordActivity)
    fun inject(forgotPasswordPresenter: ForgotPasswordPresenter)

    fun inject(homeActivity: HomeActivity)
    fun inject(homePresenter: HomePresenter)
    fun inject(blogFragment: BlogFragment)


}

