/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author harry
 */
public class User {
        private static String userName;
        private static String userPassword;

        public User(String userName, String userPassword) {
            this.userName = userName;
            this.userPassword = userPassword;
        }


        public static String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public static String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }


}