package com.dyga.Engine.Source.Utils.JsonLoaders;

public abstract class JsonLoader<T> {

    public abstract T parse(String path);

}
