package com.example.student.babydiary.data;

import java.util.ArrayList;

/**
 * Created by Student on 2018/1/23.
 */

public interface sleepDAO {
    public boolean add(Sleep_Data s);
    public ArrayList<Sleep_Data> getList();
    public Sleep_Data getsleep_data(int id);
    public boolean update(Sleep_Data s);
    public boolean delete(int id);
}
