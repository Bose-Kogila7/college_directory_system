package com.cts.cda.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.cts.cda.entity.FacultyProfile;
import com.cts.cda.models.FacultyModel;

public interface FacultyProfileRespository extends JpaRepository<FacultyProfile, Long>{
    @Query("SELECT new com.cts.cda.models.FacultyModel(f.id,f.user.id, f.photo, u.name, u.email, u.phone,d.id, d.name, f.officeHours) " +
            "FROM FacultyProfile f " +
            "JOIN f.user u " +
            "JOIN f.department d " +
            "WHERE f.user.id = :userId")
     FacultyModel findFacultyByUserId(@Param("userId") Long userId);
    @Query("SELECT new com.cts.cda.models.FacultyModel(f.id,f.user.id, f.photo, u.name, u.email, u.phone,d.id, d.name, f.officeHours) " +
            "FROM FacultyProfile f " +
            "JOIN f.user u " +
            "JOIN f.department d ")
     List<FacultyModel> findAllFacultyModel();
}
