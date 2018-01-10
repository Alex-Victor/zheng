package com.cache.config;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class RedisByteSerializer {
    private static Logger logger = LoggerFactory.getLogger(RedisByteSerializer.class);

    public static  byte[] toByteArray(Object object){
        ByteArrayOutputStream bout = null;
        ObjectOutputStream out = null;
        try{
            bout = new ByteArrayOutputStream(1024);
            out = new ObjectOutputStream(bout);
            out.writeObject(object);
            out.flush();
            return bout.toByteArray();
        }catch (IOException e){
            logger.error(e.getMessage(), e);
            return null;
        }
        finally {
            IOUtils.closeQuietly(bout);
            IOUtils.closeQuietly(bout);
        }
    }

    public static  Object toObject(byte[] bytes){
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try{
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            return ois.readObject();
        }catch (IOException | ClassNotFoundException e){
            logger.error(e.getMessage(), e);
            return null;
        }finally {
            IOUtils.closeQuietly(bis);
            IOUtils.closeQuietly(ois);
        }
    }
}
