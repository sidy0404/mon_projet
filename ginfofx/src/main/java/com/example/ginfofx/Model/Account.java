package com.example.ginfofx.Model;

import java.io.Serializable;
import java.util.UUID;

    public class Account implements Serializable {
        private String  id = UUID.randomUUID().toString();
        private String prenom;
        private String nom;
        private String mail;
        private String telephone;
        private String login;
        private String password;
        private Role role;

        public Account(String id, String prenom, String nom, String mail, String telephone, String login, String password, Role role) {
            this.id = (id != null) ? id : UUID.randomUUID().toString();
            this.prenom = prenom;
            this.nom = nom;
            this.mail = mail;
            this.telephone = telephone;
            this.login = login;
            this.password = password;
            this.role = role;
        }

        public Account(String prenom, String nom, String mail, String telephone, String login, String password, Role role) {
            this.prenom = prenom;
            this.nom = nom;
            this.mail = mail;
            this.telephone = telephone;
            this.login = login;
            this.password = password;
            this.role = role;
        }

        public String getId() {
            return id;
        }

        public String getPrenom() {
            return prenom;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;
        }

        @Override
        public String toString() {
            return "Account{" +
                    "prenom='" + prenom + '\'' +
                    ", nom='" + nom + '\'' +
                    ", mail='" + mail + '\'' +
                    ", telephone='" + telephone + '\'' +
                    ", login='" + login + '\'' +
                    ", password='" + password + '\'' +
                    ", role=" + role +
                    '}';
        }
    }
