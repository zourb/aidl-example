package com.afollestad.aidlexample;
import com.afollestad.aidlexample.MainObject;

interface IMainService {
    MainObject[] listFiles(String path);

    void exit();
}
