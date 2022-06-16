package org.innov.sam.domain.web.rest.errors;

public class UserNameAlreadyUsedException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public UserNameAlreadyUsedException() {
        super(ErrorConstants.EMAIL_ALREADY_USED_TYPE, "Email is already in use!",
                "userManagement", "emailexists");
    }


}
