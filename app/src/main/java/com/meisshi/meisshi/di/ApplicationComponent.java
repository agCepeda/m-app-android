package com.meisshi.meisshi.di;

import com.meisshi.meisshi.presenter.BasePresenter;
import com.meisshi.meisshi.ui.activity.BaseActivity;
import com.meisshi.meisshi.ui.fragment.BaseFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                NetworkModule.class
        }
)
public interface ApplicationComponent {
    void inject(BaseActivity activity);
    void inject(BaseFragment fragment);
    void inject(BasePresenter presenter);
}
