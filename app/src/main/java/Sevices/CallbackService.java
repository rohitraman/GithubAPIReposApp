package Sevices;

import data.Current_User;

/**
 * Created by darthvader on 9/12/17.
 */

public interface CallbackService {
    void serviceSuccess(Current_User user);
    void serviceFailure(Exception e);
    void repoNotFound(Exception e);
}
