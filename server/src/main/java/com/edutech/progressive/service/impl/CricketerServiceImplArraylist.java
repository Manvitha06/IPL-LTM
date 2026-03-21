package com.edutech.progressive.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.edutech.progressive.entity.Cricketer;
import com.edutech.progressive.service.CricketerService;

public class CricketerServiceImplArraylist implements CricketerService  {
    List<Cricketer> cricketers= new ArrayList<>();

    @Override
    public List<Cricketer> getAllCricketers() {
       return cricketers;
    }

    @Override
    public Integer addCricketer(Cricketer cricketer) {
        if(cricketer!=null){
            cricketers.add(cricketer);
            return cricketers.size();
        }
        return -1;
    }

    @Override
    public List<Cricketer> getAllCricketersSortedByExperience() {
        List<Cricketer> sorted= new ArrayList<>(cricketers);
        Collections.sort(sorted);
        return sorted;
    }
    @Override
    public void emptyArrayList() {
        cricketers.clear();
    }
}