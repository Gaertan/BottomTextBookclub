package com.example.bottomtextbookclub.data.model.negocio.dominio;

import android.accounts.Account;
import android.accounts.AccountManager;

import java.io.Serializable;

public class UsuariosAccount implements Serializable {

    public static final String ACCOUNT_TYPE = "com.example.bottomtextbookclub.data.model";
   // public static final String ACCOUNT_NAME = "BottomTextBookClub";

    public static Account crearCuenta(String nombreUsuario, String password) {
        return new Account(nombreUsuario, ACCOUNT_TYPE);
    }

    public static void guardarCuentaCredenciales(AccountManager accountManager, String nombreUsuario, String password) {
        Account account = crearCuenta(nombreUsuario, password);
        accountManager.addAccountExplicitly(account, password, null);
    }

    public static boolean validarCredenciales(AccountManager accountManager, String nombreUsuario, String password) {
        Account[] accounts = accountManager.getAccountsByType(ACCOUNT_TYPE);
        for (Account account : accounts) {
            if (account.name.equals(nombreUsuario)) {
                String passwordGuardada = accountManager.getPassword(account);
                return passwordGuardada != null && passwordGuardada.equals(password);
            }
        }
        return false;
    }

    public static void setCategoriaFavorita(AccountManager accountManager, String nombreUsuario, String categoria) {
        Account[] accounts = accountManager.getAccountsByType(ACCOUNT_TYPE);
        for (Account account : accounts) {
            if (account.name.equals(nombreUsuario)) {
                accountManager.setUserData(account, "categoriaFavorita", categoria);
                return;
            }
        }
    }

    public static String getCategoriaFavorita(AccountManager accountManager, String nombreUsuario) {
        //por implementar uso
        Account[] accounts = accountManager.getAccountsByType(ACCOUNT_TYPE);
        for (Account account : accounts) {
            if (account.name.equals(nombreUsuario)) {
                return accountManager.getUserData(account, "categoriaFavorita");
            }
        }
        return null;
    }


    public static void tabulaRasa(AccountManager accountManager){
        Account[] accounts = accountManager.getAccountsByType(ACCOUNT_TYPE);
        for (Account account : accounts) {
          //  accountManager.removeAccount(account, null, null);
            accountManager.removeAccountExplicitly(account);
        }

    }

    }