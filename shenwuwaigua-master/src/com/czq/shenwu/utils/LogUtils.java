package com.czq.shenwu.utils;


import java.io.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogUtils {
    public static Logger logger = Logger.getLogger("logger name");
    static {
        logger.setLevel(Level.INFO);
    }



    private static boolean s_show_code_position = false;
    private static boolean s_write_file = true;
    private static int offset;
//    private static String m_log_path = "C:/iport_log.txt";
    private static String m_log_path = "C:\\Users\\hebang\\IdeaProjects\\shenwuwaigua-master\\C_\\iport_log.txt";
    private static int s_log_size = 500;
    private static String global_tag = "iport/";

    public LogUtils() {
    }

    public static void setShowPosition(boolean showPosition) {
        s_show_code_position = showPosition;
    }

    private static void println(int level, String tag, String message) {

        if (s_write_file) {
            write_log(tag, message);
        }

        if (s_show_code_position) {
            message = message + getCodePosition();
        }

        printAndroidLog(level, global_tag + tag, message);


    }

    private static void printAndroidLog(int level, String tag, String message) {
        logger.info(tag + ":" +message);
    }


    public static void write_file(boolean write_file) {
        s_write_file = write_file;
    }

    public static void setLog_size(int log_size) {
        s_log_size = log_size;
    }

    public static void setLog_path(String m_log_path) {
        m_log_path = m_log_path;
    }



    public static void d(String tag, String msg) {
        println(3, tag, msg);
    }



    private static String getStackTraceString(Throwable tr) {
        if (tr == null) {
            return "";
        } else {
            for(Throwable t = tr; t != null; t = t.getCause()) {
                ;
            }

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            tr.printStackTrace(pw);
            pw.flush();
            return sw.toString();
        }
    }

    private static String getCodePosition() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int index = 5 + offset;
        offset = 0;
        String className = stackTrace[index].getFileName();
        String methodName = stackTrace[index].getMethodName();
        int lineNumber = stackTrace[index].getLineNumber();
        return String.format(".(%s:%s) %s()", className, lineNumber, methodName);
    }

    private static boolean check_log_file() {
        File log_file = new File(m_log_path);
        if (!log_file.exists()) {
            if (!log_file.getParentFile().exists()) {
                boolean mkdirs = log_file.getParentFile().mkdirs();
                if (!mkdirs) {
                    logger.info(getStackTraceString(new IOException("Can't create the directory of trace. Please check the trace path.")));
                    return false;
                }
            }

            try {
                log_file.createNewFile();
            } catch (IOException var2) {
                logger.info( getStackTraceString(var2));
                return false;
            }
        } else if (log_file.length() > (long)(1024 * s_log_size)) {
            log_file.renameTo(new File(m_log_path + "(1)"));
        }

        return true;
    }

    private static void write_log(String tag, String msg) {
        File log_file = new File(m_log_path);
        if (check_log_file()) {
            String text = getFormatLog(tag, msg);
            FileOutputStream fos = null;

            try {
                boolean append = log_file.length() <= (long)(1024 * s_log_size);
                fos = new FileOutputStream(log_file, append);
                fos.write(text.getBytes());
                fos.write("\n".getBytes());
            } catch (Exception var14) {
                var14.printStackTrace();
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (Exception var13) {
                    var13.printStackTrace();
                }

            }

        }
    }

    private static String getFormatLog(String tag, String msg) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String dateStr = sdf.format(new Date());
        return dateStr + " " + String.format("%s ", convertThreadId((int)Thread.currentThread().getId())) + String.format("%s: ", tag) + msg;
    }

    private static String convertThreadId(int value) {
        int limit = 5;
        String src = String.valueOf(value);
        int i = limit - src.length();
        if (i < 0) {
            src = src.substring(-i, src.length());
        }

        while(i > 0) {
            src = "0" + src;
            --i;
        }

        return src;
    }
}
