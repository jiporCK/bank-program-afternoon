package util;

import controller.AccountController;
import model.repository.AccountRepository;
import model.service.AccountService;
import view.AccountView;

public class Singleton {

    private Singleton() {}

    private static AccountRepository repository = null;
    private static AccountService service = null;
    private static AccountView view = null;
    private static AccountController controller = null;

    public static synchronized AccountRepository getRepoInstance() {
        if (repository == null) {
            repository = new AccountRepository();
        }
        return repository;
    }

    public static synchronized AccountService getServiceInstance() {
        if (service == null) {
            service = new AccountService( getRepoInstance() );
        }
        return service;
    }

    public static synchronized AccountView getViewInstance() {
        if (view == null) {
            view = new AccountView();
        }
        return view;
    }

    public static synchronized AccountController getControllerInstance() {
        if (controller == null) {
            controller = new AccountController(getViewInstance(), getServiceInstance());
        }
        return controller;
    }

}
