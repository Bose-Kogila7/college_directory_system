package com.cts.cda;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cts.cda.entity.Department;
import com.cts.cda.entity.FacultyProfile;
import com.cts.cda.entity.User;
import com.cts.cda.models.FacultyModel;
import com.cts.cda.repository.DepartmentRepository;
import com.cts.cda.repository.FacultyProfileRespository;
import com.cts.cda.repository.UserRepository;
import com.cts.cda.service.impl.FacultyProfileServiceImpl;



@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FacultyServiceTests {

    @InjectMocks
    private FacultyProfileServiceImpl facultyProfileService;

    @Mock
    private FacultyProfileRespository facultyProfileRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void testGetAllFacultyProfiles() {
        List<FacultyProfile> facultyProfiles = new ArrayList<>();
        facultyProfiles.add(new FacultyProfile());
        facultyProfiles.add(new FacultyProfile());

        when(facultyProfileRepository.findAll()).thenReturn(facultyProfiles);

        List<FacultyProfile> result = facultyProfileService.getAllFacultyProfiles();
        assertEquals(2, result.size());
        verify(facultyProfileRepository, times(1)).findAll();
    }

    @Test
    public void testSaveFacultyProfile() {
        FacultyModel facultyModel = new FacultyModel(1L,1L,"faculty1","faculty1@example.com","1234567890","department1",1L,"9am-5pm","photo1");
        
        User user = new User(1L, "faculty1", "faculty", "faculty1", "faculty1@example.com", "1234567890");
        Department department = new Department(1L, "department1");

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(department));

        facultyProfileService.saveFacultyProfile(facultyModel);

        verify(userRepository, times(1)).save(any(User.class));
        verify(departmentRepository, times(1)).findById(anyLong());
        verify(facultyProfileRepository, times(1)).save(any(FacultyProfile.class));
    }

    @Test
    public void testGetFacultyProfileById() {
        FacultyProfile facultyProfile = new FacultyProfile();
        facultyProfile.setUserId(1L);
        facultyProfile.setUser(new User(1L, "faculty1", "faculty", "faculty1", "faculty1@example.com", "1234567890"));
        facultyProfile.setDepartment(new Department(1L, "department1"));
        facultyProfile.setOfficeHours("9am-5pm");
        facultyProfile.setPhoto("photo1");

        when(facultyProfileRepository.findById(1L)).thenReturn(Optional.of(facultyProfile));

        FacultyProfile result = facultyProfileService.getFacultyProfileById(1L);
        assertEquals("faculty1", result.getUser().getName());
        verify(facultyProfileRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteFacultyProfileById() {
        doNothing().when(facultyProfileRepository).deleteById(1L);

        facultyProfileService.deleteFacultyProfileById(1L);

        verify(facultyProfileRepository, times(1)).deleteById(1L);
    }
}
