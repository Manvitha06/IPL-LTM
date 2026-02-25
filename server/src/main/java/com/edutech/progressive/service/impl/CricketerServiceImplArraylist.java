package com.edutech.progressive.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.edutech.progressive.entity.Cricketer;
import com.edutech.progressive.service.CricketerService;

public class CricketerServiceImplArraylist implements CricketerService {
List<Cricketer>cricket=new ArrayList<>();
    @Override
    public List<Cricketer> getAllCricketers() {
      return cricket;
    }

    @Override
    public Integer addCricketer(Cricketer cricketer) {
       cricket.add(cricketer);
       return cricket.size();
    }

    @Override
    public List<Cricketer> getAllCricketersSortedByExperience() {
     Collections.sort(cricket);
     return cricket;
    }

}