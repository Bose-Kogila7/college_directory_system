package com.example.cda.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.cda.entity.FacultyProfile;
import com.example.cda.models.FacultyModel;


public interface FacultyProfileRespository extends JpaRepository<FacultyProfile, Long>{
    @Query("SELECT new com.example.cda.models.FacultyModel(f.id,f.user.id, f.photo, u.name, u.email, u.phone,d.id, d.name, f.officeHours) " +
            "FROM FacultyProfile f " +
            "JOIN f.user u " +
            "JOIN f.department d " +
            "WHERE f.user.id = :userId")
     FacultyModel findFacultyByUserId(@Param("userId") Long userId);
    @Query("SELECT new com.example.cda.models.FacultyModel(f.id,f.user.id, f.photo, u.name, u.email, u.phone,d.id, d.name, f.officeHours) " +
            "FROM FacultyProfile f " +
            "JOIN f.user u " +
            "JOIN f.department d ")
     List<FacultyModel> findAllFacultyModel();
}
