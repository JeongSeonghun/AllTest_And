package com.jsh.kr.alltest.util;

import com.jsh.kr.alltest.C;

import java.io.File;

public class FileUtil {
   public enum FileType{
      dir(1),
      file(2);

      private int code;
      FileType(int code){
         this.code = code;
      }
   }

   public static String getFileTypeStr(File file){
      if(file != null && file.exists()){
         if(file.isDirectory()){
            return C.FileStr.SimpleType.Directory;
         } else if(file.isFile()){
            return C.FileStr.SimpleType.File;
         } else {
            return C.FileStr.SimpleType.UnKnown;
         }
      }
      return "empty";
   }
}
