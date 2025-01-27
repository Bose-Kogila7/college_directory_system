package com.cts.cda.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.cda.entity.Department;
import com.cts.cda.entity.FacultyProfile;
import com.cts.cda.entity.StudentProfile;
import com.cts.cda.entity.User;
import com.cts.cda.models.FacultyModel;
import com.cts.cda.models.StudentModel;
import com.cts.cda.repository.DepartmentRepository;
import com.cts.cda.repository.StudentProfileRepository;
import com.cts.cda.repository.UserRepository;
import com.cts.cda.service.StudentProfileService;

@Service
public class StudentServiceImpl implements StudentProfileService {

	private StudentProfileRepository studentProfileRepository;
	private UserRepository userRepository;
	private DepartmentRepository departmentRepository;
	private PasswordEncoder passwordEncoder;
	
	 private static final Logger logger = LoggerFactory.getLogger(StudentProfileService.class);

	public StudentServiceImpl(StudentProfileRepository studentProfileRepository, UserRepository userRepository,
			DepartmentRepository departmentRepository, PasswordEncoder passwordEncoder) {
		super();
		this.studentProfileRepository = studentProfileRepository;
		this.userRepository = userRepository;
		this.departmentRepository = departmentRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public List<StudentProfile> getAllStudentProfiles() {
		// TODO Auto-generated method stub
		return studentProfileRepository.findAll();
	}

	@Override
	public void saveStudentProfile(StudentModel studentModel) {
		System.out.println(studentModel.getEmail() + " " + studentModel.getName() + " " + studentModel.getPassword()
				+ " " + studentModel.getUserName() + " " + studentModel.getYear());
		User user = new User(studentModel.getId(), studentModel.getUserName(), studentModel.getRole(),
				studentModel.getName(), studentModel.getEmail(), studentModel.getPhone());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser = userRepository.save(user);
		System.out.println(savedUser.getId() + " " + savedUser.getEmail() + " " + savedUser.getPassword());
		Department department = departmentRepository.findById(studentModel.getDepartmentId())
				.orElseThrow(() -> new RuntimeException("Department not found"));
		System.out.println(savedUser.getId() + " " + savedUser + " " + studentModel.getPhoto() + " " + department + " "
				+ studentModel.getYear());
		StudentProfile sp = new StudentProfile();
		sp.setUser(savedUser);
		// sp.setUser(savedUser);
		sp.setDepartment(department);
		sp.setPassword(passwordEncoder.encode(user.getPassword()));
		sp.setPhoto(studentModel.getPhoto());
		sp.setYear(studentModel.getYear());
		studentProfileRepository.save(sp);
	}

	@Override
	public StudentProfile getStudentProfileById(Long id) {
		// TODO Auto-generated method stub
		return studentProfileRepository.findById(id).get();
	}

	@Override
	public StudentProfile updateStudentProfile(StudentProfile studentProfile) {
		// TODO Auto-generated method stub
		return studentProfileRepository.save(studentProfile);
	}

	@Override
	public StudentModel getStudentProfileByuserId(Long userId) {
		// TODO Auto-generated method stub
		return studentProfileRepository.findUserIdPhotoYearDepartmentByUserId(userId);
	}

	@Override
	public void deleteStudentProfileById(Long id) {
		// TODO Auto-generated method stub
		studentProfileRepository.deleteById(id);

	}

	@Override
	public List<StudentModel> getStudentByKey(String key) {
		// TODO Auto-generated method stub
		return studentProfileRepository.findStudentBYKey(key);
	}

	@Override
	public List<StudentModel> getAllStudentModel() {
		return studentProfileRepository.findAllStudentModel();
	}

	@Override
	public Optional<StudentProfile> findById(long id) {
		// TODO Auto-generated method stub
		return studentProfileRepository.findById(id);
	}

	@Override
	public StudentProfile updateStudentProfile(long id, StudentModel studentModel) {
		User user = userRepository.findById(id).get();
		user.setEmail(studentModel.getEmail());
		user.setPhone(studentModel.getPhone());
		user.setName(studentModel.getName());
		user.setPassword(passwordEncoder.encode(studentModel.getPassword()));
		user = userRepository.save(user);

		StudentModel sp = studentProfileRepository.findStudentByUserId(id);
		StudentProfile sac = studentProfileRepository.findById(sp.getId()).get();
		Department department = departmentRepository.findById(studentModel.getDepartmentId())
				.orElseThrow(() -> new RuntimeException("Department not found"));
		sac.setPhoto(studentModel.getPhoto());
		sac.setYear(studentModel.getYear());
		sac.setPassword(passwordEncoder.encode(studentModel.getPassword())); 
		sac.setUser(user);
		return studentProfileRepository.save(sac);
	}

	@Override
	public ResponseEntity<byte[]> getImage(Long id) {
		try {
            StudentProfile student = studentProfileRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));

            return ResponseEntity.ok()
                    .header("Content-Type", "image/jpeg") // Adjust the content type as needed
                    .body(student.getPhoto());
        } catch (Exception e) {
            logger.error("Error fetching image for student ID " + id, e);
            return ResponseEntity.status(500).build();
        }

	}

}
