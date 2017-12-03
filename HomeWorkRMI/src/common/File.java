/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author user
 */
public class File {
        private static String fileName;
        private static String fileOwner;
        private static String filePrivacy;

        public File(String fileName, String fileOwner, String filePrivacy){
          this.fileName =fileName;
          this.fileOwner = fileOwner;
          this.filePrivacy = filePrivacy;
        }
        public static String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }
         public static String getFileOwner() {
            return fileOwner;
        }

        public void setFileOwner(String fileOwner) {
            this.fileOwner = fileOwner;
        }

        public static String getFilePrivacy() {
            return filePrivacy;
        }

        public void setFilePrivacy(String filePrivacy) {
            this.filePrivacy = filePrivacy;
        }
}
