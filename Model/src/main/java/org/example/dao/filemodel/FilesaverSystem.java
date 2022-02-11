package org.example.dao.filemodel;

import org.example.AppConfiguration.Config;
import org.example.Exceptions.data.ConfigException;
import org.example.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FilesaverSystem<T> implements Dao<T> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String fileName;

    public FilesaverSystem(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public T read() throws IOException, ClassNotFoundException {
        Config config;
        try (FileInputStream inputFileStream = new FileInputStream(fileName);
             ObjectInputStream in = new ObjectInputStream(inputFileStream)) {
            config = (Config)in.readObject();
        } catch (FileNotFoundException exception) {
            logger.error("Loading config error");
            throw new ConfigException();
        }

        return (T) config;
    }

    @Override
    public void write(T obj) throws IOException {
        logger.debug("Save options");
        try (FileOutputStream outputFileStream = new FileOutputStream(fileName);
             ObjectOutputStream out = new ObjectOutputStream(outputFileStream)) {
            out.writeObject(obj);
        } catch (IOException exception) {
            logger.error("Save config error");
            throw new ConfigException();
        }
    }

    @Override
    public void close() throws Exception {

    }
}
