package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.edutech.progressive.dao.CricketerDAO;
import com.edutech.progressive.entity.Cricketer;
import com.edutech.progressive.service.CricketerService;

public class CricketerServiceImplJdbc implements CricketerService {
    CricketerDAO cricketerDAO;
    
    public CricketerServiceImplJdbc(CricketerDAO cricketerDAO) {
        this.cricketerDAO = cricketerDAO;
    }

    @Override
    public List<Cricketer> getAllCricketers() throws SQLException {
        return cricketerDAO.getAllCricketers();
    }

    @Override
    public Integer addCricketer(Cricketer cricketer) throws SQLException {
        return cricketerDAO.addCricketer(cricketer);
    }

    @Override
    public List<Cricketer> getAllCricketersSortedByExperience() throws SQLException {
        List<Cricketer> sorted= cricketerDAO.getAllCricketers();
        Collections.sort(sorted);
        return sorted;

    }
    @Override
    public void updateCricketer(Cricketer cricketer) throws SQLException {
        cricketerDAO.updateCricketer(cricketer);
    }
    @Override
    public void deleteCricketer(int cricketerId) throws SQLException {
        cricketerDAO.deleteCricketer(cricketerId);
    }
    @Override
    public Cricketer getCricketerById(int cricketerId) throws SQLException {
        return cricketerDAO.getCricketerById(cricketerId);
    }
}