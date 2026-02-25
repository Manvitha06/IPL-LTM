package com.edutech.progressive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edutech.progressive.entity.Team;
@Repository
public interface TeamRepository extends JpaRepository <Team,Integer> {
}
