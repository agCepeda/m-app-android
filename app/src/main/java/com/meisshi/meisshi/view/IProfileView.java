package com.meisshi.meisshi.view;

import com.meisshi.meisshi.model.Review;
import com.meisshi.meisshi.model.User;

/**
 * Created by DevAg on 27/10/2017.
 */

public interface IProfileView extends IView {
    User getUser();
    void addReviews(Review[] items);
    void lockAddRemove();
    void unlockAddRemove();
    void setUser(User body);

    void setToolText(int resourceString);

    void showFollowers();
    void showFollowed();
    void showReviewForm();
}
